public class Prism{

    private final Figure base;
    private final double height;

    public Prism(Figure shape, double H){
        this.base = shape;
        this.height = H;
    }

    public double calculateVolume(){
        return base.calculateArea()*height;
    }

    public double calculateArea(){
        return base.calculateArea()*2 + base.calculatePerimeter()*height;
    }

    public void print(){
        System.out.print("Base shape: ");
        base.print();
        System.out.println("Prism base height: "+height+", volume: "+calculateVolume()+", area: "+calculateArea());
    }

}
