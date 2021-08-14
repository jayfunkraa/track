package uk.co.jamhammer.track.controller;

import jdk.nashorn.internal.ir.RuntimeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uk.co.jamhammer.track.model.Customer;
import uk.co.jamhammer.track.service.CustomerService;

@Controller
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping("/customers")
    public String customerList(Model model) {
        model.addAttribute("customers", customerService.findAll());
        return "customer/list";
    }

    @RequestMapping("customer/add")
    public String customerAdd(Model model) {
        model.addAttribute("customer", new Customer());
        return "customer/add";
    }

    @PostMapping("/customer")
    public String customerPost(Customer customer) {
        customerService.save(customer);
        return "redirect:/customers";
    }

    @RequestMapping("/customer/edit/{id}")
    public String customerEdit(Model model, @PathVariable long id) {
        if (customerService.findById(id).isPresent()) {
        model.addAttribute("customer", customerService.findById(id));
        }
        return "customer/edit";
    }

    @RequestMapping("/customer/delete/{id}")
    public String customerDelete(@PathVariable long id) {
        if (customerService.findById(id).isPresent()) {
            customerService.delete(customerService.findById(id).get());
        }
        return "redirect:/customers";
    }

}
