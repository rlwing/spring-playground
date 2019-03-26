package com.galvanize.springplayground.Controllers;

import com.galvanize.springplayground.object.ShapeDetails;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/math")
public class MathController {

    @GetMapping("pi")
    public String getPi() {
        return Double.toString(Math.PI);
    }
    @GetMapping("calculate")
    public String calculate(@RequestParam(required = false, defaultValue = "add") String operation,
                            @RequestParam String x,
                            @RequestParam String y){
        int xi = Integer.parseInt(x);
        String op = null;
        int yi = Integer.parseInt(y);
        int answer;

        if(operation.equals("subtract")){
            op = "-";
            answer = xi - yi;
        }else if(operation.equals("multiply")){
            op = "*";
            answer = xi * yi;
        }else if(operation.equals("divide")){
            op = "/";
            answer = xi / yi;
        }else{ //Addition is the default
            op = "+";
            answer = xi + yi;
        }

        return String.format("%s %s %s = %s", x, op, y, answer);

    }

    @PostMapping("/volume/{length}/{width}/{height}")
    public String getPostVolume(@PathVariable int length,
                          @PathVariable int width,
                          @PathVariable int  height){
        int answer = length*width*height;
        return String.format("The volume of a %sx%sx%s rectangle is %s", length, width, height, answer);
    }

    @PatchMapping("/volume/{length}/{width}/{height}")
    public String getPatchVolume(@PathVariable int length,
                              @PathVariable int width,
                              @PathVariable int  height){
        return getPostVolume(length, width, height);
    }

    @PostMapping(value = "/area", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String getShapeArea(@RequestParam Map<String, Object> details){
        ShapeDetails shape = new ShapeDetails();
        if(details.get("type") == null){
            return "type is required";
        }else{
            shape.setType((String)details.get("type"));
        }
        if(shape.getType().equals("circle")){
            shape.setRadius(Double.parseDouble((String)details.get("radius")));

        }else if(shape.getType().equals("rectangle")){
            shape.setHeight(Double.parseDouble((String)details.get("height")));
            shape.setWidth(Double.parseDouble((String)details.get("width")));
        }else{
            return String.format("Unknown type '%s'", shape.getType());
        }
        if(shape.getType().equals("circle")){
            return String.format("Area of a %s with a radius of %s is %s", shape.getType(), shape.getRadius().toString(), shape.calcArea().toString());
        }else if(shape.getType().equals("rectangle")){
            return String.format("Area of a %sx%s rectangle is %s", shape.getHeight(), shape.getWidth(), shape.calcArea());
        }else{
            return "Can't compute";
        }
    }
}
