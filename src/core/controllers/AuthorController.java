/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.Author;
import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.storage.IMegaferiaStorage;

/**
 * Controlador para la creación y gestión de autores.
 * Refactorizado como instancia con inyección de dependencias.
 * 
 * @author dandr
 */
public class AuthorController extends BaseController {

    /**
     * Constructor que inyecta el almacenamiento.
     * 
     * @param storage El almacenamiento a utilizar
     */
    public AuthorController(IMegaferiaStorage storage) {
        super(storage);
    }

    /**
     * Crea un autor con los datos especificados.
     * 
     * @param id        ID del autor
     * @param firstname Nombre del autor
     * @param lastname  Apellido del autor
     * @return Respuesta indicando éxito o fallo
     */
    public Response createAuthor(String id, String firstname, String lastname) {
        try {
            // Validar ID
            int idInt;
            try {
                idInt = Integer.parseInt(id.trim());
                if (id.length() > 15) {
                    return new Response("El ID debe tener 15 dígitos o menos", Status.BAD_REQUEST);
                }
                if (idInt < 0) {
                    return new Response("El ID debe ser positivo", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException ex) {
                return new Response("El ID debe ser numérico", Status.BAD_REQUEST);
            }

            // Validar nombre
            if (firstname.trim().isEmpty()) {
                return new Response("El nombre no debe estar vacío", Status.BAD_REQUEST);
            }

            // Validar apellido
            if (lastname.trim().isEmpty()) {
                return new Response("El apellido no debe estar vacío", Status.BAD_REQUEST);
            }

            // Crear y adicionar autor
            Author author = new Author(idInt, firstname, lastname);
            if (!storage.addAuthor(author)) {
                return new Response("Un autor con ese ID ya existe", Status.BAD_REQUEST);
            }

            return new Response("Autor creado exitosamente", Status.CREATED);

        } catch (Exception ex) {
            return new Response("Error inesperado: " + ex.getMessage(), Status.INTERNAL_SERVER_ERROR);
        }
    }
}
