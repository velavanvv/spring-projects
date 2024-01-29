package com.flower.shop.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.flower.shop.Entity.CustomerEntity;

@Component
public interface CustomerRepository extends CrudRepository<CustomerEntity,Integer> {
    public CustomerEntity findByCustomerEmail(String email);
}
