

class Main {


    public static void main(String[] args) {


        Material materiaGround = new Lambertian(new Vec3(0.8,0.8,0.0));
        Material materiaCenter = new Lambertian(new Vec3(0.1,0.2,0.5));
        Material materiaLeft = new Metal(new Vec3(0.8,0.8,0.8));
        Material materiaRight = new Metal(new Vec3(0.8,0.6,0.2));

        HitableList world = new HitableList();

        world.add(new Sphere(new Vec3(0.0,0.0,-1.2), 0.5, materiaCenter));
        world.add(new Sphere(new Vec3(0.0,-100.5,-1.2), 100, materiaGround));
        world.add(new Sphere(new Vec3(1.0,0.0,-1.0), 0.5, materiaLeft));
        world.add(new Sphere(new Vec3(-1.0,0.0,-1.0), 0.5, materiaRight));


        Camera cam= new Camera();
        cam.aspectRatio = 16.0/9.0;
        cam.imageWidth = 1080;
        cam.samplesPerPixel = 500.0;

        cam.render(world);

    }
}