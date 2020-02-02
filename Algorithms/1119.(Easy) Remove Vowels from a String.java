/****************************************************************************************************
1119.Remove Vowels from a String
1119. 删去字符串中的元音

Difficulty: Easy


****************************************************************************************************/


/****************************************************************************************************
解题思路：
1）创建一个包含所有元音vowel的数组链。
2）指针 i 遍历查询当下字母元素s.chatAt(i) 是否在vowel中。
3）如果不在，则说明当前字母不是元音，则将当前字母存入StringBuilder中。
****************************************************************************************************/


class Solution {
    public String reverseVowels(String s) {
        ArrayList<Character> vowel = new ArrayList<>(Arrays.asList('a','e','i','o','u','A','E','I','O','U')); 
        
        StringBuilder sb = new StringBuilder();
        
        for(int i = 0; i < s.length(); i++) {
            if(!vowel.contains(s.charAt(i))) {
                sb.append(s.charAt(i));
            }
        }
        
        return sb.toString();
    }
}


