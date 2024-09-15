public class Sphere extends Hitable {
    private Vec3 center;
    private double radius;
    public Sphere(Vec3 center, double radius) {
        this.center = center;
        this.radius = Math.max(0, radius);
    }

    @Override
    public boolean hit(Ray r, double t_min, double t_max, HitRecord rec){
        Vec3 oc= center.sub(r.origin());
        double a= r.direction().length_squared();
        //double b= -2.0*Vec3.dot(oc, r.direction());
        double h= Vec3.dot(oc, r.direction());
        double c= oc.length_squared() - radius*radius;
        double discriminant=h*h-a*c;
        if (discriminant < 0) {
            return false;
        }
        double sqrtd = Math.sqrt(discriminant);

        double root = (h - sqrtd) / a;
        if (root <= t_min || t_max <= root) {
            root = (h + sqrtd) / a;
            if (root <= t_min || t_max <= root)
                return false;
        }


        rec.t = root;
        rec.p = r.at(rec.t);

        rec.normal = (rec.p.sub( center)).divide( radius);
        //rec.set_face_normal(r, outward_normal);


        return true;

    
    }
}
