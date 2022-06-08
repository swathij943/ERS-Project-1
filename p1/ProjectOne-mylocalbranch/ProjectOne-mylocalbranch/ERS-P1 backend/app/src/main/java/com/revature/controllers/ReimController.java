package com.revature.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Reimbursement;
import com.revature.services.ReimService;
import io.javalin.http.Handler;

public class ReimController {
    private ObjectMapper om;
    private ReimService rService;
    public ReimController(ReimService rService) {
        this.rService = rService;
        this.om = new ObjectMapper();
    }

    public Handler handleCreateReim = ctx -> {

        //First check to see if the use already logged in
        if(ctx.req.getSession().getAttribute("user_id") == null){
            ctx.status(401);
            ctx.result("You must login to create reimbursement requests");
        } else {
            int author = Integer.parseInt(ctx.req.getSession().getAttribute("user_id").toString());

            Reimbursement r = om.readValue(ctx.body(), Reimbursement.class);
            r.setReimbursement_author(author);

            rService.createReim(r);
            ctx.status(201);
            ctx.result("Reimbursement request created");
        }

    };

    public Handler handleViewReim = ctx -> {

        //First check to see if the use already logged in
        if(ctx.req.getSession().getAttribute("user_id") == null){
            ctx.status(401);
            ctx.result("You must login to view reimbursements");
        } else {
            int author = Integer.parseInt(ctx.req.getSession().getAttribute("user_id").toString());

            ctx.status(200);
            ctx.result(om.writeValueAsString(rService.getAllUsersReimbursements(author)));
        }

    };

    public Handler handleViewPendingReim = ctx -> {
        //check to see if the user is a manager
        if (ctx.req.getSession().getAttribute("user_id") == null) {
            ctx.status(401);
            ctx.result("You must login to view reimbursements");
        } else if ((ctx.req.getSession().getAttribute("role").toString()).equals("1")) {
            ctx.status(401);
            ctx.result("Only Managers are authorized to view this page");
        } else {
            ctx.status(200);
            ctx.result(om.writeValueAsString(rService.getAllPendingReimbursements()));
        }
    };

    public Handler handleDeny = ctx -> {
        //Deny a reimbursement request if user is manager
        if (ctx.req.getSession().getAttribute("user_id") == null) {
            ctx.status(401);
            ctx.result("You must login to Deny reimbursements");
        } else if ((ctx.req.getSession().getAttribute("role").toString()).equals("1")) {
            ctx.status(401);
            ctx.result("Only Managers are authorized to deny reimbursements");
        } else {
            Reimbursement r = om.readValue(ctx.body(), Reimbursement.class);
            System.out.println("Reiumbursement ID"+r.getReimbursement_id());
            rService.denyReim(r.getReimbursement_id());
            ctx.status(200);
            ctx.result("Reimbursement request denied");
        }
    };

    public Handler handleApprove = ctx -> {
        //Approve a reimbursement request if user is manager
        if (ctx.req.getSession().getAttribute("user_id") == null) {
            ctx.status(401);
            ctx.result("You must login to Approve reimbursements");
        } else if ((ctx.req.getSession().getAttribute("role").toString()).equals("1")) {
            ctx.status(401);
            ctx.result("Only Managers are authorized to approve reimbursements");
        } else {
            Reimbursement r = om.readValue(ctx.body(), Reimbursement.class);
            rService.approveReim(r.getReimbursement_id());
            ctx.status(200);
            ctx.result("Reimbursement request approved");
        }
    };


}