import java.util.*;

public class L0210_TS {

    public static void main(String[] args) {
        // System.out.println(Arrays.toString(new L0210_TS().findOrder(2, new int[][] {{1, 0}})));
        System.out.println(Arrays.toString(new L0210_TS().findOrder(4, new int[][] {{1, 0}, {2, 0}, {3, 1}, {3, 2}})));
    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        
        int[] inorder = new int[numCourses];
        List<List<Integer>> graph = new ArrayList<>();
        
        for(int i = 0; i < numCourses; i++) graph.add(new ArrayList<Integer>());
        
        for(int i = 0; i < prerequisites.length; i++) {
            graph.get(prerequisites[i][1]).add(prerequisites[i][0]);
            inorder[prerequisites[i][0]]++;
        }
        
        int[] answer = new int[numCourses];
        int answerPosition = 0;
        LinkedList<Integer> queue = new LinkedList<>();
        
        for(int i = 0; i < numCourses; i++) 
            if(inorder[i] == 0) 
                queue.addLast(i);
        
        while(queue.size() > 0) {
            int size = queue.size();
            while(size-- > 0) {
                int removed = queue.removeFirst();
                answer[answerPosition++] = removed;
                for(int adj : graph.get(removed)) {
                    if(--inorder[adj] == 0) queue.addLast(adj);
                }
            }
        }
        
        if(answerPosition != numCourses) return new int[] {};
        else return answer;
    }
}