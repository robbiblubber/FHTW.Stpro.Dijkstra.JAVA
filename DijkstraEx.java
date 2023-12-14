import java.util.ArrayList;

/** This class provides an implementation of the Dijkstra algorithm using a node class. */
public class DijkstraEx
{
    /** Gets the shortest path between nodes.
     * @param graph Graph.
     * @param start Start node.
     * @param end End node.
     * @return Returns the path as an array of nodes. */
    public static ArrayList<Node> getPath(int[][] graph, int start, int end)
    {
        Node.init(graph);                                                       // initializes the node representation of the graph

        Node cur = Node.nodes.get(start);                                       // set the current node to the start node
        cur.distance = 0;                                                       // making the start node final with distance 0
        cur.isFinal = true;

        while(cur.name != end)
        {                                                                       // walk through nodes
            for(Neighbour i: cur.neigbours)                                     // iterate all neighbours
            {
                if(!i.node.isFinal)
                {                                                               // recalculate distance for tentative neigbours
                    int d = cur.distance + i.distance;
                    if(d < i.node.distance) { i.node.distance = d; }
                }
            }

            cur = Node.getNearestTentative();                                   // retreive tentative node with least distance to start, make it current
            if(cur == null) break;                                              // if there is no tentative node left, there is no path to end
            cur.isFinal = true;                                                 // make current (nearest) node final
        }

        ArrayList<Node> rval = new ArrayList<>();                               // create return list

        cur = Node.nodes.get(end);                                              // make end node current
        rval.add(Node.nodes.get(end));                                          // add end node to list

        while(cur.name != start)
        {                                                                       // walk through nodes from end to start
            for(Neighbour i: cur.ancestors)
            {                                                                   // look in ancestors for a consistent node
                if(i.node.isFinal && (i.node.distance + i.distance == cur.distance))
                {
                    rval.add(0, i.node);
                    cur = i.node;
                    break;
                }
                if(cur.name == end) break;                                      // if current node is still the end node, there is no path
            }
        }

        return rval;                                                            // return the list of nodes
    }
}
