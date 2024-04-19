package cn.allbs.admin.config.utils;

/**
 * 类 CharUtil
 *
 * @author ChenQi
 * @date 2024/4/19
 */
public class CharUtil {

    /**
     * 是否空白符<br>
     * 空白符包括空格、制表符、全角空格和不间断空格<br>
     *
     * @param c 字符
     * @return 是否空白符
     * @see Character#isWhitespace(int)
     * @see Character#isSpaceChar(int)
     * @since 4.0.10
     */
    public static boolean isBlankChar(char c) {
        return isBlankChar((int) c);
    }

    /**
     * 是否空白符<br>
     * 空白符包括空格、制表符、全角空格和不间断空格<br>
     *
     * @param c 字符
     * @return 是否空白符
     * @see Character#isWhitespace(int)
     * @see Character#isSpaceChar(int)
     * @since 4.0.10
     */
    public static boolean isBlankChar(int c) {
        return Character.isWhitespace(c)
                || Character.isSpaceChar(c)
                || c == '\ufeff'
                || c == '\u202a'
                || c == '\u0000'
                // issue#I5UGSQ，Hangul Filler
                || c == '\u3164'
                // Braille Pattern Blank
                || c == '\u2800';
    }

    /**
     * 截取两个字符串的不同部分（长度一致），判断截取的子串是否相同<br>
     * 任意一个字符串为null返回false
     *
     * @param str1       第一个字符串
     * @param start1     第一个字符串开始的位置
     * @param str2       第二个字符串
     * @param start2     第二个字符串开始的位置
     * @param length     截取长度
     * @param ignoreCase 是否忽略大小写
     * @return 子串是否相同
     * @since 3.2.1
     */
    public static boolean isSubEquals(CharSequence str1, int start1, CharSequence str2, int start2, int length, boolean ignoreCase) {
        if (null == str1 || null == str2) {
            return false;
        }

        return str1.toString().regionMatches(ignoreCase, start1, str2.toString(), start2, length);
    }
}
