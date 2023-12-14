/* This class represents an neighbouring node, successor or ancessor. */
public class Neighbour
{
    /** Contains the neighbouring node object. */
    public Node node;

    /** Contains the distance to the node. */
    public int distance;


    /** Creates a new instance of this class.
     * @param n Node.
     * @param d Distance. */
    public Neighbour(Node n, int d)
    {
        node = n;
        distance = d;
    }
}
