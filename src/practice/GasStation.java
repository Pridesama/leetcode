package practice;

/**
 * https://leetcode.com/problems/gas-station/
 *
 * There are N gas stations along a circular route, where the amount of gas at station i is gas[i].
 * you have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1).
 * You begin the journey with an empty tank at one of the gas stations.
 * Return the starting gas station's index if you can travel around the circuit once, otherwise return -1.
 *
 * Created by mukui on 3/12/15.
 */
public class GasStation {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length, start = n - 1, cursor = 0;
        int[] extra = new int[n + 1];
        while (start >= 0) {
            // if from this start can conquer last time's failure (to cursor)
            extra[start] = gas[start] - cost[start] + extra[start + 1];
            if (extra[start] >= 0) {
                for (int i = cursor; i != start; i = (i + 1) % (n + 1) /* next */) {
                    // after travel from i to i+1, there remain gas
                    extra[start] += gas[i] - cost[i];
                    if (extra[start] < 0) {
                        // start from this {start} is not okay when met {cursor}, roll back start
                        cursor = (i + 1) % (n + 1);
                        break;
                    }
                }
                if (extra[start] >= 0) return start;
            }
            --start;
        }
        return start;
    }
}
