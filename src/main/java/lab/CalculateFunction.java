package lab;

public class CalculateFunction {

    public static double funAgent1(double x){
        double result = Math.exp(0.2*x);
        return result;
    }
    public static double funAgent2(double x){
        double result = Math.pow(2, -x);
        return result;
    }
    public static double funAgent3(double x){
        double result = Math.cos(x);
        return result;
    }

}