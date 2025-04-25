package com.example.demo.service;
import com.example.demo.model.User;
import java.util.*;

public class FollowSolver {
    public List<Integer> findNthLevelFollowers(List<User> users, int startId, int level) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (User user : users) {
            graph.put(user.getId(), user.getFollows());
        }
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(startId);
        visited.add(startId);
        int current = 0;
        while (!queue.isEmpty() && current < level) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int node = queue.poll();
                for (int follower : graph.getOrDefault(node, new ArrayList<>())) {
                    if (!visited.contains(follower)) {
                        visited.add(follower);
                        queue.add(follower);
                    }
                }
            }
            current++;
        }
        return new ArrayList<>(queue);
    }
}