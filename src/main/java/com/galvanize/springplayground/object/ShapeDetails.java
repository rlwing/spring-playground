package com.galvanize.springplayground.object;

import java.util.Objects;

public class ShapeDetails {
    private String type;
    private double radius;
    private double width;
    private double height;

    public Double calcArea(){
        Double area = null;

        if (type.equals("circle")){
            area =  Math.PI * (radius*radius);
        }else{
            area =  width*height;
        }

        return area;
    }

    public ShapeDetails() {
    }

    public ShapeDetails(String type, Double radius) {
        this.type = type;
        this.radius = radius;
    }

    public ShapeDetails(String type, Double width, Double height) {
        this.type = type;
        this.width = width;
        this.height = height;
    }

    public String getType() {
        return type;
    }

    public Double getRadius() {
        return radius;
    }

    public Double getWidth() {
        return width;
    }

    public Double getHeight() {
        return height;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ShapeDetails)) return false;
        ShapeDetails that = (ShapeDetails) o;
        return Objects.equals(type, that.type) &&
                Objects.equals(radius, that.radius) &&
                Objects.equals(width, that.width) &&
                Objects.equals(height, that.height);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, radius, width, height);
    }

}
