package pl.jpraz.credit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class App {
    public static void main(String[] args){
        SpringApplication.run(App.class, args);
    }

    @Bean
    NamesProvider create(NameTransformer nameTransformer) {
        return new NamesProvider(nameTransformer);
    }

    @Bean
    NameTransformer createNT(){
        return new NameTransformer();
    }
}

