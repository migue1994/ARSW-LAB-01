package edu.eci.arsw.math;

import java.awt.List;
import java.util.ArrayList;

public class PiDigitsThread extends Thread{
	
	private int value;
	private int numberAfterValues;
	private byte[] arrayHecDig;	    
	    
	public PiDigitsThread(int value, int numberAfterValues) {
		this.value=value;
		this.numberAfterValues=numberAfterValues;
	}
	    
	    
	@Override
	public void run() {
		long ini=System.currentTimeMillis();
		System.out.println("Thread was started");
		System.out.println(this.value +" "+ this.numberAfterValues);

		this.arrayHecDig=PiDigits.getDigits(this.value, this.numberAfterValues);
		long fin=System.currentTimeMillis();
		System.out.println(fin-ini);
	}
	
	
	public byte[] getarrayHecDig() {
		return this.arrayHecDig;
	}
	
	public void setValue(int value) {
		this.value=value;
	}
	
	public void setNumberAfterValues(int numberAfterValues) {
		this.numberAfterValues=numberAfterValues;
	}
}
