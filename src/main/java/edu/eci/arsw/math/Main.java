/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.math;

import java.util.ArrayList;
import java.util.Arrays;

import edu.eci.arsw.threads.CountThread;

/**
 *
 * @author hcadavid
 */
public class Main {

    public static void main(String a[]) throws InterruptedException {
    	PiDigits piDigit=new PiDigits();
    	//piDigit.inicializeListNumber(0, 99, 5);
    	
    	/*ArrayList<Integer>w=piDigit.getNumbersCountStart();
    	ArrayList<Integer>q=piDigit.getNumbersCountEnd();

    	
    	for(int i:w) {
        	System.out.println(i);
        }
    	System.out.println();
    	for(int i:q) {
        	System.out.println(i);
        }*/
        
    	piDigit.getDigits(0,99,3);
        ArrayList<byte[]> total=piDigit.getTotalList();
    	System.out.println(total.size());
        
    	for(int i=0;i<total.size();i++) {
        	System.out.println(total.get(i));
        	//System.out.println(i[]);
        }
    	
    	
    }

    private final static char[] hexArray = "0123456789ABCDEF".toCharArray();

    public static String bytesToHex(byte[] bytes) {
       char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        StringBuilder sb=new StringBuilder();
        for (int i=0;i<hexChars.length;i=i+2){
            //sb.append(hexChars[i]);
            sb.append(hexChars[i+1]);            
        }
        return sb.toString();
    }

}
