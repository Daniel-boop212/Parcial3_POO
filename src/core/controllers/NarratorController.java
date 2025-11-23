/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controllers.utils.Response;
import core.models.CreateNarrator;

/**
 *
 * @author dandr
 */
public class NarratorController {
    public static Response createNarrator(String id, String firstname, String lastname) {  
        CreateNarrator create = new CreateNarrator();
        return create.createPerson(id, firstname, lastname); 
        
    }
}
