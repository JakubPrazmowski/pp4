package pl.jpraz.credit;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class NamesProvider {
    NameTransformer nameTransformer;

    public NamesProvider(NameTransformer nameTransformer) {
        this.nameTransformer = nameTransformer;
    }
    public List<String> allNames(){
        return Arrays
                .asList("Kuba","Ola","Aga","Ola")
                .stream()
                .map(name -> nameTransformer.transform(name))
                .collect(Collectors.toList());
    }
}
