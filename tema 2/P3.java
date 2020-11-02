import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

public class P3 {
	static class Task {
		public static final String INPUT_FILE = "p3.in";
		public static final String OUTPUT_FILE = "p3.out";
		public static final int NMAX = 200001;
		public static final int INF = (int) 1e9;

		int n;
		int m;
		int energy;

		public class Edge implements Comparable<Edge> {
			public int node;
			public double cost;

			Edge(int _node, double _cost) {
				node = _node;
				cost = _cost;
			}

			public int compareTo(Edge e) {
				if (cost > e.cost) {
					return -1;
				}
				return 1;
			}

		}

		@SuppressWarnings("unchecked")
		ArrayList<Edge>[] adj = new ArrayList[NMAX];

		private void readInput() {
			try {
				Scanner sc = new Scanner(new BufferedReader(new FileReader(INPUT_FILE)));
				n = sc.nextInt();
				m = sc.nextInt();
				energy = sc.nextInt();

				for (int i = 1; i <= n; i++) {
					adj[i] = new ArrayList<>();
				}
				for (int i = 1; i <= m; i++) {
					int x, y;
					double w;
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


		private double getResult(ArrayList<Integer> drum) {
			//vector in care retin energia cu care se ajunge in fiecare nod
			double[] remainingEnergy = new double[n + 1];

			//vector in care retin parintele nodului
			int[] parent = new int[n + 1];
			parent[1] = -1;

			//initializam energia ramasa in fiecare nod cu 0
			for (int i = 0; i <= n; i++) {
				remainingEnergy[0] = 0.0;
			}

			dijkstra(remainingEnergy, parent);

			//aflarea rutei parcurgerii optime
			//se merge inapoi pe vectorul de parinti pana se ajunge la sursa
			int nodFinal = parent[n];
			drum.add(n);
			while (nodFinal != -1) {
				drum.add(nodFinal);
				nodFinal = parent[nodFinal];
			}
			Collections.reverse(drum);

			return remainingEnergy[n];
		}

		private void dijkstra(double[] remainingEnergy, int[] parent) {
			boolean[] visited = new boolean[n + 1];
			for (int i = 1; i <= n; i++) {
				visited[i] = false;
			}
			remainingEnergy[1] = (double) energy;
			PriorityQueue<Edge> pq = new PriorityQueue<>();
			pq.add(new Edge(1, energy));

			// Cat timp inca avem noduri de procesat
			while (!pq.isEmpty()) {
				// Scoatem head-ul cozii
				int u = pq.peek().node;
				double cost = pq.poll().cost;
				visited[u] = true;

				for (Edge e : adj[u]) {
					//am gasit o solutie mai buna
					if (!visited[e.node] && cost - cost * e.cost / 100 > remainingEnergy[e.node]) {
						remainingEnergy[e.node] = cost - cost * e.cost / 100;
						parent[e.node] = u;
						pq.add(new Edge(e.node, remainingEnergy[e.node]));
					}
				}
			}
		}

		public void solve() {

			readInput();
			ArrayList<Integer> route = new ArrayList<>();
			try {
				StringBuilder sb = new StringBuilder();
				sb.append(getResult(route));
				sb.append("\n");
				for (int i : route) {
					sb.append(i);
					sb.append(" ");
				}
				sb.deleteCharAt(sb.length() - 1);
				sb.append("\n");
				BufferedWriter bw = new BufferedWriter(new FileWriter(OUTPUT_FILE));
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
