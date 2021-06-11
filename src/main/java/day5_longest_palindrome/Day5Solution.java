package day5_longest_palindrome;

import java.util.*;



public class Day5Solution {
    public static void main(String[] args) {
        System.out.println(longestPalindrome("nmngaowrbsssvihklwmuqshcddwlxrywrlwtennwfvrevgvhsvgeccfulmuvrcksdmgeqrblnlwoepefhcwhmgyvgcoyyygrmttyfycxwbqktpurlcfhzlakhmrddsydgygganpmaglaxyhfwjusukzcnakznygqplngnkhcowavxoiwrfycxwdkxqfcjqwyqutcpyedbnuogedwobsktgioqdczxhikjrbkmqspnxcpngfdwdaboscqbkwforihzqdcppxjksiujfvlpdjryewaxgmdgigvxdlstxwngtbdrrkfudjinzyxbdmkautclvvyguekuzwwetmsxittgtxbnvvrgasvnlogdiepltweaehubwelznidltzlbzdsrxmhjpkmylnwkdsxnpkplkdzywioluaqguowtbaoqzqgjfewphqcvlnwlojbxgomvxxkhwwykawegxubjiobizicuxzeafgautefsurgjlbhcfevqzsbhwxycrcaibdsgluczcuewzqupakbzmcvzsfodbmgtugnihyhqkvyeboqhqldifbxuaxqzxtyejoswikbzpsvzkxcndgeyvfnyrfbkhlalzpqjueibnodamgpnxlkvwvliouvejcpnakllfxepldfmdzszagkyhdgqqbkb"));
        System.out.println(longestPalindrome("cbbd"));
        System.out.println(longestPalindrome("ac"));
    }

    static boolean isPalindromeDynamic(String s, int pos1, int pos2, Map<String, Boolean> checkedSubstrings) {
        if (pos1 == pos2 || pos1 + 1 == pos2) {
            return true;
        } else {
            Boolean isKnownResult = checkedSubstrings.get(pos1 + ":" + pos2);
            if (isKnownResult != null) {
                return isKnownResult;
            } else if (s.charAt(pos1) == s.charAt(pos2 - 1)) {
                boolean isMiddlePalindrome = isPalindromeDynamic(s, pos1 + 1, pos2 - 1, checkedSubstrings);
                checkedSubstrings.put(pos1 + ":" + pos2, isMiddlePalindrome);
                return isMiddlePalindrome;
            } else {
                checkedSubstrings.put(pos1 + ":" + pos2, false);
                return false;
            }
        }

    }

    public static String longestPalindromeDynamic(String s) {
        Map<String, Boolean> checkedSubstrings = new HashMap<>();
        int pMaxLen = 0;
        String pMax = "";
        for (int pos1 = 0; pos1 < s.length(); ++pos1) {
            for (int pos2 = pos1 + 1; pos2 <= s.length(); ++pos2) {
                if (isPalindromeDynamic(s, pos1, pos2, checkedSubstrings) && (pos2 - pos1 + 1) > pMaxLen) {
                    pMaxLen = pos2 - pos1 + 1;
                    pMax = s.substring(pos1, pos2);
                }

            }
        }

        return pMax;
    }

    public static String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return "";
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private static int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
    }
}
