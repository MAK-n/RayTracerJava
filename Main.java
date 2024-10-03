
class Main {

    public static void main(String[] args) {


        // Material materialGround = new Lambertian(new Vec3(0.8, 0.8, 0.0));
        // Material materialCenter = new Lambertian(new Vec3(0.1, 0.2, 0.5));
        // Material materialLeft = new Dielectric(1.5);
        // Material materialRight = new Metal(new Vec3(0.8, 0.6, 0.2), 1.0);

        // HitableList world = new HitableList();
        // world.add(new Sphere(new Vec3(0.0, 0.0, -1.2), 0.5, materialCenter));
        // world.add(new Sphere(new Vec3(0.0, -100.5, -1.0), 100, materialGround));
        // world.add(new Sphere(new Vec3(-1.0, 0.0, -1.0), 0.5, materialLeft));
        // world.add(new Sphere(new Vec3(1.0, 0.0, -1.0), 0.5, materialRight));

        // Camera cam = new Camera();
        // cam.aspectRatio = 16.0 / 9.0;
        // cam.imageWidth = 400;
        // cam.samplesPerPixel = 100.0;
        // cam.maxDepth = 50;
        // cam.vfov = 30.0;

        // cam.lookFrom = new Vec3(0, 1, 2); // Position above the scene
        // cam.lookAt = new Vec3(0, 0, -1);  // Looking towards the center
        // cam.vUp = new Vec3(0, 1, 0);      // Up direction

        // cam.render(world);


        HitableList world = new HitableList();

        // Create the ground material
        Material groundMaterial = new Lambertian(new Vec3(0.5, 0.5, 0.5));
        world.add(new Sphere(new Vec3(0, -1000, 0), 1000, groundMaterial));

        // Populate the scene with random spheres
        for (int a = -11; a < 11; a++) {
            for (int b = -11; b < 11; b++) {
                double chooseMat = Utils.randomDouble();
                Vec3 center = new Vec3(a + 0.9 * Utils.randomDouble(), 0.2, b + 0.9 * Utils.randomDouble());

                if (center.sub(new Vec3(4, 0.2, 0)).length() > 0.9) {
                    Material sphereMaterial;

                    if (chooseMat < 0.8) {
                        // Diffuse material
                        Vec3 albedo = Vec3.random().multiply(Vec3.random());
                        sphereMaterial = new Lambertian(albedo);
                        world.add(new Sphere(center, 0.2, sphereMaterial));
                    } else if (chooseMat < 0.95) {
                        // Metal material
                        Vec3 albedo = Vec3.random(0.5, 1);
                        double fuzz = Utils.randomDouble(0, 0.5);
                        sphereMaterial = new Metal(albedo, fuzz);
                        world.add(new Sphere(center, 0.2, sphereMaterial));
                    } else {
                        // Glass material
                        sphereMaterial = new Dielectric(1.5);
                        world.add(new Sphere(center, 0.2, sphereMaterial));
                    }
                }
            }
        }

        // Adding specific spheres with different materials
        Material material1 = new Dielectric(1.5);
        world.add(new Sphere(new Vec3(0, 1, 0), 1.0, material1));

        Material material2 = new Lambertian(new Vec3(0.4, 0.2, 0.1));
        world.add(new Sphere(new Vec3(-4, 1, 0), 1.0, material2));

        Material material3 = new Metal(new Vec3(0.7, 0.6, 0.5), 0.0);
        world.add(new Sphere(new Vec3(4, 1, 0), 1.0, material3));

        // Initialize the camera
        Camera cam = new Camera();

        cam.aspectRatio = 16.0 / 9.0;
        cam.imageWidth = 400;
        cam.samplesPerPixel = 500;
        cam.maxDepth = 50;

        cam.vfov = 20;
        cam.lookFrom = new Vec3(13, 2, 3);
        cam.lookAt = new Vec3(0, 0, 0);
        cam.vUp = new Vec3(0, 1, 0);

        cam.defocusAngle = 0.6;
        cam.focusDistance = 10.0;

        // Render the scene
        cam.render(world);

    }


}   