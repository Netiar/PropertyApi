package org.example;

import com.google.gson.Gson;
import org.example.data.PropertyDao;
import org.example.data.PropertyManagerDao;
import org.example.data.TenantDao;
import org.example.models.Property;
import org.example.models.PropertyManager;
import org.example.models.Tenant;

import static spark.Spark.*;

public class Main {

    public static void main(String[] args) {
        PropertyManagerDao propertyManagerDao = new PropertyManagerDao();
        TenantDao tenantDao = new TenantDao();
        PropertyDao propertyDao = new PropertyDao();
        Gson gson = new Gson();


        //add a property manager
        post("/propertymanager","application/json ",(request, response) -> {
            PropertyManager propertyManager = gson.fromJson(request.body(), PropertyManager.class);
            propertyManagerDao.addPropertyManager(propertyManager);
            response.status(201);
            return gson.toJson(propertyManager);
        });

        //get all property managers
        get("/propertymanagers", "application/json", (req, res) -> { //accept a request in format JSON from an app
            return gson.toJson(propertyManagerDao.getAllPropertyManagers());//send it back to be displayed
        });

        //get property manager by id
        get("/propertymanager/:id", "application/json", (req, res) -> { //accept a request in format JSON from an app
            int id = Integer.parseInt(req.params(":id"));
            PropertyManager propertyManager = propertyManagerDao.getPropertyManagerById(id);
            return gson.toJson(propertyManagerDao.getPropertyManagerById(id));//send it back to be displayed
        });


        //add a tenant
        post("/tenant","application/json ",(request, response) -> {
            Tenant tenant = gson.fromJson(request.body(), Tenant.class);
            tenantDao.addTenant(tenant);
            response.status(201);
            return gson.toJson(tenant);
        });

        //get property manager by id
        get("/tenant/:id", "application/json", (req, res) -> { //accept a request in format JSON from an app
            int id = Integer.parseInt(req.params(":id"));
            return gson.toJson(tenantDao.getTenantById(id));//send it back to be displayed
        });

        //get tenant by id
        get("/tenants", "application/json", (req, res) -> { //accept a request in format JSON from an app
            return gson.toJson(tenantDao.getAllTenants());//send it back to be displayed
        });

        //add a property
        post("/property","application/json ",(request, response) -> {
            Property property = gson.fromJson(request.body(), Property.class);
            propertyDao.addProperty(property);
            response.status(201);
            return gson.toJson(property);
        });

        //get property by id
        get("/property/:id", "application/json", (req, res) -> { //accept a request in format JSON from an app
            int id = Integer.parseInt(req.params(":id"));
            return gson.toJson(propertyDao.getPropertyById(id));//send it back to be displayed
        });

        //get all properties
        get("/property", "application/json", (req, res) -> { //accept a request in format JSON from an app
            return gson.toJson(propertyDao.getAllProperties());//send it back to be displayed
        });



        after((req,res)-> res.type("application/json"));
    }

}
