package pl.jpraz.credit;

import org.junit.jupiter.api.Test;
import static  org.junit.jupiter.api.Assertions.*;
import java.math.BigDecimal;

public class CreditCardTest {
    @Test
    void itAllowsToAssignCreditLimit(){
        //A Arrange / Given
        CreditCard creditCard =  new CreditCard();
        //A Act     / When
        creditCard.assignLimit(BigDecimal.valueOf(1000));
        //A Assert  / Then / Expected
        assertEquals(BigDecimal.valueOf(1000), creditCard.getSaldo());
    }

    @Test
    void itAllowsToAssignCreditLimit1(){
        //A Arrange / Given
        CreditCard creditCard =  new CreditCard();
        //A Act     / When
        creditCard.assignLimit(BigDecimal.valueOf(3000));
        //A Assert  / Then / Expected
        assertEquals(BigDecimal.valueOf(3000), creditCard.getSaldo());
    }
    @Test
    void itAllowsToAssignCreditLimit3(){
        //A Arrange / Given
        CreditCard creditCard =  new CreditCard();
        //A Act     / When
        creditCard.assignLimit(BigDecimal.valueOf(2000));
        //A Assert  / Then / Expected
        assertEquals(BigDecimal.valueOf(2000), creditCard.getSaldo());
    }

    @Test
    void itDenyToAssignLimitTwice(){
        CreditCard creditCard =  new CreditCard();
        creditCard.assignLimit(BigDecimal.valueOf(2000));

        assertThrows(CantAssignLimitTwiceException.class, () -> {
           creditCard.assignLimit(BigDecimal.valueOf(2000));
        });
    }
}
