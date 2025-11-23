/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models;

import core.Stand;
import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.storage.Storage;

/**
 *
 * @author dandr
 */
public class CreateStand implements StandService{

    @Override
    public Response CreateStand(int id, double price) {
        
            Storage storage = Storage.getInstance();
            if (!storage.addStand(new Stand(id, price))) {
                return new Response("A stand with that id already exists", Status.BAD_REQUEST);
            }
            return new Response("Stand created successfully", Status.CREATED);
        
    }
    
}
