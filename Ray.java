

public class Ray {
    private final Vec3 origin;
    private final Vec3 direction;

    public Ray(){
        this.origin= new Vec3(0,0,0);
        this.direction= new Vec3(0,0,0);
    }
    public Ray(Vec3 origin, Vec3 direction){
        this.origin = origin;
        this.direction = direction;
    }
    void setOrigin(Vec3 newOrigin) {
        for (int i = 0; i < 3; i++) {
          this.origin.e[i] = newOrigin.e[i];
        }
    }
    void setDirection(Vec3 newDirection) {
        for (int i = 0; i < 3; i++) {
          this.direction.e[i] = newDirection.e[i];
        }
    }

    public Vec3 origin(){
        return origin;
    }
    public Vec3 direction(){
        return direction;
    }

    public Vec3 at(double t){
        return origin.add(direction.multiply(t));
    }
    
}
