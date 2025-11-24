/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.Stand;
import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.storage.IMegaferiaStorage;

/**
 * Controlador para la creación y gestión de stands.
 * Refactorizado como instancia con inyección de dependencias.
 * 
 * @author dandr
 */
public class StandController extends BaseController {

    /**
     * Constructor que inyecta el almacenamiento.
     * 
     * @param storage El almacenamiento a utilizar
     */
    public StandController(IMegaferiaStorage storage) {
        super(storage);
    }

    /**
     * Crea un stand con el ID y precio especificados.
     * 
     * @param id    ID del stand
     * @param price Precio del stand
     * @return Respuesta indicando éxito o fallo
     */
    public Response createStand(String id, String price) {
        int idInt;
        double priceDou;

        try {
            // Validar ID
            try {
                idInt = Integer.parseInt(id.trim());
                if (idInt < 0) {
                    return new Response("El ID debe ser positivo", Status.BAD_REQUEST);
                }
                if (id.length() > 15) {
                    return new Response("El ID debe tener 15 dígitos o menos", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException ex) {
                return new Response("El ID debe ser numérico", Status.BAD_REQUEST);
            }

            // Validar precio
            try {
                priceDou = Double.parseDouble(price);
                if (priceDou <= 0) {
                    return new Response("El precio debe ser positivo", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException ex) {
                return new Response("El precio debe ser numérico", Status.BAD_REQUEST);
            }

            // Crear y adicionar stand
            Stand stand = new Stand(idInt, priceDou);
            if (!storage.addStand(stand)) {
                return new Response("Un stand con ese ID ya existe", Status.BAD_REQUEST);
            }

            return new Response("Stand creado exitosamente", Status.CREATED);

        } catch (Exception ex) {
            return new Response("Error inesperado: " + ex.getMessage(), Status.INTERNAL_SERVER_ERROR);
        }
    }
}
