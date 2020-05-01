import java.util.*;

public class L0207_TS {

    public static void main(String args[]) {
        // System.out.println(new L0207_TS().canFinish(2, new int[][] {{1,0}}));
        System.out.println(new L0207_TS().canFinish(2, new int[][] {{1,0}, {0, 1}}));
    }

    public List<List<Integer>> graph = new ArrayList<>();

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if(prerequisites.length == 0 || prerequisites[0].length == 0) return true;

        for(int i = 0; i < numCourses; i++) graph.add(new ArrayList<>());
        int[] inbound = new int[numCourses];
        for(int i = 0; i < prerequisites.length; i++) {
            graph.get(prerequisites[i][1]).add(prerequisites[i][0]);
            inbound[prerequisites[i][0]]++;
        }

        // System.out.println(Arrays.toString(inbound));
        // for(List<Integer> list : graph) System.out.println(list);

        LinkedList<Integer> queue = new LinkedList<>();
        int answer = 0;

        for(int i = 0; i < inbound.length; i++)
            if(inbound[i] == 0) 
                queue.addLast(i);

        while(queue.size() > 0) {
            int size = queue.size();
            while(size-- > 0) {
                int removed = queue.removeFirst();
                answer++;
                for(int adj : graph.get(removed)) {
                    if(--inbound[adj] == 0) {
                        queue.addLast(adj);
                    }
                }
            }
        }

        if(answer == numCourses) return true;
        else return false;
    }
}