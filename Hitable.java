public abstract class Hitable {
    public abstract boolean hit(Ray r, Interval ray_t, HitRecord rec);
}
