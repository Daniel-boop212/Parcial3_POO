/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models;

import core.Manager;
import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.storage.Storage;

/**
 *
 * @author dandr
 */
public class CreateManager implements PersonService{
    
    @Override
    public Response createPerson(int idInt, String firstname, String lastname) {  
        try{
            
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

    
