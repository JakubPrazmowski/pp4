package pl.jpraz.sales;

import org.springframework.web.bind.annotation.*;
import pl.jpraz.sales.offer.Offer;
import pl.jpraz.sales.payment.PaymentData;

import javax.servlet.http.HttpSession;
import java.util.UUID;

@RestController
public class SalesController {
    public static final String CUSTOMER_ID = "KUBA";

    Sales sales;

    public SalesController(Sales sales, HttpSession httpSession) {
        this.sales = sales;
    }

    @GetMapping("/api/sales/offer")
    Offer currentOffer() {
        return sales.getCurrentOffer(getCurrentCustomerId());
    }

    @PostMapping("/api/sales/add-product/{productId}")
    void addToCart(@PathVariable String productId) {
        sales.addToCart(getCurrentCustomerId(), productId);
    }

    @PostMapping("api/sales/accept-offer")
    PaymentData acceptOffer(@RequestBody Offer seenOffer, ClientData clientData) {
        //when //act
        PaymentData payment = sales.acceptOffer(
                getCurrentCustomerId(),
                clientData
        );
        return payment;
    }

    private String getCurrentCustomerId() {
        Object customerId = httpSession.getAttribute(s:"CUSTOMER_ID");

        if (customerId == null) {
            customerId = UUID.randomUUID().toString()
        }
    }
}
