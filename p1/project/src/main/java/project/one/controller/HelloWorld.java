package project.one.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Javalin;
import project.one.dao.Employee;
import project.one.db.Repository;

import java.util.List;

public class HelloWorld {
    static ObjectMapper objectMapper = new ObjectMapper();
    public static void main(String[] args) {
        Javalin app = Javalin.create().start(7070);

        app.get("/employees", ctx -> {
            ctx.json( Repository.getInstance().getEmployees());
        });
        app.post("/employees", ctx -> {
            System.out.println(ctx.body());
            ctx.json( Repository.getInstance().getEmployees());
        });
    }
}