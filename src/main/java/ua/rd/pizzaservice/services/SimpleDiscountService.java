package ua.rd.pizzaservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ua.rd.pizzaservice.domain.Order;
import ua.rd.pizzaservice.domain.discounts.Discount;

import java.math.BigDecimal;
import java.util.List;

/**
 * //TODO add comments
 *
 * @author Anton Mishkyroff
 */
@Service("discountService")
public class SimpleDiscountService implements DiscountService {

    private List<Discount> discountList;

    @Autowired
    public SimpleDiscountService(@Qualifier("discountList") List<Discount> discountList) {
        this.discountList = discountList;
    }

    @Override
    public BigDecimal getDiscount(Order order) {
        BigDecimal result = BigDecimal.ZERO;

        for (Discount discount : discountList) {
            result = result.add(discount.calc(order, result));
        }
        return result;
    }
}
