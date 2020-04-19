/****************************************************************************************************
930. Binary Subarrays With Sum
930. 和相同的二元子数组

Difficulty: Medium

In an array A of 0s and 1s, how many non-empty subarrays have sum S?
在由若干 0 和 1  组成的数组 A 中，有多少个和为 S 的非空子数组。

Example 1:
Input: A = [1,0,1,0,1], S = 2
Output: 4
Explanation: 
The 4 subarrays are bolded below:
[[1,0,1],0,1]
[[1,0,1,0],1]
[1,[0,1,0,1]]
[1,0,[1,0,1]]

Note:
A.length <= 30000
0 <= S <= A.length
A[i] is either 0 or 1.
****************************************************************************************************/


/****************************************************************************************************
解题思路01：
Map<key, value> = <当前总和，总和的出现次数>
如果能在 map 中找到 key = (当前总和 sum - s)，那么从那个元素到当前元素的子数组就符合条件。
再将当前数据更新，即将当前总和出现次数 +1 存储入 map。
****************************************************************************************************/


class Solution {
    public int numSubarraysWithSum(int[] A, int S) {
        if (A == null || A.length == 0) {
            return 0;
        }
       
        int answer = 0;
        int sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        
        //放入初始值
        map.put(0, 1);
        for(int i = 0; i < A.length; i++) {
            sum += A[i];
            if(map.containsKey(sum - S)) {
                answer += map.get(sum - S);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        
        return answer;
    }
}


/****************************************************************************************************
解题思路02：
设P[i] = A[0] + …… + A[i-1];
那么P[j+1] - P[i] = A[i] + …… + A[j]；就代表着数组元素 i 到 j 的所有和，也就是一个子数组的和。
只要判断上式 P[j+1] - P[i] = S 的个数就可以了。
遍历数组 P，并找到每个满足 P[j] = P[i] + S；并且 i<j 的个数。
Map<key, value> = <P[i]+S，出现次数>，这就代表着，每个 P[i]+S 也就是上式的 P[j] 的个数。
在遍历数组 P 时，把所有P[j] (P[i] + S) 的 value 值取出来加和，得到 answer。并将每个新的 (P[j] + S) 存入到 map 中，为接下来使用。
****************************************************************************************************/


class Solution {
    public int numSubarraysWithSum(int[] A, int S) {
        if (A == null || A.length == 0) {
            return 0;
        }
        
        int[] P = new int[A.length + 1]; 
        //存储该元素之前所有元素的总和（不包括自己）。P[0] = 0
        for(int i = 0; i < A.length; i++) {
            P[i+1] = P[i] + A[i];
        }
        
        int answer = 0;
        Map<Integer, Integer> map = new HashMap<>();
        
        for(int n = 0; n < P.length; n++) {
            //找到在 n 之前， P[n] ,即 (任意元素 P[i] + S) 存在的个数。
            answer += map.getOrDefault(P[n], 0);
            //将自己存入 map
            map.put(S + P[n], map.getOrDefault(S + P[n], 0) + 1);
        }
        
        return answer;
    }
}


