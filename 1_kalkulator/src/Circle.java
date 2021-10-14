import java.lang.Math;

public class Circle implements Figure{

    private double r;

    Circle(double R){
        this.r = R;
    }

    public Circle() {
        ;
    }

    @Override
    public double calculateArea() {
        return Math.PI*r*r;
    }

    @Override
    public double calculatePerimeter() {
        return 2*Math.PI*r;
    }

    @Override
    public void print() {
        System.out.println("CIRCLE INFO: ");
        System.out.println("Circle, radius = : "+r);
        System.out.println("Perimeter: ' "+calculatePerimeter()+", area: "+calculateArea());
    }

    public void setR(double R){
        this.r = R;
    }
}
