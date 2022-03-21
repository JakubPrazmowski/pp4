package pl.jpraz.credit;

import java.math.BigDecimal;

public class CreditCard {
    private BigDecimal creditLimit;
    public void assignLimit(BigDecimal newCreditLimit) {
        if (creditLimit != null) {
            throw new CantAssignLimitTwiceException();
        }
        this.creditLimit = newCreditLimit;

    }

    public BigDecimal getSaldo() {
        return creditLimit;
    }
}
