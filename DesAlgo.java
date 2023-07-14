/*
 *
 * SWARUP DEEPAK VISHWAS 71 
 * IP : Initial Permutation
 * P : PlainText
 * K : Key
 * C : Cipher Text
 * P10 : 10 bits arrangement
 * P8 : 8 bits arrangement
 * EP :Expanded Permutation
 * 
 */

import java.lang.reflect.Array;
import java.util.*;

public class DesAlgo {

    public ArrayList<String> permute(ArrayList<String> key, int[] pVal) {
        ArrayList<String> resultList = new ArrayList<String>();
        for (int i = 0; i < pVal.length; i++) {
            resultList.add(key.get(pVal[i] - 1));
        }
        return resultList;
    }

    public ArrayList<String> leftShift(ArrayList<String> key, int b) {

        for (int i = 0; i < b; i++) {
            String first = key.get(0);
            int j;
            for (j = 0; j < key.size(); j++) {
                try {
                    key.set(j, key.get(j + 1));
                } catch (Exception e) {

                }
            }
            key.set(0, key.get(j - 1));
        }
        return key;
    }

    public ArrayList<String> divide(ArrayList<String> key, int from, int to) {
        ArrayList<String> result = new ArrayList<String>();
        for (int i = from; i < key.size(); i++) {
            result.add(key.get(i));
        }
        return result;
    }

    public ArrayList<String> joinKey(ArrayList<String> l1, ArrayList<String> l2) {
        for (int i = 0; i < l2.size(); i++) {
            l1.add(l2.get(i));
        }
        return l1;
    }

    public void deriveKey(String p, String k) {
        int[] p10 = { 3, 5, 2, 7, 4, 10, 1, 9, 8, 6 };
        int[] p8 = { 6, 3, 7, 4, 8, 5, 10, 9 };
        int[] ep = { 4, 1, 2, 3, 2, 3, 4, 1 };
        int[] ip = { 3, 5, 2, 7, 4, 10, 1, 9, 8, 6 };

        ArrayList<String> key = new ArrayList<String>(Arrays.asList(k.split("")));

        key = permute(key, p10);
        ArrayList<String> part1 = divide(key, 0, 5);
        ArrayList<String> part2 = divide(key, 5, 10);
        ArrayList<String> mini1 = leftShift(part1, 1);
        ArrayList<String> mini2 = leftShift(part2, 1);
        ArrayList<String> keyOneList = permute(joinKey(mini1, mini2), p8);
        String keyOne = String.join("", keyOneList);

        System.out.println("Key One : " + keyOne);

        mini1 = leftShift(part1, 2);
        mini2 = leftShift(part2, 2);
        ArrayList<String> keyTwoList = permute(joinKey(mini1, mini2), p8);
        String keyTwo = String.join("", keyTwoList);
        System.out.println("Key Two : " + keyTwo);

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // System.out.print("Enter the input : ");
        String p = "10100101";
        // System.out.print("Enter the key : ");
        String k = "0010010111";
        DesAlgo des = new DesAlgo();
        des.deriveKey(p, k);
        sc.close();
    }
}