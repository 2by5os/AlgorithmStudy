package MST;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class MstBasic {
	private Map<String, String> parent;
	private Map<String, Integer> rank;
	
	public class Edge implements Comparable<Edge>{
		
		private String fromNode;
		private String toNode;
		private int weight;
		
		public Edge(String fromNode, String toNode, int weight) {
			this.fromNode = fromNode;
			this.toNode = toNode;
			this.weight = weight;
		}
		
		public String getFromNode() {
			return fromNode;
		}
		public void setFromNode(String fromNode) {
			this.fromNode = fromNode;
		}
		public String getToNode() {
			return toNode;
		}
		public void setToNode(String toNode) {
			this.toNode = toNode;
		}
		public int getWeight() {
			return weight;
		}
		public void setWeight(int weight) {
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			// TODO Auto-generated method stub
			return Integer.compare(this.weight, o.weight);
		}
	}
	
	public class Graph{
		private int numOfVertex;
		private int numOfEdge;
		private List<Edge> edges;
		public int getNumOfVertex() {
			return numOfVertex;
		}
		public void setNumOfVertex(int numOfVertex) {
			this.numOfVertex = numOfVertex;
		}
		public int getNumOfEdge() {
			return numOfEdge;
		}
		public void setNumOfEdge(int numOfEdge) {
			this.numOfEdge = numOfEdge;
		}
		public List<Edge> getEdges() {
			return edges;
		}
		public void setEdges(List<Edge> edges) {
			this.edges = edges;
		}
		public Graph(int numOfVertex, int numOfEdge, List<Edge> edges) {
			super();
			this.numOfVertex = numOfVertex;
			this.numOfEdge = numOfEdge;
			this.edges = edges;
		}
	}
	
	public Graph makeGraph(String graphPath) throws Exception{
		/*
		 * graph는 txt 파일에서 읽어옴.
		 * first line, second line에는 각각 vertex와 edge 수
		 * 나머지 line에는 각 line별 edge정보(from, to, weight)
		 * vertex는 숫자
		 */
		
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		List<Edge> edges = new ArrayList<>();
		
		int V = Integer.parseInt(br.readLine());
		int E = Integer.parseInt(br.readLine());
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			Edge e = new Edge(st.nextToken(), st.nextToken(), Integer.parseInt(st.nextToken()));
			edges.add(e);
		}
		Graph graph = new Graph(V, E, edges);
		return graph;
	}
	
	public void union(String nodeV, String nodeU) {
		String rootV = find(nodeV);
		String rootU = find(nodeU);
		
		if(rank.get(rootV) > rank.get(rootU)) {
			parent.put(rootU, rootV);
		}else {
			parent.put(rootV, rootU);
			if(rank.get(rootV) == rank.get(rootU))
				rank.put(rootU, rank.get(rootU)+1);
		}
	}
	
	public String find(String node) {
		String parentNode = parent.get(node);
		if (!node.equals(parentNode)) {
			parentNode = find(parentNode);
		}
		return parentNode;
	}
	
	public List<Edge> getMstByKruskal() throws Exception{
		Graph graph = makeGraph("src/graph.txt");
		List<Edge> mst = new ArrayList<>();
		List<Edge> edges = graph.getEdges();
		Collections.sort(edges);
		
		for(int i=0; i<graph.getNumOfVertex(); i++) {
			parent.put(String.valueOf(i), String.valueOf(i));
			rank.put(String.valueOf(i), 0);
		}
	
		for (Edge edge : edges) {
			if(!find(edge.getFromNode()).equals(find(edge.getToNode()))) {
				union(edge.getFromNode(), edge.getToNode());
				mst.add(edge);
			}
		}
		
		return mst;
	}
	
	public List<Edge> getMstByPrim(String startNode) throws Exception{
		Graph graph = makeGraph("src/graph.txt");
		
		List<Edge> mst = new ArrayList<>();
		List<Edge> edges = graph.getEdges();
		
		Map<String, List<Edge>> adjacentMap = new HashMap<>();
		List<String> visited = new ArrayList<>();
		PriorityQueue<Edge> candidate = new PriorityQueue<>();
		
		for(int i=0; i<graph.getNumOfVertex(); i++)
			adjacentMap.put(String.valueOf(i+1), new ArrayList<Edge>());
		
		for(Edge e : edges) {
			String fromNode = e.getFromNode();
			String toNode = e.getToNode();
			adjacentMap.get(fromNode).add(e);
			adjacentMap.get(toNode).add(e);
		}
		
		visited.add(startNode);
		candidate.addAll(adjacentMap.get(startNode));
		
		while(!candidate.isEmpty()) {
			Edge selected = candidate.poll();
			if(!visited.contains(selected.getToNode())) {
				visited.add(selected.getToNode());
				mst.add(selected);
				
				for(Edge e : adjacentMap.get(selected.getToNode())) {
					if(!mst.contains(e))
						candidate.add(e);
				}
			}
		}
		
		return mst;
	}
}
