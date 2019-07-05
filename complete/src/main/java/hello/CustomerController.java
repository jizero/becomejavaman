package hello;

import java.util.List;
import java.lang.String;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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


    @GetMapping("/list/{id}")
    public String customerList(Model model, @PathVariable long id) {
        //Optional<Customer> optcustom = customerRepository.findOne(id);

        List<Customer> postList = null;
        if(id <= 5){
            postList = customerRepository.findByIdLimit(id);
        }

        model.addAttribute("user", postList);
        model.addAttribute("paramid", id );

        return "customer/list";
    }


    @PostMapping("/save")
    public String customerSave(@ModelAttribute Customer customermodel) {
        Customer customer = new Customer();
        customer.setUsername(customermodel.getUsername());
        customer.setId(customermodel.getId());

        customerRepository.save(customer);

        return "redirect:/customer/list/"+customermodel.getId();
    }



}