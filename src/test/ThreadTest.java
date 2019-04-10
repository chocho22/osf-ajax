package test;

import java.util.ArrayList;
import java.util.List;

public class ThreadTest extends Thread {
	public ThreadTest(String name) {
		super(name);
	}
	public void run() {
		for(int i=1;i<=10;i++) {
			System.out.println(currentThread().getName()+"번째 실행됨");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		List<Thread> tList = new ArrayList<>();
		for(int i=1;i<=5;i++) {
			Thread t = new ThreadTest(i+"");
			tList.add(t);
		}
		for(Thread t:tList) {
			t.start();
		}
	}
}
