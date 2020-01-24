###Laboratorio 1

##Part I - Introduction to Java Threads

Change the beginning with start() to run(). How does the output change? Why?

##Part III - Performance Evaluation

#1. Single thread

#4. 200 threads

#5. 500 threads 


#According to Amdahls law, where S (n) is the theoretical performance improvement, P the parallel fraction of the algorithm, and n the number of threads, the greater n, the greater the improvement should be. Why is the best performance not achieved with the 500 threads? How does this performance compare when 200 are used?

#How does the solution behave using as many processing threads as cores compared to the result of using twice as much?

#According to the above, if for this problem instead of 500 threads on a single CPU, 1 wire could be used on each of 500 hypothetical machines, would Amdahls's law be better applied? If, instead, c threads were used in 500 / c distributed machines (where c is the number of cores of said machines), would it be improved? Explain your answer.
