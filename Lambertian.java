public class Lambertian extends Material {
    private Vec3 albedo;
    
    Lambertian(Vec3 albedo) {
        this.albedo= albedo;
    }

    @Override
    public boolean scatter(Ray rIn, HitRecord rec, Vec3 attenuation, Ray scattered) {
        Vec3 scatterDirection= rec.normal.add(Vec3.randomUnitVector());

        if(scatterDirection.nearZero()) {
            scatterDirection= rec.normal;
        }
        Ray scat= new Ray(rec.p, scatterDirection);

        attenuation.set(albedo);

        scattered.setOrigin(scat.origin());
        scattered.setDirection(scat.direction());
        return true;
    }
}
