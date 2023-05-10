                    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package inventorysystem;

import java.sql.SQLException;

/**
 *
 * @author hermi
 */
public class InventorySystem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
//        Item item = new Item();
//        item.setLocationRelativeTo(null);
//        item.show();
          InventorySelect inv = new InventorySelect();
          inv.setLocationRelativeTo(null);
          inv.show();
          
    }
    
}
