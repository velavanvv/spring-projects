package com.flower.shop.Service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.flower.shop.Entity.CustomerEntity;
import com.flower.shop.Entity.imgEntity;
import com.flower.shop.Repository.CustomerRepository;
import com.flower.shop.Repository.imageRepository;

@Component
public class CustomerService {
@Autowired
CustomerRepository customerRepo;


@Autowired
imageRepository img;

    public void saveCustomer(CustomerEntity customer) {

      customerRepo.save(customer);
    }


    public CustomerEntity getCustomer(String email) {
   
      return customerRepo.findByCustomerEmail(email);
    }

    public CustomerEntity getCustomer(int  id) {
  
      return customerRepo.findByCustomerId(id);
    }

/* 
    public void saveimage(MultipartFile file,CustomerEntity customer) {

        try {
            byte[] data = file.getBytes();
            imgEntity image = new imgEntity();
            image.setCustomerId(customer);
            image.setName(file.getOriginalFilename());
            image.setImg(data);
            img.save(image);
        } catch (IOException e) {
            e.printStackTrace();
            // Handle exception
        }

    } */
    public boolean checkCustomer(String email, String password) {
       
     CustomerEntity customer=customerRepo.findByCustomerEmail(email);
     if(customer!=null && customer.getCustomerPassword().equals(password)){
      return true;
     }
return false; 
    }
    
}
