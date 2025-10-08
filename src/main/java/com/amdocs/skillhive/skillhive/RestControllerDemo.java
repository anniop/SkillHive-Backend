package com.amdocs.skillhive.skillhive;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestControllerDemo {
    @GetMapping("/ganesh")
    String ganesh(){
        return "Jay Ganesh";
    }
}
