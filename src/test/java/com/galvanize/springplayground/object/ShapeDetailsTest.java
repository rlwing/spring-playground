package com.galvanize.springplayground.object;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

public class ShapeDetailsTest {

    @Test
    public void testCircle(){
        ShapeDetails shape = new ShapeDetails("circle", 3.0);
        double area = shape.calcArea();
        assertTrue("was "+area+"should be 28.274333882308138", area == 28.274333882308138);
    }

    @Test
    public void testRectangle(){
        ShapeDetails shape = new ShapeDetails("rectangle", 4.0, 5.0);
        double area = shape.calcArea();
        assertTrue("was "+area+" should be 20", area == 20);
    }

}
