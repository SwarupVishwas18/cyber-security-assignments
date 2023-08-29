import java.util.*;

public class SimplifiedAES {
    static Scanner sc = new Scanner(System.in);
    static HashMap<String, String> sBoxMap = new HashMap<>();

    public static void main(String args[]) {
        sBoxMap.put("0000", "1001");
        sBoxMap.put("0001", "0100");
        sBoxMap.put("0010", "1010");
        sBoxMap.put("0011", "1011");
        sBoxMap.put("0100", "1101");
        sBoxMap.put("0101", "0001");
        sBoxMap.put("0110", "1000");
        sBoxMap.put("0111", "0101");
        sBoxMap.put("1000", "0110");
        sBoxMap.put("1001", "0010");
        sBoxMap.put("1010", "0000");
        sBoxMap.put("1011", "0011");
        sBoxMap.put("1100", "1100");
        sBoxMap.put("1101", "1110");
        sBoxMap.put("1110", "1111");
        sBoxMap.put("1111", "0111");
        String key = "";
        System.out.println("Key Generation for Simplified AES");
        System.out.print("Enter the key: ");
        key = sc.nextLine();
        String w0 = key.substring(0, 8);
        String w1 = key.substring(8);
        System.out.print("\nW0: " + w0);
        System.out.print("\nW1: " + w0);
        String w2Const = "10000000", w4Const = "00110000";
        String w2_part1 = xorOperation(w0, w2Const);
        System.out.print("\nw2 part 1: " + w2_part1);
        String w2_part2 = subNib(rotNib(w1));
        System.out.print("\nw2 part 2: " + w2_part2);
        String w2 = xorOperation(w2_part1, w2_part2);
        System.out.print("\nW2: " + w2);
        String w3 = xorOperation(w1, w2);
        System.out.print("\nW3: " + w3);
        String w4_part1 = xorOperation(w2, w4Const);
        String w4_part2 = subNib(rotNib(w3));
        String w4 = xorOperation(w4_part1, w4_part2);
        System.out.print("\nW4: " + w4);
        String w5 = xorOperation(w4, w3);
        System.out.print("\nW5: " + w5);
        String key1 = w0 + w1;
        String key2 = w2 + w3;
        String key3 = w4 + w5;
        System.out.print("\n\nKey1: " + key1 + " \nKey2: " + key2 + "\nKey3: "
                + key3);
        /*
         * System.out.print("\n\nEnter you plain text: ");
         * String plainText = sc.nextLine();
         * String xorPTwithKey = xorOperation(plainText, key1);
         * System.out.print("XORed plain text: " + xorPTwithKey);
         * String xorPTSubNib = subNibForEncryption(xorPTwithKey);
         * System.out.print("\nXORed plain text after substitue nibble: " +
         * xorPTSubNib);
         * String xorPTRotNib2_4 = rotNibForEncryption(xorPTSubNib);
         * System.out.print("\nXORed plain text after rot nib: " + xorPTRotNib2_4);
         * String me[][] = {{"0001", "0100"}, {"0100", "0001"}};
         * String sMatrix[][] = {{xorPTRotNib2_4.substring(0, 4),
         * xorPTRotNib2_4.substring(8, 12)}, {xorPTRotNib2_4.substring(4, 8) ,
         * xorPTRotNib2_4.substring(12)}};
         * int mixColMultiplication[][] = new int[me.length][sMatrix.length];
         * String mixColMultiplicationStr = "";
         * for(int i = 0; i < me.length; i++){
         * for(int j = 0; j < sMatrix.length; j++) {
         * for(int k = 0; k < me.length; k++){
         * int prt1 = Integer.parseInt(me[i][k]) *
         * Integer.parseInt(sMatrix[k][j]);
         * String prt1Str = String.valueOf(prt1);
         * if(prt1Str.length() > 4)
         * }
         * }
         * }
         */
    }

    public static String xorOperation(String str1, String str2) {
        StringBuffer bfr = new StringBuffer();
        int lenStr1 = str1.length();
        int lenStr2 = str2.length();
        for (int i = 0, j = 0; i < lenStr1 && j < lenStr2; i++, j++)
            bfr.append(str1.charAt(i) ^ str2.charAt(j));
        return bfr.toString();
    }

    public static String rotNib(String str) {
        StringBuffer bfr = new StringBuffer();
        bfr.append(str.substring(4));
        bfr.append(str.substring(0, 4));
        return bfr.toString();
    }

    public static String rotNibForEncryption(String str) {
        StringBuffer bfr = new StringBuffer();
        bfr.append(str.substring(0, 4));
        bfr.append(str.substring(12));
        bfr.append(str.substring(8, 12));
        bfr.append(str.substring(4, 8));
        return bfr.toString();
    }

    public static String subNib(String str) {
        StringBuffer bfr = new StringBuffer();
        bfr.append(sBoxMap.get(str.substring(0, 4)));
        bfr.append(sBoxMap.get(str.substring(4)));
        return bfr.toString();
    }

    public static String subNibForEncryption(String str) {
        StringBuffer bfr = new StringBuffer();
        bfr.append(sBoxMap.get(str.substring(0, 4)));
        bfr.append(sBoxMap.get(str.substring(4, 8)));
        bfr.append(sBoxMap.get(str.substring(8, 12)));
        bfr.append(sBoxMap.get(str.substring(12)));
        return bfr.toString();
    }
}
