package com.prs.business;


import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class PurchaseRequest {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name = "userID")
	private User user;
	private String description;
	private String justification;
	private LocalDate dateNeeded;
	private String deliveryMode;
	private String status;
	private double total;
	private LocalDateTime submittedDate;
	private String reasonForRejection;
	


public PurchaseRequest(int id, User user, String description, String justification, LocalDate dateNeeded,
		String deliveryMode, String status, double total, LocalDateTime submittedDate, String reasonForRejection) {
	super();
	this.id = id;
	this.user = user;
	this.description = description;
	this.justification = justification;
	this.dateNeeded = dateNeeded;
	this.deliveryMode = deliveryMode;
	this.status = status;
	this.total = total;
	this.submittedDate = submittedDate;
	this.reasonForRejection = reasonForRejection;
	}


public PurchaseRequest() {
	
	}


public int getId() {
	return id;
}


public void setId(int id) {
	this.id = id;
}


public User getUser() {
	return user;
}


public void setUser(User user) {
	this.user = user;
}


public String getDescription() {
	return description;
}


public void setDescription(String desc) {
	this.description = desc;
}


public String getJustification() {
	return justification;
}


public void setJustification(String justification) {
	this.justification = justification;
}


public LocalDate getDateNeeded() {
	return dateNeeded;
}


public void setDateNeeded(LocalDate dateNeeded) {
	this.dateNeeded = dateNeeded;
}


public String getDeliveryMode() {
	return deliveryMode;
}


public void setDelivereyMode(String deliveryMode) {
	this.deliveryMode = deliveryMode;
}


public String getStatus() {
	return status;
}


public void setStatus(String status) {
	this.status = status;
}


public double getTotal() {
	return total;
}


public void setTotal(double total) {
	this.total = total;
}


public LocalDateTime getSubDate() {
	return submittedDate;
}


public void setSubDate(LocalDateTime subDate) {
	this.submittedDate = subDate;
}


public String getReasonForRej() {
	return reasonForRejection;
}


public void setReasonForRej(String reasonForRej) {
	this.reasonForRejection = reasonForRej;
}


@Override
public String toString() {
	return "PurchaseRequest [id=" + id + ", userId=" + user + ", desc=" + description + ", justification=" + justification
			+ ", dateNeeded=" + dateNeeded + ", deliveryMode=" + deliveryMode + ", status=" + status + ", total="
			+ total + ", submittedDate=" + submittedDate + ", reasonForRejection=" + reasonForRejection + "]";
}




}

