package application.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/")
public class IndexController {
    @GetMapping
    public String home() {
        return "home";
    }

    @PostMapping
    public String postHome() {
        return "post home";
    }
}
