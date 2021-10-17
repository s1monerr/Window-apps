import java.lang.Math;

public class Square implements Figure{

    private double a;

    public Square(double A) {
        if (A <= 0) {
            System.out.println("Error: value must be positive. ");
            this.a = 0;
        }
        else {
            this.a = A;
        }
    }

    @Override
    public double calculateArea() {
        return Math.pow(a, 2);
    }

    @Override
    public double calculatePerimeter() {
        return a*4;
    }

    @Override
    public void print() {
        System.out.println("SQUARE INFO: ");
        System.out.println("Square, a = : "+a);
        System.out.println("Perimeter: "+calculatePerimeter()+", area: "+calculateArea());
    }

}
