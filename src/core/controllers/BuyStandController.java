/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import static core.models.BuyStand.buyStandM;

/**
 *
 * @author dandr
 */
public class BuyStandController {
    
    public static Response buyStand(String[] standIds, String[] publishersData){
        if (standIds.length == 1 && standIds[0].trim().equals("")){
                        return new Response("select at least 1 stand", Status.BAD_REQUEST);
            }
            if (publishersData.length == 1 && publishersData[0].trim().equals("")){
                        return new Response("select at least 1 publisher", Status.BAD_REQUEST);
            } 
           
            if (standIds.length == 1 && standIds[0].trim().equals("Seleccione uno...")){
                        return new Response("select at least 1 stand", Status.BAD_REQUEST);
            }
            if (publishersData.length == 1 && publishersData[0].trim().equals("Seleccione uno...")){
                        return new Response("select at least 1 publisher", Status.BAD_REQUEST);
            } 
            
        return buyStandM(standIds,publishersData);
    }
}
