
public class Security {

    private static int key = 14;

    static String encrypt(String input)  {
        char[] chars = input.toCharArray();
        char[] charsNew = new char[chars.length];
        for(int i=0; i<chars.length;i++){
            charsNew[i] = (char) (chars[i]+key);
        }
        return String.valueOf(charsNew);
    }

    static String decrypt(String encryptedMessage){
        char[] chars = encryptedMessage.toCharArray();
        char[] charsNew = new char[chars.length];
        for(int i=0; i<chars.length;i++){
            charsNew[i] = (char) (chars[i]-key);
        }
        return String.valueOf(charsNew);
    }
}
