/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.Manager;
import core.Publisher;
import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.storage.IMegaferiaStorage;
import java.util.regex.Pattern;

/**
 * Controlador para la creación y gestión de editoriales.
 * Refactorizado como instancia con inyección de dependencias.
 * 
 * @author dandr
 */
public class PublisherController extends BaseController {

    private static final Pattern NIT_PATTERN = Pattern.compile("\\d{3}\\.\\d{3}\\.\\d{3}-\\d");

    /**
     * Constructor que inyecta el almacenamiento.
     * 
     * @param storage El almacenamiento a utilizar
     */
    public PublisherController(IMegaferiaStorage storage) {
        super(storage);
    }

    /**
     * Crea una editorial con los datos especificados.
     * 
     * @param nit         NIT de la editorial
     * @param name        Nombre de la editorial
     * @param address     Dirección de la editorial
     * @param managerData Datos del gerente responsable
     * @return Respuesta indicando éxito o fallo
     */
    public Response createPublisher(String nit, String name, String address, String[] managerData) {
        try {
            // Validar NIT
            if (!NIT_PATTERN.matcher(nit).matches()) {
                return new Response("El NIT debe seguir el formato XXX.XXX.XXX-X", Status.BAD_REQUEST);
            }

            // Validar nombre
            if (name.trim().isEmpty()) {
                return new Response("El nombre no debe estar vacío", Status.BAD_REQUEST);
            }

            // Validar dirección
            if (address.trim().isEmpty()) {
                return new Response("La dirección no debe estar vacía", Status.BAD_REQUEST);
            }

            // Validar y obtener gerente
            if (managerData.length == 0 || managerData[0].trim().equals("Seleccione uno...")) {
                return new Response("Debe seleccionar un gerente", Status.BAD_REQUEST);
            }

            int managerId;
            try {
                managerId = Integer.parseInt(managerData[0]);
                if (managerId < 0) {
                    return new Response("El ID del gerente debe ser positivo", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException ex) {
                return new Response("El ID del gerente debe ser numérico", Status.BAD_REQUEST);
            }

            // Obtener gerente del almacenamiento
            Manager manager = storage.getManager(managerId);
            if (manager == null) {
                return new Response("El gerente con ID " + managerId + " no existe", Status.BAD_REQUEST);
            }

            // Crear y adicionar editorial
            Publisher publisher = new Publisher(nit, name, address, manager);
            if (!storage.addPublisher(publisher)) {
                return new Response("Una editorial con ese NIT ya existe", Status.BAD_REQUEST);
            }

            return new Response("Editorial creada exitosamente", Status.CREATED);

        } catch (Exception ex) {
            return new Response("Error inesperado: " + ex.getMessage(), Status.INTERNAL_SERVER_ERROR);
        }
    }
}
