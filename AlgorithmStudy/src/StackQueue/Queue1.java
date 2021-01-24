package StackQueue;

import java.util.LinkedList;
import java.util.Queue;


// 프로그래머스 코딩테스트 고득점 KIT
// 스택/큐 - 다리를 지나는 트럭 
public class Queue1 {
	
	public int run(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        Queue<Integer> q = new LinkedList<>();
        Queue<Truck> bridge = new LinkedList<>();
        int b_weight = 0;
        for(int i : truck_weights)
        	q.add(i);
        while(!q.isEmpty() || !bridge.isEmpty()) {
        	go(bridge);
        	if(!bridge.isEmpty())
        		b_weight = out(bridge, bridge_length, b_weight);
        	if(!q.isEmpty()) {
        		int truck = q.peek();
            	if(b_weight + truck <= weight && bridge.size() < bridge_length) {
            		b_weight = enter(bridge, truck, b_weight);
            		q.poll();
            	}
        	}
        	answer++;
        }
        return answer;
    }
	
	static class Truck{
		int weight;
		int idx;
		public Truck(int weight, int idx) {
			this.weight = weight;
			this.idx = idx;
		}
	}
	
	public void go(Queue<Truck> q) {
		for(Truck t : q) {
			t.idx = t.idx + 1;
		}
	}
	
	public int enter(Queue<Truck> q, int truck, int b_weight) {
		q.add(new Truck(truck, 1));
		return b_weight + truck;
	}
	
	public int out(Queue<Truck> q, int b_length, int b_weight) {
		if(q.peek().idx > b_length) {
			return b_weight - q.poll().weight;
		}
		return b_weight;
	}
	
	public void print(Queue<Truck> q) {
		for(Truck t : q)
			System.out.println("truck " + t.weight + " idx " + t.idx);
		System.out.println("-------------");
	}

}
