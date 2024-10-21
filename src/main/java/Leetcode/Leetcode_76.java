package Leetcode;

// 最小覆盖字串，滑动窗口解
public class Leetcode_76 {
    public static String minWindow(String s, String t) {
        int tLen = t.length();
        int sLen = s.length();
        if (sLen < tLen) {
            return "";
        }
        int[] arr = new int[58]; // 创建一个包含大小写字母的数组用来判断字符串中是否有某个字符

        for (int i = 0; i < tLen; i++) { //初始化数组
            arr[t.charAt(i) - 'A']++;
        }
        int l = 0;      //滑动窗口的左边界
        int start = -1;     // 目标字符的开始和结束索引必原字符大方便后续判断是否存在最小字串
        int end = sLen;
        int count = 0;  // 计数，当count== tLen的时候说明一个覆盖字串出现了
        for (int r = 0; r < sLen; r++) { // r 为右边界
            int idx = s.charAt(r) - 'A';
            if (--arr[idx] >= 0) {   // 当某个位置的索引减完以后还>=0说明匹配到了一个字符，count+1
                count++;
            }
            while (count == tLen) {  // 此时开始判断是否是最小字串
                int lIdx = s.charAt(l) - 'A';
                // 通过左边界索引相加是可以恢复之前有边界--的影响，如果加完以后大于0，说明此时l的位置即为字串的左边界，r为有边界
                if (++arr[lIdx] > 0) {
                    if (r - l < end - start) {
                        start = l;
                        end = r;
                    }
                    count--;  // 找到一个字串后count要恢复，继续继续寻找下一个满足条件的字串
                }
                l++; // 左边界向由移动
            }

        }

        return end - start == sLen + 1 ? "" : s.substring(start, end + 1);
    }

    public static void main(String[] args) {
        String s = "ADOBECODEBANC";
        String t = "ABC";
        System.out.println(minWindow(s, t));
    }
}
