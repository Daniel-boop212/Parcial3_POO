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
public interface StandService {
    
    Response CreateStand(String id, String price);
}
