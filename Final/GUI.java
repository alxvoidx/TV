import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * File name: 		GUI.java
 *
 * @author ALEXANDER ORLOV     Input:		    /     Opis: 			Graphic interface to add, delete, update and invoke a other gui to control Tv's     Output:  	    GUI
 */
public class GUI extends JFrame implements ActionListener {

    /**
     * The panel
     */
    private JPanel jPanel;
    /**
     * The button add TV
     */
    private JButton jButtonAdd;
    /**
     * The button delete TV
     */
    private JButton jButtonDel;
    /**
     * The button update TV
     */
    private JButton jButtonUpd;

    /**
     * The button to start GUI to control selected TV
     */
    private JButton jButtonPult;
    /**
     * The Tv name text field
     */
    private JTextField TV_name;
    /**
     * The Tv options text field
     */
    private JTextField TV_options;
    /**
     * The table to show
     */
    private JTable jTable;
    /**
     * The MyModelTable extending class Table
     */
    private MyModelTable myModelTable;
    /**
     * The Combo box
     */
    private JComboBox<String> combo;
    /**
     * The Scroll pane
     */
    private JScrollPane scrollPane;
    /**
     * The Label
     */
    private JLabel label;
    /**
     * The Pult label
     */
    private JLabel pult_label;
    /**
     * The Combo item (choosing item from combo box)
     */
    private String combo_item = "TV";

    /**
     * Instantiates a new Gui
     *
     * @param n         Header of Window
     * @param arrayList the array list with TV's
     */
//    public static void main(String[] args) {
//        GUI gui = new GUI("MAIN MENU OF TV LIST");
//    }
    public GUI(String n, ArrayList<TV> arrayList)
    {
        super(n);
        JFrame.setDefaultLookAndFeelDecorated(true);

        jPanel = new JPanel();
        jButtonAdd = new JButton("ADD TV");
        jButtonDel = new JButton("Delete TV");
        jButtonUpd = new JButton("Update TV");
        jButtonPult = new JButton("Use PULT");
        TV_name = new JTextField(10);
        TV_options = new JTextField(20);
        TV_options.setVisible(false);
        label = new JLabel("No additional options needed");
        pult_label = new JLabel("");
        combo = new JComboBox<String>(new String[] { "TV", "PLAZMA", "PROJECTOR"});
        myModelTable = new MyModelTable(arrayList);
        jTable = new JTable(myModelTable);
        scrollPane = new JScrollPane(jTable);
        this.setLayout(new BorderLayout());
        this.add(scrollPane, BorderLayout.CENTER);
        jTable.setFillsViewportHeight(true);

        add(jPanel);

        /**
         * using lambdas and anonymous classes when adding ActionListeners to Buttons
         */
        jButtonAdd.addActionListener(
                e -> {
                    TV tv = null;
                    if(combo_item.equals("PLAZMA")) {
                        tv = new Plasma_TV(TV_name.getText(), Float.parseFloat(TV_options.getText()));
                    }
                    if(combo_item.equals("PROJECTOR")) {
                        tv = new TV_Projector(TV_name.getText(), Integer.parseInt(TV_options.getText()));
                    }
                    if(combo_item.equals("TV")) {
                        tv = new TV(TV_name.getText());
                    }
                    arrayList.add(tv);
                    myModelTable.addTV(tv);
                }
        );

        combo.addActionListener(
                e -> {
                    JComboBox box = (JComboBox)e.getSource();
                    combo_item = (String)box.getSelectedItem();
                    if(combo_item.equals("PLAZMA")) {
                        label.setText("Diagonal size: ");
                        TV_options.setVisible(true);
                        revalidate();
                        repaint();
                    }
                    if(combo_item.equals("PROJECTOR")) {
                        label.setText("Distance to the screen: ");
                        TV_options.setVisible(true);
                        revalidate();
                        repaint();
                    }
                    if(combo_item.equals("TV")) {
                        label.setText("No additional options needed");
                        TV_options.setVisible(false);
                        revalidate();
                        repaint();
                    }
                }
        );

        jButtonDel.addActionListener(
                e -> {
                    DefaultTableModel dtm = (DefaultTableModel) jTable.getModel();
                    int select = jTable.getSelectedRow();
                    if(select!=-1) {
                        dtm.removeRow(select);
                        arrayList.remove(select);
                    }
                    else{
                        pult_label.setText("TV not selected");
                    }
                }
        );

        jButtonUpd.addActionListener(
                e -> {
                    DefaultTableModel dtm = (DefaultTableModel) jTable.getModel();
                    int select= jTable.getSelectedRow();
                    if(select!=-1) {
                        dtm.removeRow(select);
                        arrayList.remove(select);
                        jButtonAdd.doClick();
                    }
                    else{
                        pult_label.setText("TV not selected");
                    }
                }
        );

        jButtonPult.addActionListener(
                e -> {
                    int select= jTable.getSelectedRow();
                    if(select!=-1) {
                        pult_label.setText("");
                        TV tv = arrayList.get(select);
                        new PultGUI(tv.getTV_name()+" TV PULT", tv);
                    }
                    else {
                        pult_label.setText("TV not selected");
                    }
                }
        );

        jPanel.add(new JLabel("TV NAME:"));
        jPanel.add(TV_name);
        jPanel.add(new JLabel("TV TYPE:"));
        jPanel.add(combo);
        jPanel.add(jButtonAdd);
        jPanel.add(label);
        jPanel.add(TV_options);
        jPanel.add(jButtonUpd);
        jPanel.add(scrollPane, BorderLayout.CENTER);
        jPanel.add(pult_label, BorderLayout.SOUTH);
        jPanel.add(jButtonPult, BorderLayout.SOUTH);
        jPanel.add(jButtonDel, BorderLayout.SOUTH);

        setVisible(true);
        setSize(500,600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        System.out.println("---------------\nStart GUI");
    }

    /**
     * Action performed
     *
     * @param e the e
     */
    @Override
    public void actionPerformed(ActionEvent e) {}
}
