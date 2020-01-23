/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.threads;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *
 * @author hcadavid
 */
@Data
@AllArgsConstructor
public class CountThread implements Runnable{

    private int start, end;

    private void recorrido(int start, int end){
        for (int i = start; i < end; i++){
            System.out.println(i); 
        }
    }

    @Override
    public void run() {
        recorrido(start, end);
    }

}
