package day207_course_schedule;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class Day207CourseSchedule {
    @Test
    public void testGraphWithoutCycle() {
        assertThat(canFinish(2, new int[][]{{1, 0}})).isTrue();
    }

    @Test
    public void testGraphWithCycle() {
        assertThat(canFinish(2, new int[][]{{1, 0}, {0, 1}})).isFalse();
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // can finish if topological ordering has size numCourses
        // used Kahn's Algorithm
        var inDegree = new int[numCourses];
        for (var pr: prerequisites) {
            inDegree[pr[0]]++;
        }
        var adjList = new HashMap<Integer, List<Integer>>();
        for(var i = 0; i < numCourses; ++i) {
            adjList.put(i, new ArrayList<>());
        }
        for (var pr: prerequisites) {
            adjList.get(pr[1]).add(pr[0]);
        }

        var q = new LinkedList<Integer>();
        for(var i = 0; i < numCourses; ++i) {
            if (inDegree[i] == 0) {
                q.add(i);
            }
        }
        var topSortOrder = new ArrayList<Integer>();
        while (!q.isEmpty()) {
            var current = q.poll();
            topSortOrder.add(current);
            var currentTo = adjList.get(current);
            for (var n: currentTo) {
                inDegree[n]--;
                if (inDegree[n] == 0) {
                    q.add(n);
                }
            }
        }
        return topSortOrder.size() == numCourses;
    }
}
