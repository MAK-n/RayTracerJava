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



    public boolean hit(Ray r, Interval ray_t, HitRecord rec) {
        HitRecord temp_rec= new HitRecord();
        boolean hit_anything = false;
        double closest_so_far = ray_t.max;


        for(int i=0;i<objects.size();i++) {
            if ((objects.get(i)).hit(r,new Interval(ray_t.min, closest_so_far), temp_rec)) {
                hit_anything = true;
                closest_so_far = temp_rec.t;
                
                rec.p = temp_rec.p;
                rec.normal = temp_rec.normal;
                rec.t = temp_rec.t;
                rec.mat = temp_rec.mat;
            }
        }

        return hit_anything;
    }
}
    

