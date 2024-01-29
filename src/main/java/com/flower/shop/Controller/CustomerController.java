package com.flower.shop.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.flower.shop.Entity.CustomerEntity;
import com.flower.shop.Service.CustomerService;

@Controller
public class CustomerController {
    @Autowired
    CustomerService serviceCustomer;

    @GetMapping("/register")
    public String register(){
        return "Register.html";
    }
  


    @PostMapping("/addingCustomer")
   public String addCustomer(@ModelAttribute CustomerEntity customer){
    serviceCustomer.saveCustomer(customer);
    return "Login";
   }


@GetMapping("/login")
public String login(){
    return "Login";
}


   @GetMapping("/customerLogin")
   public String checkLogin(@ModelAttribute CustomerEntity customer ,Model model){
   String email=customer.getCustomerEmail();
   String password=customer.getCustomerPassword();
   boolean isflag=serviceCustomer.checkCustomer(email,password);
   if(isflag==true){
    return "Home";}
    else{
        model.addAttribute("error", "invalid password");
        return "Login";
    }
    
   }
}