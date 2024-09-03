// Vector Utility Functions
class Vec3Utils {
    public static Vec3 add(Vec3 u, Vec3 v) {
        return u.add(v);
    }

    public static Vec3 subtract(Vec3 u, Vec3 v) {
        return new Vec3(u.e[0] - v.e[0], u.e[1] - v.e[1], u.e[2] - v.e[2]);
    }

    public static Vec3 multiply(Vec3 u, Vec3 v) {
        return new Vec3(u.e[0] * v.e[0], u.e[1] * v.e[1], u.e[2] * v.e[2]);
    }

    public static Vec3 multiply(double t, Vec3 v) {
        return v.multiply(t);
    }

    public static Vec3 multiply(Vec3 v, double t) {
        return multiply(t, v);
    }

    public static Vec3 divide(Vec3 v, double t) {
        return v.divide(t);
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
}