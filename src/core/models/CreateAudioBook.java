/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models;

import core.Audiobook;
import core.Author;
import core.Narrator;
import core.Publisher;
import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.storage.Storage;
import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 *
 * @author dandr
 */
public class CreateAudioBook implements BookService{
    
    @Override
    public Response createBook(String title, ArrayList<Author> authors, String isbn, String genre, String format, double valueDou, Publisher publisher, int pages, int copies, String hyperlink, int duration, Narrator narrator) {
        try{  
           Storage storage = Storage.getInstance();
            
                    
                if (!storage.addAudioBook(new Audiobook(title, authors, isbn, genre, format, valueDou, publisher, duration, narrator))) {
                    return new Response("An audio book with that isbn already exists", Status.BAD_REQUEST);
                }
                return new Response("audio book created successfully", Status.CREATED);
            
        }catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }
    
}
