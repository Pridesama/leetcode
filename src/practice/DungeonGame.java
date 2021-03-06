package practice;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/dungeon-game/
 * 一些恶魔抓住了公主（P）并将她关在了地下城的右下角。
 * 地下城是由 M x N 个房间组成的二维网格。
 * 我们英勇的骑士（K）最初被安置在左上角的房间里，他必须穿过地下城并通过对抗恶魔来拯救公主。
 *
 * 骑士的初始健康点数为一个正整数。如果他的健康点数在某一时刻降至 0 或以下，他会立即死亡。
 * 有些房间由恶魔守卫，因此骑士在进入这些房间时会失去健康点数（若房间里的值为负整数，则表示骑士将损失健康点数）；
 * 其他房间要么是空的（房间里的值为 0），要么包含增加骑士健康点数的魔法球（若房间里的值为正整数，则表示骑士将增加健康点数）。
 * 为了尽快到达公主，骑士决定每次只向右或向下移动一步。
 *
 * 编写一个函数来计算确保骑士能够拯救到公主所需的最低初始健康点数。
 * 例如，考虑到如下布局的地下城，如果骑士遵循最佳路径 右 -> 右 -> 下 -> 下，则骑士的初始健康点数至少为 7。
 * -2 (K)   -3      3
 * -5       -10     1
 * 10       30      -5 (P)
 *  
 *
 * 说明:
 * 骑士的健康点数没有上限。
 * 任何房间都可能对骑士的健康点数造成威胁，也可能增加骑士的健康点数，包括骑士进入的左上角房间以及公主被监禁的右下角房间。
 */
public class DungeonGame {
    public int calculateMinimumHP(int[][] dungeon) {
        int m = dungeon.length /* 行 */, n = dungeon[0].length /* 列 */;
        int[][] k = new int[m + 1][n + 1];
        k[m - 1][n - 1] = Math.max(1, 1 - dungeon[m - 1][n - 1]);
        for (int i = m - 2; i >= 0; i --) {// 右下角向上
            k[i][n - 1] = Math.max(1, k[i + 1][n - 1] -  dungeon[i][n - 1]);
        }
        for (int j = n - 2; j >= 0; j --) {// 右下角向左
            k[m - 1][j] = Math.max(1, k[m - 1][j + 1] -  dungeon[m - 1][j]);
        }
        for (int i = m - 2; i >= 0; i --) {
            for (int j = n - 2; j >= 0; j --) {
                k[i][j] = Math.max(1, Math.min(k[i + 1][j], k[i][j + 1]) - dungeon[i][j]);
            }
        }
        return k[0][0];
    }

    public static void main (String[] args) {
        DungeonGame solution = new DungeonGame();

        Map<int[][], Integer> expects = new HashMap<>(2);
        expects.put(new int[][]{
                {-2, -3, 3},
                {-5, -10, 1},
                {10, 30, -5}
        }, 7);
        expects.put(new int[][]{
                {2, 1},
                {1, -1}
        }, 1);

        expects.forEach((k, v) -> {
            System.out.println(String.format("expected %d, actual %d",
                    v, solution.calculateMinimumHP(k)));
        });
    }
}
