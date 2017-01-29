package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by user on 29.01.2017.
 */
public class PointTests {
  @Test
  public void Test_Distance_Should_Be_Zero(){
    Point p = new Point(5.0, 5.0);

    Assert.assertEquals(p.distance(p), 0.0);
  }

  @Test
  public void Test_Distances_Between_Two_Points_Should_Be_Equal(){
    Point p1 = new Point(1.0, 1.0);
    Point p2 = new Point(15.0, 15.0);

    Assert.assertEquals(p1.distance(p2), p2.distance(p1));
  }

  @Test
  public void Test_Distances_Between_OppositeX_Should_Be_Equals(){
    Point p0 = new Point(0.0, 0.0);
    Point p1 = new Point(5.0, 5.0);
    Point p2 = new Point(-5.0, 5.0);

    Assert.assertEquals(p0.distance(p1), p0.distance(p2));
  }

  @Test
  public void Test_Distances_Between_OppositeY_Should_Be_Equals(){
    Point p0 = new Point(0.0, 0.0);
    Point p1 = new Point(1.0, 5.0);
    Point p2 = new Point(1.0, -5.0);

    Assert.assertEquals(p0.distance(p1), p0.distance(p2));
  }

  @Test
  public void Test_Distances_Between_OppositeXY_Should_Be_Equals(){
    Point p0 = new Point(0.0, 0.0);
    Point p1 = new Point(1.0, 5.0);
    Point p2 = new Point(-1.0, -5.0);

    Assert.assertEquals(p0.distance(p1), p0.distance(p2));
  }

  @Test
  public void Test_Distances_Between_If_X_Same_Should_Be_Equals(){
    Point p0 = new Point(7.0, 0.0);
    Point p1 = new Point(7.0, 5.0);

    Assert.assertEquals(p0.distance(p1), (p1.y - p0.y));
  }

  @Test
  public void Test_Distances_Between_If_Y_Same_Should_Be_Equals(){
    Point p0 = new Point(1.0, 10.0);
    Point p1 = new Point(7.0, 10.0);

    Assert.assertEquals(p0.distance(p1), (p1.x - p0.x));
  }

  @Test
  public void Test_Distances_Between_Reflections_Should_Be_Equals(){
    Point p0 = new Point(1.0, 10.0);
    Point p1 = new Point(7.0, 10.0);

    Point p3 = new Point(p0.x, p1.y);
    Point p4 = new Point(p1.x, p0.y);

    Assert.assertEquals(p0.distance(p1), p3.distance(p4));
  }

}

