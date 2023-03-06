import javax.swing.table.*;
import java.util.ArrayList;

/**
 * File name: 		MyModelTable.java
 *
 * @author ALEXANDER ORLOV     Subclass extending DefaultTableModel
 */
public class MyModelTable extends DefaultTableModel {
    /**
     * Instantiates a new MyModeTable.
     *
     * @param arrayList the array with List of TV's
     */
    public MyModelTable(ArrayList<TV> arrayList) {
        super();
        /*
          adding header of the table
         */
        addColumn("TV NAME");
        addColumn("OPTIONS");
        /*
          Adding all existing TVs to the table
         */
        for (TV tv : arrayList) {
            addTV(tv);
        }

    }

    /**
     * Add tv to Table
     *
     * @param tv the tv to add to table
     */
    public void addTV(TV tv){
        Object[] row = new Object[]{tv.getTV_name(), tv.options()};
        addRow(row);
    }
}
