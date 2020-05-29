import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class TopologicalSort extends Graph implements PrintUtil {
    public static void main(String args[]) {
        PrintUtil.markHeading("TOPOLOGICAL SORTING");
        constructGraph();
        PrintUtil.printLine("Graph looks like.");
        displayGraph();

        PrintUtil.printLine("Sorting using DFS.");
        topologicalSort_start();

        PrintUtil.printLine();
        PrintUtil.syso("can detect cycle in directed graph using Kahn's algorithm : Only valid with BFS Directed graph\n");
        topologicalSortStartWtihCycleDetection();

        PrintUtil.printLine();
        PrintUtil.syso("can detect cycle in directed graph using DFS as well with two array");
        detectCycleUsingDFS_twoArray();

        PrintUtil.printLine();
        PrintUtil.syso("can detect cycle in directed graph using DFS as well with one array");
        detectCycleUsingDFS();
    }

    public static void detectCycleUsingDFS() {
        byte[] marking = new byte[totalNodes];
        LinkedList<Integer> answer = new LinkedList<>();
        boolean result = false;
        for(int i = 0; i < graph.size() && !result; i++) {
            if(marking[i] == 0) {
                result = result || callDfsCycleDetect_01(i, marking, i + "", answer);
            }
        }

        if(result) PrintUtil.syso("Was cycle ");
        else PrintUtil.syso("Sorted arrangement is " + answer);
    }

    public static boolean callDfsCycleDetect_01(int src, byte[] marking, String cycle, LinkedList<Integer> answer) {
        if(marking[src] == 1) {
            PrintUtil.syso("cycle detected " + cycle);
            return true;
        }
        if(marking[src] == 2) return false;

        marking[src] = 1;
        boolean result = false;
        for(int i = 0; i < graph.get(src).size() && !result; i++) {
            result = result || callDfsCycleDetect_01(graph.get(src).get(i), marking, cycle + " " + graph.get(src).get(i), answer);
        }
        marking[src] = 2;
        answer.addFirst(src);
        return result;
    }

    public static void detectCycleUsingDFS_twoArray() {
        boolean visitedMark[] = new boolean[totalNodes];
        boolean cycleMark[] = new boolean[totalNodes];
        LinkedList<Integer> answer = new LinkedList<>();
        boolean result = false;
        for(int i = 0; i < graph.size() && !result; i++) {
            if(visitedMark[i] == false) {
                result = result || callDfsCycleDetect(i, cycleMark, visitedMark, i + "", answer);
            }
        }

        if(result) PrintUtil.syso("Was cycle ");
        else PrintUtil.syso("Sorted arrangement is " + answer);
    }
    
    public static boolean callDfsCycleDetect(int src, boolean[] cycleMark, boolean[] visitedMark, String cycle, LinkedList<Integer> answer) {
        if(cycleMark[src] == true) {
            PrintUtil.syso("cycle detected " + cycle);
            return true;
        }
        if(visitedMark[src] == true) return false;

        visitedMark[src] = true;
        cycleMark[src] = true;
        boolean result = false;

        for(int i = 0; i < graph.get(src).size() && !result; i++) {
            result = result || callDfsCycleDetect(graph.get(src).get(i), cycleMark, visitedMark, cycle + " " + graph.get(src).get(i), answer);
        }

        cycleMark[src] = false;
        answer.addFirst(src);
        return result;
    }

    public static void topologicalSortStartWtihCycleDetection() {
        int[] inOrder = new int[totalNodes];
        for(List<Integer> list : graph) {
            for(int vrtx : list) inOrder[vrtx]++;
        }
        ArrayList<Integer> answer = new ArrayList<>();
        LinkedList<Integer> queue = new LinkedList<>();
        for(int i = 0; i < inOrder.length; i++) {
            if(inOrder[i] == 0) queue.addLast(i);
        }

        while(queue.size() > 0) {
            int size = queue.size();
            while(size-- > 0) {
                int removed = queue.removeFirst();
                answer.add(removed);
                for(int adj : graph.get(removed)) {
                    if(--inOrder[adj] == 0) {
                        queue.addLast(adj);
                    }
                }
            }
        }

        if(answer.size() == totalNodes) {
            PrintUtil.syso("Topologically sorted array is :\t" + answer);
        } else {
            PrintUtil.syso("Cycle detected\t" + answer);
        }
    }

    static List<Integer> ts_answer = new ArrayList<>();
    public static void topologicalSort_start() {
        for(int i = 0; i < totalNodes; i++) {
            if(mark[i] == false) {
                callDfs(i);
            }
        }
        PrintUtil.syso(ts_answer);
    }

    public static void callDfs(int src) {
        mark[src] = true;

        for(Integer adj : graph.get(src)) {
            if(mark[adj] == false)
                callDfs(adj);
        }

        ts_answer.add(src);
    }
}