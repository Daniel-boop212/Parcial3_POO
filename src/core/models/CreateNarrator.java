/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models;

import core.Narrator;
import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.storage.Storage;

/**
 *
 * @author dandr
 */
public class CreateNarrator implements PersonService{

    @Override
    public Response createPerson(int idInt, String firstname, String lastname) {
        try{
            
            
            Storage storage = Storage.getInstance();
            if (!storage.addNarrator(new Narrator(idInt, firstname, lastname))) {
                return new Response("A narrator with that id already exists", Status.BAD_REQUEST);
            }
            return new Response("Narrator created successfully", Status.CREATED);
        }catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }
    
}
