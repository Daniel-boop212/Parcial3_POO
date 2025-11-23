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
   
    private static final Pattern NIT_PATTERN = Pattern.compile("\\d{3}\\.\\d{3}\\.\\d{3}-\\d");
    
    @Override
    public Response createPublisher(String nit, String name, String address, String[] managerData) {
        try{
            if (!NIT_PATTERN.matcher(nit).matches()) {
                return new Response("NIT must follow XXX.XXX.XXX-X", Status.BAD_REQUEST);
            }
            
            if (managerData.length == 1 && managerData[0].trim().equals("Seleccione uno...")){
                return new Response("select a manager", Status.BAD_REQUEST);
            }
            int managerId;
            try {
                managerId = Integer.parseInt(managerData[0]);
                if (managerId < 0) {
                    return new Response("Id must be positive", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException ex) {
                return new Response("Id must be numeric", Status.BAD_REQUEST);
            }
            
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
