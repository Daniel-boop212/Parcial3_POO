/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models;

import core.Stand;
import core.models.storage.Storage;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author dandr
 */
public class StandTable {
    public static void updateStandTableM(DefaultTableModel model){ 
        Storage storage = Storage.getInstance();
            
            for (Stand stand : storage.getStands()) {
            String publishers = "";
            if (stand.getPublisherQuantity() > 0) {
                publishers += stand.getPublishers().get(0).getName();
                for (int i = 1; i < stand.getPublisherQuantity(); i++) {
                    publishers += (", " + stand.getPublishers().get(i).getName());
                }
            }
            model.addRow(new Object[]{stand.getId(), stand.getPrice(), stand.getPublisherQuantity() > 0 ? "Si" : "No", publishers});
        }
    }
}
