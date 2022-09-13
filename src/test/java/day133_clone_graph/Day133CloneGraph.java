package day133_clone_graph;

import java.util.*;

// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}


public class Day133CloneGraph {

    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }
        return cloneGraphDFS(node, new HashMap<>());
    }


    public Node cloneGraphDFS(Node node, HashMap<Node, Node> visited) {
        if (visited.containsKey(node))
            return visited.get(node);

        Node cloneNode = new Node(node.val, new ArrayList());
        visited.put(node, cloneNode);

        for (Node neighbor : node.neighbors) {
            cloneNode.neighbors.add(cloneGraphDFS(neighbor, visited));
        }

        return cloneNode;
    }


    public Node cloneGraphBFS(Node node) {
        Set<Node> visited = new HashSet<>();
        Queue<Node> toVisit = new LinkedList<>();
        toVisit.add(node);
        Map<Node, Node> oldNodeToNewNode = getOldNodeToNewNode(node);

        while (!toVisit.isEmpty()) {
            var current = toVisit.poll();
            if (visited.contains(current)) {
                continue;
            }
            visited.add(current);

            var currentNew = oldNodeToNewNode.get(current);

            for (var n : current.neighbors) {
                currentNew.neighbors.add(oldNodeToNewNode.get(n));
                toVisit.add(n);
            }
        }

        return oldNodeToNewNode.get(node);
    }


    public Map<Node, Node> getOldNodeToNewNode(Node node) {
        Set<Node> visited = new HashSet<>();
        Queue<Node> toVisit = new LinkedList<>();
        Map<Node, Node> oldNodeToNewNode = new HashMap<>();
        toVisit.add(node);
        while (!toVisit.isEmpty()) {
            var current = toVisit.poll();
            if (visited.contains(current)) {
                continue;
            }
            visited.add(current);

            oldNodeToNewNode.put(current, new Node(current.val));
            for (var n : current.neighbors) {
                toVisit.add(n);
            }

        }
        return oldNodeToNewNode;
    }

}