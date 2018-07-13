package jianzhioffer;

import java.util.HashMap;

public class offer50 {
    /**
     * 利用Hash表，需要遍历两次字符串
     * @param str
     * @return
     */
    public static int FirstNotRepeatingChar(String str) {
        if(str == null || str.length() <= 0)
            return -1;
        HashMap<Character,Integer> map = new HashMap<>();
        for(int i = 0;i < str.length();i++){
            char c = str.charAt(i);
            if(!map.containsKey(c)){
                map.put(c,1);
            }else{
                int count = map.get(c).intValue();
                map.put(c,count + 1);
            }
        }
        //System.out.println(map.toString());
        for(int i = 0;i < str.length();i++){
            if(map.get(str.charAt(i)).intValue() == 1)
                return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        int result = FirstNotRepeatingChar("abaccdeff");
        System.out.println(result);
    }
}
