public class Dielectric extends Material {

    double refractiveIndex;

    Dielectric(double refractiveIndex) {
        this.refractiveIndex = refractiveIndex;
    }

    @Override
    public boolean scatter(Ray rIn, HitRecord rec, Vec3 attenuation, Ray scattered) {
        attenuation.set(new Vec3(0.98, 0.98, 1.0)); // Dielectric material is assumed to be transparent (no attenuation)

        double ri = rec.front_face ? (1.0 / refractiveIndex) : (refractiveIndex); // Refraction index
        Vec3 unitDirection = Vec3.unitVector(rIn.direction());

        // Calculate cosTheta and sinTheta
        double cosTheta = Math.min(Vec3.dot(unitDirection.negate(), rec.normal), 1.0);
        double sinTheta = Math.sqrt(1.0 - cosTheta * cosTheta);

        boolean cannotRefract = ri * sinTheta > 1.0; // Total internal reflection condition
        Vec3 direction = new Vec3();

        if (cannotRefract || schlick(cosTheta, ri) > Math.random()) {
            // Reflect
            direction.set(Vec3.reflect(unitDirection, rec.normal));
        } else {
            // Refract
            direction.set(Vec3.refract(unitDirection, rec.normal, ri));
        }
        // System.out.println("CosTheta: " + cosTheta);
        // System.out.println("Cannot Refract: " + cannotRefract);
        // System.out.println("Direction: " + direction);

        scattered.setOrigin(rec.p);
        scattered.setDirection(direction);

        return true;
    }


    private double schlick(double cosine, double refIndex) {
        double r0 = (1 - refIndex) / (1 + refIndex);
        r0 = r0 * r0;
        return r0 + (1 - r0) * Math.pow((1 - cosine), 5);
    }
}
