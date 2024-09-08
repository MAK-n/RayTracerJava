public class HitRecord {
    public Vec3 p;
    public Vec3 normal;
    public double t;

    HitRecord(Vec3 p, Vec3 normal, double t) {
        this.p = p;
        this.normal = normal;
        this.t = t;
    }

    HitRecord(){
        this.p= new Vec3(0,0,0);
        this.normal= new Vec3(0,0,0);
        this.t=0;
    }

    boolean front_face;

    public void set_face_normal(Ray r, Vec3 outward_normal) {
        // Sets the hit record normal vector.
        // NOTE: the parameter `outward_normal` is assumed to have unit length.

        front_face = Vec3.dot(r.direction(), outward_normal) < 0;
        normal= front_face ? outward_normal : outward_normal.negate();
    }

}
