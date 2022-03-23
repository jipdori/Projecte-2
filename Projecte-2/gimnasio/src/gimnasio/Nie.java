
package gimnasio;

class Nie {
    
    private String nie;
    private int numero;
    private char lletra;
    
    Nie(String nie) {
        this.nie = nie;
    }

    Nie() {
        
    }

    public boolean validarNie(String nie){
        
        boolean esValid = false;
        int i=0;
        int caracterASCII = 0;
        char lletra=' ';
        int mynie = 0;
        int resto;
        char[] assignacioLletra = {'T','R','W','A','G','M','Y','F','P','D','X','B','N','J','Z','S','Q','V','H','L','C','K','E'};
        
        if (nie.length() == 9 && Character.isLetter(nie.charAt(8))){
            
            do{
                caracterASCII = nie.codePointAt(i);
                esValid = (caracterASCII > 47 && caracterASCII < 58);
                i++;
            }
            while(i < nie.length() - 1 && esValid);
            
        }
        
        if(esValid){
            lletra = Character.toUpperCase(nie.charAt(8));
            mynie = Integer.parseInt(nie.substring(0,8));
            resto= mynie % 23;
            esValid = (lletra == assignacioLletra[resto]);
        }

        return esValid;
    
    }

    public String getNie() {
        return nie;
    }

    public void setNie(String nie) {
        this.nie = nie;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setLletra(char lletra) {
        this.lletra = lletra;
    }

    @Override
    public String toString() {
        return nie + numero + lletra;
    }

}
