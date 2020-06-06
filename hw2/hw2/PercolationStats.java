package hw2;

import  edu.princeton.cs.introcs.StdRandom;
import  edu.princeton.cs.introcs.StdStats;

public class PercolationStats {

        private double[] threshold;
        private int numOfExperiments;

        // perform T independent experiments on an N-by-N grid
        public PercolationStats(int N, int T, PercolationFactory pf){
            if(N<=0 || T<=0){
                throw new java.lang.IllegalArgumentException();
            }
            numOfExperiments = T;
            threshold = new double[T];
            for (int i = 0;i<T;i++){
                Percolation percolation = pf.make(N);
                while(!percolation.percolates()){
                    int randomRow = StdRandom.uniform(N);
                    int randomCol = StdRandom.uniform(N);
                    percolation.open(randomRow,randomCol);
                }
                int pNum = percolation.numberOfOpenSites();
                threshold[i] = 1.0*pNum/(N*N);
            }
        }

        // sample mean of percolation threshold
        public double mean(){
            return StdStats.mean(threshold);
        }

        // sample standard deviation of percolation threshold
        public double stddev(){
            return StdStats.stddev(threshold);
        }

        // low endpoint of 95% confidence interval
        public double confidenceLow(){
            return mean()-1.96*stddev()/Math.sqrt(numOfExperiments);

        }


        // high endpoint of 95% confidence interval
        public double confidenceHigh(){
            return mean()+1.96*stddev()/Math.sqrt(numOfExperiments);
        }

}
