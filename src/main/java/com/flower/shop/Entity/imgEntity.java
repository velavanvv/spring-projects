package com.flower.shop.Entity;

import java.util.Arrays;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;

@Entity
public class imgEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

private String name;

    @Override
public String toString() {
    return "imgEntity [id=" + id + ", name=" + name  /*+, img="  + Arrays.toString(img) */ + ", customerId=" + customerId
            + "]";
}

    public imgEntity(int id, String name, byte[] img, CustomerEntity customerId) {
    this.id = id;
    this.name = name;
  /*   this.img = img; */
    this.customerId = customerId;
}

    public String getName() {
    return name;
}

public void setName(String name) {
    this.name = name;
}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

  /*   public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    } */

    public CustomerEntity getCustomerId() {
        return customerId;
    }

    public void setCustomerId(CustomerEntity customerId) {
        this.customerId = customerId;
    }

/*     @Lob
    @Column(columnDefinition = "BLOB", length = 16777215)
    private byte[] img; */



    public imgEntity() {
    }

    @ManyToOne
    @JoinColumn(name = "customerId")
    private CustomerEntity customerId;

}
