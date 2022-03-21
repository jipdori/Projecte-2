
package gimnasio;

import java.math.BigInteger;

/**
 *
 * @author DAM
 */
public class CompteBancari {
    
    private String CCC;

    /**
     *
     * @param CCC
     */
    public CompteBancari(String CCC) {
        this.CCC = CCC;
    }

    CompteBancari() {
        
    }

    /**
     *
     * @return
     */
    public String getCCC() {
        return CCC;
    }

    /**
     *
     * @param CCC
     * @return
     */
    public boolean setCCC(String CCC) {
        if(validarCCC(CCC)){
            this.CCC = CCC;
            return true;
        } else{
            return false;
        }
        
    }

    /**
     *
     * @param CCC
     * @return
     */
    public boolean validarCCC(String CCC) {
        
        boolean valid = false;
        int i = 2;
        int ASCII = 0;
        int resto = 0;
        int dc = 0;
        String cadenaDc = "";
        BigInteger comptenum = new BigInteger("0");
        BigInteger mode = new BigInteger("97");
        
        if(CCC.length() == 24 && CCC.substring(0,1).toUpperCase().equals("E") && CCC.substring(1,2).toUpperCase().equals("S")){
            
            do {
                ASCII = CCC.codePointAt(i);
                valid = (ASCII > 47 && ASCII < 58);
                i++;
            }
            while(i < CCC.length() && valid);
            
            if(valid){
                comptenum = new BigInteger(CCC.substring(4,24) + "142800");
                resto = comptenum.mod(mode).intValue();
                dc = 98 - resto;
                cadenaDc = String.valueOf(dc);
            }
            
            if(dc < 10){
                cadenaDc = "0" + cadenaDc;
            }
            
            if(CCC.substring(2,4).equals(cadenaDc)){
                valid = true;
            }else{
                valid = false;
            }
        }    
        
        return valid;
        
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return CCC;
    }

    
}
