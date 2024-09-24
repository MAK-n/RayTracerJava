

class Main {


    public static void main(String[] args) {


        Material materialGround = new Lambertian(new Vec3(0.8,0.8,0.0));
        Material materialCenter = new Lambertian(new Vec3(0.1,0.2,0.5));
        // Material materialRight = new Metal(new Vec3(0.8,0.8,0.8),0.3);
        Material materialLeft = new Dielectric(1.0/1.33);
        Material materialRight = new Metal(new Vec3(0.8,0.6,0.2),1.0);

        HitableList world = new HitableList();

        world.add(new Sphere(new Vec3(0.0,0.0,-1.2), 0.5, materialCenter));
        world.add(new Sphere(new Vec3(0.0,-100.5,-1.0), 100, materialGround));
        world.add(new Sphere(new Vec3(-1.0,0.0,-1.0), 0.5, materialLeft));
        world.add(new Sphere(new Vec3(1.0,0.0,-1.0), 0.5, materialRight));


        Camera cam= new Camera();
        cam.aspectRatio = 16.0/9.0;
        cam.imageWidth = 400;
        cam.samplesPerPixel = 100.0;

        cam.render(world);

    }
}