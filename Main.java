/** Program main class. */
public class Main 
{
    public static final int n = -1;                                             // no connection

    public static final int[][] graph =                                         // graph
    {
        { 0, 2, 3, n, n, n, n, n, n, n },                                       // 0
        { n, 0, n, n, n, n, 1, n, n, n },                                       // 1
        { n, n, 0, 2, n, n, 4, n, n, n },                                       // 2
        { n, n, n, 0, 3, n, n, n, n, n },                                       // 3
        { n, n, n, n, 0, n, n, n, 2, 3 },                                       // 4
        { n, n, n, 3, 3, 0, 5, n, n, n },                                       // 5
        { 3, n, n, n, n, n, 0, 4, n, n },                                       // 6
        { n, n, n, n, n, 7, n, 0, n, n },                                       // 7
        { n, n, n, n, n, n, n, n, 0, 2 },                                       // 8
        { n, n, 8, n, n, n, n, n, n, 0 },                                       // 9
    };
    
    
    /** Program entry point.
     * @param args Command line arguments. */
    public static void main(String[] args)
    {
        int[] result = Dijkstra.getPath(graph, 0, 9);                           // call Dijkstra implementation
        
        for(int i: result)
        {                                                                       // print path
            System.out.printf("%d\n", i);
        }
    }
}
