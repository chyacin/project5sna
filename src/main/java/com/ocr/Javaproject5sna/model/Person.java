package com.ocr.Javaproject5sna.model;

public class Person {
 
		public String firstName;
		public String lastName;
		public String phone;
		public String zip;
		public String address;
		public String city;
		public String email;

		public Person(String firstName, String lastName, String phone, String zip, String address, String city, String email) {
			this.firstName = firstName;
			this.lastName = lastName;
			this.phone = phone;
			this.zip = zip;
			this.address = address;
			this.city = city;
			this.email = email;
		}


		public String getFirstName() {
	        return firstName;
	    }

	    public void setFirstName(String firstName) {
	        this.firstName = firstName;
	    }

	    public String getLastName() {
	        return lastName;
	    }

	    public void setLastName(String lastName) {
	        this.lastName = lastName;
	    }

	    public String getAddress() {
	        return address;
	    }

	    public void setAddress(String address) {
	        this.address = address;
	    }

	    public String getCity() {
	        return city;
	    }

	    public void setCity(String city) {
	        this.city = city;
	    }

	    public String getZip() {
	        return zip;
	    }

	    public void setZip(String zip) {
	        this.zip = zip;
	    }

	    public String getPhone() {
	        return phone;
	    }

	    public void setPhone(String phone) {
	        this.phone = phone;
	    }

	    public String getEmail() {
	        return email;
	    }

	    public void setEmail(String email) {
	        this.email = email;
	    }

	    @Override
	    public String toString() {
	        return "Person{" +
	                "firstName='" + firstName + '\'' +
	                ", lastName='" + lastName + '\'' +
	                ", address='" + address + '\'' +
	                ", city='" + city + '\'' +
	                ", zip='" + zip + '\'' +
	                ", phone='" + phone + '\'' +
	                ", email='" + email + '\'' +
	                '}';
	    }	
}

