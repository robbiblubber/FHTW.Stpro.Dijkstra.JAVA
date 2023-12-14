/** This class provides an array-based implementation of the Dijkstra algorithm. */
public class Dijkstra
{
    /** Gets the shortest path between nodes.
     * @param graph Graph.
     * @param start Start node.
     * @param end End node.
     * @return Returns the path as an array of nodes. */
    public static int[] getPath(int[][] graph, int start, int end)
    {
        int[][] dist = new int[graph.length][2];                                // distances: dist[node][0] => distance to start; dist[node][1] => final?
                                                                                // initialize, every node is far away and tentative
        for(int i = 0; i < graph.length; i++) { dist[i][0] = Integer.MAX_VALUE; dist[i][1] = 0; }
        dist[start][0] = 0; dist[start][1] = 1;                                 // start node is final

        int vk = start;                                                         // set current node to start
        while(vk != end)                                                        // main loop, finds shortest path
        {
            for(int i = 0; i < graph.length; i++)                               // calculate distances for all (tentative) current node neighbours
            {
                if((graph[vk][i] > 0) && (dist[i][1] == 0))                     // node is adjacent and tentative
                {
                    int d = dist[vk][0] + graph[vk][i];                         // distance is current node distance plus distance to that adjacent node
                    if(d < dist[i][0]) { dist[i][0] = d; }                      // update distance if calculated is smaller
                }
            }

            int vmin = -1; int dmin = Integer.MAX_VALUE;                        // nearest node (vmin) and minimal distance for that (dmin)
            for(int i = 0; i < graph.length; i++)                               // iterate all nodes to find the nearest (to start) that's still tentative
            {                                                                   // if nearer than all before, set to nearest so far
                if((dist[i][1] == 0) && (dist[i][0] < dmin)) { dmin = dist[i][0]; vmin = i; }
            }

            if(vmin == -1) break;                                               // in that case there are no more nodes, meaning start and end are not connected
            dist[vk = vmin][1] = 1;                                             // set current node to nearest found and mark it final
        }


        int[] temp = new int[graph.length];                                     // temporary array to store shortest path
        int idx = 1;                                                            // index for temp
        vk = temp[0] = end;                                                     // first node is end, make this current
        while(vk != start)                                                      // reconstruct path from end to start finding consistent nodes
        {
            for(int i = 0; i < graph.length; i++)                               // look at all nodes
            {                                                                   // pick one that is connected, final, and consistent
                if((graph[i][vk] > -1) && (dist[i][1] == 1) && (dist[i][0] + graph[i][vk] == dist[vk][0]))
                {
                    vk = temp[idx++] = i;                                       // make consistent node current
                    break;                                                      // already found one consistent node, no need to look any further
                }
            }

            if(temp[idx] == vk) break;                                          // if index has not moved, there is no path
        }

        int[] rval = new int[idx];                                              // we now know the correct size of the resulting array
        int j = 0;                                                              // index for rval
        for(int i = idx -1; i >= 0; i--) { rval[j++] = temp[i]; }               // revert temp into rval

        return rval;                                                            // return the result
    }

    
    /** Dumps the distance array.
     * @param v Distance array. */
    public static void dump(int[][] v)
    {
        for(int i = 0; i < 10; i++)
        {
            System.out.printf(" %d   %d\n", v[i][0], v[i][1]);
        }
    }
}