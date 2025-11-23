/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.CreatePublisher;
import java.util.regex.Pattern;

/**
 *
 * @author dandr
 */
public class PublisherController {
    
    private static final Pattern NIT_PATTERN = Pattern.compile("\\d{3}\\.\\d{3}\\.\\d{3}-\\d");
    
    public static Response createPublisher(String nit, String name, String address, String[] managerData){ 
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
        CreatePublisher create = new CreatePublisher();
        return create.createPublisher(nit, name, address, managerId); 
        
    }
        
        
    
}
