import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Gard implements Comparable<Gard> {
	int x;
	int y;
	public Gard(int x,int y) {
		this.x = x;
		this.y = y;
	}
	/* Comparator, ordoneaza crescator dupa prima valoare si, in caz de
	egalitate, descrescator dupa a doua valoare */
	public int compareTo(Gard g) {
		if (this.x < g.x) {
			return -1;
		}
		if (this.x > g.x) {
			return 1;
		}
		if (this.y < g.y) {
			return 1;
		}
		return -1;
	}



	public static void main(String[] args) {
		ArrayList<Gard> gardList = new ArrayList<Gard>();
		int n;
		String[] str;
		String inputFile = "gard.in";
		String outputFile = "gard.out";
		try {
			BufferedReader in = new BufferedReader(new FileReader(inputFile));
			str = in.readLine().split(" ");
			n = Integer.parseInt(str[0]);
			for (int i = 1; i <= n; i++) {
				str = in.readLine().split(" ");
				int x = Integer.parseInt(str[0]);
				int y = Integer.parseInt(str[1]);
				Gard gard = new Gard(x, y);
				gardList.add(gard);
			}
			in.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		int result = 0;

		Collections.sort(gardList);

		/* Vom compara pe rand bucatile de gard: daca a doua are a doua valoare
		mai mica, este redundanta. Altfel, schimbam bucata de gard cu care
		comparam */
		Gard toCompare = gardList.get(0);
		for (Gard g : gardList) {
			if (g.y <= toCompare.y) {
				result++;
			} else {
				toCompare = g;
			}
		}
		try {
			PrintWriter pw = new PrintWriter(new File(outputFile));
			pw.printf("%d\n",result - 1);
			pw.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
