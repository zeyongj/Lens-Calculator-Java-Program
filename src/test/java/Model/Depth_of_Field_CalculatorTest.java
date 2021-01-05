package Model;

import org.junit.Test;

import java.text.DecimalFormat;

import static org.junit.jupiter.api.Assertions.*;

class Depth_of_Field_CalculatorTest {

    Lens l1 = new Lens("Canon", 1.8, 50);
    Lens l2 = new Lens("Tamron", 2.8, 90);
    Lens l3 = new Lens("Sigma", 2.8, 200);
    Lens l4 = new Lens("Nikon", 4.0, 200);
    Lens l5 = new Lens("ElCheepo", 12.0, 24);
    Lens l6 = new Lens("Leica", 5.6, 1600);
    Lens l7 = new Lens("TheWide", 1.0, 16);
    Lens l8 = new Lens("IWish", 1.0, 200);
    private static double circle_of_confusion = 0.029;


    private String formatM(double distanceInM) {
        if (distanceInM == Double.POSITIVE_INFINITY)
            return "INF";
        else if (distanceInM == Double.NEGATIVE_INFINITY)
            return "-INF";
        else {
            DecimalFormat df = new DecimalFormat("0.00");
            return df.format(distanceInM);
        }
    }

    @org.junit.jupiter.api.Test
    void Test1() {
        Depth_of_Field_Calculator myCalculator1 = new Depth_of_Field_Calculator(l1,1.1,1.8);
        assertEquals(47.89, myCalculator1.hyperfocalDistance()/1000,0.01);
        assertEquals("1.08",formatM(myCalculator1.nearFocalPoint()/1000));
        assertEquals("1.12",formatM(myCalculator1.farFocalPoint()/1000));
        assertEquals("0.05",formatM(myCalculator1.depthOfField()/1000));
    }

    @org.junit.jupiter.api.Test
    void Test2() {
        Depth_of_Field_Calculator myCalculator2 = new Depth_of_Field_Calculator(l1,1.0,8.0);
        assertEquals(10.78, myCalculator2.hyperfocalDistance()/1000,0.01);
        assertEquals("0.92",formatM(myCalculator2.nearFocalPoint()/1000));
        assertEquals("1.10",formatM(myCalculator2.farFocalPoint()/1000));
        assertEquals("0.18",formatM(myCalculator2.depthOfField()/1000));
    }

    @org.junit.jupiter.api.Test
    void Test3() {
        Depth_of_Field_Calculator myCalculator3 = new Depth_of_Field_Calculator(l1,15.0,11.0);
        assertEquals(7.84, myCalculator3.hyperfocalDistance()/1000,0.01);
        assertEquals("5.16",formatM(myCalculator3.nearFocalPoint()/1000));
        assertEquals("INF",formatM(myCalculator3.farFocalPoint()/1000));
        assertEquals("INF",formatM(myCalculator3.depthOfField()/1000));
    }

    @org.junit.jupiter.api.Test
    void Test4() {
        Depth_of_Field_Calculator myCalculator4 = new Depth_of_Field_Calculator(l2,2.0,2.8);
        assertEquals(99.75, myCalculator4.hyperfocalDistance()/1000,0.01);
        assertEquals("1.96",formatM(myCalculator4.nearFocalPoint()/1000));
        assertEquals("2.04",formatM(myCalculator4.farFocalPoint()/1000));
        assertEquals("0.08",formatM(myCalculator4.depthOfField()/1000));
    }

    @org.junit.jupiter.api.Test
    void Test5() {
        Depth_of_Field_Calculator myCalculator5 = new Depth_of_Field_Calculator();
        assertThrows(IllegalArgumentException.class,  () -> Depth_of_Field_Calculator.setLens(null));
    }

    @org.junit.jupiter.api.Test
    void Test6() {
        Depth_of_Field_Calculator myCalculator6 = new Depth_of_Field_Calculator(l1,15.0,11.0);
        assertThrows(IllegalArgumentException.class,  () -> Depth_of_Field_Calculator.setDistance(-100));
    }

    @org.junit.jupiter.api.Test
    void Test7() {
        Depth_of_Field_Calculator myCalculator7 = new Depth_of_Field_Calculator(l1,15.0,11.0);
        assertThrows(IllegalArgumentException.class,  () -> Depth_of_Field_Calculator.setAperture(1.3));
        assertThrows(IllegalArgumentException.class,  () -> Depth_of_Field_Calculator.setAperture(-105));
        assertThrows(IllegalArgumentException.class,  () -> Depth_of_Field_Calculator.setAperture(23));
    }

    @org.junit.jupiter.api.Test
    void Test8() {
        Depth_of_Field_Calculator myCalculator8 = new Depth_of_Field_Calculator(l1, 0.0, 22);
        assertEquals(3.92, myCalculator8.hyperfocalDistance() / 1000, 0.01);
        assertEquals("0.00", formatM(myCalculator8.nearFocalPoint() / 1000));
        assertEquals("0.00", formatM(myCalculator8.farFocalPoint() / 1000));
        assertEquals("0.00", formatM(myCalculator8.depthOfField() / 1000));
    }





}