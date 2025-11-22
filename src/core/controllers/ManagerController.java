/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.Manager;
import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.storage.Storage;

/**
 *
 * @author dandr
 */
public class ManagerController {
    public static Response CreateManager(String id, String firstname, String lastname) {  
        try{
            int idInt;
            try {
                idInt = Integer.parseInt(id.trim());
                if (idInt < 0) {
                    return new Response("Id must be positive", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException ex) {
                return new Response("Id must be numeric", Status.BAD_REQUEST);
            }
            
            if (firstname.trim().equals("")) {
                return new Response("Firstname must be not empty", Status.BAD_REQUEST);
            }
            
            if (lastname.trim().equals("")) {
                return new Response("Lastname must be not empty", Status.BAD_REQUEST);
            }
            
            Storage storage = Storage.getInstance();
            if (!storage.addManager(new Manager(idInt, firstname, lastname))) {
                return new Response("A manager with that id already exists", Status.BAD_REQUEST);
            }
            return new Response("Manager created successfully", Status.CREATED);
        }catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
        
    }
}
