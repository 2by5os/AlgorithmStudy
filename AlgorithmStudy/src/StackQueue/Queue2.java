package StackQueue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//프로그래머스 코딩테스트 고득점 KIT
//스택/큐 - 기능개발 
//queue.peek시, 항상 queue.isEmpty체크
public class Queue2 {

	public int[] run(int[] progresses, int[] speeds) {
        int[] answer = {};
        List<Integer> answer_list = new ArrayList<>();
        int n = progresses.length;
        Queue<Work> q = new LinkedList<>();
        for(int i=0; i<n; i++)
        	q.add(new Work(i, progresses[i], speeds[i]));
        while(!q.isEmpty()) {
        	int cnt = 0;
        	for(Work w : q)
        		w.doWork();
        	while(!q.isEmpty() && q.peek().progress >= 100) {
        		q.poll();
        		cnt++;
        	}
        	if(cnt!=0) {
        		answer_list.add(cnt);
        	}
        }
        answer = new int[answer_list.size()];
        for(int i=0; i<answer_list.size(); i++) answer[i]=answer_list.get(i);
        return answer;
    }
	
	static class Work{
		int idx;
		int progress;
		int speed;
		public Work(int idx, int progress, int speed) {
			this.idx = idx;
			this.progress = progress;
			this.speed = speed;
		}
		public void doWork() {
			this.progress += this.speed;
		}
	}
}
