package common.util;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

/**
 * String ó�� ����
 * @author  javaworker
 * @version $Id: StringUtil.java,v 1.1 2013/08/30 09:09:48 javaworker Exp $
 * @since   1.0
 *
 */
public class StringUtil
{
    /**
     * �迭�� �ݴ�� �Ѵ�.
     * @author  javaworker
     * @version $Id: StringUtil.java,v 1.1 2013/08/30 09:09:48 javaworker Exp $
     * @since   1.0
     * 
     * @param origin
     * @return
     */
    public static String [] convertArray(String [] origin)
    {
        int dataLength = 0;
        if (origin == null || (dataLength = origin.length) == 0)
        {
            return origin;
        }

        String [] result = new String[dataLength];
        
        for (int i=0; i<dataLength; i++)
        {
            result[dataLength-(i+1)] = origin[i];
        }

        return result;
    }
    
    /**
     * �迭�� �ݴ�� �Ѵ�.
     * @author  javaworker
     * @version $Id: StringUtil.java,v 1.1 2013/08/30 09:09:48 javaworker Exp $
     * @since   1.0
     * 
     * @param origin
     * @return
     */
    public static String [][] convertArray(String [][] origin)
    {
        int dataLength = 0;
        if (origin == null || (dataLength = origin.length) == 0)
        {
            return origin;
        }

        String [][] result = new String[dataLength][];
        
        for (int i=0; i<dataLength; i++)
        {
            result[dataLength-(i+1)] = origin[i];
        }

        return result;
    }
    
    /**
     * String�� length ��ŭ �迭�� ���� �����Ѵ�.
     * @author  javaworker
     * @version $Id: StringUtil.java,v 1.1 2013/08/30 09:09:48 javaworker Exp $
     * @since   1.0
     * 
     * @param origin
     * @param length
     * @return
     */
    public static String [] stringToArray(String origin, int length)
    {
        String [] resultArray = new String[length];
        
        for (int i=0; i<length; i++)
        {
            resultArray[i] = origin;
        }
        
        return resultArray;
    }
    
    /**
     * String[]�� length ��ŭ �迭�� ���� �����Ѵ�.
     * @author  javaworker
     * @version $Id: StringUtil.java,v 1.1 2013/08/30 09:09:48 javaworker Exp $
     * @since   1.0
     * 
     * @param origin
     * @param length
     * @return
     */
    public static String [] arrayLength(String [] origin, int length)
    {
        String [] resultArray = new String[length];
        
        // �ڸ����� �迭�� ���̺��� �迭�� �۴ٸ� ���� �迭�� �����Ѵ�.
        if (origin.length < length)
        {
            return origin;
        }
        
        for (int i=0; i<length; i++)
        {
            resultArray[i] = origin[i];
        }
        
        return resultArray;
    }
    
    /**
     * ������ ���ڿ��� �����Ѵ�.
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @return
     */
    public static String randomString() {
        char[] buf = new char[21];
        Random random = new SecureRandom();
        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lower = upper.toLowerCase(Locale.ROOT);
        String digits = "0123456789";
        String alphanum = upper + lower + digits;
        char[] symbols = alphanum.toCharArray();
        
        for (int idx = 0; idx < buf.length; ++idx)
            buf[idx] = symbols[random.nextInt(symbols.length)];
        
        return new String(buf);
    }
    /**
     * ������ ���ڿ��� �����Ѵ�.
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param length
     * @return
     */
    public static String randomString(int length) {
        char[] buf = new char[length];
        Random random = new SecureRandom();
        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lower = upper.toLowerCase(Locale.ROOT);
        String digits = "0123456789";
        String alphanum = upper + lower + digits;
        char[] symbols = alphanum.toCharArray();
        
        for (int idx = 0; idx < buf.length; ++idx)
            buf[idx] = symbols[random.nextInt(symbols.length)];
        
        return new String(buf);
    }
    /**
     * ������ ���ڿ��� �����Ѵ�.
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param length
     * @param symbols
     * @return
     */
    public static String randomString(int length, String symbols) {
        char[] buf = new char[length];
        Random random = new SecureRandom();
        char[] symbolArr = symbols.toCharArray();
        
        for (int idx = 0; idx < buf.length; ++idx)
            buf[idx] = symbolArr[random.nextInt(symbolArr.length)];
        
        return new String(buf);
    }
    
    /**
     * Returns the string representation of the Object argument.
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param obj an Object.
     * @return if the argument is null, then a string equal to ""; otherwise, the value of obj.toString() is returned.
     */
    public static String valueOf(Object obj) {
        return obj==null?"":obj.toString();
    }
    
    /**
     * JdbcTemplate�� queryForList ����� ���� List�� Ư�� key�� value���� List�� �����Ѵ�.
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param list
     * @param key
     * @return
     * @throws IOException
     */
    public static List<String> toSingleList(List<Map> list, String key) throws IOException {
        List<String> resultList = new ArrayList<String>();
        
        for(Map map:list){
            if(!map.containsKey(key)) throw new IOException("Key is unavailable.");
            resultList.add(StringUtil.valueOf(map.get(key)));
        }
        
        return resultList;
    }
    
    /**
     * JdbcTemplate�� queryForList ����� ���� List�� Ư�� key�� value���� �迭�� �����Ѵ�.
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param list
     * @param key
     * @return
     * @throws IOException 
     */
    public static String[] toSingleArray(List<Map> list, String key) throws IOException {
        return toSingleList(list, key).toArray(new String[0]);
    }
}
