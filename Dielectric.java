public class Dielectric extends Material {

    double refractiveIndex;

    Dielectric(double refractiveIndex) {
        this.refractiveIndex = refractiveIndex;
    }

    @Override
    public boolean scatter(Ray rIn, HitRecord rec, Vec3 attenuation, Ray scattered) {

        attenuation.set(new Vec3(1.0, 1.0, 1.0));

        double ri = rec.front_face ? (1.0 / refractiveIndex) : (refractiveIndex);
        Vec3 unitDirection = Vec3.unitVector(rIn.direction());

        double cosTheta = Math.min(Vec3.dot(unitDirection.negate(), rec.normal), 1.0);
        double sinTheta = Math.sqrt(1.0 - cosTheta * cosTheta);

        boolean cannotRefract = ri * sinTheta > 1.0;
        Vec3 direction = new Vec3();

        if (cannotRefract) {
            direction.set(Vec3.reflect(unitDirection, rec.normal));
        } else {
            direction.set(Vec3.refract(unitDirection, rec.normal, ri));
        }

        scattered.setOrigin(rec.p);
        scattered.setDirection(direction);

        return true;
    }
}
