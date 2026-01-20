//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import java.util.*;

public class Main {
    public static void main(String[] args) {

        System.out.println("Enter sequence to encode");
        Scanner scan = new Scanner(System.in);
        String encodeString = scan.nextLine().toLowerCase();

        String encoded = "";
        String encodedFinal = "";
        for (int i = 0; i < encodeString.length(); i++) {
            if (encodeString.substring(i,i+1).equals(" "))
            {
                encodedFinal += " ";
            }
            else
            {
                encoded = Switchboard(encodeString.substring(i, i + 1));
                encoded = Rotor(encoded);
                encoded = Reflector(encoded);
                encoded = BackRotor(encoded);
                encodedFinal += Switchboard(encoded);
            }
        }
        System.out.println(encodedFinal);
    }
    //Switchboard
    public static String Switchboard(String encodeString)
    {
        String switchEncoded = "";
        String[] switch1 = {"b", "c", "d", "e", "k", "m", "o", "p", "u", "g"};
        String[] switch2 = {"q", "r", "i", "j", "w", "t", "s", "x", "z", "h"};
        boolean switched = false;
        for (int i = 0; i < switch1.length; i += 1) {
            if (encodeString.equals(switch1[i])) {
                switchEncoded = switchEncoded + switch2[i];
                switched = true;
                break;
            } else if (encodeString.equals(switch2[i])) {
                switchEncoded = switchEncoded + switch1[i];
                switched = true;
                break;
            }
        }

            if (!switched) {
                switchEncoded = switchEncoded + encodeString.charAt(0);
            }

        return(switchEncoded);
    }
    //Rotator
    public static int p3 = 0;
    public static int p2 = 0;
    public static int p1 = 0;
    public static ArrayList<String> alphabet = new ArrayList<String>(List.of("a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z", " "));
      public static ArrayList<String> rotor1 = new ArrayList<String>(List.of("e","k","m","f","l","g","d","q","v","z","n","t","o","w","y","h","x","u","s","p","a","i","b","r","c","j"));
      public static ArrayList<String> rotor2 = new ArrayList<String>(List.of("a","j","d","k","s","i","r","u","x","b","l","h","w","t","m","c","q","g","z","n","p","y","f","v","o","e"));
      public static ArrayList<String> rotor3 = new ArrayList<String>(List.of("b","d","f","h","j","l","c","p","r","t","x","v","z","n","y","e","i","w","g","a","k","m","u","s","q","o"));

    public static String Rotor(String switchEncoded) {
        String rotatorEncoded = "";

        int num = alphabet.indexOf(switchEncoded);

        rotatorEncoded = rotor3.get(num);
        num = alphabet.indexOf(rotatorEncoded);

        rotatorEncoded = rotor2.get(num);
        num = alphabet.indexOf(rotatorEncoded);

        rotatorEncoded = rotor1.get(num);

        return(rotatorEncoded);
    }
    //Reflector
    public static String Reflector(String rotatorEncoded)

    {
        String reflectEncoded = "";
        String[] reflect1 = {"a", "b", "c", "d", "e", "f", "g", "i", "j", "k", "m", "t", "v"};
        String[] reflect2 = {"y", "r", "u", "h", "q", "s", "l", "p", "x", "n", "o", "z", "w"};
        for (int i = 0; i < reflect1.length; i++)
        {
            if (rotatorEncoded.equals(reflect1[i]))
                reflectEncoded = reflect2[i];
            else if (rotatorEncoded.equals(reflect2[i]))
                reflectEncoded = reflect1[i];
        }

        return(reflectEncoded);
    }
    //Reversed rotor
    public static String BackRotor(String switchEncoded) {
        String backRotatorEncoded = "";

        int num = rotor1.indexOf(switchEncoded);

        backRotatorEncoded = alphabet.get(num);
        num = rotor2.indexOf(backRotatorEncoded);

        backRotatorEncoded = alphabet.get(num);
        num = rotor3.indexOf(backRotatorEncoded);

        backRotatorEncoded = alphabet.get(num);

        p3++;
        rotor3.add(rotor3.getFirst());
        rotor3.removeFirst();
        if (p3 == rotor3.size())
        {
            p3 = 0;
            p2++;
            rotor2.add(rotor2.getFirst());
            rotor2.removeFirst();

        }
        if (p2 == rotor3.size())
        {
            p2 = 0;
            p1++;
            rotor1.add(rotor1.getFirst());
            rotor1.removeFirst();
        }
        return(backRotatorEncoded);
    }
}
