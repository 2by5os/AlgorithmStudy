package StackQueue;

import java.util.LinkedList;
import java.util.Queue;

//프로그래머스 코딩테스트 고득점 KIT
//스택/큐 - 프린터 
public class Queue3 {
	public int run(int[] priorities, int location) {
        Queue<Document> q = new LinkedList<>();
        for(int i=0; i<priorities.length; i++)
        	q.add(new Document(i, priorities[i]));
        int idx = 0;
        while(true) {
        	Document d = q.peek();
        	boolean flag = true;
        	for(Document doc : q)
        		if(doc.priority > d.priority) {
        			q.add(q.poll());
        			flag = false;
        			break;
        		}
        	if(flag) {
        		idx++;
        		if(q.poll().idx == location)
        			return idx;
        	}
        }
    }
	
	static class Document{
		int idx;
		int priority;
		public Document(int idx, int priority) {
			this.idx= idx;
			this.priority= priority;
		}
	}
}
