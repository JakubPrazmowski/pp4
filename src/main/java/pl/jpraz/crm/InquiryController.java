package pl.jpraz.crm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InquiryController {
    @Autowired
    InquiryCRUD inquiryCRUD;
    @GetMapping("/contacts")
    List<Inquiry> all(){
        return inquiryCRUD.findAll();
    }
    @GetMapping("/contacts/{id})
    Inquiry getOneById(@PathVariable Long id){
        return inquiryCRUD.getById(id);
    }
    @PostMapping("/contacts")
    void createInquiry(@RequestBody Inquiry inquiry){
        inquiryCRUD.save(inquiry);
    }
}
