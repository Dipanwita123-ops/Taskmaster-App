package com.bootangularpjct.rest.webservices.todo;

import java.util.Date;
import java.util.Objects;

import jakarta.persistence.Table;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "Todo")
public class Todo {

	@Column(name = "Id")
    @JsonProperty("Id")
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

	@Column(name = "username")
    private String username;

    @Column(name = "Description")
    @JsonProperty("Description")
    private String Description;

    @Column(name = "TargetDate")
   
    @JsonProperty("TargetDate")
    private Date TargetDate;
 
    @Column(name = "Done")
    @JsonProperty("Done")
    private boolean Done;

    //default constructor : The  constructor is needed because when user creates/updates a Todo , Jackson (via @RequestBody) uses default constructor
    //to create/update a Todo object and then sets its fields using setters in the POJO  during JSON deserialization.(JSON given by user ->Java obj conversion)


    protected Todo() {

    }


    // Constructor
    public Todo(Long id, String username, String description, Date targetDate, boolean done) {
        this.Id = id;
        this.username = username;
        this.Description = description;
        this.TargetDate = targetDate;
        this.Done = done;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        this.Id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        this.Description = description;
    }

    public Date getTargetDate() {
        return TargetDate;
    }

    public void setTargetDate(Date targetDate) {
        this.TargetDate = targetDate;
    }

    public boolean isDone() {
        return Done;
    }

    public void setDone(boolean done) {
        this.Done = done;
    }

	@Override
	public int hashCode() {
		return Objects.hash(Id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if ((obj == null) || (getClass() != obj.getClass()))
			return false;
		Todo other = (Todo) obj;
		return Id == other.Id;
	}




}
