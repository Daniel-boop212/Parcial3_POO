/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.storage.Storage;
import core.Author;

/**
 *
 * @author dandr
 */
public class CreateAuthor implements PersonService{
    
    @Override
    public Response createPerson(int idInt, String firstname, String lastname){
        try{
            
            Storage storage = Storage.getInstance();
            if (!storage.addAuthor(new Author(idInt, firstname, lastname))) {
                return new Response("An author with that id already exists", Status.BAD_REQUEST);
            }
            return new Response("author created successfully", Status.CREATED);
        }catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }
    
}
