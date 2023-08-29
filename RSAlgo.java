import java.util.Scanner;
import java.lang.Math;

public class RSAlgo {

	private int n, e, d;

	int power(int x, int y, int p) {
		int res = 1;

		while (y > 0) {

			if ((y & 1) != 0)
				res = res * x;

			y = y >> 1;
			x = x * x;
		}
		return res % p;
	}

	int modExp(int a, int b, int n) {
		if (b < 5) {
			return (int) (Math.pow(a, b) % n);
		}
		int i = (int) Math.ceil(b / 2);
		int j = (int) Math.floor(b / 2);
		if (b % 2 == 1) {
			j++;
		}
		// System.out.println(i + " -- " + j);
		return (modExp(a, i, n) * modExp(a, j, n)) % n;
	}

	public int gcd(int i, int j) {
		int gcd = 1;
		int min = i < j ? i : j;
		for (int k = 1; k <= min; k++) {
			if (i % k == 0 && j % k == 0) {
				gcd = k;
			}
		}

		return gcd;
	}

	public void moduloInverse(int phi_n) {
		int di = 1;

		while (((di % phi_n) * (e % phi_n)) % phi_n != 1)
			di++;
		this.d = di;
	}

	public long encryption(int p) {
		return modExp(p, e, n);
	}

	public long decryption(int c) {
		return modExp(c, d, n);
	}

	public void keyGen(int p, int q) {
		n = p * q;
		int phi_n = (p - 1) * (q - 1);
		for (e = 2; e < phi_n; e++) {
			if (gcd(e, phi_n) == 1)
				break;
		}

		moduloInverse(phi_n);
		System.out.println("Private Key : (" + d + "," + n + ")");
		System.out.println("Public Key : (" + e + "," + n + ")");
	}

	public static void main(String[] args) {
		int p = 7, q = 11;
		RSAlgo rsa = new RSAlgo();
		rsa.keyGen(p, q);
		var sc = new Scanner(System.in);
		System.out.print("Enter the Plain Text : ");
		int pl = sc.nextInt();
		int enc = (int) rsa.encryption(pl);
		System.out.println("CipherText : " + enc);
		System.out.println("Decrypted Text : " + rsa.decryption(enc));
	}
}
