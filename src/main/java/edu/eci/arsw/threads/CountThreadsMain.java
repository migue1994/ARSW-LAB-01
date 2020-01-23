/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.threads;

/**
 *
 * @author hcadavid
 */
public class CountThreadsMain {
    
	public static void main(String a[]) {
    	CountThread interval1=new CountThread(0,99);
    	CountThread interval2=new CountThread(99,199);
    	CountThread interval3=new CountThread(200,299);
    	
    	Thread thread1=new Thread(interval1);
    	Thread thread2= new Thread(interval2);
    	Thread thread3= new Thread(interval3);
    	
    	thread1.start();
    	thread2.start();
    	thread3.start();
    	/*interval1.run();
    	interval2.run();
    	interval3.run();*/
    	
    }
    
}
