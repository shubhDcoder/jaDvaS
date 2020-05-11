import java.util.*;

public class L0399_UF {
    public static void main(String args[]) {
        String[][] equationsArray = {{"a", "b"}, {"b", "c"}};
        List<List<String>> equations = new ArrayList<>();
        for(String[] arr : equationsArray) {
            List<String> temp = new ArrayList<>();
            for(String s : arr) temp.add(s);
            equations.add(temp);
        }
        double[] values = {2.0, 3.0};
        String[][] queriesArray = {{"a", "c"}, {"b", "a"}, {"a", "e"}, {"a", "a"}, {"x", "x"}};
        // String[][] queriesArray = {{"b", "a"}};
        List<List<String>> queries = new ArrayList<>();
        for(String[] arr : queriesArray) {
            List<String> temp = new ArrayList<>();
            for(String s : arr) temp.add(s);
            queries.add(temp);
        }

        System.out.println(Arrays.toString(new L0399_UF().calcEquation(equations, values, queries)));
    }

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        
        double[] res = new double[queries.size()];
        Map<String, String> parent = new HashMap<>();
        Map<String, Double> distance = new HashMap<>();
        
        for(int i=0; i<equations.size(); i++){
            String r1 = find(parent, distance, equations.get(i).get(0));
            String r2 = find(parent, distance, equations.get(i).get(1));
            parent.put(r1,r2);
            distance.put(r1, distance.get(equations.get(i).get(1)) * values[i] / distance.get(equations.get(i).get(0)));
        }
        
        for(int i=0; i<queries.size(); i++){
            if(!parent.containsKey(queries.get(i).get(0)) || !parent.containsKey(queries.get(i).get(1)) ){
                res[i] = -1.0;
                continue;
            }
            
            String r1 = find(parent, distance, queries.get(i).get(0));
            String r2 = find(parent, distance, queries.get(i).get(1));
            
            if(!r1.equals(r2)){
                res[i] = -1.0;
                continue;
            }
            
            res[i] = (double) distance.get(queries.get(i).get(0))/distance.get(queries.get(i).get(1));
            
        }
        
        return res;
    }
    
    public String find( Map<String, String> parent, Map<String, Double> distance, String s){
        if(!parent.containsKey(s)){
            parent.put(s,s);
            distance.put(s,1.0);
            return s;
        }
        
        if(parent.get(s).equals(s)) return s;
        String p = parent.get(s);
        String newParent = find(parent, distance, p);
        parent.put(s, newParent);
        distance.put(s, distance.get(s) * distance.get(p));
        return newParent;
    }
}


// DFS approach

/*
class Solution {
    static class Edge {
        String toVertex;
        double weight;

        public Edge(String toVertex, double weight) {
            this.toVertex = toVertex;
            this.weight = weight;
        }
    }

    Map<String, List<Edge>> graph = new HashMap<>();
    Set<String> visited = new HashSet<>();

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {

        double[] answer = new double[queries.size()];

        int count = 0;
        for(List<String> equation : equations) {
            addEdge(equation.get(0), equation.get(1), values[count++]);
        }

        count = 0;
        for(List<String> query : queries) {
            String source = query.get(0);
            String dest = query.get(1);
            if(!graph.containsKey(source) || !graph.containsKey(dest)) {
                answer[count++] = -1;
                continue;
            }
            if(source.equals(dest)) {
                answer[count++] = 1;
                continue;   
            }
            answer[count++] = dfs(new Edge(source, 1), dest);
        }
        return answer;
    }

    public double dfs(Edge source, String dest) {
        if(source.toVertex.equals(dest)) {
            return 1;
        }
        double ans = -1.0;
        visited.add(source.toVertex);
        System.out.println("came for " + source.toVertex + " " + source.weight);
        for(Edge edge : graph.get(source.toVertex)) {
            if(!visited.contains(edge.toVertex)) {
                ans = dfs(edge, dest);
                if(ans != -1) {
                    ans = ans * edge.weight;
                    break;
                }
            }
        }
        visited.remove(source.toVertex);
        return ans;
    }

    public void addEdge(String u, String v, double weight) {
        Edge node1 = new Edge(v, weight);
        Edge node2 = new Edge(u, (1 / weight));

        graph.putIfAbsent(u, new ArrayList<>());
        graph.putIfAbsent(v, new ArrayList<>());

        graph.get(u).add(node1);
        graph.get(v).add(node2);
    }
} */