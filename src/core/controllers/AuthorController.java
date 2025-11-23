/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controllers.utils.Response;
import core.models.CreateAuthor;


/**
 *
 * @author dandr
 */
public class AuthorController {
    
    public static Response CreateAuthor(String id, String firstname, String lastname) {
        CreateAuthor create = new CreateAuthor();
        return create.createPerson(id, firstname, lastname);     
    }

}
