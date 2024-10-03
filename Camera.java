import java.io.FileWriter;
import java.io.IOException;

public class Camera {


    double aspectRatio;
    int imageWidth;
    int imageHeight;
    Vec3 camCentre;
    Vec3 pixel100Loc;
    Vec3 deltaU;
    Vec3 deltaV;
    double samplesPerPixel=10.0;
    double pixelSamplesScale;
    int maxDepth=50;
    double vfov=90;
    private Vec3 u,v,w;

    double defocusAngle=0;
    double focusDistance=10;

    Vec3 defocusDiskU;
    Vec3 defocusDiskV;

    Vec3 lookFrom= new Vec3(); //point camera looking from
    Vec3 lookAt= new Vec3(0,0,-1); //point camera looking at
    Vec3 vUp= new Vec3(0,1,0); // camera-relative up vector

    static double linearToGamma(double linear_component){
    if (linear_component > 0)
        return Math.sqrt(linear_component);

    return 0;
    }

    public void render(HitableList world) {
        initialize();

        FileWriter myWriter;
        try {
            myWriter = new FileWriter("Image.ppm");
            myWriter.write(String.format("P3\n%d %d\n255\n", imageWidth, imageHeight));
            final Interval intensity = new Interval(0.0, 0.999);

            for (int j = 0; j < imageHeight; j++) {

                for (int i = 0; i < imageWidth; i++) {


                    Vec3 pixelColour= new Vec3(0,0,0);

                    for (int s = 0; s < samplesPerPixel; s++) {
                        Ray r = getRay(i, j);
                        pixelColour= pixelColour.add(rayColour(r, world, maxDepth));
                    }
                    
                    pixelColour=pixelColour.multiply(pixelSamplesScale);
                    
                    
                    double r = linearToGamma(pixelColour.x());
                    double g = linearToGamma(pixelColour.y());
                    double b = linearToGamma(pixelColour.z());

                    
                    
                    // Clamp and format color values
                    int rByte = (int)(intensity.clamp(r) * 255.999);
                    int gByte = (int)(intensity.clamp(g) * 255.999);
                    int bByte = (int)(intensity.clamp(b) * 255.999);


                    myWriter.write(String.format("%d %d %d\n", rByte, gByte, bByte));



                }
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred. ");
            e.printStackTrace();
        }
    }



    private void initialize(){
         //image
        aspectRatio = 16.0/9.0;
        //imageWidth = 400;
        int imageHeight = (int) (imageWidth/aspectRatio);
        this.imageHeight = (imageHeight < 1) ? 1 : imageHeight;
 

        // double viewportHeight = 2.0;
        double theta= Utils.degreesToRadians(vfov);
        double h= Math.tan(theta/2.0);
        double viewportHeight= 2.0*h*focusDistance;
        double viewportWidth = viewportHeight * aspectRatio;
        camCentre= lookFrom;
 
         // World
        pixelSamplesScale= 1.0/samplesPerPixel;


        //Calculate u,v,w for the camera coordinate frame
        w= Vec3.unitVector(lookFrom.sub(lookAt));
        u= Vec3.unitVector(Vec3.cross(vUp, w));
        v= Vec3.cross(w, u);
 
         //Vectors across horizontal and vertical viewport edges
         Vec3 viewportU= u.multiply(viewportWidth);
         Vec3 viewportV= v.multiply(viewportHeight).negate();
 
         //Horizontal and vertical delta vectors from pixel to pixel
         deltaU= viewportU.divide(imageWidth);
         deltaV= viewportV.divide(imageHeight);
 
         //Location of upper left pixel
         Vec3 viewportUpperLeft= camCentre.sub(w.multiply(focusDistance)).sub(viewportV.divide(2)).sub(viewportU.divide(2));
         pixel100Loc= viewportUpperLeft.add(deltaU.add(deltaV).multiply(0.5));
        
        //Calculate the camera defocus disk basis vectors
        double defocusRadius= focusDistance*Math.tan(Utils.degreesToRadians(defocusAngle/2.0));
        defocusDiskU= u.multiply(defocusRadius);
        defocusDiskV= v.multiply(defocusRadius);
    }

    Ray getRay(int i, int j) {
        // Construct Camera Ray from the origin and directed at the randomly sampled pixel
        // point around the location i,j

        Vec3 offset= sampleSquare();
        Vec3 pixelSample= pixel100Loc.add(deltaU.multiply(i+offset.x())).add(deltaV.multiply(j+offset.y()));
        
        Vec3 rayOrigin= (defocusAngle<=0) ? camCentre : defocusDiskSample();
        Vec3 rayDirection= (pixelSample.sub(rayOrigin));
        
        return new Ray(rayOrigin, rayDirection);

    }

    Vec3 defocusDiskSample(){
        //return a random point in the camera defocus disk
        Vec3 p= Vec3.randomInUnitDisk();
        return this.camCentre.add(this.defocusDiskU.multiply(p.x())).add(this.defocusDiskV.multiply(p.y()));
    }

    Vec3 sampleSquare(){
        // Generate a random point in the sample square [-0.5,0.5] to [-0.5,0.5]
        return new Vec3(Utils.randomDouble()-0.5,Utils.randomDouble()-0.5, 0.0);
    }

    private Vec3 rayColour(Ray r, HitableList world, int Depth) {
        if(Depth<=0){
            return new Vec3(0,0,0);
        }
        HitRecord rec= new HitRecord();
        if(world.hit(r, new Interval(0.001, Double.MAX_VALUE), rec)) {
            Ray scattered= new Ray();
            Vec3 attenuation= new Vec3();
            if(rec.mat.scatter(r, rec, attenuation, scattered)) {
                return rayColour(scattered, world, Depth-1).multiply(attenuation);
            }
            else{
                return new Vec3(0,0,0);
            }
            
            // Vec3 direction= rec.normal.add(Vec3.randomOnHemisphere(rec.normal));
            // return rayColour(new Ray(rec.p, direction), world,Depth-1).multiply(0.5);
            //return (rec.normal.add(new Vec3(1,1,1))).multiply(0.5);
        }
        
        Vec3 unit_dir = Vec3.unitVector(r.direction());
        double t = 0.5 * (unit_dir.y() + 1.0);
        return (new Vec3(1,1,1).multiply(1.0-t)).add(new Vec3(0.5,0.7,1).multiply(t));

    }
}
