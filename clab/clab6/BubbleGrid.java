public class BubbleGrid {
    private int[][] grid;
    private int rowNum;
    private int columnNum;
    int ceiling;


    /* Create new BubbleGrid with bubble/space locations specified by grid.
     * Grid is composed of only 1's and 0's, where 1's denote a bubble, and
     * 0's denote a space. */
    public BubbleGrid(int[][] grid) {
        this.grid = grid;
        this.rowNum = grid.length;
        this.columnNum = grid[0].length;
        this.ceiling = rowNum*columnNum;
    }

    /* Returns an array whose i-th element is the number of bubbles that
     * fall after the i-th dart is thrown. Assume all elements of darts
     * are unique, valid locations in the grid. Must be non-destructive
     * and have no side-effects to grid. */
    public int[] popBubbles(int[][] darts) {
        // TODO
        int[] bubblesFall = new int[darts.length];
        for(int i = 0; i < darts.length;i++){
            bubblesFall[i] = popBubbles(darts[i]);
        }
        return bubblesFall;
    }

    public int popBubbles(int[] dart){

        int rowOfDart = dart[0];
        int columnOfDart = dart[1];
        UnionFind beforeUnion = numberOfStuckBubbles(grid);
        int beforeNumber = beforeUnion.sizeOf(ceiling)-1;
        grid[rowOfDart][columnOfDart] = 0;
        UnionFind afterUnion = numberOfStuckBubbles(grid);
        int afterNumber = afterUnion.sizeOf(ceiling)-1;
        clearFallBubbles(afterUnion);
        if(beforeNumber==afterNumber){
            return 0;
        }
        else {
            return beforeNumber-afterNumber-1;
        }
    }

    public void clearFallBubbles(UnionFind bg){
        for(int row=0;row<rowNum;row++){
            for (int col=0;col<columnNum;col++){
                if(grid[row][col]==1){
                    if(!bg.connected(xyTo1D(row,col),ceiling)){
                        grid[row][col]=0;
                    };
                }
            }
        }
    }

    public UnionFind numberOfStuckBubbles(int[][] grid){
        UnionFind bg = new UnionFind(rowNum*columnNum+1); //the last element is the ceiling;


        for (int i=0;i<columnNum;i++){
            if(grid[0][i]==1){
                bg.union(xyTo1D(0,i),ceiling);
            }
        }
        for (int row=1;row<rowNum;row++){
            for(int col=0;col<columnNum;col++){
                if(grid[row][col]==1){
                    unionNeighbors(row,col,bg);
                }
            }
        }

        return bg;
    }

    public void unionNeighbors(int row, int col, UnionFind bg){
        int adjrow = row+1;
        int adjcol = col;
        if(adjrow < rowNum && grid[adjrow][adjcol]==1) {
            bg.union(xyTo1D(row, col), xyTo1D(adjrow, adjcol));
        }
        adjrow = row-1;
        adjcol = col;
        if(adjrow >= 0 && grid[adjrow][adjcol]==1) {
            bg.union(xyTo1D(row, col), xyTo1D(adjrow, adjcol));
        }
        adjrow = row;
        adjcol = col+1;
        if(adjcol < columnNum && grid[adjrow][adjcol]==1) {
            bg.union(xyTo1D(row, col), xyTo1D(adjrow, adjcol));
        }
        adjrow = row;
        adjcol = col-1;
        if(adjcol >= 0 && grid[adjrow][adjcol]==1) {
            bg.union(xyTo1D(row, col), xyTo1D(adjrow, adjcol));
        }
    }


    public int xyTo1D (int row, int column){
        return column + row*columnNum;
    }



}
