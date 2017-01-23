package ru.stqa.pft.sandbox;

public class MyFirstProgram
{
	public static void main(String[] args) 
	{
      Point p1 = new Point(0.0, 0.0);
      Point p2 = new Point(-1.0, -1.0);

      double dist = distance(p1, p2);

      System.out.println(dist);

      hello ("World");

      Rectangle r = new Rectangle(4,6);
      System.out.println("Площадь треугольника со сторонами " + r.a + " и " + r.b + " = " + r.area () );
  }
    public static void hello(String somebody) {
        System.out.println("Hello," + somebody + "!");
    }

    public static double distance(Point p1, Point p2){

        return Math.sqrt(((p1.x - p2.x) * (p1.x - p2.x)) + ((p1.y - p2.y) * (p1.y - p2.y)));

    }
}
