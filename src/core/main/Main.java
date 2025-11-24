
package core.main;

import com.formdev.flatlaf.FlatDarkLaf;
import core.controllers.AuthorController;
import core.controllers.BookController;
import core.controllers.ManagerController;
import core.controllers.NarratorController;
import core.controllers.PublisherController;
import core.controllers.StandController;
import core.models.storage.Storage;
import core.views.MegaferiaFrame;
import javax.swing.UIManager;

/**
 * Clase principal de la aplicación.
 * Actúa como punto de composición de dependencias (IoC Container).
 * 
 * @author equipo-refactorización
 */
public class Main {

    public static void main(String args[]) {
        System.setProperty("flatlaf.useNativeLibrary", "false");

        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                // Crear la instancia única del almacenamiento (Singleton)
                Storage storage = Storage.getInstance();

                // Inyectar el almacenamiento en los controladores
                StandController standController = new StandController(storage);
                AuthorController authorController = new AuthorController(storage);
                ManagerController managerController = new ManagerController(storage);
                NarratorController narratorController = new NarratorController(storage);
                PublisherController publisherController = new PublisherController(storage);
                BookController bookController = new BookController(storage);

                // Crear la vista e inyectar los controladores
                MegaferiaFrame frame = new MegaferiaFrame(
                        standController,
                        authorController,
                        managerController,
                        narratorController,
                        publisherController,
                        bookController);

                frame.setVisible(true);
            }
        });
    }
}
