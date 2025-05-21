package pl.szmitka.ecommerce.sales;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SalesController {
    SalesFacade salesFacade;

    public SalesController(SalesFacade salesFacade) {
        this.salesFacade = salesFacade;
    }

    @GetMapping("/api/current-offer")
    Offer getCurrentOffer() {
        return salesFacade.getCurentOffer(getCurrentCustomer());
    }
    @PostMapping("/api/add-product/{productId}")
    void addProduct(@PathVariable(name= "productId") String productId) {
        salesFacade.addToCart(getCurrentCustomer(), productId);
    }
    @PostMapping("/api/accept-offer")
    void acceptOffer(AcceptOfferCommand acceptOfferCommand) {
        salesFacade.acceptOffer(acceptOfferCommand);
    }

    private String getCurrentCustomer() {
        return "kuba";
    }
}
