import java.io.*;
import java.util.*;
public class CaesarCipher implements Encryption{
    private char encryptKey[] = new char[26];
    private String message;
    private int shift;
    
    public CaesarCipher(){
        message = null;
        shift = 0;
        encryptKey = alphabet;
    }
    
    public CaesarCipher(String input){
        message = input;
        this.shift = ((int)(Math.random() * 25) + 1);
        int next = shift;
        for(int i = 0; i < 26; i++){
            if(next >= alphabet.length){
                next = 0;
            }
            encryptKey[i] = alphabet[next];
            next++;
        }
    }
    
    public String encrypt(){
        String s = "";
        char[] characters = message.toCharArray();
        for(int i = 0; i < characters.length; i++){
            for(int c = 0; c < encryptKey.length; c++){
                if(characters[i] == ' '){
                    s = s + ' ';
                    break;
                }
                if(characters[i]==(alphabet[c])){
                    s = s + encryptKey[c];
                    break;
                }
            }
        }
        message = s;
        return message;
    }
    
    public void decrypt(){
        String s = "";
        char[] characters = message.toCharArray();
        for(int i = 0; i < characters.length; i++){
            for(int c = 0; c < encryptKey.length; c++){
                if(characters[i] == ' ' ||
                characters[i] == '-' ||
                characters[i] == ',' ||
                characters[i] == '.' ||
                characters[i] == ';'){
                    s = s + characters[i];
                    break;
                }
                if(characters[i]==(encryptKey[c])){
                    s = s + alphabet[c];
                    break;
                }
            }
        }
        message = s;
    }
    
    public void decryptWithKey(){
        char[] characters = message.toCharArray();
        String decryptedMessage = "";
        for(int i = 0; i < characters.length; i++){
            char dec = ' ';
            for(int c = 0; c < encryptKey.length; c++){
                if(characters[i] == ' '){
                    dec = ' ';
                    break;
                }
                else if(characters[i] == encryptKey[c]){
                    dec = alphabet[c];
                    break;
                }
            }
            decryptedMessage = decryptedMessage + dec;
        }
        message = decryptedMessage.toString();
    }
    
    public void printKey(){
        String keyString = "";
        for(char r : encryptKey){
            keyString = keyString + r + "  ";
        }
        System.out.println(shift + "\n" + keyString);
    }
    
    public String toString(){
        return message;
    }
}