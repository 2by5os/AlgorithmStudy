package Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class GraphBasic {

	public final int MAX_VAL = 9999;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public Queue<String> getBFS(HashMap<String, ArrayList<String>> graph_list, String start_node ){
		Queue<String> visited = new LinkedList<String>();
		Queue<String> need_visit = new LinkedList<String>();
		
		need_visit.add(start_node);
		
		while (!need_visit.isEmpty()) {
			String node = need_visit.poll();
			if (!visited.contains(node)) {
				visited.add(node);
				need_visit.addAll(graph_list.get(node));
			}
		}
		
		return visited;
	}
	
	public Queue<String> getDfs(HashMap<String, ArrayList<String>> graph_list, String start_node ){
		Queue<String> visited = new LinkedList<String>();
		Stack<String> need_visit = new Stack<String>();
		
		need_visit.add(start_node);
		
		while (!need_visit.isEmpty()) {
			String node = need_visit.pop();
			if (!visited.contains(node)) {
				visited.add(node);
				need_visit.addAll(graph_list.get(node));
			}
		}
		
		return visited;
	}
	
	public class Node implements Comparable<Node>{
		private String vertex;
		private int edge;
		
		public Node(String vertex, int edge) {
			this.vertex = vertex;
			this.edge = edge;
		}

		public String getVertex() {
			return vertex;
		}

		public int getEdge() {
			return edge;
		}

		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			// return  값이 양수인 경우 객체의 자리가 바뀜, 비교 대상의 edge 값이 더 작을 경우 1이므로 오름차순 정렬 
			return this.edge <= o.edge ? -1 : 1;
		}
	}
	
	public Map<String, Node> getDijkstra(HashMap<String, ArrayList<Node>> graph, String start_node, String end_node){
		Map<String, Node> distances = new HashMap<>();
		HashMap<String, ArrayList<String>> paths = new HashMap<>();
		PriorityQueue<Node> need_visit = new PriorityQueue<>();
		
		Iterator<String> nodes = graph.keySet().iterator();
		while(nodes.hasNext()) {
			distances.put(nodes.next(), new Node(start_node, MAX_VAL));
		}
		distances.put(start_node, new Node(start_node, 0));
		
		while(!need_visit.isEmpty()) {
			Node current = need_visit.poll();
			
			if(distances.get(current.getVertex()).getEdge() < current.getEdge())
				continue;
			
			for(Node n : graph.get(current.getVertex())) {
				int distance = current.getEdge() + n.getEdge();
				if(distance < distances.get(n.getVertex()).getEdge()) {
					distances.put(n.getVertex(), new Node(current.getVertex(), distance));
					need_visit.add(new Node(n.vertex, distance));
				}
			}
		}
		
		return distances;
	}

}
