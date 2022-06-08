package com.revature.models;

import java.sql.Timestamp;

public class Reimbursement {
    private int reimbursement_id;
    private double amount;
    private Timestamp submitted_date;
    private Timestamp resolved_date;
    private String description;
    private int reimbursement_author;

    private String author;
    private int reimbursement_resolver;

    private String resolver;
    private int reimbursement_status;

    private String status;
    private int reimbursement_type;

    private String type;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getResolver() {
        return resolver;
    }

    public void setResolver(String resolver) {
        this.resolver = resolver;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Reimbursement() {
    }

    public Reimbursement(double amount, String description, int reimbursement_type) {
        this.amount = amount;
        this.description = description;
        this.reimbursement_type = reimbursement_type;
    }


    public Reimbursement(int reimbursement_id, double amount, Timestamp submitted_date, Timestamp resolved_date, String description, String author, String resolver, String status, String type) {
        this.reimbursement_id = reimbursement_id;
        this.amount = amount;
        this.submitted_date = submitted_date;
        this.resolved_date = resolved_date;
        this.description = description;
        this.author = author;
        this.resolver = resolver;
        this.status = status;
        this.type = type;
    }

    public Reimbursement(int reimbursement_id, double amount, Timestamp submitted_date, String description, String author, String status, String type) {
        this.reimbursement_id = reimbursement_id;
        this.amount = amount;
        this.submitted_date = submitted_date;
        this.description = description;
        this.author = author;
        this.status = status;
        this.type = type;
    }

    public int getReimbursement_id() {
        return reimbursement_id;
    }

    public void setReimbursement_id(int reimbursement_id) {
        this.reimbursement_id = reimbursement_id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Timestamp getSubmitted_date() {
        return submitted_date;
    }

    public void setSubmitted_date(Timestamp submitted_date) {
        this.submitted_date = submitted_date;
    }

    public Timestamp getResolved_date() {
        return resolved_date;
    }

    public void setResolved_date(Timestamp resolved_date) {
        this.resolved_date = resolved_date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getReimbursement_author() {
        return reimbursement_author;
    }

    public void setReimbursement_author(int reimbursement_author) {
        this.reimbursement_author = reimbursement_author;
    }

    public int getReimbursement_resolver() {
        return reimbursement_resolver;
    }

    public void setReimbursement_resolver(int reimbursement_resolver) {
        this.reimbursement_resolver = reimbursement_resolver;
    }

    public int getReimbursement_status() {
        return reimbursement_status;
    }

    public void setReimbursement_status(int reimbursement_status) {
        this.reimbursement_status = reimbursement_status;
    }

    public int getReimbursement_type() {
        return reimbursement_type;
    }

    public void setReimbursement_type(int reimbursement_type) {
        this.reimbursement_type = reimbursement_type;
    }

    @Override
    public String toString() {
        return "Reimbursement{" +
                "reimbursement_id=" + reimbursement_id +
                ", amount=" + amount +
                ", submitted_date=" + submitted_date +
                ", resolved_date=" + resolved_date +
                ", description='" + description + '\'' +
                ", author='" + author + '\'' +
                ", resolver='" + resolver + '\'' +
                ", status='" + status + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
