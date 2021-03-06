package com.delicacy.oatmeal.common.util.base;

import com.delicacy.oatmeal.common.util.io.IOUtil;
import com.delicacy.oatmeal.common.util.io.URLResourceUtil;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.util.Properties;

/**
 * 关于Properties的工具类
 * 1. 统一读取Properties
 * 2. 从文件或字符串装载Properties
 */
public class PropertiesUtil {


    public static Boolean getBoolean(Properties p, String name, Boolean defaultValue) {
        return BooleanUtil.toBooleanObject(p.getProperty(name), defaultValue);
    }

    public static Integer getInt(Properties p, String name, Integer defaultValue) {
        return toIntObject(p.getProperty(name), defaultValue);
    }

    public static Long getLong(Properties p, String name, Long defaultValue) {
        return toLongObject(p.getProperty(name), defaultValue);
    }

    public static Double getDouble(Properties p, String name, Double defaultValue) {
        return toDoubleObject(p.getProperty(name), defaultValue);
    }

    public static String getString(Properties p, String name, String defaultValue) {
        return p.getProperty(name, defaultValue);
    }


    private static Integer toIntObject(String str, Integer defaultValue) {
        if (StringUtils.isEmpty(str)) {
            return defaultValue;
        }
        try {
            return Integer.valueOf(str);
        } catch (final NumberFormatException nfe) {
            return defaultValue;
        }
    }

    private static Long toLongObject(String str, Long defaultValue) {
        if (StringUtils.isEmpty(str)) {
            return defaultValue;
        }
        try {
            return Long.valueOf(str);
        } catch (final NumberFormatException nfe) {
            return defaultValue;
        }
    }

    private static Double toDoubleObject(String str, Double defaultValue) {
        if (StringUtils.isEmpty(str)) {
            return defaultValue;
        }
        try {
            return Double.valueOf(str);
        } catch (final NumberFormatException nfe) {
            return defaultValue;
        }
    }


    /**
     * 从文件路径加载properties.
     * <p>
     * 路径支持从外部文件或resources文件加载, "file://"或无前缀代表外部文件, "classpath://"代表resources,
     */
    public static Properties loadFromFile(String generalPath) {
        Properties p = new Properties();
        InputStream is = null;
        try {
            is = URLResourceUtil.asStream(generalPath);
            p.load(is);
        } catch (IOException e) {
            System.out.println("Load property from " + generalPath + " fail ");
        } finally {
            IOUtil.closeQuietly(is);
        }
        return p;
    }

    /**
     * 从字符串内容加载Properties
     */
    public static Properties loadFromString(String content) {
        Properties p = new Properties();
        Reader reader = new StringReader(content);
        try {
            p.load(reader);
        } catch (IOException ignored) {
        } finally {
            IOUtil.closeQuietly(reader);
        }
        return p;
    }

}
