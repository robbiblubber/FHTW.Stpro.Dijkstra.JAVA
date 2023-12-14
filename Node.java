import java.util.ArrayList;



/** This class represents a node. */
public class Node
{
    ////////////////////////////////////////////////////////////////////////////
    // public static members                                                  //
    ////////////////////////////////////////////////////////////////////////////

    /** A node-based representation of a graph, */
    public static ArrayList<Node> nodes;


    ////////////////////////////////////////////////////////////////////////////
    // public members                                                         //
    ////////////////////////////////////////////////////////////////////////////

    /* A list of successiveor neighbour nodes. */
    public ArrayList<Neighbour> neigbours = new ArrayList<>();

    /* A list of precessor neighbour nodes. */
    public ArrayList<Neighbour> ancestors = new ArrayList<>();

    /* The node name or index. */
    public int name;

    /* Determines if the node if final (or tentative). */
    public boolean isFinal = false;

    /* The distance to the node this is neighbouring. */
    public int distance = Integer.MAX_VALUE;



    ////////////////////////////////////////////////////////////////////////////
    // constructors                                                           //
    ////////////////////////////////////////////////////////////////////////////

    /** Creates a new instance of this class.
     * @param name Node name. */
    public Node(int name)
    {
        this.name = name;
    }



    ////////////////////////////////////////////////////////////////////////////
    // public static methods                                                  //
    ////////////////////////////////////////////////////////////////////////////

    /** Initializes the node representation of a graph.
     * @param graph Graph. */
    public static void init(int[][] graph)
    {
        nodes = new ArrayList<>();                                              // make node list an empty list

        for(int i = 0; i < graph.length; i++)
        {                                                                       // add all nodes from the graph
            nodes.add(new Node(i));
        }

        for(int i = 0; i < graph.length; i++)
        {                                                                       // iterate all nodes
            for(int k = 0; k < graph.length; k++)
            {                                                                   // iterate all neigbouring nodes
                if(graph[i][k] > 0)
                {                                                               // add directly connected nodes to neighbours
                    nodes.get(i).neigbours.add(new Neighbour(nodes.get(k), graph[i][k]));
                }
                if(graph[k][i] > 0)
                {                                                               // add node that directly connect to ancestors
                    nodes.get(i).ancestors.add((new Neighbour(nodes.get(k), graph[k][i])));
                }
            }
        }
    }



    ////////////////////////////////////////////////////////////////////////////
    // public methods                                                         //
    ////////////////////////////////////////////////////////////////////////////

    /** Gets the tentative node with minimal distance to the start.
     * @return Returns the nearest tentative node or NULL if there are no tentative nodes left. */
    public static Node getNearestTentative()
    {
        Node rval = null;

        for(Node i: nodes)
        {                                                                       // iterate all nodes
            if(!i.isFinal)
            {                                                                   // remember nearest
                if((rval == null) || (rval.distance > i.distance)) { rval = i; }
            }
        }
        return rval;                                                            // return result
    }
}
