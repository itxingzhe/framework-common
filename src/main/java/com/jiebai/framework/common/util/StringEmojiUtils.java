package com.jiebai.framework.common.util;

import com.vdurmont.emoji.EmojiParser;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;

/**
 * 字符串處理-共同類
 */
public class StringEmojiUtils {

  private static Pattern emoji = Pattern.compile ("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]",Pattern.UNICODE_CASE | Pattern . CASE_INSENSITIVE ) ;

  /**
   * 去除null
   *
   * @param str str
   * @return String
   */
  public static String takeNull (String str) {
    return str == null ? "" : str;
  }

  /**
   * 去除null
   *
   * @param number number
   * @return Integer
   */
  public static Integer takeIntNull (Integer number) {
    return number == null ? 0 : number;
  }

  /**
   * 去除null
   *
   * @param number number
   * @return Double
   */
  public static Double takeDoubleNull (Double number) {
    return number == null ? 0 : number;
  }

  /**
   * sqlSpecialEscape
   *
   * @param str str
   * @return String
   */
  public static String sqlSpecialEscape(String str) {
    if (StringUtils.isEmpty(str)) {
      return "";
    }
    //return str.replaceAll("(\'|\"|%|_|\\\\)", "\\\\$0");
    return str.replaceAll("[\ue000-\uefff]", "").replaceAll("/\\xf0.../", "");
  }

  /**
   * 移除字符串中的emoji表情
   * @param source target
   * @return String
   */
  public static String filterEmoji(String source) {
    if (source != null) {
      Matcher emojiMatcher = emoji.matcher(source);
      if (emojiMatcher.find()) {
        source = emojiMatcher.replaceAll("*");
        return source ;
      }
      return source;
    }
    return source;
  }

  /**
   * 移除字符串中的emoji表情
   * @param source target
   * @return String
   */
  public static String removeAllEmojis(String source) {
    return EmojiParser.removeAllEmojis(source);
  }

    /**
     * 转义包含表情的字符串
     * @param target target
     * @return String
     */
    public static String parseToAliases(String target){
        if(target !=null){
            return EmojiParser.parseToAliases(target);
        }
        return null;
    }

    /**
     * 反转义包含表情的字符串
     * @param target target
     * @return String
     */
    public static String parseToUnicode(String target){
        if(target != null){
            return EmojiParser.parseToUnicode(target);
        }
        return null;
    }
}
