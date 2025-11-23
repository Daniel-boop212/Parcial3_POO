/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models;

import core.controllers.utils.Response;
import java.util.ArrayList;
import core.Author;
import core.Narrator;
import core.Publisher;

/**
 *
 * @author dandr
 */
public interface BookService{
    
    Response createBook(String title, ArrayList<Author> authors, String isbn, String genre, String format, double value, Publisher publisher, int pages, int copies, String hyperlink, int duration, Narrator narrator);

}
