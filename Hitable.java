public abstract class Hitable {
    public abstract boolean hit(Ray r, double t_min, double t_max, HitRecord rec);
}
