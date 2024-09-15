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

    public void render(HitableList world) {
        initialize();

        FileWriter myWriter = null;
        try {
            myWriter = new FileWriter("Image.ppm");
            myWriter.write(String.format("P3\n%d %d\n255\n", imageWidth, imageHeight));

            for (int j = 0; j < imageHeight; j++) {
                for (int i = 0; i < imageWidth; i++) {

                    Vec3 pixelCenter= pixel100Loc.add(deltaU.multiply(i)).add(deltaV.multiply(j));
                    Vec3 rayDirection= pixelCenter.sub(camCentre);

                    Ray ray= new Ray(camCentre, rayDirection);

                    Vec3 clr= rayColour(ray, world);


                    clr= clr.multiply(255.999);

                    myWriter.write(String.format("%d %d %d\n",
                    (int) clr.x(),
                    (int) clr.y(),
                    (int) clr.z()));
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
         imageHeight = (imageHeight < 1) ? 1 : imageHeight;
 
         //Camera
         double focalLength = 1.0;
         double viewportHeight = 2.0;
         double viewportWidth = viewportHeight * aspectRatio;
         camCentre= new Vec3(0,0,0);
 
         // World
 
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

    private Vec3 rayColour(Ray r, HitableList world) {
        HitRecord rec= new HitRecord();
        if(world.hit(r, new Interval(0.0, Double.MAX_VALUE), rec)) {
            return (rec.normal.add(new Vec3(1,1,1))).multiply(0.5);
        }
        
        Vec3 unit_dir = r.direction();
        double t = -0.5 * (unit_dir.y() + 1.0);
        return (new Vec3(1,1,1).multiply(t)).add(new Vec3(0.5,0.7,1).multiply(1-t));

    }
}
