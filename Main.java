

class Main {


    public static void main(String[] args) {

        HitableList world = new HitableList();
        world.add(new Sphere(new Vec3(0.0,0.0,-1.0), 0.5));
        //world.add(new Sphere(new Vec3(0.3,0.0,-1.0), 0.3));
        world.add(new Sphere(new Vec3(0.0,-100.5,-1.0), 100));


        Camera cam= new Camera();
        cam.aspectRatio = 16.0/9.0;
        cam.imageWidth = 400;

        cam.render(world);

    }
}