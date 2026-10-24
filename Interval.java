public class Interval {
    public double min;
    public double max;
    Interval(double min, double max) {
        this.min = min;
        this.max = max;
    }
    Interval(){
        this.min= Double.POSITIVE_INFINITY;
        this.max= Double.NEGATIVE_INFINITY;
    }

    double size(){
        return this.max - this.min;
    }

    boolean contains(double x){
        return this.min <= x && x <= this.max;
    }

    boolean surrounds(double x){
        return this.min < x && x < this.max;
    }

    double clamp(double x){
        if(x < this.min) return this.min;
        if(x > this.max) return this.max;
        return x;
    }


    static final Interval EMPTY = new Interval(Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY);
    static final Interval UNIVERSE = new Interval(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
}
