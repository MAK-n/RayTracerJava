import java.util.ArrayList;

public class HitableList extends Hitable {

    ArrayList<Sphere> objects = new ArrayList<>();
    public HitableList(ArrayList<Sphere> list) {
        this.objects = new ArrayList<Sphere>(list);
    }
    HitableList() { objects = new ArrayList<Sphere>(); }

    void clear() { objects.clear(); }

    void add(Sphere object) {
        objects.add(object);
    }



    public boolean hit(Ray r, double t_min, double t_max, HitRecord rec) {
        HitRecord temp_rec= new HitRecord();
        boolean hit_anything = false;
        double closest_so_far = t_max;


        for(int i=0;i<objects.size();i++) {
            if ((objects.get(i)).hit(r, t_min, closest_so_far, temp_rec)==true) {
                hit_anything = true;
                closest_so_far = temp_rec.t;
                rec = temp_rec;
            }
        }

        return hit_anything;
    }
}
    

