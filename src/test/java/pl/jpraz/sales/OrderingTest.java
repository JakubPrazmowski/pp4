package pl.jpraz.sales;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.jpraz.sales.cart.CartStorage;
import pl.jpraz.sales.offer.Offer;
import pl.jpraz.sales.offer.OfferNotMatchedException;
import pl.jpraz.sales.payment.DummyPaymentGateway;
import pl.jpraz.sales.payment.PaymentData;
import pl.jpraz.sales.products.ListProductDetailsProvider;
import pl.jpraz.sales.products.ProductDetails;
import pl.jpraz.sales.reservation.Reservation;
import pl.jpraz.sales.reservation.ReservationStorage;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;



public class OrderingTest {

    private List<ProductDetails> products;
    private ReservationStorage reservationStorage;

    @BeforeEach
    void setUp() {
        products = new ArrayList<>();
        reservationStorage = new ReservationStorage();
    }

    @Test
    void itAllowsToOrderCollectedProducts() {
        //Given // Arrange
        String productId = thereIsExampleProduct();
        Sales sales = thereIsSalesModule();
        String customerId = thereIsCustomer();
        sales.addToCart(customerId, productId);
        Offer seenOffer = sales.getCurrentOffer(customerId);
        //when //act
        PaymentData payment = sales.acceptOffer(customerId, seenOffer, exampleCustomerData());

        //then // assert
        String reservationId = payment.getReservationId();

        assertNotNull(payment.getUrl);
        assertNotNull(reservationId);
        thereIsPendingReservationWithId(reservationId);
    }

    private void thereIsPendingReservationWithId(String reservationId) {
        Optional<Reservation> optionalReservation = reservationStorage.find(reservationId);

        assertTrue(optionalReservation.isPresent());
    }

    @Test
    void dennayPurchaseWhenOfferDifferentThenSeenOffer() {
        String productId = thereIsExampleProduct();
        Sales sales = thereIsSalesModule();
        String customerId = thereIsCustomer();
        sales.addToCart(customerId, productId);
        Offer seenOffer = sales.getCurrentOffer(customerId);
        Offer newOffer = Offer.of(BigDecimal.valueOf(1000), 1);
        //when

        assertThrows(OfferNotMatchedException.class, () -> {
            sales.acceptOffer(
                    customerId,
                    newOffer,
                    exampleCustomerData());
        });
    }

    private String thereIsExampleProduct() {
        String productId = "123";
        products.add(new ProductDetails(
                productId,
                "Lego",
                BigDecimal.valueOf(10.10)));
        return productId;
    }

    private Sales thereIsSalesModule() {
        return new Sales(
                new CartStorage(),
                new ListProductDetailsProvider(products),
                new DummyPaymentGateway(),
                reservationStorage
        );
    }

    private String thereIsCustomer() {
        return "Kuba";
    }

    private ClientData exampleCustomerData() {
        return ClientData.builder()
                .firstname("John")
                .lastname("Doe")
                .email("john@doe.pl")
                .build();
    }


    @Test
    void itAllowsToOrderCollectedProducts() {
        String productId = thereIsExampleProduct();
        Sales sales = thereIsSalesModule();
        String customerId = thereIsCustomer();
        sales.addToCart(customerId, productId);
        Offer seenOffer = sales.getCurrentOffer(customerId);
        //when

        PaymentData payment = sales.acceptOffer(
                customerId,
                seenOffer,
                getExampleClientData());
        //payemntUrl

        assertNotNull(payment.getUrl());
    }

    private String thereIsExampleProduct() {
        String productId = "123";
        products.add(new ProductDetails(
                productId,
                "Lego",
                BigDecimal.valueOf(10.10)));
        return productId;
    }

    private Sales thereIsSalesModule() {
        return new Sales(
                new CartStorage(),
                new ListProductDetailsProvider(products)
        );
    }

    private String thereIsCustomer() {
        return "Kuba";
    }

    private ClientData getExampleClientData() {
        return ClientData.builder()
                .firstname("John")
                .lastname("Doe")
                .email("john@doe.pl")
                .build();
    }


}
