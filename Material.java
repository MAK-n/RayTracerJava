public abstract class Material {

    Material() {}

    public boolean scatter(Ray rIn, HitRecord rec, Vec3 attenuation, Ray scattered){
        return false;
    }
}
