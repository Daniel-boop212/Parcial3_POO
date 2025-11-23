/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controllers.utils.Response;
import core.models.CreatePublisher;
import java.util.regex.Pattern;

/**
 *
 * @author dandr
 */
public class PublisherController {
    
    private static final Pattern NIT_PATTERN = Pattern.compile("\\d{3}\\.\\d{3}\\.\\d{3}-\\d");
    
    public static Response createPublisher(String nit, String name, String address, String[] managerData){      
        CreatePublisher create = new CreatePublisher();
        return create.createPublisher(nit, name, address, managerData); 
        
    }
        
        
    
}
