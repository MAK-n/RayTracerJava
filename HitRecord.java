public class HitRecord {
    public Vec3 p;
    public Vec3 normal;
    public double t;

    boolean front_face;

    public Vec3 set_face_normal(Ray r, Vec3 outward_normal) {
        // Sets the hit record normal vector.
        // NOTE: the parameter `outward_normal` is assumed to have unit length.

        front_face = Vec3.dot(r.direction(), outward_normal) < 0;
        return front_face ? outward_normal : outward_normal.negate();
    }

}
