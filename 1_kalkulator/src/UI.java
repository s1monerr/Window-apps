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
                    break;

                case 2:
                    squareCalc();
                    break;

                case 3:
                    circleCalc();
                    break;

                case 4:
                    prismCalc();
                    break;

                case 0:
                    return;

                default:
                    System.out.println("Wrong choice!");
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
        Triangle triangle = null;
        boolean run = true; // stop condition for inner menu loop
        while(run) {
            System.out.print("\033[H\033[2J");
            printInnerMenu();
            switch (getKey()) {
                case 1:
                    System.out.println("Enter a:");
                    System.out.print("<");
                    double a = scanner.nextDouble();

                    System.out.println("Enter b:");
                    System.out.print("<");
                    double b = scanner.nextDouble();

                    System.out.println("Enter c:");
                    System.out.print("<");
                    double c = scanner.nextDouble();

                    triangle = new Triangle(a, b, c);
                    break;

                case 2:
                    try {
                        triangle.print();
                    } catch (NullPointerException e){
                        System.out.println("An NullException has occured: the shape is not initialized.");
                    }
                    break;

                case 3:
                    run = false;
                    break;

                case 0:
                    System.exit(0);

                default:
                    System.out.println("Wrong choice!");
            }
        }
        return;
    }

    // circle menu
    public void circleCalc(){
        circle = null;
        boolean run = true;
        printInnerMenu();
        while(run) {
            System.out.print("\033[H\033[2J");
            printInnerMenu();
            switch (getKey()) {
                case 1:
                    System.out.println("Enter r:");
                    System.out.print("<");
                    double r = scanner.nextDouble();
                    circle = new Circle(r);

                    break;

                case 2:
                    try {
                        circle.print();
                    } catch (NullPointerException e){
                        System.out.println("An NullException has occured: the shape is not initialized.");
                    }
                    break;

                case 3:
                    run = false;
                    break;

                case 0:
                    System.exit(0);

                default:
                    System.out.println("Wrong choice!");
            }
        }
    }

    // square menu
    public void squareCalc(){
        printInnerMenu();
        boolean run = true;
        square = null;
        while(run) {
            System.out.print("\033[H\033[2J");
            switch (getKey()) {
                case 1:
                    System.out.println("Enter r:");
                    System.out.print("<");
                    double a = scanner.nextDouble();
                    square = new Square(a);

                    break;

                case 2:
                    try {
                        square.print();
                    }catch (NullPointerException e){
                        System.out.println("An NullException has occured: the shape is not initialized.");
                    }
                    break;

                case 3:
                    run = false;
                    break;

                case 0:
                    System.exit(0);

                default:
                    System.out.println("Wrong choice!");
            }
            printInnerMenu();
        }
    }

    // square menu
    public void prismCalc(){

        System.out.println("Enter prism height: ");
        double H = scanner.nextDouble();

        System.out.println("Choose a base figure for prism: ");
        System.out.println("1. Triangle");
        System.out.println("2. Square");
        System.out.println("3. Circle");

        int shapeChoice = scanner.nextInt();
        // choosing base for prism
        switch (shapeChoice){
            case 1:
                System.out.println("Enter a:");
                double x = scanner.nextDouble();
                triangle = new Triangle(x); // equilateral triangle

                prism = new Prism(triangle, H);
                break;

            case 2:
                System.out.println("Enter a:");
                double a = scanner.nextDouble();
                square = new Square(a);

                prism = new Prism(square, H);
                break;

            case 3:
                System.out.println("Enter r:");
                double r = scanner.nextDouble();
                circle = new Circle(r);

                prism = new Prism(circle, H);
                break;

            default:
                System.out.println("Wrong choice!");
        }

        boolean run = true;

        while(run) {
            System.out.print("\033[H\033[2J");
            System.out.println("1. Print info");
            System.out.println("2. Main menu");
            System.out.println("0. EXIT PROGRAM");

            switch (getKey()) {
                case 1:
                    prism.print();
                    break;

                case 2:
                    run = false;
                    break;

                case 0:
                    System.exit(0);

                default:
                    System.out.println("Wrong choice!");
            }
        }
    }

}
