import java.util.ArrayList;

public class HitableList extends Hitable {

    ArrayList<Hitable> objects = new ArrayList<>();
    public HitableList(ArrayList<Hitable> list) {
        this.objects = new ArrayList<Hitable>(list);
    }
    HitableList() { objects = new ArrayList<Hitable>(); }

    void clear() { objects.clear(); }

    void add(Hitable object) {
        objects.add(object);
    }



    public boolean hit(Ray r, double t_min, double t_max, HitRecord rec) {
        HitRecord temp_rec= new HitRecord();
        boolean hit_anything = false;
        double closest_so_far = t_max;


        for(int i=0;i<objects.size();i++) {
            if ((objects.get(i)).hit(r, t_min, closest_so_far, temp_rec)) {
                hit_anything = true;
                closest_so_far = temp_rec.t;
                
                rec.p = temp_rec.p;
                rec.normal = temp_rec.normal;
                rec.t = temp_rec.t;
            }
        }

        return hit_anything;
    }
}
    

