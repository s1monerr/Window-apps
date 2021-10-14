import java.util.Scanner;

public class UI {
    public static Scanner scanner = new Scanner(System.in);
    private Triangle triangle;
    private Square square;
    private Circle circle;
    private Prism prism;

    public void run(){
        while(true) {
            printFigureMenu();
            switch (getKey()) {

                case 1:
                    triangleCalc();

                case 0:
                    return;
                }
            }
        }

    public static int getKey(){
        String in = null;
        try{
            while(in == null){
                System.out.println(">>");
                in = scanner.nextLine();
                try{
                    int value = Integer.parseInt(in);
                    return value;
                } catch(Exception e){
                    //
                }
            }
        }catch (Exception e){
            return -1;
        }
        return -1;
    }

    public void printFigureMenu(){
        System.out.println("CHOOSE FIGURE");
        System.out.println("1. Triangle");
        System.out.println("2. Square");
        System.out.println("3. Circle");
        System.out.println("4. Prism");
        System.out.println("0. EXIT PROGRAM");
    }

    // inner menu for each figure
    public void printInnerMenu(){
        System.out.println("1. Enter data ");
        System.out.println("2. Print info");
        System.out.println("3. Main menu");
        System.out.println("0. EXIT PROGRAM");
    }

    // triangle menu
    public void triangleCalc(){
        triangle = new Triangle();
        while(true) {
            System.out.print("\033[H\033[2J");
            printInnerMenu();
            switch (getKey()) {
                case 1:
                    System.out.println("Enter a:");
                    System.out.print("<");
                    int a = scanner.nextInt();
                    triangle.setA(a);

                    System.out.println("Enter b:");
                    System.out.print("<");
                    int b = scanner.nextInt();
                    triangle.setB(b);

                    System.out.println("Enter c:");
                    System.out.print("<");
                    int c = scanner.nextInt();
                    triangle.setC(c);

                    break;

                case 2:
                    triangle.print();
                    break;

                case 3:
                    break;

                case 0:
                    return;
            }
        }
    }

    // triangle menu
    public void circleCalc(){
        circle = new Circle();
        while(true) {
            System.out.print("\033[H\033[2J");
            printInnerMenu();
            switch (getKey()) {
                case 1:
                    System.out.println("Enter r:");
                    System.out.print("<");
                    int r = scanner.nextInt();
                    circle.setR(r);

                    break;

                case 2:
                    circle.print();
                    break;

                case 3:
                    break;

                case 0:
                    return;
            }
        }
    }

}
