import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.util.LinkedList;
import javax.swing.*;

import static java.lang.Math.*;

public class main extends JComponent{
    public static Color paintColor = new Color((float)Math.random(),
            (float)Math.random(), (float)Math.random());
    public static double pi = 3.14159;
    private static class Line{ // klasa linii
        final double x1;
        final double y1;final double x2;
        final double y2;
        final Color color;
        public Line(double x1, double y1, double x2, double y2, Color color) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            this.color = color;
        }
    }
    private static class Input extends JFrame{
        JLabel label;
        JLabel label2;
        JTextField radius;
        JTextField iterations;
        JButton button;
        main GUI;
        public Input(double mainRadius, int mainIterations, double width, double
                height, main user){
            setLayout(new FlowLayout());
            GUI = user;
// dodaj ramke akcji
            label = new JLabel("Podaj promien okregu.");
            add(label);
            radius = new JTextField(10);
            add(radius);
            label2 = new JLabel("Podaj liczbe iteracji algorytmu.");
            add(label2);
            iterations = new JTextField(10);
            add(iterations);
            button = new JButton("Rysuj okrąg.");
            add(button);
// przycisk - zapisz dane
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String userStringRadius = radius.getText();
                    double userRadius = Double.parseDouble(userStringRadius);
                    String userStringIter = iterations.getText();
                    int userIterations = Integer.parseInt(userStringIter);
                    GUI.circleAlghoritm(userRadius,userIterations,width, height);
                }
            });
        }
    }
    // listy przechowujace linie i okregi
    private final LinkedList<Line> lines = new LinkedList<Line>();
    public void addLine(double x1, double x2, double x3, double x4, Color color) {
        lines.add(new Line(x1,x2,x3,x4, color));
        repaint(); // rysuj cala liste od nowa po dodaniu nowej linii
    }
    public void clearScreen() {
        lines.clear();
        repaint();}
    public void circleAlghoritm(double radius, int iterations, double height,
                                double width){
        double angle = 0.0;
        double angleJump = 2*pi/iterations;
        double x1;
        double y1;
        double x2 = width/2+radius*cos(angle); // x2, y2 - na srodku ekranu (czyliwitdh/2 - promien i heigth/2 - promien
        double y2 = height/2+radius*sin(angle);
// algorytm kola - dodaj linie o wspolrzednych poprzedniej linii i nowych -> wspolrzedne biegunowe
        for(int i = 0; i < iterations; i++){
            x1 = x2;
            y1 = y2;
            angle += angleJump;
            x2 = width/2+radius*cos(angle);
            y2 = height/2+radius*sin(angle);
            addLine(x1,y1,x2,y2, paintColor); // dodaj linie do listy
        }
    }
    @Override
    protected void paintComponent(Graphics g) { // override funkcji malujacej z JComponent
        Graphics2D g2 = (Graphics2D) g; // obiekt do rysowania z dokladnoscia double
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON); // wlaczenie antyliasingu
        for (Line line : lines) {
            Shape l = new Line2D.Double(line.x1, line.y1, line.x2, line.y2);
            g2.setColor(line.color);
            g2.draw(l);
        }
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame(); // nowa ramka
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Projekt - Rysowanie prostych ksztaltow.");
        final main comp = new main();
        final double width = 620;
        final double height = 500;
        comp.setPreferredSize(new Dimension((int)width, (int)height)); //ustawienia okna
        frame.getContentPane().add(comp, BorderLayout.CENTER); // dodaj pole wewnatrz ramki
// *** OBSLUGA PRZYCISKOW ***//
        JPanel buttonsPanel = new JPanel(); // panel przyciskow
// Przyciki do rysowania, zmiany koloru i czyszczenia okna
        JButton circleSimple = new JButton("Draw simple circle");
        JButton colorButton = new JButton("Change color");
        JButton clearButton = new JButton("Clear window");
// dodaj przyciski do okna
        buttonsPanel.add(clearButton);
        buttonsPanel.add(circleSimple);
        buttonsPanel.add(colorButton);
        frame.getContentPane().add(buttonsPanel, BorderLayout.SOUTH); // polozenie przyciskow - na dole okna
// override actionPerformed - przy przycisnieciu przycisku okreslone akcje// zmiana koloru na nowy losowy kolor
        colorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paintColor = new Color((float)Math.random(), (float)Math.random(),
                        (float)Math.random());
            }
        });
// rysowanie nowej linii
        circleSimple.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double radius = 50;
                int iterations = 50;
                Input input = new Input(radius, iterations, width, height, comp);
                input.setSize(300,300);
                input.setTitle("Dane okręgu");
                input.setVisible(true);
            }
        });
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comp.clearScreen();
            }
        });
        frame.pack(); // ustaw atrybuty okna w zakresie okna
        frame.setVisible(true); // okno widoczne
    }
}
