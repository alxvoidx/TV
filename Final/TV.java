/**
 *     File name: 		Plasma_TV.java
 *     @author  		ALEXANDER ORLOV
 *     Input:		    /
 *     Opis: 			Program to manage TV's and control each of them (from console or GUI)
 *     Output:  	    Status, properties of TV and control interface (to console or GUI)
**/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Main class for creating and managing TV implementations
 */
public class TV implements Options{
    /**
     * The On and off TV
     */
    private boolean ON_OFF;
    /**
     * The Current channel of TV
     */
    private int curr_channel;
    /**
     * The Tv name
     */
    private String TV_name;
    /**
     * The Current Volume of TV
     */
    private int volume;
    /**
     * The Type of TV
     */
    private final String TYPE = "SIMPLE LED TV";

    /**
     * Instantiates a new Tv
     *
     * @param TV_name the tv name
     */
    public TV(String TV_name) {
        this.ON_OFF = false;
        this.curr_channel = 1;
        this.TV_name = TV_name;
        this.volume = 5;
    }

    /**
     * function whoami() is interface implementation, returning String with info about TV.
     *
     * @return the string
     */
    @Override
    public String whoami() {
        return TV_name  + " | " + TYPE;
    }

    /**
     * function optoins() is interface implementation, returning String only with type of TV
     *
     * @return the string
     */
    @Override
    public String options() {
        return TYPE;
    }

    /**
     * Check еру TV is on(true) or off(false)
     *
     * @return the boolean
     */
    public boolean isON_OFF() {
        return ON_OFF;
    }

    /**
     * Gets curr channel
     *
     * @return the current channel
     */
    public int getCurr_channel() {
        return curr_channel;
    }

    /**
     * Gets tv name
     *
     * @return the tv name
     */
    public String getTV_name() {
        return TV_name;
    }

    /**
     * Gets volume
     *
     * @return the current volume
     */
    public int getVolume() {
        return volume;
    }

    /**
     * Sets TV on or off
     */
    public void setON_OFF() {
        if(this.ON_OFF == false) this.ON_OFF = true;
        else if(this.ON_OFF == true) this.ON_OFF = false;
    }

    /**
     * Sets curr channel
     *
     * @param curr_channel the channel to set
     */
    public void setCurr_channel(int curr_channel) {
        if(curr_channel==1000) this.curr_channel = 0;
        else if(curr_channel==-1) this.curr_channel = 999;
        else if(curr_channel<0) this.curr_channel = 0;
        else if(curr_channel>=1000) this.curr_channel = 999;
        else this.curr_channel = curr_channel;
    }

    /**
     * Sets tv name.
     *
     * @param TV_name the tv name to set
     */
    public void setTV_name(String TV_name) {
        this.TV_name = TV_name;
    }

    /**
     * Sets volume.
     *
     * @param volume the volume to set
     */
    public void setVolume(int volume) {
        if (volume<0) this.volume = 0;
        else if (volume>10) this.volume = 10;
        else this.volume = volume;
    }

    /**
     * Current Tv info to output to console
     */
    public void TV_curr_info(){
        System.out.println("TV model:" + getTV_name() + " | Is working: " + (this.isON_OFF()?"ON":"OFF") + " | Current channel: " + getCurr_channel() + " | Current volume: " + getVolume());
    }

    /**
     * The entry point of application
     *
     * @param args the input arguments
     * @throws IOException           the io exception
     * @throws SmthGoesWrongExeption extending class ioexeption
     */
    public static void main(String[] args) throws IOException, SmthGoesWrongExeption {
        ArrayList<TV> arrayList = new ArrayList<>();
        //new GUI("MAIN MENU OF TV LIST", arrayList);
        show_menu();
        main_menu(arrayList);
        System.out.print("Time to sleep");
    }

    /**
     * Main menu to manage TV's: create, delete, use
     *
     * @param arrayList the array list of TV's
     * @throws IOException           the io exception
     * @throws SmthGoesWrongExeption extending class ioexeption
     */
    private static void main_menu(ArrayList<TV> arrayList) throws IOException, SmthGoesWrongExeption {
        int ch;
        boolean exit = false;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        do {
            System.out.print("You choose: ");
            ch = Integer.parseInt(reader.readLine());
        } while (ch > 5 | ch < 0);
        do {
            try {
                switch (ch) {
                    case (0):
                        tv_list(arrayList);
                        break;
                    case (1):
                        int tv_type;
                        System.out.print("Choose a Type of new TV:\n[1] TV [2] Plasma [3] Projector: ");
                        tv_type = Integer.parseInt(reader.readLine());
                        if (tv_type < 1 | tv_type > 3) System.out.println("Wrong number!");
                        else {
                            String new_tv_name;
                            System.out.print("Set up a new TV name: ");
                            new_tv_name = reader.readLine();
                            add_tv(new_tv_name, arrayList, tv_type);
                            System.out.println("Add a new TV in list: " + new_tv_name);
                        }
                        break;
                    case (2):
                        tv_list(arrayList);
                        int to_remove;
                        System.out.print("Choose a № of TV to remove: ");
                        to_remove = Integer.parseInt(reader.readLine());
                        if (to_remove > arrayList.size() | to_remove < 1) System.out.println("Wrong number!");
                        else {
                            arrayList.remove(to_remove - 1);
                            System.out.println("TV was removed from list");
                        }
                        break;
                    case (3):
                        tv_list(arrayList);
                        int tv_num;
                        System.out.print("Choose a № of TV: ");
                        tv_num = Integer.parseInt(reader.readLine());
                        if (tv_num > arrayList.size() | tv_num < 1) System.out.println("Wrong number!");
                        else {
                            TV tv = choose_tv(tv_num, arrayList);
                            show_tv_menu(tv);
                            pult(tv);
                        }
                        break;
                    case (4):
                        new GUI("MAIN MENU OF TV LIST", arrayList);
                        break;
                    case (5):
                        exit = true;
                        return;
                    default:
                        break;
                }
            }catch(IOException e){
            throw new SmthGoesWrongExeption("Menu is broken :(", e);
            }
            show_menu();
            main_menu(arrayList);
        }while (exit);
    }

    /**
     * Pult to manage the choosen TV
     *
     * @param tv the tv to manage
     * @throws IOException the io exception
     */
    private static void pult(TV tv) throws IOException{
        int ch;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        do {
            System.out.print("You choose: ");
            ch = Integer.parseInt(reader.readLine());
        } while (ch > 9 | ch < 0);
        int vol;
        int channel;
        while (ch != 9) {
            switch (ch) {
                case (0):
                    tv.TV_curr_info();
                    break;
                case (1):
                    tv.setON_OFF();
                    System.out.println("TV is " + (tv.isON_OFF() ? "ON" : "OFF"));
                    break;
                case (2):
                    if (tv.isON_OFF()) {
                        vol = tv.getVolume() + 1;
                        tv.setVolume(vol);
                        System.out.println("Volume set up at " + tv.getVolume());
                    } else System.out.println("TV is OFF");
                    break;
                case (3):
                    if (tv.isON_OFF()) {
                        vol = tv.getVolume() - 1;
                        tv.setVolume(vol);
                        System.out.println("Volume set up on " + tv.getVolume());
                    } else System.out.println("TV is OFF");
                    break;
                case (4):
                    if (tv.isON_OFF()) {
                        channel = tv.getCurr_channel() - 1;
                        tv.setCurr_channel(channel);
                        System.out.println("Switch on a " + tv.getCurr_channel() + " channel");
                    } else System.out.println("TV is OFF");
                    break;
                case (5):
                    if (tv.isON_OFF()) {
                        channel = tv.getCurr_channel() + 1;
                        tv.setCurr_channel(channel);
                        System.out.println("Switch on a " + tv.getCurr_channel() + " channel");
                    } else System.out.println("TV is OFF");
                    break;
                case (6):
                    if (tv.isON_OFF()) {
                        System.out.print("Input № of channel: ");
                        channel = Integer.parseInt(reader.readLine());
                        tv.setCurr_channel(channel);
                        System.out.println("Switch on a " + tv.getCurr_channel() + " channel");
                    } else System.out.println("TV is OFF");
                    break;
                case (7):
                    show_tv_menu(tv);
                    break;
                case (8):
                    new PultGUI(tv.getTV_name()+" TV PULT", tv);
                	System.out.print("You choose: ");
                    break;
                default:
                    break;
            }
            System.out.print("You choose: ");
            ch = Integer.parseInt(reader.readLine());
        }
    }

    /**
     * Show choosen tv menu at console
     *
     * @param tv the tv's menu to show
     */
    private static void show_tv_menu(TV tv) {
        System.out.println("---------------\n" + tv.TV_name + " | " + tv.TYPE + " TV Menu:\n [0] Show TV status\n [1] ON/OFF TV\n [2] Volume UP\n [3] Volume DOWN\n [4] Previous channel\n [5] Next channel\n [6] Set channel \n [7] Show This Menu\n [8] Start Pult GUI\n [9] Exit\n ---------------\n");
    }

    /**
     * Show menu of managing TV's in console
     */
    private static void show_menu() {
        System.out.println("---------------\n TV Menu:\n [0] Show TV list\n [1] Add TV\n [2] Remove TV\n [3] Use TV\n [4] Start GUI\n [5] Exit\n ---------------\n");
    }

    /**
     * Choose TV from Array.
     *
     * @param num       the num of TV in array
     * @param arrayList the array list of Tv's
     * @return the instance of choosen TV from Array
     */
    private static TV choose_tv (int num, ArrayList<TV> arrayList) {
        return arrayList.get(num-1);
    }

    /**
     * Add TV to Array
     *
     * @param name      the name of new TV
     * @param arrayList the array list to add
     * @param tv_type   the tv type
     * @throws IOException           the io exception
     * @throws SmthGoesWrongExeption extending class ioexeption
     */
    public static void add_tv (String name, ArrayList<TV> arrayList,int tv_type) throws IOException, SmthGoesWrongExeption {
        try {
            if (tv_type == 1) {
                arrayList.add(new TV(name));
            }
            if (tv_type == 2) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                System.out.print("Input a diagonal size of TV (in inches): ");
                float dim = Float.parseFloat(reader.readLine());
                arrayList.add(new Plasma_TV(name, dim));
            }
            if (tv_type == 3) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                System.out.print("Input a distance to the screen (in meters): ");
                int dis = Integer.parseInt(reader.readLine());
                arrayList.add(new TV_Projector(name, dis));
            }
        } catch(IOException e) {
            throw new SmthGoesWrongExeption("Something Goes Wrong at adding TV process...", e);
        }
    }

    /**
     * Show Tv list from Array to console
     *
     * @param arrayList the array list of exiting TV's
     */
    private static void tv_list(ArrayList<TV> arrayList) {
        int list_size = arrayList.size();
        if(list_size==0) System.out.println("You don't have a TV");
        else
        {
            System.out.println("My TV's:");
            for (int i = 0; i < arrayList.size(); i++)
            {
                System.out.println("[" + (i + 1) + "] " + arrayList.get(i).whoami());
            }
        }
    }
}
