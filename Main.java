
class Main {

    public static void main(String[] args) {

        Material materialGround = new Lambertian(new Vec3(0.8, 0.8, 0.0));
        Material materialCenter = new Lambertian(new Vec3(0.1, 0.2, 0.5));
        // Material materialRight = new Metal(new Vec3(0.8,0.8,0.8),0.3);
        Material materialLeft = new Dielectric(1.0/1.33);
        Material materialRight = new Metal(new Vec3(0.8, 0.6, 0.2), 1.0);

        HitableList world = new HitableList();

        world.add(new Sphere(new Vec3(0.0, 0.0, -1.2), 0.5, materialCenter));
        world.add(new Sphere(new Vec3(0.0, -100.5, -1.0), 100, materialGround));
        world.add(new Sphere(new Vec3(-1.0, 0.0, -1.0), 0.5, materialLeft));
        world.add(new Sphere(new Vec3(1.0, 0.0, -1.0), 0.5, materialRight));

        // CAMERA SHIII
        // double R= Math.cos(Math.PI/4.0);

        // Material materialLeft= new Lambertian(new Vec3(0,0,1));
        // Material materialRight= new Lambertian(new Vec3(1,0,0));

        // world.add(new Sphere(new Vec3(-R,0,-1), R, materialLeft));
        // world.add(new Sphere(new Vec3(R,0,-1), R, materialRight));

        Camera cam = new Camera();
        cam.aspectRatio = 16.0 / 9.0;
        cam.imageWidth = 400;
        cam.samplesPerPixel = 100.0;
        cam.maxDepth = 50;
        cam.vfov = 90.0;

        cam.render(world);

    }
}