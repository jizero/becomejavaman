package hello;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;


    @GetMapping("/write")
    public String write(Model model) {
        model.addAttribute("customer", new Customer());
        return "customer/write";
    }


    @GetMapping("/list")
    public String customerList(Model model) {
        List<Customer> postList = customerRepository.findAll();
        model.addAttribute("user", postList);
        return "customer/list";
    }


    @PostMapping("/save")
    public String customerSave(@ModelAttribute Customer customermodel) {
        Customer customer = new Customer();
        customer.setUsername(customermodel.getUsername());
        customer.setId(customermodel.getId());

        customerRepository.save(customer);

        return "redirect:/customer/list";
    }



}