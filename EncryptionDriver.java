import java.io.*;
import java.util.*;
public class EncryptionDriver{
    public static void main(String args[]){
        Scanner test = new Scanner("Four score and seven years ago our fathers brought forth on " + 
                "this continent, a new nation, conceived in Liberty, and dedicated to the proposition that all men are created equal. " +
                "Now we are engaged in a great civil war, testing whether that nation, or any nation so conceived and so dedicated, can long endure. " + 
                "We are met on a great battle-field of that war. We have come to dedicate a portion of" + 
                "that field, as a final resting place for those who here gave their lives that that " + 
                "nation might live. It is altogether fitting and proper that we should do this. But, " +
                "in a larger sense, we can not dedicate -- we can not consecrate we can not hallow this" +
                "ground. The brave men, living and dead, who struggled here, have consecrated it, far " +
                "above our poor power to add or detract. The world will little note, nor long remember " +
                "what we say here, but it can never forget what they did here. It is for us the living, " +
                "rather, to be dedicated here to the unfinished work which they who fought here have thus " +
                "far so nobly advanced. It is rather for us to be here dedicated to the great task remaining" +
                "before us -- that from these honored dead we take increased devotion to that cause " +
                "for which they gave the last full measure of devotion, that we here highly resolve " +
                "that these dead shall not have died in vain -- that this nation, under God, shall " +
                "have a new birth of freedom -- and that government of the people, by the people, for" +
                "the people, shall not perish from the earth.");
        String testString = test.nextLine();
        //String testString = new String("I milked the cows I churned the butter I stored the cheese I baked the bread I brewed the tea I washed the clothes I dressed the children the cat meowed the dog barked the horse neighed the mouse squeaked the fly buzzed the goldfish living in a bowl stretched its jaws the door banged shut the stairs creaked the fridge hummed the curtains billowed up the pot boiled the gas hissed through the stove the tree branches heavy with snow crashed against the roof my heart beat loudly thud! thud! tiny beads of water grew folds I shed my skin");
        testString = testString.toUpperCase();
        System.out.println("ORIGINAL STRING\n\t" + testString + "\n");
        
        /* ------------------------------------------- */
        CaesarCipher test1 = new CaesarCipher(testString);
        // test1.printKey
        test1.encrypt();
        System.out.println("ENCRYPTED: " + test1);
        test1.decrypt();
        System.out.println("DECRYPT: " + test1);
        /* ------------------------------------------- */
        System.out.println("\n");

        SubstitutionCipher test2 = new SubstitutionCipher(testString);
        // test2.printKey();
        test2.encrypt();
        System.out.println("ENCRYPTED: " + test2);
        test2.decrypt();
        System.out.println("DECRYPT #1: " + test2);
        test2.decrypt2();
        System.out.println("DECRYPT #2: " + test2);
        /* ------------------------------------------- */
        System.out.println("\n");
        
        Enigma test3 = new Enigma(testString);
        test3.encrypt();
        System.out.println("DECRYPTED: " + test3);
    }
}