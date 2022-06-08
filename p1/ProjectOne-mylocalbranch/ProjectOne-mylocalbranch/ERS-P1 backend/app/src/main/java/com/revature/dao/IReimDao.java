package com.revature.dao;

import com.revature.models.Reimbursement;

import java.util.List;

public interface IReimDao {
    //CRUD
    //create
    public void createReim(Reimbursement r);

    //read
    public List<Reimbursement> getReim(int user_id);

    public List<Reimbursement> getAllPendingReim();

    public void denyReim(int reimId);

    public void approveReim(int reimId);
}