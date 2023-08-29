import java.util.Scanner;

public class AESComplete {

  static String[] round_keys = new String[6];

  static String[] substitute_nibbles = {
      "1001", "0100", "1010", "1011", "1101", "0001",
      "1000", "0101", "0110", "0010", "0000",
      "0011", "1100", "1110", "1111", "0111"
  };

  static String[] inverse_substitute = {
      "1010", "0101", "1001", "1011", "0001", "0111",
      "1000", "1111", "0110", "0000", "0010",
      "0011", "1100", "0100", "1101", "1110"
  };

  public static void main(String[] args) {

    Scanner input = new Scanner(System.in);
    String plaintext = input.nextLine();
    String key = input.nextLine();

    generate_round_keys(key);

    String ciphertext = encrypt(plaintext);

    System.out.println("Ciphertext: " + ciphertext);

    input.close();

  }

  static String encrypt(String plaintext) {

    String state = xor(plaintext, round_keys[0] + round_keys[1]);

    String left = substitute_nibbles(state.substring(0, 8));
    String right = substitute_nibbles(state.substring(8, 16));

    state = left + right;

    state = swap_nibbles(state);

    state = mix_columns(state);

    state = xor(state, round_keys[2] + round_keys[3]);

    left = substitute_nibbles(state.substring(0, 8));
    right = substitute_nibbles(state.substring(8, 16));

    state = left + right;

    state = swap_nibbles(state);

    String ciphertext = xor(state, round_keys[4] + round_keys[5]);

    return ciphertext;

  }

  static String swap_nibbles(String input) {
    String left = input.substring(0, 4);
    String mid1 = input.substring(4, 8);
    String mid2 = input.substring(8, 12);
    String right = input.substring(12, 16);

    return left + right + mid1 + mid2;
  }

  static void generate_round_keys(String key) {

    String left = key.substring(0, 8);
    String right = key.substring(8, 16);

    round_keys[0] = left;
    round_keys[1] = right;

    round_keys[2] = xor(round_keys[0], "10000000");
    round_keys[2] = substitute_nibbles(swap_nibbles(round_keys[2]));
    round_keys[2] = xor(round_keys[2], round_keys[1]);

    round_keys[3] = xor(round_keys[2], round_keys[1]);

    round_keys[4] = xor(round_keys[2], "00110000");
    round_keys[4] = substitute_nibbles(swap_nibbles(round_keys[4]));
    round_keys[4] = xor(round_keys[4], round_keys[3]);

    round_keys[5] = xor(round_keys[4], round_keys[3]);

  }

  static String xor(String a, String b) {
    String result = "";
    for (int i = 0; i < a.length(); i++) {
      if (a.charAt(i) == b.charAt(i)) {
        result += "0";
      } else {
        result += "1";
      }
    }
    return result;
  }

  static String mix_columns(String columns) {

    String c1 = multiply("1011", columns.substring(0, 4));
    String c2 = multiply("1100", columns.substring(4, 8));
    String c3 = multiply("1101", columns.substring(8, 12));
    String c4 = multiply("1110", columns.substring(12, 16));

    String out1 = xor(c1, c2);
    String out2 = xor(c3, c4);

    return out1 + out2;

  }

  static String multiply(String a, String b) {

    // GF(2^4) multiplication logic

    return result;

  }

}