package Q7;

import java.util.Random;
import java.util.concurrent.*;

/**

 This program multiplies two matrices using threads.
 */
public class MatrixMultiplier {
    private static final int NUM_THREADS = 4; // number of threads to use

    public static void main(String[] args) {
        int matrixSize = 3; // size of matrices
        Random random = new Random();
        // create matrices A and B with random values
        int[][] matrixA = new int[matrixSize][matrixSize];
        int[][] matrixB = new int[matrixSize][matrixSize];
        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize; j++) {
                matrixA[i][j] = random.nextInt();
                matrixB[i][j] = random.nextInt();
            }
        }

        // create a thread pool with NUM_THREADS threads
        ExecutorService pool = Executors.newFixedThreadPool(NUM_THREADS);

        // create an array of Future objects to hold the results of each thread
        Future<Double>[][] futures = new Future[matrixSize][matrixSize];

        // multiply the matrices using threads
        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize; j++) {
                final int row = i;
                final int col = j;
                futures[i][j] = pool.submit(() -> {
                    double sum = 0;
                    for (int k = 0; k < matrixSize; k++) {
                        sum += matrixA[row][k] * matrixB[k][col];
                    }
                    return sum;
                });
            }
        }

        // wait for all threads to finish and get the results
        double[][] matrixC = new double[matrixSize][matrixSize];
        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize; j++) {
                try {
                    matrixC[i][j] = futures[i][j].get();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }

        // shutdown the thread pool
        pool.shutdown();

        // print the result
        System.out.println("Result:");
        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize; j++) {
                System.out.print(matrixC[i][j] + " ");
            }
            System.out.println();
        }
    }
}

