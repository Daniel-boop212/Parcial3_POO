/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storage;

import core.Audiobook;
import core.Author;
import core.Book;
import core.DigitalBook;
import core.Manager;
import core.Narrator;
import core.Person;
import core.PrintedBook;
import core.Publisher;
import core.Stand;
import java.util.ArrayList;

/**
 * Interfaz que abstrae la capa de almacenamiento.
 * Permite que los controladores dependan de esta abstracción en lugar de la
 * clase Storage concreta.
 */
public interface IMegaferiaStorage {

    // Stand operations
    boolean addStand(Stand stand);

    ArrayList<Stand> getStands();

    // Person operations
    boolean addPerson(Person person);

    // Author operations
    boolean addAuthor(Author author);

    ArrayList<Author> getAutores();

    // Manager operations
    boolean addManager(Manager manager);

    ArrayList<Manager> getGerentes();

    Manager getManager(int id);

    // Narrator operations
    boolean addNarrator(Narrator narrator);

    ArrayList<Narrator> getNarradores();

    // Publisher operations
    boolean addPublisher(Publisher publisher);

    ArrayList<Publisher> getEditoriales();

    // PrintedBook operations
    boolean addPrintedBook(PrintedBook printedBook);

    ArrayList<PrintedBook> getLibrosImpresos();

    // DigitalBook operations
    boolean addDigitalBook(DigitalBook digitalBook);

    ArrayList<DigitalBook> getLibrosDigital();

    // Audiobook operations
    boolean addAudioBook(Audiobook audiobook);

    ArrayList<Audiobook> getAudioLibros();

    // General book operations
    ArrayList<Book> getLibros();
}
