/**
 * File name: 		TV_Projector.java
 *
 * @author ALEXANDER ORLOV     Child-Class Extending Parent-Class TV
 */
public class TV_Projector extends TV{

    /**
     * The Length from user to screen of projector
     */
    private int length;
    /**
     * The Type of TV
     */
    private final String TYPE = "PROJECTOR";

    /**
     * Instantiates a new Tv projector.
     *
     * @param TV_name the tv name
     * @param length  the length from user to screen of projector
     */
    public TV_Projector(String TV_name, int length) {
        super(TV_name );
        this.length = length;
    }

    /**
     * Gets length from user to screen of projector
     *
     * @return the distance from user to screen of projector
     */
    public int getLength() {
        return length;
    }

    /**
     * Sets length from user to screen of projector
     *
     * @param length the distance from user to screen of projector
     */
    public void setLength(int length) {
        this.length = length;
    }

    /**
     * function whoami() is interface implementation, returning String with info about TV Projector.
     *
     * @return the string
     */
    @Override
    public String whoami(){
        return this.getTV_name() + " | " + TYPE + " | Distance to the screen: " + getLength() + "m";
    }

    /**
     * function optoins() is interface implementation, returning String only with type and distance from user to screen of projector
     *
     * @return the string
     */
    public String options() {
        return TYPE + " | Distance: " + getLength() + " m";
    }

}
