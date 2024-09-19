public class Utils {
    public static double randomDouble(){
        return Math.random();
    }

    public static double randomDouble(double min, double max){
        return min + (max - min) * randomDouble();
    }
}
