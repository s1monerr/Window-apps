import java.lang.Math;

public class Triangle implements Figure{

    private double a, b, c;

    public Triangle(double A, double B, double C){
        if(A < 0 || B < 0 || C < 0){
            System.out.println("Error: value has to be positive.");
            this.a = 0;
            this.b = 0;
            this.c = 0;
            return;
        }

        // triangle condition
        else if(A+B < C || A+C < B || B+C < A){
            System.out.println("Error: wrong values.");
            this.a = 0;
            this.b = 0;
            this.c = 0;
        }
        this.a = A;
        this.b = B;
        this.c = C;
    }

    // constructor for prism - equilateral triangle
    public Triangle(double A){
        if(A < 0){
            System.out.println("Error: value has to be positive.");
            this.a = 0;
            this.b = 0;
            this.c = 0;
            return;
        }
        else {
            this.a = A;
            this.b = A;
            this.c = A;
        }
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
        System.out.println("Perimeter: "+calculatePerimeter()+", area: "+calculateArea());
    }


}
