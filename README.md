# Laboratorio 1

## Part I - Introduction to Java Threads

### Change the beginning with start() to run(). How does the output change? Why?

Al utilizar start () el resultado de los hilos aparecen combinados en la salida; por otro lado, con run (), las salidas de los hilos se dan en el orden en que fueron ejecutados. Esto se debe a que cuando se hace el llamado al método run() solo existe un hilo, es como si se llamara a un método normal; por otro lado en el caso de start(), para ejecutarlo se crean hilos distintos.

Resultado con start()

![](https://github.com/migue1994/ARSW-LAB-01/blob/master/img/start.png)

Resultado con run()

![](https://github.com/migue1994/ARSW-LAB-01/blob/master/img/run.png)

## Part III - Performance Evaluation

### 1. Single thread

![](https://github.com/migue1994/ARSW-LAB-01/blob/master/img/caso1.png)

### 2. As many threads as processing cores

![](https://github.com/migue1994/ARSW-LAB-01/blob/master/img/cores.png)

### 3. So many threads as double processing cores

![](https://github.com/migue1994/ARSW-LAB-01/blob/master/img/dcores.png)

### 4. 200 threads

![](https://github.com/migue1994/ARSW-LAB-01/blob/master/img/caso2.png)

### 5. 500 threads 

![](https://github.com/migue1994/ARSW-LAB-01/blob/master/img/caso3.png)

### According to Amdahls law, where S (n) is the theoretical performance improvement, P the parallel fraction of the algorithm, and n the number of threads, the greater n, the greater the improvement should be. Why is the best performance not achieved with the 500 threads? How does this performance compare when 200 are used?

-El mejor rendimiento no es logrado en el caso de 500 hilos porque el computador no tiene los suficientes cores para ejecutar estos 500 hilos en simultaneo. Así que, aunque en el código el "n" sea 500, al momento de ejecutar, este "n" va a ser mucho menor que esta cifra, por lo tanto no se puede aplicar la fórmula de la ley de Amdahls con "n" 500, sino se tiene que poner un "n" acorde al número de hilos que realmente pueden ser ejecutados en simultaneo.

-Se logra un mejor rendimiento con 200 que con 500, porque el equipo al no poseer la suficiente capacidad de ejecutar tantos hilos en simultaneo, tiene que ir pausando unos hilos y reunudando otros hilos. Estos cambios llevan un tiempo considerable y causan, que en este caso el programa que usa menos hilos, y que por lo tanto realiza menos pausas y reanudaciones, sea más rápido

### How does the solution behave using as many processing threads as cores compared to the result of using twice as much?

Se logró un mejor rendimiento cuando se utilizaron el doble de cores de la máquina, los cuales eran 4.

### According to the above, if for this problem instead of 500 threads on a single CPU, 1 wire could be used on each of 500 hypothetical machines, would Amdahls's law be better applied? If, instead, c threads were used in 500 / c distributed machines (where c is the number of cores of said machines), would it be improved? Explain your answer.

-Estaría mejor aplicada en 500 máquinas distintas, ya que aquí se tendrían los procesadores suficientes para poder ejecutar los 500 hilos en paralelo (haciendo que "n" sea efectivamente igual a 500). En cambio, si se usa una sola máquina, no es posible hacer que estos 500 hilos sean ejecutados simultaneamente, haciendo que el "n" de la fórmula no sea igual a los hilos que se quieren ejecutar, sino igual a los hilos que la única máquina podría soportar simultaneamente.

-Al usarse misma cantidad de hilos con misma cantidad de cores se lograría una paralización total, haciendo que la mejora en el rendimiento sea igual a la que se logra al tener mismo número de hilos y máquinas, donde también se logra una paralización total, aunque a un mayor costo.