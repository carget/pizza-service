package ua.rd.pizzaservice.web.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.rd.pizzaservice.domain.Pizza;
import ua.rd.pizzaservice.services.PizzaService;

import java.util.Collections;

/**
 * @author Anton_Mishkurov
 */
@Controller
public class PizzaController {

    @Autowired
    private PizzaService pizzaService;

    @RequestMapping("/hello")
//    @ResponseBody
    public String hello() {
        return "hello";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list(ModelAndView modelAndView) {
        modelAndView.setViewName("list");
        modelAndView.addObject("pizzas", pizzaService.findAll());
        return modelAndView;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable(name = "id") Long id, ModelAndView modelAndView) {
        modelAndView.setViewName("edit");
        modelAndView.addObject("pizza", pizzaService.getPizzaByID(id));
        return modelAndView;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView save(ModelAndView modelAndView, @ModelAttribute Pizza pizza) {
        pizzaService.save(pizza);
        modelAndView.setViewName("redirect:/app/list");
        return modelAndView;
//        return "redirect:list";
    }

    @RequestMapping(value = "pizza/{id}", method = RequestMethod.GET)
    public ModelAndView pizza(@PathVariable(name = "id") Long id, ModelAndView modelAndView) {
        modelAndView.setViewName("list");
        modelAndView.addObject("pizzas", Collections.singletonList(pizzaService.getPizzaByID(id)));
        return modelAndView;
    }

    @ModelAttribute
    public Pizza pizzaById(@RequestParam(name = "pizzaId", required = false) Long id) {
        if (id != null) {
            return pizzaService.getPizzaByID(id);
        } else {
            return new Pizza();
        }
    }

}
