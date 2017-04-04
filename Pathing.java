public class Pathing{
	int vert;
    int dist[]; 
    Boolean shortest[];
    
	public Pathing(int value){
		vert = value;
		dist = new int[vert];
		shortest = new Boolean[vert];
        // Initialize all distances as infinite and shortest as false
        for (int i = 0; i < vert; i++)
        {
            dist[i] = 1000000000;
            shortest[i] = false;
        }
	}

 
    // prints distance
    public void print(int dist[], int source, int destination)
    {
        System.out.println(dist[destination]);
    }
    
    // finds the vertex with minimum distance value,
    // from the vertices not yet included
	public int minDist(int dist[], Boolean shortpath[])
    {
       	int min = 1000000000;
       	int index=-1;
       
       	for (int i = 0; i < vert; i++)
       		if (!shortpath[i] && dist[i] <= min)
           	{
               	min = dist[i];
               	index = i;
           	}
         	return index;
   	
   	}
    // Dijkstra's algorithm for a graph using adjacency matrix
	public void dijkstra(int adjMatrix[][], int source, int destination)
    {
       dist[source] = 0;
        // Find shortest path for all vertices
        for (int i = 0; i < vert-1; i++)
        {
            int shortDistance = minDist(dist, shortest);
            // Mark the visited vertex
            shortest[shortDistance] = true;
 
            // Update dist value of the adjacent vertices
            for (int j = 0; j < vert; j++)
 
                // Update dist[j] only if its not in shortest
                // and if there is a shorter path
                if (adjMatrix[shortDistance][j]+dist[shortDistance] < dist[j] && 
                		!shortest[j] && adjMatrix[shortDistance][j]!=0)
                    dist[j] = adjMatrix[shortDistance][j] + dist[shortDistance];
        }
 
        // print the distance
        print(dist, source, destination);
    }
}
 