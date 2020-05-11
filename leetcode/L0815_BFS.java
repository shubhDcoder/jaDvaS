import java.util.*;

public class L0815_BFS {
    public static void main(String args[]) {

        int[][] routes = {{1, 2, 7}, {3, 6, 7}};
        int S = 1;
        int T = 6;

        System.out.println(new L0815_BFS().numBusesToDestination(routes, S, T));
    }

    public int numBusesToDestination(int[][] routes, int S, int T) {
        if(S == T) return 0;
        if(routes.length == 0 || routes[0].length == 0) return 0;

        Map<Integer, List<Integer>> invertIndex = new HashMap<>();

        for(int i = 0; i < routes.length; i++) {
            for(int ele : routes[i]) {
                invertedIndex.putIfAbsent(ele, new ArrayList<>());
                invertedIndex.get(ele).add(i);
                }
            }
        }

        Set<Integer> visited = new HashSet<>();
        byte[] busStandMark = new byte[routes.length];
        LinkedList<Integer> queue = new LinkedList<>();
        queue.addLast(S);
        visited.add(S);
        busStandMark[S] = true;

        int level = 0;

        while(queue.size() > 0) {
            int size = queue.size();
            while(size-- > 0) {
                int removed = queue.removeFirst();
                for(int bus : invertIndex.get(removed)) {
                    if(busStandMark[bus] == true) continue;
                    for(int dest : routes[bus]) {
                        if(visited.contains(dest)) continue;
                        if(dest == T) return level + 1;
                        queue.addLast(dest);
                        visited.add(dest);
                    }
                }
            }
            level++;
        }

        return -1;
    }
}