package top.cfish.spider.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;


@Slf4j
public class PropertiesUtil
{
    private static Properties props;
    
    static
    {
        String fileName = "conf/key.properties";
        props = new Properties();
        try
        {
            props.load(new InputStreamReader(PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName), "UTF-8"));
        }
        catch (IOException e)
        {
            log.error("配置文件读取异常", e);
        }
    }
    
    public static String getProperty(String key)
    {
        // 处理key两边的空格
        String value = props.getProperty(key.trim());
        if (StringUtils.isBlank(value))
        {
            return null;
        }
        
        // 处理value两边的空格
        return value.trim();
    }
    
    public static String getProperty(String key, String defaultValue)
    {
        String value = props.getProperty(key.trim());
        if (StringUtils.isBlank(value))
        {
            value = defaultValue;
        }
        return value.trim();
    }
}
