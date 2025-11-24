/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.models.storage.IMegaferiaStorage;

/**
 * Clase base abstracta para todos los controladores.
 * Proporciona inyección de dependencias del almacenamiento.
 */
public abstract class BaseController {

    protected IMegaferiaStorage storage;

    /**
     * Constructor que inyecta el almacenamiento.
     * 
     * @param storage El almacenamiento a utilizar
     */
    public BaseController(IMegaferiaStorage storage) {
        if (storage == null) {
            throw new IllegalArgumentException("El almacenamiento no puede ser nulo");
        }
        this.storage = storage;
    }

    /**
     * Obtiene el almacenamiento.
     * 
     * @return El almacenamiento inyectado
     */
    public IMegaferiaStorage getStorage() {
        return storage;
    }
}
