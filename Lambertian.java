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
        attenuation.e[0]= albedo.e[0];
        attenuation.e[1]= albedo.e[1];
        attenuation.e[2]= albedo.e[2];

        scattered.origin().e[0]= scat.origin().e[0];
        scattered.origin().e[1]= scat.origin().e[1];
        scattered.origin().e[2]= scat.origin().e[2];
        scattered.direction().e[0]= scat.direction().e[0];
        scattered.direction().e[1]= scat.direction().e[1];
        scattered.direction().e[2]= scat.direction().e[2];

        return true;
    }
}
