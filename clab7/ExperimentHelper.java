/**
 * Created by hug.
 */
public class ExperimentHelper {

    /** Returns the internal path length for an optimum binary search tree of
     *  size N. Examples:
     *  N = 1, OIPL: 0
     *  N = 2, OIPL: 1
     *  N = 3, OIPL: 2
     *  N = 4, OIPL: 4
     *  N = 5, OIPL: 6
     *  N = 6, OIPL: 8
     *  N = 7, OIPL: 10
     *  N = 8, OIPL: 13
     */
    public static int optimalIPL(int N) {
        int group = (int)(Math.log(N)/Math.log(2));
        int remain = (int) (N -Math.pow(2,group));
        return remain*group+sumOrder(group);
    }

    private static int sumOrder(int group){
        if(group==0) {
            return 0;
        }
        return (int)(sumOrder(group-1)+Math.pow(2,group-1)*(group-1)+1);
    }


    /** Returns the average depth for nodes in an optimal BST of
     *  size N.
     *  Examples:
     *  N = 1, OAD: 0
     *  N = 5, OAD: 1.2
     *  N = 8, OAD: 1.625
     * @return
     */
    public static double optimalAverageDepth(int N) {
        if (N == 1){
            return 0;
        }
        return 1.0*optimalIPL(N)/N;
    }


}
