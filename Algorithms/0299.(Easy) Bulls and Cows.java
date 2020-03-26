/****************************************************************************************************
299. Bulls and Cows

Difficulty: Easy

You are playing the following Bulls and Cows game with your friend: 
You write down a number and ask your friend to guess what the number is. 
Each time your friend makes a guess, you provide a hint that indicates: 
how many digits in said guess match your secret number exactly in both digit and position (called "bulls") 
and how many digits match the secret number but locate in the wrong position (called "cows"). 
Your friend will use successive guesses and hints to eventually derive the secret number.

Write a function to return a hint according to the secret number and friend's guess, 
use A to indicate the bulls and B to indicate the cows. 
Please note that both secret number and friend's guess may contain duplicate digits.

你正在和你的朋友玩 猜数字（Bulls and Cows）游戏：你写下一个数字让你的朋友猜。
每次他猜测后，你给他一个提示，
告诉他有多少位数字和确切位置都猜对了（称为“Bulls”, 公牛），有多少位数字猜对了但是位置不对（称为“Cows”, 奶牛）。
你的朋友将会根据提示继续猜，直到猜出秘密数字。

请写出一个根据秘密数字和朋友的猜测数返回提示的函数，用 A 表示公牛，用 B 表示奶牛。
请注意秘密数字和朋友的猜测数都可能含有重复数字。

Example 1:
Input: secret = "1807", guess = "7810"
Output: "1A3B"
Explanation: 1 bull and 3 cows. The bull is 8, the cows are 0, 1 and 7.

Example 2:
Input: secret = "1123", guess = "0111"
Output: "1A1B"
Explanation: The 1st 1 in friend's guess is a bull, the 2nd or 3rd 1 is a cow.

Note: You may assume that the secret number and your friend's guess only contain digits, and their lengths are always equal.
/****************************************************************************************************


/****************************************************************************************************
需要统计两个数据：
同时猜中数字和位置的次数，用A记录。
只猜中数字而位置不对的次数，用B记录。

第一个数据的统计，只需遍历一次secret字符串每个字符，若guess中的字符位置和值都相同，则A++。
第二个数据的统计，只需注意一点，假设guess中含有3个数字x，而secret中只含有2个，则B应该为2。由此，我们需要知道secret中所有没被猜中数值和位置的数字有哪些，以及它们出现的次数，同理，我们也需要知道guess中这样的数字有哪些和它们出现的次数。

我们采用两个哈希表记录这两个字符串中这些多余的数字和它们出现的次数。由于数字只有0到9十个，所以用长度为10的int[]数组作为哈希表即可满足需求。

在第一次循环中，对secret中没被猜中位置和数值的数字i，存放在哈希表int[] secret1中，次数secret1[i]++；对guess中没被猜中位置和数值的数字i，存放在哈希表int[] guess1中，次数guess1[i]++。
两个哈希表记录完成后，遍历哈希表，累加哈希表每个位置的Math.min(secret1[i], guess1[i])就能得到B。

时间复杂度：O(n)。需要遍历字符串每个字符。
空间复杂度：O(1)。哈希表大小固定为10，因为只有0到9十个数字。
****************************************************************************************************/


class Solution {
    public String getHint(String secret, String guess) {
        int A = 0, B = 0;
        int[] secret1 = new int[10];
        int[] guess1 = new int[10];
        
        for(int i = 0; i < secret.length(); i++)
        {
            //数字和ASCII码之间的转换
            int a = secret.charAt(i) - '0';
            int b = guess.charAt(i) - '0';
            if(a == b)  A++;
            else
            {
                secret1[a]++;
                guess1[b]++;
            }   
        }
        for(int i = 0; i < 10; i++) {
            B += Math.min(secret1[i], guess1[i]);
        }

        return A + "A" + B + "B";
    }
}


