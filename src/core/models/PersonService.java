/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models;

import core.controllers.utils.Response;


/**
 *
 * @author dandr
 */
public interface PersonService {
    
    Response createPerson(String id, String firstname, String lastname);
        
}
