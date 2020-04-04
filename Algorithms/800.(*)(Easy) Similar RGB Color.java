/****************************************************************************************************
800. Similar RGB Color

Difficulty: Easy

In the following, every capital letter represents some hexadecimal digit from 0 to f.
The red-green-blue color "#AABBCC" can be written as "#ABC" in shorthand. For example, "#15c" is shorthand for the color "#1155cc".
Now, say the similarity between two colors "#ABCDEF" and "#UVWXYZ" is -(AB - UV)^2 - (CD - WX)^2 - (EF - YZ)^2.
Given the color "#ABCDEF", return a 7 character color that is most similar to #ABCDEF, and has a shorthand. 
(that is, it can be represented as some "#XYZ")

在本题中，每个大写字母代表从 0 到 f 的一些十六进制数字。
红绿蓝三元色 "#AABBCC" 可以简写为 "#ABC"。 例如，"#15c" 是颜色 "#1155cc" 的简写。
现在，假设两种颜色 "#ABCDEF" 和 "#UVWXYZ" 之间的相似性是 - (AB - UV) ^ 2 - (CD - WX) ^ 2 - (EF - YZ) ^ 2。
给定颜色 "#ABCDEF"，返回与 "#ABCDEF" 最相似且含有一个简写的7字符颜色（也就是说，它可以用类似 "#XYZ" 的形式表示）。

Example 1:
Input: color = "#09f166"
Output: "#11ee66"
Explanation:  
The similarity is -(0x09 - 0x11)^2 -(0xf1 - 0xee)^2 - (0x66 - 0x66)^2 = -64 -9 -0 = -73.
This is the highest among any shorthand color.

Example 2:
Input: color = "#010000"
Output: "#000000"
Explanation:  
The similarity is -(0x01 - 0x00)^2 -(0x00 - 0x00)^2 - (0x00 - 0x00)^2 = -1 -0 -0 = -1.
This is the highest among any shorthand color.

Notice
color is a string of length 7.
color is a valid RGB color: for i > 0, color[i] is a hexadecimal digit from 0 to f
Any answer which has the same (highest) similarity as the best answer will be accepted.
All inputs and outputs should use lowercase letters, and the output is 7 characters.
****************************************************************************************************/


/****************************************************************************************************
解题思路：
对输入的string，从index 1开始，把每两个字符的子字符串转换为最接近的16进制”AA"形式的数，然后拼在一起就可以了。
要注意的一点是，颜色字符串必须是7个字符，记得对于0一定要转化成00才可以。


这道题定义了一种表示颜色的十六进制字符串，然后说是有一种两两字符相等的颜色可以缩写。
然后又给了我们一个人一的字符串，让我们找出距离其最近的可以缩写的颜色串。
通过分析题目中给的例子, 我们知道可以将给定的字符串拆成三个部分，每个部分分别来进行处理。
比如对于字符串"#09f166"来说，我们就分别处理"09"，"f1"，"66"即可。
我们的目标是要将每部分的两个字符变为相同，并且跟原来的距离最小。
那么实际上我们并不需要遍历所有的组合，因为比较有参考价值的就是十位上的数字。
因为如果十位上的数字不变，或者只是增减1，而让个位上的数字变动大一些，这样距离会最小，因为个位上的数字权重最小。
就拿"09"来举例，这个数字可以变成"11"或者"00"，十六进制数"11"对应的十进制数是17，跟"09"相差了8，而十六进制数"00"对应的十进制数是0，跟"09"相差了9。
显然我们选择"11"会好一些。
所以我们的临界点是"8"，如果个位上的数字大于"8"，那么十位上的数就加1。

下面来看如何确定十位上的数字，比如拿"e1"来举例，其十进制数为225，其可能的选择有"ff"，"ee"，和"dd"，其十进制数分别为255，238，和221。
我们目测很容易看出来是跟"dd"离得最近，但是怎么确定十位上的数字呢。
我们发现"11"，"22"，"33"，"44"... 这些数字之间相差了一个"11"，十进制数为17，所以我们只要将原十六进制数除以一个"11"，就知道其能到达的位置。
比如"e1"除以"11"，就只能到达"d"，那么十进制上就是"d"，至于个位数的处理情况跟上面一段讲解相同，我们对"11"取余，然后跟临界点"8"比较。
如果个位上的数字大于"8"，那么十位上的数就加1。这样就可以确定正确的数字了，那么组成正确的十六进制字符串即可。
****************************************************************************************************/


public class Solution {
    /**
     * @param color: the given color
     * @return: a 7 character color that is most similar to the given color
     */
    public String similarRGB(String color) {
        StringBuilder res = new StringBuilder("#");
        
        for(int i = 1; i < color.length(); i = i + 2) {
            res.append(process(color.substring(i, i + 2)));
        }

        return res.toString();
    }

    public String process(String hex) {
        int[] nums = new int[] {0x0, 0x11, 0x22, 0x33, 0x44, 0x55, 0x66, 0x77, 0x88, 0x99, 0xAA, 0xBB, 0xCC, 0xDD, 0xEE, 0xFF};

        int num = Integer.parseInt(hex, 16);
        int res = nums[0];

        for(int i = 1; i < nums.length; i++) {
            if(Math.abs(num - nums[i]) < Math.abs(num - res)) {
                res = nums[i];
            }
        }

        if(res != 0x0)
            return Integer.toHexString(res);
        else
            return "00";
    }
}


