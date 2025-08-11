package Java_Oops;

public class Getters_Setters {
    public static void main(String[] args) {
        Pen p1 = new Pen();
        p1.setColor("Red");
        System.out.println("Pen color: " + p1.getColor());
        p1.setTip(0.7);
        System.out.println("Pen tip size: " + p1.getTipSize());
    }
}
class Pen {
    private String color;
    private double tipSize;

    public void setColor(String newColor) {
        this.color = newColor;
    }

    public String getColor() {
        return this.color;
    }

    public void setTip(double newTipSize) {
        this.tipSize = newTipSize;
    }

    public double getTipSize() {
        return this.tipSize;
    }
}
