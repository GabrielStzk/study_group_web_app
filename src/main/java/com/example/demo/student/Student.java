package com.example.demo.student;


public class Student {
	private String id;
	private String password;
	private String fname;
	private String lname;
	private String email;
	private String phone;
	
	
	public Student(){}
	
	public Student(String id,
			String password,
			String fname,
			String lname,
			String email,
			String phone) {
		this.id = id;
		this.password = password;
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.phone = phone;
	}
	
	public Student(String fname,
			String lname,
			String email,
			String phone) {
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.phone = phone;
	}
	
	// Copy method
	public void copy(Student another) {
		this.id = another.id;
		this.password = another.password;
		this.fname = another.fname;
		this.lname = another.lname;
		this.email = another.email;
		this.phone = another.phone;
	}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    // toString method to return a string representation of the object
    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
