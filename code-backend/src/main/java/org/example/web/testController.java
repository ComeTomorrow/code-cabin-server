package org.example.web;

import org.example.common.core.result.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class testController {

    @GetMapping("test")
    public Result test(){
        System.out.println("ok");
        return Result.success("ok");
    }


}
