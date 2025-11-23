/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controllers.utils.Response;
import core.models.CreateManager;

/**
 *
 * @author dandr
 */
public class ManagerController {
    public static Response createManager(String id, String firstname, String lastname){
        CreateManager create = new CreateManager();
        return create.createPerson(id, firstname, lastname); 
    }
}
