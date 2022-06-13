package pl.jpraz.sales.offer;

import pl.jpraz.sales.cart.Cart;
import pl.jpraz.sales.cart.CartItem;
import pl.jpraz.sales.offer.Offer;

import java.math.BigDecimal;

public class OfferMaker {
    public Offer calculateOffer(Cart cart) {
        BigDecimal total = cart
                .getItems()
                .stream()
                .map(this::calculateLineTotal)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);

        return Offer.of(total, cart.items.size());
    }

    private BigDecimal calculateLineTotal(CartItem cartItem) {
        return cartItem.getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity()));
    }
}
