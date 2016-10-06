package ua.rd.pizzaservice.services;

/**
 * @author Anton_Mishkurov
 */
public class UsageSomeService {
    private SomeService someService;

    public UsageSomeService(SomeService someService) {
        this.someService = someService;
    }

    public  void  init() {
        System.out.println(someService.toString());
    }
}
