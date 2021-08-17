package uk.co.jamhammer.track.controller;

import jdk.nashorn.internal.ir.RuntimeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uk.co.jamhammer.track.model.Customer;
import uk.co.jamhammer.track.service.CustomerService;

@Controller
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping("/customers")
    public String customerList(Model model) {
        model.addAttribute("customers", customerService.findAll());
        model.addAttribute("customer", new Customer());
        return "customer/list";
    }

    @PostMapping("/customer")
    public String customerPost(Customer customer,
                               @RequestHeader(HttpHeaders.REFERER) final String referer) {
        customerService.save(customer);
        return "redirect:" + referer;
    }

    @RequestMapping("/customer/delete/{id}")
    public String customerDelete(@PathVariable long id,
                                 @RequestHeader(HttpHeaders.REFERER) final String referer) {
        if (customerService.findById(id).isPresent()) {
            customerService.delete(customerService.findById(id).get());
        }
        return "redirect:" + referer;
    }

}
