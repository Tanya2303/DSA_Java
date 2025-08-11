package Java_Oops;

public class OOPS {
    public static void main(String[] args) {
        Pen p1 = new Pen();
        p1.setColor("Blue");
        System.out.println("Pen color: " + p1.color);
        p1.setTip(5);
        System.out.println("Pen tip size: " + p1.tipSize);

        Student s1 = new Student();
        s1.name = "John";
        s1.age = 20;
        s1.percentage(85, 90, 80);
        System.out.println("Student Name: " + s1.name);
        System.out.println("Student Age: " + s1.age);
        System.out.println("Student Percentage: " + s1.percentage + "%");
    }
}

    class Pen {
        String color;
        double tipSize;

        void setColor(String newcolor) {
            color = newcolor;
        }

        void setTip(int newTipSize) {
            tipSize = newTipSize;
        }
    }
    class Student {
        String name;
        int age;
        float percentage;

        void percentage(int phy,int chem, int math) {
            percentage = (phy + chem + math) / 3.0f;
        }
    }

