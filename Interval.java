public class Interval {
    public double min;
    public double max;
    Interval(double min, double max) {
        this.min = min;
        this.max = max;
    }
    Interval(){
        this.min= Double.MIN_VALUE;
        this.max= Double.MAX_VALUE;
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


    static final Interval EMPTY = new Interval(Double.MIN_VALUE,Double.MAX_VALUE);
    static final Interval UNIVERSE = new Interval(Double.MIN_VALUE,Double.MAX_VALUE);
}
