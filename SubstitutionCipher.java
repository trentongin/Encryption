import java.util.*;
import java.io.*;
public class SubstitutionCipher implements Encryption{
    private char[] encryptKey = new char[52];
    private String message;
    public char[] mostUsed = {'E', 'A', 'R', 'I', 'O', 'T', 'N', 'S', 'L', 'C', 'U', 'D',
            'P', 'M', 'H', 'G', 'B', 'F', 'Y', 'W', 'K', 'V', 'X', 'Z', 'J', 'Q'};
    public double[] percentIndex = {11.1607, 8.4966, 7.5809, 7.5448, 7.1635, 6.9509, 
            6.6544, 5.7351, 5.4893, 4.5388, 3.6308, 3.3844, 3.1671, 3.0129, 3.0034, 2.4705, 
            2.0720, 1.8121, 1.7779, 1.2899, 1.1016, 1.0074, 0.2902, 0.2722, 0.1965, 0.1962};

    public SubstitutionCipher(){
        message = null;
        encryptKey = alphabet;
    }

    public SubstitutionCipher(String input){
        message = input;
        String[] keyArray = new String[26];
        ArrayList<Integer> takenValues = new ArrayList<Integer>();
        for(int i = 0; i < 26; i++){
            int randInd = (int)(Math.random() * 26);
            // Check with takenValues
            for(int c = 0; c < takenValues.size(); c++){
                while(randInd == takenValues.get(c)){
                    randInd = (int)(Math.random() * 26);
                    c = 0;
                }
            }
            takenValues.add(randInd);
            encryptKey[i] = alphabet[randInd];
        }
    }

    public String encrypt(){
        String s = "";
        char[] characters = message.toCharArray();
        for(int i = 0; i < characters.length; i++){
            for(int c = 0; c < alphabet.length; c++){
                if(characters[i] == alphabet[c]){
                    characters[i] = encryptKey[c];
                    break;
                }
            }
            s = s + characters[i];
        }
        message = s;
        return message;
    }

    public void decrypt(){
        char[] characters = message.toCharArray();
        int[] count = new int[alphabet.length];
        for(int i = 0; i < alphabet.length; i++){
            char letter = alphabet[i];
            for(int c = 0; c < characters.length; c++){
                if(characters[c] == letter){
                    count[i]++;
                }
            }
        }
        int max = findMax(characters, count);
        int usageIndex = 0;
        int selectedChar = 0;
        for(int i = 0; i < count.length; i++){
            if(count[i] == max){
                char letter = characters[i];
                for(int c = 0; c < characters.length; c++){
                    if(characters[c] == alphabet[i] && (characters[c] != ' ' ||
                    characters[c] != ',' || characters[c] != '-' ||
                    characters[c] != '.' || characters[c] != ';')){
                        selectedChar = c;
                        break;
                    }
                }
                message = message.replace(message.charAt(selectedChar), mostUsed[usageIndex]);
                count[i] = Integer.MIN_VALUE;
                max = findMax(characters, count);
                usageIndex++;
                i = 0;
            }
        }
    }

    public void decrypt2(){
        char[] characters = message.toCharArray();
        int[] count = new int[alphabet.length];
        char[] decryptedChars = new char[characters.length];
        for(int i = 0; i < alphabet.length; i++){
            char letter = alphabet[i];
            for(int c = 0; c < characters.length; c++){
                if(characters[c] == letter){
                    count[i]++;
                }
            }
        }
        double max = -1, min = Integer.MAX_VALUE;
        char letterMin = ' ', letterMax = ' ';
        double minFloor, maxFloor, lesserDist;
        for(int i = 0; i < count.length; i++){
            double ratio = (double)(count[i]) / (characters.length) * 100;
            // System.out.println(alphabet[i] + ": " + ratio);
            for(int c = percentIndex.length - 1; c >= 0; c--){
                if(ratio == percentIndex[c]){ // #1
                    decryptedChars[i] = mostUsed[c];
                    // System.out.println("#1 is happening");
                }else if(ratio > percentIndex[0]){ // #2
                    decryptedChars[i] = mostUsed[0];
                    // System.out.println("#2 is happening");
                }else if(ratio < percentIndex[percentIndex.length-1]){ // #3
                    decryptedChars[i] = mostUsed[mostUsed.length-1];
                    // System.out.println("#3 is happening");
                }else{ // #4
                    // System.out.println("#4 is happening");
                    for(int r = 1; r < percentIndex.length; r++){
                        if(ratio > percentIndex[r] && ratio < percentIndex[r - 1]){
                            min = percentIndex[r];
                            letterMin = mostUsed[r];
                            max = percentIndex[r - 1];
                            letterMax = mostUsed[r - 1];
                            // System.out.println(min + "\t" + max);
                            break;
                        }
                    }
                    minFloor = Math.abs(ratio - min);
                    maxFloor = Math.abs(ratio - max);
                    // System.out.println(minFloor + "\t" + maxFloor);
                    if(minFloor < maxFloor){
                        decryptedChars[i] = letterMin;
                        // System.out.println("MIN: " + minFloor + "\n" + letterMin + "\n");
                    }else{
                        decryptedChars[i] = letterMax;
                        // System.out.println("MAX: " + maxFloor + "\n" + letterMax + "\n");
                    }
                }
                break;
            }
        }
        for(int i = 0; i < characters.length; i++){
            char letter = characters[i];
            for(int c = 0; c < alphabet.length; c++){
                if(alphabet[c] == letter){
                    characters[i] = decryptedChars[c];
                }
            }
        }
        String s = "";
        for(char c : characters){
            s = s + c;
        }
        message = s;
    }

    public int findMax(char[] characters, int[] count){
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < count.length; i++){
            if(count[i] > max){
                max = count[i];
            }
        }
        return max;
    }

    public void reset(){
        Scanner keyInput = new Scanner(System.in);
        char changeFrom = keyInput.next().charAt(0);
        char changeTo = keyInput.next().charAt(0);
        for(int i = 0; i < message.length(); i++){
            if(message.charAt(i) == changeFrom){
                message = message.replace(message.charAt(i), changeTo);
            }
        }
    }
    
    public void setMessage(String message){
        this.message = message;
    }

    public void printKey(){
        String s = "";
        for(char c : encryptKey){
            s = s + c + "  ";
        }
        System.out.println(s);
    }

    public String toString(){
        return message;
    }
}