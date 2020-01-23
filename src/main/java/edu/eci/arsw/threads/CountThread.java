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
public class CountThread implements Runnable {
	
	private int number1;
	private int number2;
	
	public CountThread(int number1, int number2) {
		this.number1=number1;
		this.number2=number2;
	}

	@Override
	public void run() {
		for(int i=number1; i<=number2; i++) {
			System.out.print(i+" ");
		}
		System.out.println();
	}
    
}
