package pl.jpraz.crm;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Inquiry {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String email;
    String subject;
    String content;

    Inquiry(){
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
