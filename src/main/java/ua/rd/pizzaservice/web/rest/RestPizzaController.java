package ua.rd.pizzaservice.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import ua.rd.pizzaservice.domain.Pizza;
import ua.rd.pizzaservice.services.PizzaService;

/**
 * @author Anton_Mishkurov
 */
@RestController
public class RestPizzaController {

    private PizzaService pizzaService;

    @Autowired
    public RestPizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @RequestMapping(
            value = "hello",
            method = RequestMethod.GET)
    public String hello() {
        return "Hello from rest Controller";
    }

    @RequestMapping(
            value = "hello/{pizzaId}")
    public ResponseEntity<Pizza> getPizza(@PathVariable(name = "pizzaId") Long id) {
        final Pizza pizza = pizzaService.getPizzaByID(id);
        if (pizza == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(pizza, HttpStatus.FOUND);
        }
    }

    @RequestMapping(
            path = "pizza",
            method = RequestMethod.POST,
            consumes = "application/json"
    )
    public ResponseEntity<Void> create(@RequestBody Pizza pizza, UriComponentsBuilder builder){
        System.out.println(pizza);
        Pizza p = pizzaService.save(pizza);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/pizza/{pizzaId}").buildAndExpand(p.getId()).toUri());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(
            value = "pizza/{pizzaId}",
            method = RequestMethod.GET)
    public Pizza pizza(@PathVariable("pizzaId") Long id) {

        return pizzaService.getPizzaByID(id);
    }

    @RequestMapping(
            value = "application",
            method = RequestMethod.POST,
            consumes = "application/json"
    )
    public void pizza(@RequestBody Pizza pizza) {
        System.out.println(pizza);
    }
}
