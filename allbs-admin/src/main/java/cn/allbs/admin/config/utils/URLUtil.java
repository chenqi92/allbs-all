package cn.allbs.admin.config.utils;

import cn.allbs.admin.config.exception.UtilException;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * 类 URLUtil
 *
 * @author ChenQi
 * @date 2024/4/19
 */
public class URLUtil {

    /**
     * 获得path部分<br>
     *
     * @param uriStr URI路径
     * @return path
     * @throws UtilException 包装URISyntaxException
     */
    public static String getPath(String uriStr) {
        return toURI(uriStr).getPath();
    }

    /**
     * 转字符串为URI
     *
     * @param location 字符串路径
     * @return URI
     * @throws UtilException 包装URISyntaxException
     */
    public static URI toURI(String location) throws UtilException {
        return toURI(location, false);
    }

    /**
     * 转字符串为URI
     *
     * @param location 字符串路径
     * @param isEncode 是否编码参数中的特殊字符（默认UTF-8编码）
     * @return URI
     * @throws UtilException 包装URISyntaxException
     * @since 4.6.9
     */
    public static URI toURI(String location, boolean isEncode) throws UtilException {
        if (isEncode) {
            location = encode(location);
        }
        try {
            return new URI(StringUtil.trim(location));
        } catch (URISyntaxException e) {
            throw new UtilException(e);
        }
    }

    /**
     * 编码URL，默认使用UTF-8编码<br>
     * 将需要转换的内容（ASCII码形式之外的内容），用十六进制表示法转换出来，并在之前加上%开头。<br>
     * 此方法用于URL自动编码，类似于浏览器中键入地址自动编码，对于像类似于“/”的字符不再编码
     *
     * @param url URL
     * @return 编码后的URL
     * @throws UtilException UnsupportedEncodingException
     * @since 3.1.2
     */
    public static String encode(String url) throws UtilException {
        return encode(url, StandardCharsets.UTF_8);
    }

    /**
     * 编码字符为 application/x-www-form-urlencoded<br>
     * 将需要转换的内容（ASCII码形式之外的内容），用十六进制表示法转换出来，并在之前加上%开头。<br>
     * 此方法用于URL自动编码，类似于浏览器中键入地址自动编码，对于像类似于“/”的字符不再编码
     *
     * @param url     被编码内容
     * @param charset 编码
     * @return 编码后的字符
     * @since 4.4.1
     */
    public static String encode(String url, Charset charset) {
        return URLEncoder.encode(url, charset);
    }

    /**
     * 将Map形式的Form表单数据转换为Url参数形式
     *
     * @param paramMap Form表单数据
     * @return Url参数形式的字符串
     */
    public static String buildQuery(Map<String, Object> paramMap, Charset charset) {
        if (paramMap == null || paramMap.isEmpty()) {
            return "";
        }

        StringBuilder query = new StringBuilder();
        boolean first = true;

        for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            // 如果key为空（null和""）会被忽略
            if (key == null || key.isEmpty()) {
                continue;
            }

            // 如果value为null，会被做为空白符（""）
            if (value == null) {
                value = "";
            }

            if (first) {
                first = false;
            } else {
                query.append("&");
            }

            query.append(URLEncoder.encode(key, charset));
            query.append("=");
            query.append(URLEncoder.encode(value.toString(), charset));
        }

        return query.toString();
    }
}
