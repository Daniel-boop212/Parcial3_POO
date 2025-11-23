/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models;

import core.Publisher;
import core.Stand;
import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.storage.Storage;
import java.util.ArrayList;

/**
 *
 * @author dandr
 */
public class BuyStand {
    public static Response buyStandM(String[] standIds, String[] publishersData){
        try{
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
            
            Storage storage = Storage.getInstance();
            
            ArrayList<Stand> stands = new ArrayList<>();
            for (String standId : standIds) {
                for (Stand stand : storage.getStands()) {
                    if (stand.getId() == Long.parseLong(standId)) {
                        stands.add(stand);
                    }
                }
            }
        
            ArrayList<Publisher> publishers = new ArrayList<>();
            for (String publisherData : publishersData) {
                String publisherNit = publisherData.split(" ")[1].replace("(", "").replace(")", "");
                for (Publisher publisher : storage.getEditoriales()) {
                    if (publisher.getNit().equals(publisherNit)) {
                        publishers.add(publisher);
                    }
                }
            }
            
            for (Stand stand : stands) {
                for (Publisher publisher : publishers) {
                    stand.addPublisher(publisher);
                    publisher.addStand(stand);
                }
            }
            return new Response("Stand/s bought successfully", Status.OK);
        }catch(Exception ex){
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }
}
