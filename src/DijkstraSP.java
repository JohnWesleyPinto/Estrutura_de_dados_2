public class DijkstraSP {
    private double[] distTo;
    private DirectedEdge[] edgeTo;
    private IndexMinPQ<Double> pq;

    public DijkstraSP(EdgeWeightedDigrapg G, int s){
        for(DirectedEdge e : G.edges()){
            if(e.weight()<0){
                throw new IllegalArgumentException("edge "+ e + " has negative weight");
            }
        }

        distTo = new Double[G.V()];
        edgeTo = new DirectedEdge[G.V()];

        validateVertex(s);

        for(int v = 0; v < G.V(); v++){
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        distTo[s] = 0.0;

        pq = new IndexMinPQ<Double>(G.V());
        pq.insert(s, distTo[s]);
        while(!pq.isEmpty()){
            int v = pq.delMin();
            for(DirectedEdge e : G.adj(v)){
                relax(e);
            }

        }
        assert check(G,s);
    }
    private void relax(DirectEdge e){
        int v = e.from(), w = e.to();
        if (distTo[w] > distTo[v]+e.weight()) {
            distTo[w] = distTo[v] + e.weight();
            edgeTo[w] = e;
            if(pq.contains(w)){
                pq.descreaseKey(w, distTo[w]);
            }else{
                pq.insert(w, distTo[w]);
            }

        }
    }
    public double distTo(int v){
        validateVertex(v);
        return distTo[v];
    }
    public boolean hasPathto(int v){
        validateVertex(v);
        return distTo[v] < Double.POSITIVE_INFINITY;
    }
    public Iterable<DirectedEdge> pathTo(int v){
        validateVertex(v);
        if(!hasPathto())
    }

    // pagina 3 Interable das imagens
}