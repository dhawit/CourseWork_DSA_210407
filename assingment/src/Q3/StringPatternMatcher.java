package Q3;

class StringPatternMatcher {
    public static boolean matchPattern(String str, String pattern) {
        int strIndex = 0;
        int patternIndex = 0;
        while (strIndex < str.length() && patternIndex < pattern.length()) {
            if (pattern.charAt(patternIndex) == '@') {
                return true;
            } else if (pattern.charAt(patternIndex) == '#' && strIndex < str.length()) {
                strIndex++;
                patternIndex++;
            } else if (str.charAt(strIndex) == pattern.charAt(patternIndex)) {
                strIndex++;
                patternIndex++;
            } else {
                return false;
            }
        }
        return strIndex == str.length() && patternIndex == pattern.length();
    }

    public static void main(String[] args) {
 String inputStr = "tt";
 String pattern = "@";
 boolean isMatch = matchPattern(inputStr, pattern);
 System.out.println("Pattern match result: " + isMatch);inputStr = "ta";
 pattern = "t";
 isMatch = matchPattern(inputStr, pattern);
 System.out.println("Pattern match result: " + isMatch); inputStr = "ta";
 pattern = "t#";
 isMatch = matchPattern(inputStr, pattern);
 System.out.println("Pattern match result: " + isMatch);
    }


}
