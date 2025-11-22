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
 *
 * @author edangulo
 */
public class Storage {
    
    // Instancia Singleton
    private static Storage instance;
    
    // Atributos del Storage
    private ArrayList<Person> persons;
    private ArrayList<Stand> stands;
    private ArrayList<Author> autores;
    private ArrayList<Manager> gerentes;
    private ArrayList<Narrator> narradores;
    private ArrayList<Publisher> editoriales;
    private ArrayList<PrintedBook> librosImpresos;
    private ArrayList<DigitalBook> librosDigital;
    private ArrayList<Audiobook> audioLibros;
    private ArrayList<Book> libros;
    
    private Storage() {
        this.persons = new ArrayList<>();
        this.stands = new ArrayList<>();
        this.autores = new ArrayList<>();
        this.gerentes = new ArrayList<>();
        this.narradores = new ArrayList<>();
        this.editoriales = new ArrayList<>();
        this.librosImpresos = new ArrayList<>();
        this.librosDigital = new ArrayList<>();
        this.audioLibros = new ArrayList<>();
        this.libros = new ArrayList<>();
    }
    
    public static Storage getInstance() {
        if (instance == null) {
            instance = new Storage();
        }
        return instance;
    }
    
    public boolean addPerson(Person person) {
        for (Person p : this.persons) {
            if (p.getId() == person.getId()) {
                return false;
            }
        }
        this.persons.add(person);
        return true;
    }

    public boolean addStand(Stand stand) {
        for (Stand s : this.stands) {
            if (s.getId() == stand.getId()) {
                return false;
            }
        }
        this.stands.add(stand);
        return true;
    }

    public ArrayList<Stand> getStands() {
        return stands;
    }
    
    

    public boolean addAuthor(Author author) {
        for (Author a: this.autores) {
            if (a.getId() == author.getId()) {
                return false;
            }
        }
        this.autores.add(author);
        return true;
    }

    public ArrayList<Author> getAutores() {
        return this.autores;
    }

    public boolean addManager(Manager manager) {
        for (Manager m: this.gerentes) {
            if (m.getId() == manager.getId()) {
                return false;
            }
        }
        this.gerentes.add(manager);
        return true;
    }

    public ArrayList<Manager> getGerentes() {
        return gerentes;
    }
    
    public Manager getManager(int id){
        for (Manager m: this.gerentes) {
            if (m.getId() == id) {
                return m;
            }
        }
        return null;
    }

    public boolean addNarrator(Narrator narrator) {
        for (Narrator n: this.narradores) {
            if (n.getId() == narrator.getId()) {
                return false;
            }
        }
        this.narradores.add(narrator);
        return true;
    }

    public ArrayList<Narrator> getNarradores() {
        return narradores;
    }
    
    

    public boolean addPublisher(Publisher publisher) {
        for (Publisher p: this.editoriales) {
            if (p.getNit().equals(publisher.getNit())) {
                return false;
            }
        }
        this.editoriales.add(publisher);
        return true;
    }

    public ArrayList<Publisher> getEditoriales() {
        return editoriales;
    }
        

    public boolean addPrintedBook(PrintedBook printedBook) {
       for (PrintedBook p: this.librosImpresos) {
            if (p.getIsbn().equals(printedBook.getIsbn())) {
                return false;
            }
        }
        this.librosImpresos.add(printedBook);
        this.libros.add(printedBook);
        return true;
    }

    public boolean addDigitalBook(DigitalBook digitalBook) {
        for (DigitalBook p: this.librosDigital) {
            if (p.getIsbn().equals(digitalBook.getIsbn())) {
                return false;
            }
        }
        this.librosDigital.add(digitalBook);
        this.libros.add(digitalBook);
        return true;
    }

    public boolean addAudioBook(Audiobook audiobook) {
        for (Audiobook p: this.audioLibros) {
            if (p.getIsbn().equals(audiobook.getIsbn())) {
                return false;
            }
        }
        this.audioLibros.add(audiobook);
        this.libros.add(audiobook);
        return true;
    }

    public ArrayList<PrintedBook> getLibrosImpresos() {
        return librosImpresos;
    }

    public ArrayList<DigitalBook> getLibrosDigital() {
        return librosDigital;
    }

    public ArrayList<Audiobook> getAudioLibros() {
        return audioLibros;
    }

    public ArrayList<Book> getLibros() {
        return libros;
    }
    
    
    
    
    
}
