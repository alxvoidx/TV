import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * File name: 		PultGUI.java
 *
 * @author ALEXANDER ORLOV     Input:		    /     Opis: 			Graphic interface to control selectrd Tv     Output:  	    GUI
 */
public class PultGUI extends JFrame implements ActionListener {
    /**
     * The Jpanel
     */
    private JPanel jPanel;
    /**
     * The button on and off TV
     */
    private JButton jButtonOnOff;
    /**
     * The button volume up
     */
    private JButton jButtonVolUp;
    /**
     * The button volume down
     */
    private JButton jButtonVolDown;
    /**
     * The button channel up
     */
    private JButton jButtonChUp;
    /**
     * The button channel down
     */
    private JButton jButtonChDown;
    /**
     * The Label
     */
    private JLabel label;

//    public static void main(String[] args) {
//        PultGUI pultGUI = new PultGUI("TV PULT", new TV("LG"));
//    }

    /**
     * Instantiates a new Pult gui
     *
     * @param n  Header of new Window
     * @param tv the instance of TV to control
     */
    public PultGUI(String n, TV tv) {
        super(n);
        JFrame.setDefaultLookAndFeelDecorated(true);

        jPanel = new JPanel();
        jButtonOnOff = new JButton("ON / OFF");
        jButtonVolUp = new JButton("Volume++");
        jButtonVolDown = new JButton("Volume--");
        jButtonChUp = new JButton("Channel++");
        jButtonChDown = new JButton("Channel--");
        label = new JLabel("touch me...");
        label.setVisible(true);
        this.setLayout(new GridLayout(1, 10, 0, 0));

        add(jPanel);
        jPanel.add(label);
        jPanel.add(jButtonOnOff);
        jPanel.add(jButtonVolUp);
        jPanel.add(jButtonVolDown);
        jPanel.add(jButtonChUp);
        jPanel.add(jButtonChDown);

        setVisible(true);
        setSize(150, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //System.out.println("\n---------------\nStart Pult_GUI\n---------------\n");

        jButtonOnOff.addActionListener(
                e -> {
                    tv.setON_OFF();
                    if(tv.isON_OFF()) label.setText("NOW TV IS ON");
                    else label.setText("NOW TV IS OFF");
                }
        );

        /**
         * using lambdas and anonymous classes when adding ActionListeners to Buttons
         */
        jButtonVolUp.addActionListener(
                e -> {
                    if (tv.isON_OFF()) {
                        int vol = tv.getVolume() + 1;
                        tv.setVolume(vol);
                        label.setText("Volume level: " + tv.getVolume());
                    }
                    else label.setText("TV IS OFF");
                }
        );

        jButtonVolDown.addActionListener(
                e -> {
                    if (tv.isON_OFF()) {
                        int vol = tv.getVolume() - 1;
                        tv.setVolume(vol);
                        label.setText("Volume level: " + tv.getVolume());
                    }
                    else label.setText("TV IS OFF");
                }
        );

        jButtonChUp.addActionListener(
                e -> {
                    if (tv.isON_OFF()) {
                        int channel = tv.getCurr_channel() + 1;
                        tv.setCurr_channel(channel);
                        label.setText("Channel : " + tv.getCurr_channel());
                    }
                    else label.setText("TV IS OFF");
                }
        );

        jButtonChDown.addActionListener(
                e -> {
                    if (tv.isON_OFF()) {
                        int channel = tv.getCurr_channel() - 1;
                        tv.setCurr_channel(channel);
                        label.setText("Channel : " + tv.getCurr_channel());
                    }
                    else label.setText("TV IS OFF");
                }
        );
    }

    /**
     * Action performed
     *
     * @param e the e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
