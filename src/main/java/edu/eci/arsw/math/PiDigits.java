package edu.eci.arsw.math;

import java.util.ArrayList;
import java.util.HashMap;

///  <summary>
///  An implementation of the Bailey-Borwein-Plouffe formula for calculating hexadecimal
///  digits of pi.
///  https://en.wikipedia.org/wiki/Bailey%E2%80%93Borwein%E2%80%93Plouffe_formula
///  *** Translated from C# code: https://github.com/mmoroney/DigitsOfPi ***
///  </summary>
public class PiDigits{

    private static int DigitsPerSum = 8;
    private static double Epsilon = 1e-17;

	
	    
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
    public static void inicializeListNumber(int inicio, int fin, int n, 
    		ArrayList<Integer> numbersCountStart, ArrayList<Integer> numbersCountEnd) {
    	int divCount=fin/n;
    	int aux=inicio+divCount;
    	int start=inicio;
    	
    	for(int i=0; i<n; i++) {
    		numbersCountEnd.add(aux);
    		numbersCountStart.add(start);
    		start=aux+1;
    		aux+=divCount;
    	}
    	
    	if(numbersCountEnd.get(n-1)!=fin) numbersCountEnd.set(n-1, fin);
    }
    
    
    
    /**
     * 
     * @param start
     * @param count
     * @param numThreads
     * @throws InterruptedException
     */
    public static HashMap<Integer, PiDigitsThread> getDigits(int start, int count, int numThreads) throws InterruptedException {
    	
    	ArrayList<Integer>numbersCountStart=new ArrayList<Integer>();
    	ArrayList<Integer>numbersCountEnd=new ArrayList<Integer>();
    	HashMap<Integer, PiDigitsThread>threadsList=new HashMap<Integer, PiDigitsThread>();
    	
    	inicializeListNumber(start, start+count, numThreads, numbersCountStart, numbersCountEnd);
    	
    	for(int i=0;i<numThreads;i++) {
    		
    		int startValue=numbersCountStart.get(i);
    		int countValue=numbersCountEnd.get(i)-startValue; 
    		
    		if(countValue==0) countValue+=1;
    		
    		PiDigitsThread piDigitsThread=new PiDigitsThread(startValue,countValue);
    		
    		threadsList.put(i,piDigitsThread);

    		piDigitsThread.start();
    		piDigitsThread.join();
    	}
    	
    	return threadsList;
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
