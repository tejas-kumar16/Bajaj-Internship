package com.example.demo.service;
import com.example.demo.model.User;
import java.util.*;

public class MutualFollowSolver {
    public List<List<Integer>> findMutualFollowers(List<User> users) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (User user : users) {
            map.put(user.getId(), new HashSet<>(user.getFollows()));
        }
        Set<String> seen = new HashSet<>();
        List<List<Integer>> result = new ArrayList<>();
        for (User user : users) {
            int a = user.getId();
            for (int b : user.getFollows()) {
                if (map.containsKey(b) && map.get(b).contains(a)) {
                    int min = Math.min(a, b);
                    int max = Math.max(a, b);
                    String key = min + "-" + max;
                    if (!seen.contains(key)) {
                        seen.add(key);
                        result.add(List.of(min, max));
                    }
                }
            }
        }
        return result;
    }
}