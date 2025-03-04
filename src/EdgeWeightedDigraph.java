import java.util.NoSuchElementException;
import java.util.Stack;

public class EdgeWeightedDigraph {
    private static final String NEWLINE = System.getProperty("line.separator");

    private final int V;
    private int E;
    private Stack<DirectedEdge>[] adj;
    private int[] indegree;

    @SuppressWarnings("unchecked")
    public EdgeWeightedDigraph(int V){
        if(V<0) throw new IllegalArgumentException("Number of vertices in a Digraph must be non-negative");
        this.V = V;
        this.E = 0;
        this.indegree = new int[V];
        adj= (Stack<DirectedEdge>[]) new Stack<?>[V];
        for (int v = 0; v < V; v++){
            adj[v] = new Stack<>();
        }
    }
    public EdgeWeightedDigraph(int V, int E){
        this(V);
        if(E < 0)throw new IllegalArgumentException("Number of vertices in a Digraph must be non-negative");
        for(int i = 0; i < E; i++){
            int v = StdRandom.uniform(V);
            int w = StdRandom.uniform(V);
            double weight = 0.01 * StdRandom.uniform(100);
            DirectedEdge e = new DirectedEdge(v,w,weight);
            addEdge(e);
        }
    }
    @SuppressWarnings("unchecked")
    public EdgeWeightedDigraph(In in){
        if(in ==null) throw new IllegalArgumentException("argument is null");
        try{
            this.V = in.readInt();
            if(V<0) throw new IllegalArgumentException("number of vertices in a Digraph must be non-negative");
            indegree = new int[V];
            adj = (Stack<DirectedEdge>[]) new Stack<?>[V];
            for (int v = 0; v < V; v++){
                adj[v] = new Stack<DirectedEdge>();
            }
            int E = in.readInt();
            if(E<0) throw new IllegalArgumentException("number of edges must be non-negative");
            for(int i = 0; i<E; i++){
                int v = in.readInt();
                int w = in.readInt();
                validateVertex(v);
                validateVertex(w);
                double weight = in.readDouble();
                addEdge(new DirectedEdge(v,w,weight));
            }
        }catch (NoSuchElementException e){
            throw new IllegalArgumentException("invalid input format in EdgeWeightedDigraph constructor", e);
        }
    }
    public EdgeWeightedDigraph(EdgeWeightedDigraph G){
        this(G.V());
        this.E = G.E();
        for(int v =0; v < G.V(); v++){
            this.indegree[v] = G.indegree(v);
        }
        for(int v = 0; v < G.V(); v++){
            Stack<DirectedEdge> reverse = new Stack<DirectedEdge>();
            for(DirectedEdge e : G.adj[v]){
                reverse.push(e);
            }
            for(DirectedEdge e: reverse){
                adj[v].push(e);
            }
        }
    }
    public int V(){
        return V;
    }
    public int E(){
        return E;
    }
    private void validateVertex(int v){
        if(v < 0|| v>= V){
            throw new IllegalArgumentException("vertex "+ v +" is not between 0 and "+ (V-1));
        }
    }
    public void addEdge(DirectedEdge e){
        int v = e.from();
        int w = e.to();
        validateVertex(v);
        validateVertex(w);
        adj[v].push(e);
        indegree[w]++;
        E++;
    }
    public Iterable<DirectedEdge> adj(int v){
        validateVertex(v);
        return adj[v];
    }
    public int outdegree(int v){
        validateVertex(v);
        return adj[v].size();
    }
    public int indegree(int v){
        validateVertex(v);
        return indegree[v];
    }
    public Iterable<DirectedEdge> edges(){
        Stack<DirectedEdge> list = new Stack<DirectedEdge>();
        for(int v = 0; v < V; v++){
            for (DirectedEdge e : adj(v)){
                list.push(e);
            }
        }
        return list;
    }
    public String toString(){
        StringBuilder s = new StringBuilder();
        s.append(V+ " "+ E + NEWLINE);
        for(int v=0; v< V;v++){
            s.append(v+ ": ");
            for(DirectedEdge e: adj[v]){
                s.append(e+" ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(in);
        StdOut.println(G);
    }
}
