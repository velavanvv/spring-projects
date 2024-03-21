package com.flower.shop.Repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.flower.shop.Entity.CustomerEntity;
import com.flower.shop.Entity.imgEntity;

@Component
public interface imageRepository extends CrudRepository<imgEntity,Integer> {
  public imgEntity findByCustomerId(CustomerEntity customerId);
}
