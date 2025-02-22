import java.util.Stack;

public class EdgeWeightDiagraph {
    private static final String NEWLINE = System.getProperty("line.separator");

    private final int V;
    private int E;
    private Stack<DirectedEdge>[] adj;
    private int[] indegree;

    public EdgeWeightDiagraph(int V){
        if(V<0) throw new IllegalArgumentException("Number of vertices in a Diagraph must be non-negative");
        this.V = V;
        this.E = 0;
        this.indegree = new int[V];
        adj= (Stack<DirectedEdge>[]) new Stack[V];
        for (int v = 0; v < V; v++){
            adj[v] = new Stack<DirectedEdge>();
        }
    }
    public EdgeWeightDiagraph(int V, int E){
        this(V);
        if(E < 0)throw new IllegalArgumentException("Number of vertices in a Diagraph must be non-negative");
        for(int i = 0; i < E; i++){
            int v = StdRandom.uniform(V);
            int w = StdRandom.uniform(V);
            double weight = 0.01 * StdRandom.uniform(100);
            DirectedEdge e = new DirectedEdge(v,w,weight);
            addEdge(e);
        }
    }
}
