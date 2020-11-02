import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class Bani {
	static int modulo = 1000000007;

	// functie de calculat ridicarea la putere in O(log n), pentru tipul 1
	public static long power(long base, int exponent) {
		if (exponent == 0)  {
			return 5;
		}

		long aux = 5;
		while (exponent != 1) {
			if (exponent % 2 == 0) {
				base = (base * base) % modulo;
				exponent /= 2;
			} else {
				aux = (aux * base) % modulo;
				exponent--;
			}
		}

		return (aux * base) % modulo;
	}


	// functie de calculat rezultatul pentru tipul 2
	public static int getResult(int n) {

		/* luam 2 seturi de valori pentru nr de solutii ce se termina in
		o anumita bancnota. Cele old sunt cazul de baza*/
		int nr10old = 1,nr50old = 1,nr100old = 1,nr200old = 1,nr500old = 1;
		int nr10 = 0,nr50 = 0,nr100 = 0,nr200 = 0,nr500 = 0;
		// la fel si pentru rezultat, cel old fiind caz de baza
		int resultold = 5,resultnew = 0;
		for (int i = 2;i <= n;i++) {
			// calculam nr de solutii ce se termina in fiecare bancnota
			nr10 = ((nr50old + nr100old) % modulo + nr500old) % modulo;
			nr50 = (nr10old + nr200old) % modulo;
			nr100 = ((nr10old + nr100old) % modulo + nr200old) % modulo;
			nr200 = (nr50old + nr500old) % modulo;
			nr500 = nr200old % modulo;

			/* rezultatul este dublul rezultatului vechi + nr de solutii
			vechi ce se termina in 200, deoarece acestea au 3 variante
			de continuare, nu 2 */
			resultnew = ((2 * resultold) % modulo + nr200old) % modulo;

			// refacem pasul anterior
			resultold = resultnew;
			nr10old = nr10;
			nr50old = nr50;
			nr100old = nr100;
			nr200old = nr200;
			nr500old = nr500;
		}
		return resultold;

	}
	public static void main(String[] args) {
		int type, n;
		String[] str;
		String inputFile = "bani.in";
		String outputFile = "bani.out";
		try {
			BufferedReader in = new BufferedReader(new FileReader(inputFile));
			str = in.readLine().split(" ");
			type = Integer.parseInt(str[0]);
			n = Integer.parseInt(str[1]);

			in.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		try {
			PrintWriter pw = new PrintWriter(new File(outputFile));
			if (type == 2) {
				pw.printf("%d\n", getResult(n));
			} else {
				pw.printf("%d\n", power(2,n - 1));
			}
			pw.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
