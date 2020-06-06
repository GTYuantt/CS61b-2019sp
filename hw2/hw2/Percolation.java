package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private boolean[][] grid;
    private WeightedQuickUnionUF uf;
    private WeightedQuickUnionUF ufWithoutBottom;
    private int numberOfOpenSite;
    private int gridScale;



    // create N-by-N grid, with all sites initially blocked
    //The constructor should throw a java.lang.IllegalArgumentException if N ≤ 0.
    //0 is the top and N*N+1 is the bottom,1 to N*N is correspond to the site in the grid.
    public Percolation(int N){
        if(N <= 0){
            throw new java.lang.IllegalArgumentException();
        }
        grid = new boolean[N][N];
        gridScale = N;
        for (int i = 0;i < N;i++){
            for (int j = 0;j < N;j++){
                grid[i][j] = false;
            }
        }
        numberOfOpenSite = 0;
        uf = new WeightedQuickUnionUF(N*N+2);
        ufWithoutBottom = new WeightedQuickUnionUF(N*N+1);
    }

    private int xyTo1D (int row,int col){
        return row*gridScale+col+1;
    }


    // open the site (row, col) if it is not open already
    //Throw a java.lang.IndexOutOfBoundsException if any argument to open() is outside its prescribed range.
    public void open(int row, int col){
        if(row<0||row>=gridScale){
            throw new java.lang.IndexOutOfBoundsException();
        }
        if(col<0||col>=gridScale){
            throw new java.lang.IndexOutOfBoundsException();
        }
        if(isOpen(row, col)){
            return;
        }
        grid[row][col] = true;
        numberOfOpenSite++;
        int thisGrid = xyTo1D(row, col);
        if(row == 0){
            uf.union(thisGrid,0);
            ufWithoutBottom.union(thisGrid,0);
            unionEveryDirection(row,col);
        }
        else if(row == gridScale-1){
            uf.union(thisGrid,gridScale*gridScale+1);
            unionEveryDirection(row, col);
        }
        else {
            unionEveryDirection(row, col);
        }


    }



    private void unionEveryDirection(int row,int col){
        int thisGrid = xyTo1D(row, col);
        int topGrid = xyTo1D(row - 1, col);
        int leftGrid = xyTo1D(row, col - 1);
        int rightGrid = xyTo1D(row, col + 1);
        int bottomGrid = xyTo1D(row + 1, col);

        if (isValid(row-1,col)&&isOpen(row - 1, col)) {
            uf.union(thisGrid, topGrid);
            ufWithoutBottom.union(thisGrid,topGrid);
        }
        if (isValid(row,col-1)&&isOpen(row, col - 1)) {
            uf.union(thisGrid, leftGrid);
            ufWithoutBottom.union(thisGrid,leftGrid);
        }
        if (isValid(row,col+1)&&isOpen(row, col + 1)) {
            uf.union(thisGrid, rightGrid);
            ufWithoutBottom.union(thisGrid,rightGrid);
        }
        if (isValid(row+1,col)&&isOpen(row + 1, col)) {
            uf.union(thisGrid, bottomGrid);
            ufWithoutBottom.union(thisGrid,bottomGrid);
        }
    }

    private boolean isValid(int row,int col){
        if(row<0||row>=gridScale||col<0||col>=gridScale){
            return false;
        }
        return true;
    }
    // is the site (row, col) open?
    //Throw a java.lang.IndexOutOfBoundsException if any argument to isOpen() is outside its prescribed range.
    public boolean isOpen(int row, int col){
        if(row<0||row>=gridScale){
            throw new java.lang.IndexOutOfBoundsException();
        }
        if(col<0||col>=gridScale){
            throw new java.lang.IndexOutOfBoundsException();
        }
        return grid[row][col];
    }

    // is the site (row, col) full?
    //Throw a java.lang.IndexOutOfBoundsException if any argument to isFull() is outside its prescribed range.
    public boolean isFull(int row, int col){
        if(row<0||row>=gridScale){
            throw new java.lang.IndexOutOfBoundsException();
        }
        if(col<0||col>=gridScale){
            throw new java.lang.IndexOutOfBoundsException();
        }
        int thisGrid = xyTo1D(row,col);
        return ufWithoutBottom.connected(thisGrid,0);
    }

    // number of open sites
    public int numberOfOpenSites(){
        return numberOfOpenSite;
    }

    // does the system percolate?
    public boolean percolates(){
        return uf.connected(0,gridScale*gridScale+1);
    }

    // use for unit testing (not required, but keep this here for the autograder)
    public static void main(String[] args){

    }

}
