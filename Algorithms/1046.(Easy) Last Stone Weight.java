/****************************************************************************************************
1046. Last Stone Weight
1046. 最后一块石头的重量

Difficulty: Easy

We have a collection of rocks, each rock has a positive integer weight.
Each turn, we choose the two heaviest rocks and smash them together. 
Suppose the stones have weights x and y with x <= y.  The result of this smash is:
If x == y, both stones are totally destroyed;
If x != y, the stone of weight x is totally destroyed, and the stone of weight y has new weight y-x.
At the end, there is at most 1 stone left.  Return the weight of this stone (or 0 if there are no stones left.)

有一堆石头，每块石头的重量都是正整数。
每一回合，从中选出两块最重的石头，然后将它们一起粉碎。
假设石头的重量分别为 x 和 y，且 x <= y。那么粉碎的可能结果如下：
如果 x == y，那么两块石头都会被完全粉碎；
如果 x != y，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为 y-x。
最后，最多只会剩下一块石头。返回此石头的重量。如果没有石头剩下，就返回 0。

Example 1:
Input: [2,7,4,1,8,1]
Output: 1
Explanation: 
We combine 7 and 8 to get 1 so the array converts to [2,4,1,1,1] then,
we combine 2 and 4 to get 2 so the array converts to [2,1,1,1] then,
we combine 2 and 1 to get 1 so the array converts to [1,1,1] then,
we combine 1 and 1 to get 0 so the array converts to [1] then that's the value of last stone.

Note:
1 <= stones.length <= 30
1 <= stones[i] <= 1000

****************************************************************************************************/


/****************************************************************************************************
解题思路：
每一次循环函数都要先重新排序数组，将排在最后的两个最大值做差，差值赋值给最后一位，将 0 赋值给倒数第二位。
循环直到最后只剩下两块石头有正整数数值（其余已经被减为0了），返回结果。
****************************************************************************************************/


//Method1:
class Solution {
    public int lastStoneWeight(int[] stones) {
        int last = stones.length - 1;
        
        //一共进行石头总数的次数比较
        for(int i = 1; i <= last; i++) {
            //对石头进行递增排序
            Arrays.sort(stones);
            //最后一个石头即最大的石头，变为排在最后的两个最大值的差值。
            stones[last] = stones[last] - stones[last - 1];
            //倒数第二个归为0。
            stones[last - 1] = 0;
        }
        
        return stones[last];
    }
}


/****************************************************************************************************/


//Method2: 
class Solution {
    public int lastStoneWeight(int[] stones) {
        if(stones.length == 1) {
            return stones[0];
        }
        
        if(stones.length == 2) {
            return Math.abs(stones[1] - stones[0]);
        }
        
        Arrays.sort(stones);
        
        if(stones[stones.length - 3] == 0) {
            return stones[stones.length - 1] - stones[stones.length - 2];
        }
        
        stones[stones.length - 1] -= stones[stones.length - 2];
        stones[stones.length - 2] = 0;
        
        return lastStoneWeight(stones);
    }
}


