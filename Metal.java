public class Metal extends Material {
    Vec3 albedo;
    double fuzz;
    Metal(Vec3 albedo, double fuzz){
        this.albedo = albedo;
        this.fuzz = fuzz< 1 ? fuzz : 1;
    }

    @Override
    public boolean scatter(Ray rIn, HitRecord rec, Vec3 attenuation, Ray scattered) {
        Vec3 reflected = Vec3.reflect(rIn.direction(), rec.normal);
        reflected = Vec3.unitVector(reflected).add(Vec3.randomUnitVector().multiply(fuzz));
        Ray scat = new Ray(rec.p, reflected);
        
        attenuation.e[0]= albedo.e[0];
        attenuation.e[1]= albedo.e[1];
        attenuation.e[2]= albedo.e[2];

        scattered.origin().e[0]= scat.origin().e[0];
        scattered.origin().e[1]= scat.origin().e[1];
        scattered.origin().e[2]= scat.origin().e[2];
        scattered.direction().e[0]= scat.direction().e[0];
        scattered.direction().e[1]= scat.direction().e[1];
        scattered.direction().e[2]= scat.direction().e[2];
        return Vec3.dot(scattered.direction(),rec.normal) > 0;
    }
    
}
