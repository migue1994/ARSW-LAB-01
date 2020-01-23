package edu.eci.arsw.math;

import java.util.ArrayList;
import java.util.HashMap;

///  <summary>
///  An implementation of the Bailey-Borwein-Plouffe formula for calculating hexadecimal
///  digits of pi.
///  https://en.wikipedia.org/wiki/Bailey%E2%80%93Borwein%E2%80%93Plouffe_formula
///  *** Translated from C# code: https://github.com/mmoroney/DigitsOfPi ***
///  </summary>
public class PiDigits extends Thread{

    private static int DigitsPerSum = 8;
    private static double Epsilon = 1e-17;

    private int value;
	private int numberAfterValues;
	private byte[] arrayHecDig;	 
	private ArrayList<byte[]>totalList;
	private ArrayList<Integer>numbersCountStart;
	private ArrayList<Integer>numbersCountEnd;
	
	public PiDigits() {
		this.totalList=new ArrayList<byte[]>();
		this.numbersCountStart=new ArrayList<Integer>();
		this.numbersCountEnd=new ArrayList<Integer>();
		
	}
    
    /**
     * Returns a range of hexadecimal digits of pi.
     * @param start The starting location of the range.
     * @param count The number of digits to return
     * @return An array containing the hexadecimal digits.
     */
    public static byte[] getDigits(int start, int count) {
        if (start < 0) {
            throw new RuntimeException("Invalid Interval");
        }

        if (count < 0) {
            throw new RuntimeException("Invalid Interval");
        }

        byte[] digits = new byte[count];
        double sum = 0;

        for (int i = 0; i < count; i++) {
            if (i % DigitsPerSum == 0) {
                sum = 4 * sum(1, start)
                        - 2 * sum(4, start)
                        - sum(5, start)
                        - sum(6, start);

                start += DigitsPerSum;
            }

            sum = 16 * (sum - Math.floor(sum));
            digits[i] = (byte) sum;
        }

        return digits;
    }
    
    /**
     * 
     * @param s
     * @return
     */
    private static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                                 + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }
    
    /**
     * 
     * @param list
     * @param n
     */
    /*private static void inicializeList(ArrayList<byte[]> list, int n) {
    	for(int i=0; i<n; i++) {
    		byte[] listBytes=hexStringToByteArray("e04fd020ea3a6910a2d808002b30309d");
    		list.add(listBytes);
    	}
    }*/
    
    /**
     * 
     * @param list
     * @param n
     */
    public void inicializeListNumber(int inicio, int fin, int n) {
    	int divCount=fin/n;
    	int aux=inicio+divCount;
    	int start=inicio;
    	
    	for(int i=0; i<n; i++) {
    		this.numbersCountEnd.add(aux);
    		this.numbersCountStart.add(start);
    		start=aux+1;
    		aux+=divCount;
    	}
    	
    	if(this.numbersCountEnd.get(n-1)!=fin) this.numbersCountEnd.set(n-1, fin);
    }
    
    public ArrayList<Integer> getNumbersCountStart(){
    	return this.numbersCountStart;
    }
    
    public ArrayList<Integer> getNumbersCountEnd(){
    	return this.numbersCountEnd;
    }
    
    
    /**
     * 
     * @param start
     * @param count
     * @param numThreads
     * @throws InterruptedException
     */
    public void getDigits(int start, int count, int numThreads) throws InterruptedException {
    	
    	//inicializeList(this.totalList,numThreads); 
    	inicializeListNumber(start, start+count, numThreads);
    	
    	this.value=start;
    	
    	ArrayList<Integer>w=getNumbersCountStart();
    	ArrayList<Integer>q=getNumbersCountEnd();

    	
    	/*for(int j:w) {
        	System.out.println(j);
        }
    	System.out.println();
    	for(int j:q) {
        	System.out.println(j);
        }*/
		
    	
    	for(int i=0;i<numThreads;i++) {
    		
    		this.run();
    		this.join();
    		
    		/*totalList.add(this.arrayHecDig);
    		
    		this.value=numberAfterValues;*/
    		

    	
    	}
    }
    
    /**
     * 
     */
    //@Override
    public void run() {
    	totalList.add(getDigits(this.value, this.numberAfterValues));
    }
    
    
    public ArrayList<byte[]> getTotalList(){
    	return this.totalList;
    }
    
    

    /// <summary>
    /// Returns the sum of 16^(n - k)/(8 * k + m) from 0 to k.
    /// </summary>
    /// <param name="m"></param>
    /// <param name="n"></param>
    /// <returns></returns>
    private static double sum(int m, int n) {
        double sum = 0;
        int d = m;
        int power = n;

        while (true) {
            double term;

            if (power > 0) {
                term = (double) hexExponentModulo(power, d) / d;
            } else {
                term = Math.pow(16, power) / d;
                if (term < Epsilon) {
                    break;
                }
            }

            sum += term;
            power--;
            d += 8;
        }

        return sum;
    }

    /// <summary>
    /// Return 16^p mod m.
    /// </summary>
    /// <param name="p"></param>
    /// <param name="m"></param>
    /// <returns></returns>
    private static int hexExponentModulo(int p, int m) {
        int power = 1;
        while (power * 2 <= p) {
            power *= 2;
        }

        int result = 1;

        while (power > 0) {
            if (p >= power) {
                result *= 16;
                result %= m;
                p -= power;
            }

            power /= 2;

            if (power > 0) {
                result *= result;
                result %= m;
            }
        }

        return result;
    }

}
