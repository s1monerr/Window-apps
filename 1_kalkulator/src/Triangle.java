import java.lang.Math;

public class Triangle implements Figure{

    private double a, b, c;

    public Triangle(double A, double B, double C){
        this.a = A;
        this.b = B;
        this.c = C;
    }

    public Triangle() {
        ;
    }

    @Override
    public double calculateArea(){
        double p = calculatePerimeter()/2; // half of the perimeter
        return Math.sqrt(p*(p-a)*(p-b)*(p-c)); // heron formula
    }

    @Override
    public double calculatePerimeter(){
        return a + b + c;
    }

    @Override
    public void print(){
        System.out.println("TRIANGLE INFO: ");
        System.out.println("Triangle, a = : "+a+", b = "+b+", c = "+c);
        System.out.println("Perimeter: ' "+calculatePerimeter()+", area: "+calculateArea());
    }

    public void setA(double A){
        this.a = A;
    }

    public void setB(double B){
        this.b = B;
    }

    public void setC(double C){
        this.c = C;
    }

}
