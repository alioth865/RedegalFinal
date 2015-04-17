package com.yahoo.alioth865.redegalfinal.Modelo;

import java.io.Serializable;

/**
 * Created by Alioth on 16/04/2015.
 */
public class DireccionEspecifica implements Serializable {
    private String id;
    private String id_customer;
    private String address1;
    private String address2;
    private String postcode;
    private String city;

    public String getId_customer() {
        return id_customer;
    }

    public void setId_customer(String id_customer) {
        this.id_customer = id_customer;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return  address1 +" "+ address2 + " CP: " + postcode + " " + city;
    }
}
