/**
 * File name: 		Plasma_TV.java
 *
 * @author ALEXANDER ORLOV     Child-Class Extending Parent-Class TV
 */
public class Plasma_TV extends TV{
    /**
     * The Dimension of diagonal Plazma TV
     */
    private float dimension;
    /**
     * The Type of TV
     */
    private final String TYPE = "PLAZMA";

    /**
     * Instantiates a new Plasma tv
     *
     * @param TV_name the tv name
     * @param dim     the dimension of diagonal
     */
    public Plasma_TV(String TV_name, float dim) {
        super(TV_name);
        this.dimension = dim;
    }

    /**
     * Gets dimension of diagonal
     *
     * @return the dimension
     */
    public float getDimension() {
        return dimension;
    }

    /**
     * Sets dimension
     *
     * @param dimension the dimension of diagonal Plazma TV
     */
    public void setDimension(float dimension) {
        this.dimension = dimension;
    }

    /**
     * function whoami() is interface implementation, returning String with info about Plazma TV.
     *
     * @return the string
     */
    @Override
    public String whoami(){
        return this.getTV_name() + " | " + TYPE + " | Diagonal size: " + getDimension() + " inches";
    }

    /**
     * function optoins() is interface implementation, returning String only with type and diagolan of Plazma TV
     *
     * @return the string
     */
    @Override
    public String options() {
        return TYPE + " | Diagonal: " + getDimension() + " inches";
    }
}
