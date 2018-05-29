public class Enigma implements Encryption{
    SubstitutionCipher[] ciphers;
    private String message;
    public Enigma(){
        ciphers = new SubstitutionCipher[26];
        message = null;
    }
    
    public Enigma(String input){
        ciphers  = new SubstitutionCipher[26];
        this.message = input;
        for(int i = 0; i < ciphers.length; i++){
            ciphers[i] = new SubstitutionCipher(this.message);
        }
    }
    
    public String encrypt(){
        char[] characters = message.toCharArray();
        int rand1 = (int)(Math.random() * ciphers.length);
        int rand2 = (int)(Math.random() * ciphers.length);
        int rand3 = (int)(Math.random() * ciphers.length);
        int determinant;
        SubstitutionCipher chosenCipher;
        for(int i = 0; i < characters.length; i++){
            determinant = (int)(Math.random() * 3);
            switch(determinant){
                case 0:
                    chosenCipher = ciphers[rand1];
                    chosenCipher.setMessage(characters[i] + "");
                    break;
                case 1:
                    chosenCipher = ciphers[rand2];
                    chosenCipher.setMessage(characters[i] + "");
                    break;
                case 2:
                    chosenCipher = ciphers[rand3];
                    chosenCipher.setMessage(characters[i] + "");
                    break;
                default:
                    chosenCipher = new SubstitutionCipher();
            }
            characters[i] = chosenCipher.encrypt().charAt(0);
        }
        String s = "";
        for(char i : characters){
            s = s + i;
        }
        message = s;
        return message;
    }
    
    public void decrypt(){
        System.out.println("\n\n\t\tAbsolutely not");
    }
    
    public String toString(){
        return message;
    }
}