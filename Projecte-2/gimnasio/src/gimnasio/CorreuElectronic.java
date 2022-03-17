
package gimnasio;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author DAM
 */
public class CorreuElectronic {

    private String email;

    /**
     *
     * @param email
     */
    public CorreuElectronic(String email) {
        this.email = email;
    }
    
    /**
     *
     * @return
     */
    public String getEmail(){
        return email;
    }
    
    /**
     *
     * @param email
     * @return
     */
    public boolean setEmail(String email){
        if(validarEmail(email)){
            this.email = email;
            return true;
        } else {
            return false;
        }
    }
    
    CorreuElectronic() {
        
    }

    /**
     *
     * @param email
     * @return
     */
    public boolean validarEmail(String email) {

        Pattern pattern = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

        Matcher mather = pattern.matcher(email);

        if (mather.find() == true) {
            System.out.println("El email ingresado es válido.");
        } else {
            System.out.println("El email ingresado es inválido.");
            return false;
        }
        return true;

    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return email;
    }

}
