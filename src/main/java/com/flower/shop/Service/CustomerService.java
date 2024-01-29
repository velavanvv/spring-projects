package com.flower.shop.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.flower.shop.Entity.CustomerEntity;
import com.flower.shop.Repository.CustomerRepository;

@Component
public class CustomerService {
@Autowired
CustomerRepository customerRepo;
    public void saveCustomer(CustomerEntity customer) {
        // TODO Auto-generated method stub
      customerRepo.save(customer);
    }
    public boolean checkCustomer(String email, String password) {
        // TODO Auto-generated method stub
     CustomerEntity customer=customerRepo.findByCustomerEmail(email);
     if(customer!=null && customer.getCustomerPassword().equals(password)){
      return true;
     }
return false; 
    }
    
}
