package com.revature.services;

import com.revature.dao.IReimDao;
import com.revature.models.Reimbursement;

import java.util.List;

public class ReimService {
    IReimDao rd;

    public ReimService(IReimDao reimDao) {
        this.rd = reimDao;
    }

    public void createReim(Reimbursement r) {
        rd.createReim(r);
    }

    public List<Reimbursement> getAllUsersReimbursements(int id) {
        return rd.getReim(id);
    }

    public List<Reimbursement> getAllPendingReimbursements() {
        return rd.getAllPendingReim();
    }

    public void denyReim(int reimId) {
        rd.denyReim(reimId);
    }

    public void approveReim(int reimId) {
        rd.approveReim(reimId);
    }
}