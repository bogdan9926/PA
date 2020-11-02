import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class P2 {
	static class Task {
		public static final String INPUT_FILE = "p2.in";
		public static final String OUTPUT_FILE = "p2.out";
		public static final int NMAX = 200001;
		public static final int INF = (int) 1e9;

		int n;
		int m;
		int source;
		int destination;

		public class Edge {
			public int node;
			public int cost;

			Edge(int _node, int _cost) {
				node = _node;
				cost = _cost;
			}
		}

		@SuppressWarnings("unchecked")
		ArrayList<Edge>[] adj = new ArrayList[NMAX];

		private void readInput() {
			try {
				Scanner sc = new Scanner(new BufferedReader(new FileReader(INPUT_FILE)));
				n = sc.nextInt();
				m = sc.nextInt();
				source = sc.nextInt();
				destination = sc.nextInt();
				for (int i = 1; i <= n; i++) {
					adj[i] = new ArrayList<>();
				}
				for (int i = 1; i <= m; i++) {
					int x, y, w;
					x = sc.nextInt();
					y = sc.nextInt();
					w = sc.nextInt();
					adj[x].add(new Edge(y, w));
				}
				sc.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		private int getResult() {
			int[] d = new int[n + 1];
			for (int i = 0; i <= n; i++) {
				d[i] = 0;
			}
			bellman(source, d);
			return d[destination];
		}

		private void bellman(int source, int[] d) {
			// Initializam distantele catre toate nodurile cu infinit
			for (int i = 1; i <= n; i++) {
				d[i] = INF;
			}

			// Setez distanta pana la sursa la 0
			d[source] = 0;

			// Relaxez toate muchiile de n - 1 ori
			for (int i = 1; i < n; i++) {
				// daca nu s-au mai facut modificari, am ajuns la solutia optima 
				if (checkDistance(d) == false) {
					return;
				}

			}
		}

		private boolean checkDistance(int[] d) {
			boolean ok = false;

			for (int u = 1; u <= n; u++) {
				for (Edge e : adj[u]) {
					// am gasit o solutie mai buna
					if (d[u] + e.cost < d[e.node]) {
						d[e.node] = d[u] + e.cost;
						ok = true;
					}
				}
			}

			return ok;
		}

		public void solve() {
			readInput();
			try {
				BufferedWriter bw = new BufferedWriter(new FileWriter(OUTPUT_FILE));
				StringBuilder sb = new StringBuilder();
				sb.append(getResult());
				sb.append("\n");
				bw.write(sb.toString());
				bw.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}
	}

	public static void main(String[] args) {
		new Task().solve();
	}
}
