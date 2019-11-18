package com.revature.models;

import java.sql.Timestamp;

public class Reimbursement {
	private int id, statusId, typeId, amount, author,
	resolver;
	private Timestamp submitted, resolved;
	private String description;
	public Reimbursement() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Reimbursement(int id, int amount, Timestamp submitted,Timestamp resolved, 
			String description, int author, int resolver, int statusId, int typeId) {
		super();
		this.id = id;
		this.statusId = statusId;
		this.typeId = typeId;
		this.amount = amount;
		this.author = author;
		this.resolver = resolver;
		this.submitted = submitted;
		this.resolved = resolved;
		this.description = description;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getStatusId() {
		return statusId;
	}
	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getAuthor() {
		return author;
	}
	public void setAuthor(int author) {
		this.author = author;
	}
	public int getResolver() {
		return resolver;
	}
	public void setResolver(int resolver) {
		this.resolver = resolver;
	}
	public Timestamp getSubmitted() {
		return submitted;
	}
	public void setSubmitted(Timestamp submitted) {
		this.submitted = submitted;
	}
	public Timestamp getResolved() {
		return resolved;
	}
	public void setResolved(Timestamp resolved) {
		this.resolved = resolved;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "Reimbursement [id=" + id + ", statusId=" + statusId + ", typeId=" + typeId + ", amount=" + amount
				+ ", author=" + author + ", resolver=" + resolver + ", submitted=" + submitted + ", resolved="
				+ resolved + ", description=" + description + "]";
	}
	
}
