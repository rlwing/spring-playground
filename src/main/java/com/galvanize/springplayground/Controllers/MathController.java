package com.galvanize.springplayground.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}