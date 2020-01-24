/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.math;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import edu.eci.arsw.threads.CountThread;

/**
 *
 * @author hcadavid
 */
public class Main {

    public static void main(String a[]) throws InterruptedException, FileNotFoundException { 
    	PrintStream file=new PrintStream(new File("src/main/resources/file"));
    	
		long ini=System.currentTimeMillis();
		HashMap<Integer, PiDigitsThread> piDigits=PiDigits.getDigits(1,1000000,8);
		long fin=System.currentTimeMillis();
		System.out.println(fin-ini);
		
		for(int i=0;i<piDigits.size();i++) {
			file.println(piDigits.get(i));
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
