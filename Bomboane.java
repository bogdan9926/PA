import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.Vector;

public class Bomboane {
	int x;
	int y;
	public Bomboane(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public static void main(String[] args) {
		int modulo = 1000000007;
		int n, m;
		String[] str;
		ArrayList<Bomboane> bomboaneList = new ArrayList<Bomboane>();
		String inputFile = "bomboane.in";
		String outputFile = "bomboane.out";
		try {
			BufferedReader in = new BufferedReader(new FileReader(inputFile));
			str = in.readLine().split(" ");
			n = Integer.parseInt(str[0]);
			m = Integer.parseInt(str[1]);
			for (int i = 1; i <= n; i++) {
				str = in.readLine().split(" ");
				int x = Integer.parseInt(str[0]);
				int y = Integer.parseInt(str[1]);
				Bomboane bomboane = new Bomboane(x, y);
				bomboaneList.add(bomboane);
			}
			in.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		/* Vom avea 2 vectori, 1 pentru pasul anterior si 1 pt cel actual,
		initializati cu 0 */
		Vector<Integer> oldPosition = new Vector<>(m + 1);
		Vector<Integer> newPosition = new Vector<>(m + 1);
		for (int i = 0 ; i <= m ; i++) {
			oldPosition.add(0);
			newPosition.add(0);
		}
		Bomboane firstBomboana = bomboaneList.get(0);
		if (firstBomboana.x > m) {
			firstBomboana.x = m;
		}
		if (firstBomboana.y > m) {
			firstBomboana.y = m;
		}
		// Caz de baza, primul interval, umplem vectorul cu 1 intre cele 2 valori
		for (int i = firstBomboana.x  ; i <= firstBomboana.y  ; i ++) {
			oldPosition.set(i,1);
		}
		Boolean baza = true;
		for (Bomboane b : bomboaneList) {
			// trecem peste cazul de baza
			if (baza) {
				baza = false;
				continue;
			} else {
				/* In vectorul 2 vom pune la pozitia i suma elementelor
				dintre (i-x) si (i-y), elemente din vectorul 1.
				Daca x sau y trec de maximul de bomboane m, le modificam.
				Daca (i-x) sau (i-y) < 0 , vom lua de la 0.
				 */
				if (b.x > m) {
					b.x = m;
				}
				if (b.y > m) {
					b.y = m;
				}
				for (int i = 0 ; i <= m ; i++) {
					int index1,index2;
					if (i - b.x == 0 && i - b.y == 0) {
						index1 = 0;
						index2 = 0;
					} else {
						if (i - b.x < 0) {
							index1 = 0;
						} else {
							index1 = i - b.x;
						}
						if (i - b.y < 0) {
							index2 = 0;
						} else {
							index2 = i - b.y;
						}
						if (index1 == 0 && index2 == 0) {
							continue;
						}
					}
					// cream elementul de pe pozitia i in vectorul 2
					int next = 0;
					for (int j = Math.min(index1, index2) ; j <= Math.max(index1, index2) ; j++) {
						next = (next + oldPosition.get(j)) % modulo;

					}
					newPosition.set(i,next);

				}
			}
			//refacem pasul anterior
			oldPosition = newPosition;
			newPosition = new Vector<>(m + 1);
			for (int i = 0 ; i <= m ; i++) {
				newPosition.add(0);
			}
		}

		try {
			PrintWriter pw = new PrintWriter(new File(outputFile));
			pw.printf("%d\n",oldPosition.get(m));
			pw.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
