package pl.jpraz.credit;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class HelloController {
    NamesProvider namesProvider;
    public HelloController(NamesProvider namesProvider){
        this.namesProvider = namesProvider;
    }

    @GetMapping("/hello")
    String hello() {
        return "Hello Kuba :)";
    }

    @GetMapping("/names")
    List<String> names(){
        return namesProvider.allNames();
    }
}
