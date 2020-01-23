package edu.eci.arsw.threads;

import lombok.AllArgsConstructor;
import lombok.Data;
import edu.eci.arsw.math.PiDigits;


/**
 *
 * @author hcadavid
 */
@Data
public class PiDigitThread extends Thread{

    private int start, end;
	private byte[] res;

	public PiDigitThread (int start, int end){
		this.start = start;
		this.end = end;
	}

    @Override
    public void run() {
        res = PiDigits.getDigits(start, end);
    }
	
	public byte[] getRes(){
		return res;
	}

}