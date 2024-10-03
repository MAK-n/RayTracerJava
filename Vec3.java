import java.lang.Math;

class Vec3 {
    public double[] e;

    public Vec3() {
        e = new double[] { 0.0, 0.0, 0.0 };
    }

    public Vec3(double e0) {
        e = new double[] { e0, 0.0, 0.0 };
    }

    public Vec3(double e0, double e1) {
        e = new double[] { e0, e1, 0.0 };
    }

    public Vec3(double e0, double e1, double e2) {
        e = new double[] { e0, e1, e2 };
    }

    public double x() {
        return e[0];
    }

    public double y() {
        return e[1];
    }

    public double z() {
        return e[2];
    }

    public Vec3 negate() {
        return this.multiply(-1);
    }

    public double get(int i) {
        return e[i];
    }

    public void set(int i, double value) {
        e[i] = value;
    }

    public void set(Vec3 v) {
        this.e[0] = v.e[0];
        this.e[1] = v.e[1];
        this.e[2] = v.e[2];
    }


    public Vec3 add(Vec3 v) {
        return new Vec3(e[0] + v.e[0], e[1] + v.e[1], e[2] + v.e[2]);
    }

    public Vec3 sub(Vec3 v) {
        return new Vec3(e[0] - v.e[0], e[1] - v.e[1], e[2] - v.e[2]);
    }

    public Vec3 multiply(double t) {
        return new Vec3(e[0] * t, e[1] * t, e[2] * t);
    }

    public Vec3 multiply(Vec3 v) {
        return new Vec3(e[0] * v.e[0], e[1] * v.e[1], e[2] * v.e[2]);
    }

    public Vec3 divide(double t) {
        return multiply(1 / t);
    }

    public double length() {
        return Math.sqrt(e[0] * e[0] + e[1] * e[1] + e[2] * e[2]);
    }

    public double lengthSquared() {
        return e[0] * e[0] + e[1] * e[1] + e[2] * e[2];
    }

    public static double dot(Vec3 u, Vec3 v) {
        return u.e[0] * v.e[0] + u.e[1] * v.e[1] + u.e[2] * v.e[2];
    }

    public static Vec3 cross(Vec3 u, Vec3 v) {
        return new Vec3(u.e[1] * v.e[2] - u.e[2] * v.e[1],
                u.e[2] * v.e[0] - u.e[0] * v.e[2],
                u.e[0] * v.e[1] - u.e[1] * v.e[0]);
    }

    public static Vec3 unitVector(Vec3 v) {
        return v.divide(v.length());
    }

    public String toString() {
        return "(" + e[0] + "," + e[1] + "," + e[2] + ")";
    }

    public static Vec3 random() {
        return new Vec3(Utils.randomDouble(), Utils.randomDouble(), Utils.randomDouble());
    }

    public static Vec3 random(double min, double max) {
        return new Vec3(Utils.randomDouble(min, max), Utils.randomDouble(min, max), Utils.randomDouble(min, max));
    }

    static Vec3 randomUnitVector() {
        while (true) {
            Vec3 p = Vec3.random(-1, 1);
            double lensq = p.lengthSquared();
            if (1e-160 < lensq && lensq <= 1)
                return p.divide((Math.sqrt(lensq)));
        }
    }

    static Vec3 randomOnHemisphere(Vec3 normal) {
        Vec3 onUnitSphere = randomUnitVector();
        if (dot(onUnitSphere, normal) > 0.0) // In the same hemisphere as the normal
            return onUnitSphere;
        else
            return onUnitSphere.negate();
    }

    boolean nearZero() {
        double s = 1e-8;
        return (Math.abs(e[0]) < s) && (Math.abs(e[1]) < s) && (Math.abs(e[2]) < s);
    }

    static Vec3 refract(Vec3 uv, Vec3 n, double etaiOverEtat) {

        double cosTheta = Math.min(Vec3.dot(uv.negate(), n), 1.0);
        Vec3 rOutPerpendicular = (uv.add(n.multiply(cosTheta))).multiply(etaiOverEtat);
        Vec3 rOutParallel = n.multiply(-Math.sqrt(Math.abs(1.0 - rOutPerpendicular.lengthSquared())));

        return rOutPerpendicular.add(rOutParallel);
    }

    static Vec3 reflect(Vec3 v, Vec3 n) {
        return v.sub(n.multiply(2 * Vec3.dot(v, n)));
    }

    static Vec3 randomInUnitDisk() {
        while (true) {
            Vec3 p= new Vec3(Utils.randomDouble(-1, 1), Utils.randomDouble(-1, 1), 0);
            if(p.lengthSquared() < 1){
                return p;
            }
        }
    }
}
