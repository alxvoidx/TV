/**
 * File name: 		SmthGoesWrongExeption.java
 *
 * @author ALEXANDER ORLOV     Create a new exeption class
 */
public class SmthGoesWrongExeption extends Exception {
    /**
     * Instantiates a new exeption.
     *
     * @param errorMessage the error message
     * @param e            the exeption
     */
    public SmthGoesWrongExeption(String errorMessage, Throwable e) {
        super(e);
        System.out.println(errorMessage);
    }
}