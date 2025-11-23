/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controllers.utils.Response;
import core.models.CreateStand;



/**
 *
 * @author dandr
 */
public class StandController {

    public static Response CreateStand(String id, String price) {  
        CreateStand create = new CreateStand();
        return create.CreateStand(id, price);            
    }
}
