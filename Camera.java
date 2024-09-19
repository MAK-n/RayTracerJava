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
                    int rByte = (int)Math.min(255, Math.max(0, intensity.clamp(r) * 255.999));
                    int gByte = (int)Math.min(255, Math.max(0, intensity.clamp(g) * 255.999));
                    int bByte = (int)Math.min(255, Math.max(0, intensity.clamp(b) * 255.999));


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
        imageWidth = 400;
        int imageHeight = (int) (imageWidth/aspectRatio);
        this.imageHeight = (imageHeight < 1) ? 1 : imageHeight;
 
         //Camera
        double focalLength = 1.0;
        double viewportHeight = 2.0;
        double viewportWidth = viewportHeight * aspectRatio;
        camCentre= new Vec3(0,0,0);
 
         // World
        pixelSamplesScale= 1.0/samplesPerPixel;
         //ArrayList<Hitable> list = new ArrayList<Hitable>();
         
 
         HitableList world = new HitableList();
         world.add(new Sphere(new Vec3(0.0,0.0,-1.0), 0.5));
         //world.add(new Sphere(new Vec3(0.3,0.0,-1.0), 0.3));
         world.add(new Sphere(new Vec3(0.0,-100.5,-1.0), 100));
 
 
 
         //Vectors across horizontal and vertical viewport edges
         Vec3 viewportU= new Vec3(viewportWidth,0,0);
         Vec3 viewportV= new Vec3(0,-viewportHeight,0);
 
         //Horizontal and vertical delta vectors from pixel to pixel
         deltaU= viewportU.divide(imageWidth);
         deltaV= viewportV.divide(imageHeight);
 
         //Location of upper left pixel
         Vec3 viewportUpperLeft= camCentre.sub(new Vec3(0,0,focalLength)).sub(viewportV.divide(2)).sub(viewportU.divide(2));
         pixel100Loc= viewportUpperLeft.add(deltaU.add(deltaV).multiply(0.5));
 
    }

    Ray getRay(int i, int j) {
        // Construct Camera Ray from the origin and directed at the randomly sampled pixel
        // point around the location i,j

        Vec3 offset= sampleSquare();

        Vec3 pixelSample= pixel100Loc.add(deltaU.multiply(i+offset.x())).add(deltaV.multiply(j+offset.y()));
        Vec3 rayDirection= pixelSample.sub(camCentre);
        return new Ray(camCentre, rayDirection);

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
        if(world.hit(r, new Interval(0.0, Double.MAX_VALUE), rec)) {
            Vec3 direction= rec.normal.add(Vec3.randomOnHemisphere(rec.normal));
            return rayColour(new Ray(rec.p, direction), world,Depth-1).multiply(0.5);
            //return (rec.normal.add(new Vec3(1,1,1))).multiply(0.5);
        }
        
        Vec3 unit_dir = r.direction();
        double t = 0.5 * (-unit_dir.y() + 1.0);
        return (new Vec3(1,1,1).multiply(t)).add(new Vec3(0.5,0.7,1).multiply(1-t));

    }
}
