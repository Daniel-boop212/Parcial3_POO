/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.Stand;
import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.storage.Storage;



/**
 *
 * @author dandr
 */
public class StandController {

    public static Response CreateStand(String id, String price) {  
        try{
            int idInt;
            double priceDou;
            try {
                idInt = Integer.parseInt(id.trim());
                if (idInt < 0) {
                    return new Response("Id must be positive", Status.BAD_REQUEST);
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
            Storage storage = Storage.getInstance();
            if (!storage.addStand(new Stand(idInt, priceDou))) {
                return new Response("A stand with that id already exists", Status.BAD_REQUEST);
            }
            return new Response("Stand created successfully", Status.CREATED);
        }catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
        
    }
}
