/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.CreateStand;



/**
 *
 * @author dandr
 */
public class StandController {
    
    public static Response CreateStand(String id, String price) {  
        int idInt;
        double priceDou;
        try{
            
            try {
                idInt = Integer.parseInt(id.trim());
                if (idInt < 0) {
                    return new Response("Id must be positive", Status.BAD_REQUEST);
                }
                if (id.length() > 15){
                    return new Response("Id must have 15 digit or less", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException ex) {
                return new Response("Id must be numeric", Status.BAD_REQUEST);
            }
            
            try{
                priceDou = Double.parseDouble(price);
                if (priceDou < 0) {
                    return new Response("price must be positive", Status.BAD_REQUEST);
                }
            }catch (NumberFormatException ex) {
                return new Response("price must be numeric", Status.BAD_REQUEST);
            }
        }catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
        CreateStand create = new CreateStand();
        return create.CreateStand(idInt, priceDou);            
    }
}
