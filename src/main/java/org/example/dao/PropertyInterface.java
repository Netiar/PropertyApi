package org.example.dao;

import org.example.models.Property;
import org.example.models.PropertyManager;

import java.util.List;

public interface PropertyInterface {

    //Create
    void addProperty(PropertyManager propertymanager);

    //Read
    Property getPropertyById(int id);
    List<Property> getAllProperties();
    //Update


    //Delete
}