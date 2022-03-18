
package gimnasio;

/**
 *
 * @author DAM
 */
public class Telefon {

    private String telefon;

    /**
     *
     * @param telefon
     */
    public Telefon(String telefon) {
        this.telefon = telefon;
    }
    
    /**
     *
     * @return
     */
    public String getTelefon(){
        return telefon;
    }
    
    /**
     *
     * @param telefon
     * @return
     */
    public boolean setTelefon(String telefon){
        if(validarTelefon(telefon)){
            this.telefon = telefon;
            return true;
        } else{
            return false;
        }
    }
    

    Telefon() {
        
    }

    /**
     *
     * @param telefon
     * @return
     */
    public boolean validarTelefon(String telefon) {
        
        if(telefon.length()!=9){
            System.out.println("El telefon ha de tenir 9 digits");
            return false;
        } else{
            for(int i=0; i<telefon.length();i++){
                if(!(telefon.charAt(i)>='0' && telefon.charAt(i)<='9')){
                    System.out.println("Tots els digits han de ser numèrics");
                    return false;
                }
            }
        }
        return true;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return telefon;    }
  
}
