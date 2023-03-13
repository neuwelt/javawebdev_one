package com.webdev_one;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Person {

    // generate entity Id and store as Long value
    @Id
    @GeneratedValue
    private Long Id;

    private String firstname;
    private String lastname;
    private String email;

    public Long getId() {
	return Id;
    }

    public void setId(Long id) {
	Id = id;
    }

    public String getFirstname() {
	return firstname;
    }

    public void setFirstname(String firstname) {
	this.firstname = firstname;
    }

    public String getLastname() {
	return lastname;
    }

    public void setLastname(String lastname) {
	this.lastname = lastname;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

}
