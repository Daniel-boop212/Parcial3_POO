/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models;

import core.Publisher;
import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.storage.Storage;
import java.util.regex.Pattern;

/**
 *
 * @author dandr
 */
public class CreatePublisher implements PublisherService{
   
    
    @Override
    public Response createPublisher(String nit, String name, String address, int managerId) {
        try{
            
            
            Storage storage = Storage.getInstance();  
            
            if (!storage.addPublisher(new Publisher(nit, name, address, storage.getManager(managerId)))) {
                return new Response("A publisher with that nit nalready exists", Status.BAD_REQUEST);
            }
            return new Response("Publisher created successfully", Status.CREATED);
        }catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }
    
}
