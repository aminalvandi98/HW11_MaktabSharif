package ir.maktabsharif.entities;

import javax.persistence.*;

@Entity
public class Adrress {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = false, nullable = false,updatable = false)
    private int id;
    private String country;
    private String state;
    private String city;
    private String streetAddress;


    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }


}