package common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import dream.comm.service.DateService;

/**
 * Date Util
 * @author  javaworker
 * @version $Id: DateUtil.java,v 1.3 2014/04/04 07:39:11 pochul2423 Exp $
 * @since   1.0
 *
 */
public class DateUtil
{
    private DateUtil()
    {}

    private static Date get()
    {
        return new Date();
    }

    /**
     * �����Ⱓ ����[+]/����[-] Date object�� ��ȯ�Ѵ�.
     * 
     * @param ai_day
     *            �ϼ� ����
     * @return Date
     */
    private static Date get(int ai_day)
    {
        // �������� �� �ʱ�ȭ.
        Calendar cal = Calendar.getInstance();
        // �����Ⱓ ����/���� ���.
        if (ai_day != 0)
            cal.add(Calendar.DATE, ai_day);
        return cal.getTime();
    }

    /**
     * �����Ͻø� Ư������(yyyyMMddHHmmss)���� ��ȯ
     * 
     * @param as_format
     * @return String
     */
    public static String getDateTime(String as_format)
    {
        // �������� �� �ʱ�ȭ.
        Date date = get();
        SimpleDateFormat dateFormat = new SimpleDateFormat(as_format);
        return dateFormat.format(date);
    }

    /**
     * �����Ⱓ ����[+]/����[-] �Ͻø� Ư������(yyyyMMddHHmmss)���� ��ȯ �Ѵ�.
     * 
     * @param as_format
     * @param ai_day
     * @return String
     */
    public static String getDateTime(String as_format, int ai_day)
    {
        // �������� �� �ʱ�ȭ.
        Date date = get(ai_day);
        SimpleDateFormat dateFormat = new SimpleDateFormat(as_format);
        return dateFormat.format(date);
    }
    
    /**
     * Date�� Ư������(yyyyMMddHHmmss)���� ��ȯ �Ѵ�.
     * 
     * @param as_format
     * @param date
     * @return String
     */
    public static String getDateTime(String as_format, Date date)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat(as_format);
        return dateFormat.format(date);
    }

    /**
     * �����Ͻø� yyyyMMddHHmmss �������� ��ȯ�Ѵ�.
     * 
     * @return String
     */
    public static String getDateTime()
    {
        return getDateTime("yyyyMMddHHmmss");
    }

    /**
     * �����Ⱓ ����[+]/����[-] �Ͻ�(yyyyMMddHHmmss)�� ��ȯ �Ѵ�.
     * 
     * @param ai_day
     * @return String
     */
    public static String getDateTime(int ai_day)
    {
        return getDateTime("yyyyMMddHHmmss", ai_day);
    }

    /**
     * �б��� �����Ͻ�(yyyyMMddHHmm)�� ��ȯ�Ѵ�.
     * 
     * @return String
     */
    public static String getDateMin()
    {
        return getDateTime("yyyyMMddHHmm");
    }

    /**
     * �б��� �����Ⱓ ����[+]/����[-] �Ͻ�(yyyyMMddHHmmss)�� ��ȯ�Ѵ�.
     * 
     * @param ai_day
     * @return String
     */
    public static String getDateMin(int ai_day)
    {
        return getDateTime("yyyyMMddHHmm", ai_day);
    }

    /**
     * �ñ��� �����Ͻ�(yyyyMMddHH)�� ��ȯ�Ѵ�.
     * 
     * @return String
     */
    public static String getDateHour()
    {
        return getDateTime("yyyyMMddHH");
    }

    /**
     * �ñ��� �����Ⱓ ����[+]/����[-] �Ͻ�(yyyyMMddHH)�� ��ȯ�Ѵ�
     * 
     * @param ai_day
     * @return String
     */
    public static String getDateHour(int ai_day)
    {
        return getDateTime("yyyyMMddHH", ai_day);
    }

    /**
     * ��������(yyyyMMdd)�� ��ȯ�Ѵ�
     * 
     * @return String
     */
    public static String getDate()
    {
        return getDateTime("yyyyMMdd");
    }

    /**
     * �����Ⱓ ����[+]/����[-] �� ����(yyyyMMdd)�� ��ȯ�Ѵ�.
     * 
     * @param ai_day
     * @return String
     */
    public static String getDate(int ai_day)
    {
        return getDateTime("yyyyMMdd", ai_day);
    }

    /**
     * ����⵵�� ��ȯ�Ѵ�.
     * 
     * @return String
     */
    public static String getYear()
    {
        return getDateTime("yyyy");
    }

    /**
     * �����Ⱓ ����[+]/����[-]�� �⵵�� ��ȯ�Ѵ�.
     * 
     * @param ai_day
     * @return String
     */
    public static String getYear(int ai_day)
    {
        return getDateTime("yyyy", ai_day);
    }

    /**
     * ���� ���� ��ȯ�Ѵ�.
     * 
     * @return String
     */
    public static String getMonth()
    {
        return getDateTime("MM");
    }

    /**
     * �����Ⱓ ����[+]/����[-]�� ���� ��ȯ�Ѵ�.
     * 
     * @param ai_day
     * @return String
     */
    public static String getMonth(int ai_day)
    {
        return getDateTime("MM", ai_day);
    }

    /**
     * ���� ���� ��ȯ�Ѵ�.
     * 
     * @return String
     */
    public static String getDay()
    {
        return getDateTime("dd");
    }

    /**
     * �����Ⱓ ����[+]/����[-]�� ���� ��ȯ�Ѵ�.
     * 
     * @param ai_day
     * @return String
     */
    public static String getDay(int ai_day)
    {
        return getDateTime("dd", ai_day);
    }

    /**
     * ���� �ð��� ��ȯ�Ѵ�.(24�ð� ����)
     * 
     * @return String
     */
    public static String getHour()
    {
        return getDateTime("HH");
    }

    /**
     * ���� ���� ��ȯ�Ѵ�.
     * 
     * @return String
     */
    public static String getMinute()
    {
        return getDateTime("mm");
    }

    /**
     * ���� �ʸ� ��ȯ�Ѵ�.
     * 
     * @return String
     */
    public static String getSecond()
    {
        return getDateTime("ss");
    }

    public static Date getFormatDate(String as_format, String date)
    {
        Date returnDate = null;
        try
        {
            SimpleDateFormat formate = new SimpleDateFormat(as_format);
            returnDate = formate.parse(date);
        }
        catch (ParseException e)
        {}
        return returnDate;
    }

    public static Date getFormatDate(String date)
    {
        return getFormatDate("yyyyMMdd", date);
    }
    
    /**
     * �⿡ �ش��ϴ� ù��° �������� ���ڷ� �迭�� 1~12�� ���� �����Ѵ�.
     * @author  javaworker
     * @version $Id: DateUtil.java,v 1.3 2014/04/04 07:39:11 pochul2423 Exp $
     * @since   1.0
     * 
     * @param year
     * @return [0] : ���� ù��° ������ ���� ����� 8�ڸ�
     *         [1] : ���� ù��° �������� ��(��° ��) 
     */
    public static String [][] getFirstMonday(String year)
    {
        // ���� ù��° �ָ� ������� �ʴ´�.
        return DateUtil.getFirstMonday(year, true);
    }
    
    /**
     * �⿡ �ش��ϴ� ù��° �������� ���ڷ� �迭�� 1~12�� ���� �����Ѵ�.
     * @author  javaworker
     * @version $Id: DateUtil.java,v 1.3 2014/04/04 07:39:11 pochul2423 Exp $
     * @since   1.0
     * 
     * @param year
     * @return [0] : ���� ù��° ������ ���� ����� 8�ڸ�
     *         [1] : ���� ù��° �������� ��(��° ��) 
     */
    public static String [][] getFirstMonday(String year, boolean disableFirstWeek)
    {
        String [][] yearFirstMonday = new String[12][2];
        
        if (year == null || year.length() != 4)
        {
            return null;
        }
        
        for (int i=1; i<=12; i++)
        {
            yearFirstMonday[i-1][0] = getFirstMonday(year, i+"", disableFirstWeek);    // ���� ù��° ������ ��¥(8�ڸ�)
            yearFirstMonday[i-1][1] = getWeekOfYear(yearFirstMonday[i-1][0]);          // ù��° ������ ���� ���° ��
        }
   
        return yearFirstMonday;
    }
 
    /**
     * ��, ���� �ش��ϴ� ù��° �������� 
     * ���ڷ� �����Ѵ�. 
     * @author  javaworker
     * @version $Id: DateUtil.java,v 1.3 2014/04/04 07:39:11 pochul2423 Exp $
     * @since   1.0
     * 
     * @param year : 4�ڸ� �⵵
     * @param month : 2�ڸ� �� 01 ~ 12
     * @return 4�ڸ��� �����̴�. ex) 0105
     */
    public static String getFirstMonday(String year, String month)
    {
        // ù��° �ָ� ���� �ʰ� �Ѵ�.
        
        /*
         * ù°�ָ� �����Ѵ�.
         * ���� : 2005.12.28
         * - javaworker
         */
        return DateUtil.getFirstMonday(year, month, false);
    }
    
    /**
     * ��, ���� �ش��ϴ� ù��° �������� 
     * ���ڷ� �����Ѵ�. 
     * @author  javaworker
     * @version $Id: DateUtil.java,v 1.3 2014/04/04 07:39:11 pochul2423 Exp $
     * @since   1.0
     * 
     * @param year : 4�ڸ� �⵵
     * @param month : 2�ڸ� �� 01 ~ 12
     * @return 4�ڸ��� �����̴�. ex) 0105
     */
    public static String getFirstMonday(String year, String month, boolean disableFirstWeek)
    {
        String firstMondayDate = "";
        
        if (year == null || month == null)
        {
            return null;
        }
        
        if (year.length() != 4 || month.length() > 2)
        {
            return null;            
        }
        
        if (month.length() == 1)
        {
            // �����Ҷ� 8�ڸ��� ���߾��ֱ� ���ؼ�  ������ 2�ڸ��� �Ѵ�.
            month = "0" + month;
        }        
        
        int iYear  = 0;
        int iMonth = 0;
                
        try
        {
            iYear  = Integer.parseInt(year);
            iMonth = Integer.parseInt(month);
        }
        catch (Exception e)
        {
            return null;
        }
        
        Calendar cal = Calendar.getInstance();

        //============================================
        // month�� 0 base �̴�. ex) 0 -> 1��, 5 -> 6��
        cal.set(iYear, iMonth-1, 1);
        //============================================
        
        // 1:��, 2:��, 3:ȭ, 4:��, 5:��, 6:��, 7:��
        int iWeek = cal.get(Calendar.DAY_OF_WEEK);
        
        // �ش� ��, ���� 1���� ����
        switch(iWeek)
        {
            case Calendar.SUNDAY:
                // �Ͽ��� + 1 = ��
                firstMondayDate = year+month+"02";
                
                // ù��° �ָ� �Ⱦ��� ���
                if (disableFirstWeek)
                {
                    // 1�� 1���� ��� ù�ִ� �Ⱦ��Ƿ� + 8�� �Ѵ�. ���� ������
                    if ("0101".equals(month+"01"))
                    {
                        firstMondayDate = year+month+"09";
                    }
                }
                
                break;
            case Calendar.MONDAY:
                // ������ = ��
                firstMondayDate = year+month+"01";
                
                // ù��° �ָ� �Ⱦ��� ���
                if (disableFirstWeek)
                {
                    // 1�� 1���� ��� ù�ִ� �Ⱦ��Ƿ� + 7�� �Ѵ�.
                    if ("0101".equals(month+"01"))
                    {
                        firstMondayDate = year+month+"08";
                    }
                }
                
                break;
            case Calendar.TUESDAY:
                // ȭ���� + 6 = ��
                firstMondayDate = year+month+"07";
                break;
            case Calendar.WEDNESDAY:
                // ������ + 5 = ��
                firstMondayDate = year+month+"06";
                break;
            case Calendar.THURSDAY:
                // ����� + 4 = ��
                firstMondayDate = year+month+"05";
                break;
            case Calendar.FRIDAY:                
                // �ݿ��� + 3 = ��
                firstMondayDate = year+month+"04";
                break;
            case Calendar.SATURDAY:
                // ����� + 2 = ��
                firstMondayDate = year+month+"03";
                break;
        }
        
        // ���� ��,���� ù��° �������� �����Ѵ�.
        return firstMondayDate;
    }
    
    /**
     * ��, ���� �ش��ϴ� ù��° ������ 
     * ���ڷ� �����Ѵ�. 
     * @author  javaworker
     * @version $Id: DateUtil.java,v 1.3 2014/04/04 07:39:11 pochul2423 Exp $
     * @since   1.0
     * 
     * @param year : 4�ڸ� �⵵
     * @param month : 2�ڸ� �� 01 ~ 12
     * @return 4�ڸ��� �����̴�. ex) 0105
     */
    public static String getFirstWeek(String year, String month)
    {
        if (year == null || month == null)
        {
            return null;
        }
        
        if (year.length() != 4 || month.length() > 2)
        {
            return null;            
        }
        
        if (month.length() == 1)
        {
            // �����Ҷ� 8�ڸ��� ���߾��ֱ� ���ؼ�  ������ 2�ڸ��� �Ѵ�.
            month = "0" + month;
        }        
        
        int iYear  = 0;
        int iMonth = 0;
                
        try
        {
            iYear  = Integer.parseInt(year);
            iMonth = Integer.parseInt(month);
        }
        catch (Exception e)
        {
            return null;
        }
        
        Calendar cal = Calendar.getInstance();

        //============================================
        // month�� 0 base �̴�. ex) 0 -> 1��, 5 -> 6��
        cal.set(iYear, iMonth-1, 1);
        //============================================
        
        // 1:��, 2:��, 3:ȭ, 4:��, 5:��, 6:��, 7:��
        int iWeek = cal.get(Calendar.DAY_OF_WEEK) - 1;
        
        return iWeek+"";
    }
    
    /**
     * �ش� ��, ���� ù��° �ְ� ���� ���° �������� ����Ͽ� �����Ѵ�.
     * @author  javaworker
     * @version $Id: DateUtil.java,v 1.3 2014/04/04 07:39:11 pochul2423 Exp $
     * @since   1.0
     * 
     * @param year
     * @param month
     * @return
     * @throws Exception
     */
    public static String getFirstMondayWeek(String year, String month) throws Exception
    {
        String firstMonday = getFirstMonday(year, month);

        return getWeekOfYear(firstMonday);
    }
    
    /**
     * �ش� ����(�����)�� �ش��ϴ� 
     * �ְ� ���° ������ ����Ѵ�.
     * @author  javaworker
     * @version $Id: DateUtil.java,v 1.3 2014/04/04 07:39:11 pochul2423 Exp $
     * @since   1.0
     * 
     * @param yyyyMMdd �����
     * @return
     */
    public static String getWeekOfYear(String yyyyMMdd)
    {
        if (yyyyMMdd == null || yyyyMMdd.length() != 8)
        {
            return null;            
        }

        // ��, ��, �Ϸ� �и��Ѵ�.
        String year  = yyyyMMdd.substring(0, 4);
        String month = yyyyMMdd.substring(4, 6);
        String day   = yyyyMMdd.substring(6);

        int iYear = 0;
        int iMonth = 0;
        int iDay = 0;
        
        try
        {
            iYear  = Integer.parseInt(year);
            iMonth = Integer.parseInt(month);
            iDay   = Integer.parseInt(day);
        }
        catch(Exception e)
        {
            return null;
        }
        
        Calendar cal = Calendar.getInstance();
        // month�� 0 base �̴�.
        cal.set(iYear, iMonth-1, iDay);

        int weekOfYear = cal.get(Calendar.WEEK_OF_YEAR);
        
        // 53������ ��� 1������ ǥ�õȴ�. - ������ �ʿ��Ѵ�.
        if (iMonth == 12 && weekOfYear == 1)
        {
            // 12���̰� ������ 1�ΰ��� 53�����̴�.
            weekOfYear = 53;
        }
        
        // �ش� �⵵�� ���° ������ ���
        return weekOfYear + "";
    }
    
    /**
     * �ش� ����(�����)�� �ش��ϴ� 
     * �ְ� �� ���� ���° ������ ����Ѵ�.
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param yyyyMMdd �����
     * @return
     */
    public static int getWeekOfMonth(String yyyyMMdd)
    {
        if (yyyyMMdd == null || yyyyMMdd.length() != 8)
        {
            return -1;            
        }

        // ��, ��, �Ϸ� �и��Ѵ�.
        String year  = yyyyMMdd.substring(0, 4);
        String month = yyyyMMdd.substring(4, 6);
        String day   = yyyyMMdd.substring(6);

        int iYear = 0;
        int iMonth = 0;
        int iDay = 0;
        
        try
        {
            iYear  = Integer.parseInt(year);
            iMonth = Integer.parseInt(month);
            iDay   = Integer.parseInt(day);
        }
        catch(Exception e)
        {
            return -1;
        }
        
        Calendar cal = Calendar.getInstance();
        // month�� 0 base �̴�.
        cal.set(iYear, iMonth-1, iDay);
        return cal.get(Calendar.WEEK_OF_MONTH);
    }
    
    /**
     * �ش� ���� �� 
     * �ش����� ������ ��¥�� �����Ѵ�.
     * @author  javaworker
     * @version $Id: DateUtil.java,v 1.3 2014/04/04 07:39:11 pochul2423 Exp $
     * @since   1.0
     * 
     * @param year
     * @param weekCnt
     * @return
     */
    public static String getMondayOfWeek(String year, String weekCnt)
    {
        Calendar cal = Calendar.getInstance();

        int iYear    = Integer.parseInt(year);
        int iWeekCnt = Integer.parseInt(weekCnt);
        
        cal.set(Calendar.YEAR , iYear);
        cal.set(Calendar.WEEK_OF_YEAR , iWeekCnt);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

        // month�� 0 base �̴�.
        int iMonth = cal.get(Calendar.MONTH)+1;
        int iDay = cal.get(Calendar.DAY_OF_MONTH);
        
        // ���� ���� 2�ڸ��� ���߾� �ش�.
        String result = year + (iMonth<10?"0"+iMonth:iMonth+"") + (iDay<10?"0"+iDay:iDay+"");
        
        return result;
    }
    
    /**
     * �ش� ���� �� 
     * �ش����� ����� ��¥�� �����Ѵ�.
     * @author  oikesismiz
     * @version $Id: DateUtil.java,v 1.3 2014/04/04 07:39:11 pochul2423 Exp $
     * @since   1.0
     * 
     * @param year
     * @param weekCnt
     * @return
     */
    public static String getSaturdayOfWeek(String year, String weekCnt)
    {
        Calendar cal = Calendar.getInstance();

        int iYear    = Integer.parseInt(year);
        int iWeekCnt = Integer.parseInt(weekCnt);
        
        cal.set(Calendar.YEAR , iYear);
        cal.set(Calendar.WEEK_OF_YEAR , iWeekCnt);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);

        // month�� 0 base �̴�.
        int iMonth = cal.get(Calendar.MONTH)+1;
        int iDay = cal.get(Calendar.DAY_OF_MONTH);
        
        // ���� ���� 2�ڸ��� ���߾� �ش�.
        String result = year + (iMonth<10?"0"+iMonth:iMonth+"") + (iDay<10?"0"+iDay:iDay+"");
        
        return result;
    }
    
    /**
     * �ش� ���� �� 
     * �ش����� �Ͽ��� ��¥�� �����Ѵ�.(������ ������ �Ͽ����� ���)
     * @author  oikesismiz
     * @version $Id: DateUtil.java,v 1.3 2014/04/04 07:39:11 pochul2423 Exp $
     * @since   1.0
     * 
     * @param year
     * @param weekCnt
     * @return
     */
    public static String getFirstSundayOfWeek(String year, String weekCnt)
    {
        Calendar cal = Calendar.getInstance();

        int iYear    = Integer.parseInt(year);
        int iWeekCnt = Integer.parseInt(weekCnt);
        
        cal.set(Calendar.YEAR , iYear);
        cal.set(Calendar.WEEK_OF_YEAR , iWeekCnt);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);

        // month�� 0 base �̴�.
        int iMonth = cal.get(Calendar.MONTH)+1;
        int iDay = cal.get(Calendar.DAY_OF_MONTH);
        
        // ���� ���� 2�ڸ��� ���߾� �ش�.
        String result = year + (iMonth<10?"0"+iMonth:iMonth+"") + (iDay<10?"0"+iDay:iDay+"");
        
        return result;
    }
    
    /**
     * �ش� ���� �� 
     * �ش����� �Ͽ��� ��¥�� �����Ѵ�.(������ ������ �������� ���)
     * @author  oikesismiz
     * @version $Id: DateUtil.java,v 1.3 2014/04/04 07:39:11 pochul2423 Exp $
     * @since   1.0
     * 
     * @param year
     * @param weekCnt
     * @return
     */
    public static String getSundayOfWeek(String year, String weekCnt)
    {
        Calendar cal = Calendar.getInstance();

        int iYear    = Integer.parseInt(year);
        int iWeekCnt = Integer.parseInt(weekCnt) + 1;
        
        cal.set(Calendar.YEAR , iYear);
        cal.set(Calendar.WEEK_OF_YEAR , iWeekCnt);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);

        // month�� 0 base �̴�.
        int iMonth = cal.get(Calendar.MONTH)+1;
        int iDay = cal.get(Calendar.DAY_OF_MONTH);
        
        // ���� ���� 2�ڸ��� ���߾� �ش�.
        String result = year + (iMonth<10?"0"+iMonth:iMonth+"") + (iDay<10?"0"+iDay:iDay+"");
        
        return result;
    }

    /**
     * �� ��¥�� ���� ���� ���Ѵ�.
     * @author  javaworker
     * @version $Id: DateUtil.java,v 1.3 2014/04/04 07:39:11 pochul2423 Exp $
     * @since   1.0
     * 
     * @param startDate : ������
     * @param endDate   : ����
     * @return
     * @throws Exception
     */
    public static int getDayInterval(String startDate, String endDate) throws Exception
    {
        Calendar fromCal = Calendar.getInstance();
        Calendar toCal   = Calendar.getInstance();
        int iDayInterval = 0;
                                        
        try 
        {
            fromCal.set(Integer.parseInt(startDate.substring(0,4)),  
                        Integer.parseInt(startDate.substring(4,6))-1,
                        Integer.parseInt(startDate.substring(6,8)) 
                        );
            
            toCal.set(Integer.parseInt(endDate.substring(0,4)),  
                      Integer.parseInt(endDate.substring(4,6))-1,
                      Integer.parseInt(endDate.substring(6,8))
                      );
            
            iDayInterval = (int)((toCal.getTime().getTime() - fromCal.getTime().getTime()) 
                                   / (1000*60*60*24));
       } 
       catch(Exception e) 
       {
           throw new Exception("wrong format string");
       }
       
       return iDayInterval;
    }
    
    public static String getTimeInterval(String startDateTime, String endDateTime) throws Exception
    {
    	String workTime = "";
    	if(startDateTime.length()!=12 ||endDateTime.length()!=12) return "";
    	
    	//������ �ð�
        Calendar endDay = new GregorianCalendar( ); 
        endDay.set(Integer.parseInt(endDateTime.substring(0, 4)), Integer.parseInt(endDateTime.substring(4,6))-1, 
                Integer.parseInt(endDateTime.substring(6,8)), Integer.parseInt(endDateTime.substring(8,10)), 
                Integer.parseInt(endDateTime.substring(10,12)));
        
      //���۽ð��� ������ ������ �ð����� 5�� ���� ���۽ð����� �Է�
        GregorianCalendar startDay = new GregorianCalendar( ); 
        
        startDay.set(Integer.parseInt(startDateTime.substring(0, 4)), Integer.parseInt(startDateTime.substring(4,6))-1, 
                Integer.parseInt(startDateTime.substring(6,8)), Integer.parseInt(startDateTime.substring(8,10)), 
                Integer.parseInt(startDateTime.substring(10,12)));
        
        workTime = ((endDay.getTimeInMillis()- startDay.getTimeInMillis())/60000) + "";
        if(workTime == "" || "0".equals(workTime)) workTime = "1";
              
       return workTime;
    }
    
    /**
     * originDate �� �ش� days ���� ���Ѵ�.
     * @author  javaworker
     * @version $Id: DateUtil.java,v 1.3 2014/04/04 07:39:11 pochul2423 Exp $
     * @since   1.0
     * 
     * @param originDate
     * @param days
     * @return
     */
    public static String getAfterDays(String originDate, String days)
    {
        int iDays = Integer.parseInt(days);
        return getAfterDays(originDate, iDays) ;
    }
    
    /**
     * originDate �� �ش� days ���� ���Ѵ�.
     * @author  javaworker
     * @version $Id: DateUtil.java,v 1.3 2014/04/04 07:39:11 pochul2423 Exp $
     * @since   1.0
     * 
     * @param originDate
     * @param days
     * @return
     * @throws Exception
     */
    public static String getAfterDays(String originDate, int days) 
    {
        Calendar tempCal = Calendar.getInstance();
    
        tempCal.set(Integer.parseInt(originDate.substring(0, 4)),
                    Integer.parseInt(originDate.substring(4, 6)) - 1,
                    Integer.parseInt(originDate.substring(6, 8))
                    );
    
        tempCal.add(Calendar.DAY_OF_MONTH, days);
    
        int iYear = tempCal.get(Calendar.YEAR);
        // month�� 0 base �̴�.
        int iMonth = tempCal.get(Calendar.MONTH)+1;
        int iDay = tempCal.get(Calendar.DAY_OF_MONTH);
        
        return iYear + (iMonth<10?"0"+iMonth:iMonth+"") + (iDay<10?"0"+iDay:iDay+"");
    }
    /**
     * originDate�� �ش� ���� ���Ѵ�.
     * @author  youngkunYoo
     * @version $Id: DateUtil.java,v 1.3 2014/04/04 07:39:11 pochul2423 Exp $
     * @since   1.0
     * 
     * @param originDate
     * @param month
     * @return
     * @throws Exception
     */
    public static String getAfterMonth(String originDate, int month)
    {
        Calendar tempCal = Calendar.getInstance();
        
        tempCal.set(Integer.parseInt(originDate.substring(0, 4)),
                    Integer.parseInt(originDate.substring(4, 6)) - 1,
                    Integer.parseInt(originDate.substring(6, 8))
                    );
    
        tempCal.add(Calendar.MONTH, month);
    
        int iYear = tempCal.get(Calendar.YEAR);
        // month�� 0 base �̴�.
        int iMonth = tempCal.get(Calendar.MONTH)+1;
        int iDay = tempCal.get(Calendar.DAY_OF_MONTH);
        
        return iYear + (iMonth<10?"0"+iMonth:iMonth+"") + (iDay<10?"0"+iDay:iDay+"");
    }
    
    /**
     * �ش� �⿡ ¦��, Ȧ�� ���� 
     * ����(�����)�� �迭�� �����Ѵ�.
     * @author  javaworker
     * @version $Id: DateUtil.java,v 1.3 2014/04/04 07:39:11 pochul2423 Exp $
     * @since   1.0
     * 
     * @param year
     * @param odd : true[Ȧ��]
     *              false[¦��]
     * @return
     * @throws Exception
     */
    public static String [] getMonthYearWeeks(String year, boolean odd) throws Exception
    {
        String [] evenWeek = null;
        ArrayList weekList = new ArrayList();
        
        String [] temp = null;
        
        int startMonth = 2;
        if (odd)
        {
            // Ȧ�� ���̹Ƿ� 1�� ����
            startMonth = 1;
        }
        
        for (int i=startMonth; i<=12; i=i+2)
        {
            temp = getMonthWeek(year, i+"");
            
            for (int j=0; j<temp.length; j++)
            {
                weekList.add(temp[j]);
            }
        }
        
        //===========================================
        // ���õ� arrayList�� String [] �ٲ㼭 �����Ѵ�.
        evenWeek = new String[weekList.size()];
        
        for (int i=0; i<evenWeek.length; i++)
        {
            evenWeek[i] = (String)weekList.get(i);
        }
        
        return evenWeek;
    }
    
    /**
     * �ش� ��/���� ������ ����  
     * �����Ѵ�.
     * @author  javaworker
     * @version $Id: DateUtil.java,v 1.3 2014/04/04 07:39:11 pochul2423 Exp $
     * @since   1.0
     * 
     * @param year
     * @param month 
     * @return
     * @throws Exception
     */
    public static String getLastDayOfMonth(String year, String month) throws Exception
    {
    	String[] monthLength = new String[12];
    	
    	monthLength[0] = "31";
    	int iyear = Integer.parseInt(year);
    	if(((iyear % 4 == 0) && (iyear % 100 != 0)) || (iyear % 400 == 0))
    	{
    		monthLength[1] = "29";
    	}
    	else
    	{
    		monthLength[1] = "28";
    	}
    	monthLength[2] = "31";
    	monthLength[3] = "30";
    	monthLength[4] = "31";
    	monthLength[5] = "30";
    	monthLength[6] = "31";
    	monthLength[7] = "31";
    	monthLength[8] = "30";
    	monthLength[9] = "31";
    	monthLength[10] = "30";
    	monthLength[11] = "31";
    	
    	return monthLength[Integer.parseInt(month)-1];
    }    

    /**
     * ��, ���� �ش��ϴ� ����(�����)�� �����Ѵ�. 
     * @author  javaworker
     * @version $Id: DateUtil.java,v 1.3 2014/04/04 07:39:11 pochul2423 Exp $
     * @since   1.0
     * 
     * @param year
     * @param month
     * @return
     * @throws Exception
     */
    public static String [] getMonthWeek(String year, String month) throws Exception
    {
        int iMonth = Integer.parseInt(month); 

        // �ش� ��, ���� ù° ����(�� ����)�� ���Ѵ�.
        String tempWeek = getFirstMondayWeek(year, month);
        
        // �ش��, �������� ù° ����(�� ����)�� ���Ѵ�.
        String nextMonthWeek = getFirstMondayWeek(year, (iMonth+1)+"");
        if ("12".equals(month))
        {
            nextMonthWeek = "53";   // 12���̸� ������ ������ 53�� �Ͽ� 52�������� ���õǰ� �Ѵ�.
        }
        
        
        int iThisMonthWeek = Integer.parseInt(tempWeek);
        int iNextMonthWeek = Integer.parseInt(nextMonthWeek);
        
        String [] resultWeek = new String[iNextMonthWeek - iThisMonthWeek]; 
        
        // ���� �ش� ���� ����(�� ����) �迭�� �����Ѵ�.
        for (int i=iThisMonthWeek, z=0; i<iNextMonthWeek; i++, z++)
        {
            resultWeek[z] = i+"";
        }
        
        return resultWeek;
    }
    
    /**
     * ����(8�ڸ� �����) �� �ش��ϴ� ������ �����Ѵ�.
     * @author  mentor
     * @version $Id: DateUtil.java,v 1.3 2014/04/04 07:39:11 pochul2423 Exp $
     * @since   1.0
     * 
     * @param yyyyMMdd : �����
     * @return  1:��, 2:��, 3:ȭ, 4:��, 5:��, 6:��, 7:��
     * @throws Exception
     */
    public static int getDayOfWeek(String yyyyMMdd)
    {
        if (yyyyMMdd == null || yyyyMMdd.length() != 8)
        {
            return 0;            
        }

        // ��, ��, �Ϸ� �и��Ѵ�.
        String year  = yyyyMMdd.substring(0, 4);
        String month = yyyyMMdd.substring(4, 6);        
        String day   = yyyyMMdd.substring(6);
        
        
        int iYear = 0;
        int iMonth = 0;
        int iDay = 0;
        
        try
        {
            iYear  = Integer.parseInt(year);
            iMonth = Integer.parseInt(month);
            iDay   = Integer.parseInt(day);
        }
        catch(Exception e)
        {
            return 0;
        }
        
        Calendar tempCal = Calendar.getInstance();
        
        tempCal.set(iYear, iMonth-1, iDay);
        
        return tempCal.get(Calendar.DAY_OF_WEEK);
    }
    
    /**
     * ����(8�ڸ� �����) �� �ش��ϴ� ������ �����Ѵ�.
     * @author  javaworker
     * @version $Id: DateUtil.java,v 1.3 2014/04/04 07:39:11 pochul2423 Exp $
     * @since   1.0
     * 
     * @param yyyyMMdd : �����
     * @return  SUN:��, MON:��, TUE:ȭ, WED:��, THU:��, FRI:��, SAT:��
     * @throws Exception
     */
    public static String getDayOfWeekStr(String yyyyMMdd)
    {
        String dayWeek = "";
        
        // 1:��, 2:��, 3:ȭ, 4:��, 5:��, 6:��, 7:��
        int iDayWeek = DateUtil.getDayOfWeek(yyyyMMdd);
        switch(iDayWeek)
        {
            case 1:
                dayWeek = "SUN";
                break;
            case 2:
                dayWeek = "MON";
                break;
            case 3:
                dayWeek = "TUE";
                break;
            case 4:
                dayWeek = "WED";
                break;
            case 5:
                dayWeek = "THU";
                break;
            case 6:
                dayWeek = "FRI";
                break;
            case 7:
                dayWeek = "SAT";
                break;
        }
        
        return dayWeek;
    }
    /**
     * ����(8�ڸ� �����) �� �ش��ϴ� ������ �����Ѵ�.
     * @author  javaworker
     * @version $Id: DateUtil.java,v 1.3 2014/04/04 07:39:11 pochul2423 Exp $
     * @since   1.0
     * 
     * @param yyyyMMdd : �����
     * @return  SUN:��, MON:��, TUE:ȭ, WED:��, THU:��, FRI:��, SAT:��
     * @throws Exception
     */
    public static String getDayOfWeekLangStr(String yyyyMMdd,String lang)
    {
        String dayWeek = "";
        
        // 1:��, 2:��, 3:ȭ, 4:��, 5:��, 6:��, 7:��
        int iDayWeek = DateUtil.getDayOfWeek(yyyyMMdd);
        switch(iDayWeek)
        {
            case 1:
            	if ("ko".equals(lang)) {
            		dayWeek = "��";
				}else
                dayWeek = "SUN";
                break;
            case 2:
            	if ("ko".equals(lang)) {
            		dayWeek = "��";
				}else
                dayWeek = "MON";
                break;
            case 3:
            	if ("ko".equals(lang)) {
            		dayWeek = "ȭ";
				}else
                dayWeek = "TUE";
                break;
            case 4:
            	if ("ko".equals(lang)) {
            		dayWeek = "��";
				}else
                dayWeek = "WED";
                break;
            case 5:
            	if ("ko".equals(lang)) {
            		dayWeek = "��";
				}else
                dayWeek = "THU";
                break;
            case 6:
            	if ("ko".equals(lang)) {
            		dayWeek = "��";
				}else
                dayWeek = "FRI";
                break;
            case 7:
            	if ("ko".equals(lang)) {
            		dayWeek = "��";
				}else
                dayWeek = "SAT";
                break;
        }
        
        return dayWeek;
    }
    
    /**
     * �� ��¥�� ���Ͽ� fromDate �� �۰ų� ������ true
     * ũ�� false�� �����Ѵ�.
     * @author  javaworker
     * @version $Id: DateUtil.java,v 1.3 2014/04/04 07:39:11 pochul2423 Exp $
     * @since   1.0
     * 
     * @param fromDate
     * @param toDate
     * @return
     * @throws Exception
     */
    public static boolean compareDate(String fromDate, String toDate)
    {
        int iFromdate = Integer.parseInt(fromDate);
        int iToDate = Integer.parseInt(toDate);
        
        if (iFromdate <= iToDate)
        {
            return true;
        }

        return false;
    }

    /**
     * �� ��¥�� sign[�ε�ȣ]�� ���Ͽ� 
     * firstDate sign secondDate �� ������ ������ true, �ƴϸ� false �� �����Ѵ�.
     * ex) compareDate('20040101', '>', '20040104') ==> 20040101 > 20040104
     * @author  javaworker
     * @version $Id: DateUtil.java,v 1.3 2014/04/04 07:39:11 pochul2423 Exp $
     * @since   1.0
     * 
     * @param firstDate : ������
     * @param sign : �ε�ȣ
     * @param secondDate : ������
     * @return
     * @throws Exception
     */
    public static boolean compareDate(String firstDate, String firstTime, 
            String sign, String secondDate, String secondTime) throws Exception
    {
        //=====================================================
        if (firstTime == null || "".equals(firstTime))
        {
            firstTime = "0000";
        }
        if (secondTime == null || "".equals(secondTime))
        {
            secondTime = "0000";
        }
        
        firstDate  = firstDate + firstTime;
        secondDate = secondDate + secondTime;
        //====================================================
        
        if (firstDate.length() != secondDate.length())
        {
            throw new Exception("[" + firstDate + ":" + secondDate + "] �񱳳�¥�� ������ ��ġ���� �ʽ��ϴ�.");
        }
        
        long iFirstDate  = Long.parseLong(firstDate);
        long iSecondDate = Long.parseLong(secondDate);
        
        if ("<".equals(sign))
        {
            return (iFirstDate < iSecondDate);
        }
        else if ("==".equals(sign))
        {
            return (iFirstDate == iSecondDate);
        }
        else if (">".equals(sign))
        {
            return (iFirstDate > iSecondDate);
        }
        else if ("<=".equals(sign))
        {
            return (iFirstDate <= iSecondDate);
        }
        else if (">=".equals(sign))
        {
            return (iFirstDate >= iSecondDate);
        }
        else if ("!=".equals(sign))
        {
            return (iFirstDate != iSecondDate);
        }
        
        throw new Exception("[" + sign + "] �˼����� �ε�ȣ ���� �Դϴ�.");
    }
    
    /**
     * �ش� �⵵�� ������ ������ ������ �����Ѵ�.
     * ������ ������ �������� �����ִ� ������ �����Ѵ�.
     * ��). ������ ������ 53������ ��� 53������ �������� ���ٸ� 52������ �����Ѵ�.   
     * @author  javaworker
     * @version $Id: DateUtil.java,v 1.3 2014/04/04 07:39:11 pochul2423 Exp $
     * @since   1.0
     * 
     * @param year
     * @return
     */
    public static String lastMondayWeekOfYear(String year) throws Exception
    {
        // �ش�⵵�� ������ ���ڴ� 12�� 31���� �̴�.
        String lastDayOfYear = year+"1231";
        
        // ������ ���ڰ� ���� �������� ���Ѵ�.
        int dayOfWeek = DateUtil.getDayOfWeek(lastDayOfYear);
        
        /*
         * �ش� �⵵�� ������ ������ 53�̰�
         * ������ ���� ������ �Ͽ��� �̶�� 52�����̴�.
         * ������ ���� ������ �Ͽ����� �ƴ϶�� 53�����̴�.
         * �ش� �⵵�� ������ ������ 52�����̰�
         * ������ ���ڰ� �Ͽ����� �ƴ϶�� 52�����̴�.
         */
        String lastWeekOfYear = DateUtil.getWeekOfYear(lastDayOfYear);
        if ("53".equals(lastWeekOfYear))
        {
            if (dayOfWeek == 1)
            {
                return "52";
            }
            
            return "53";
        }
        else if ("52".equals(lastWeekOfYear))
        {
            if (dayOfWeek == 1)
            {
                return "51";
            }
            
            return "52";
        }

        return lastWeekOfYear;
    }
    
    /**
     * �� ��¥ ������ ��¥���� �迭�� �����Ѵ�.
     * @author  semanticker
     * @version $Id: DateUtil.java,v 1.3 2014/04/04 07:39:11 pochul2423 Exp $
     * @since   1.0
     * 
     * @param startDate
     * @param endDate
     * @return
     */
    public static String [] getArrayBetweenDate(String startDate, String endDate) throws Exception
    {
        ArrayList arrayDate = new ArrayList();
       
        int dateSize = getDayInterval(startDate, endDate);
        
        for(int i=0; i<dateSize+1; i++)
        {
            arrayDate.add(getAfterDays(startDate, i));
        }

        return (String [])arrayDate.toArray(new String[arrayDate.size()]);
    }
    
    /**
     * �� ��¥ ������ ���� �迭�� �����Ѵ�.
     * @author  javaworker
     * @version $Id: DateUtil.java,v 1.3 2014/04/04 07:39:11 pochul2423 Exp $
     * @since   1.0
     * 
     * @param startDate
     * @param endDate
     * @return
     * @throws Exception
     */
    public static String [] getArrayBetweenMonth(String startDate, String endDate)
    {
        ArrayList arrayDate = new ArrayList();
       
        int startYear = Integer.parseInt(startDate.substring(0, 4));
        int startMonth = Integer.parseInt(startDate.substring(4, 6));
        
        int endYear = Integer.parseInt(endDate.substring(0, 4));
        int endMonth = Integer.parseInt(endDate.substring(4, 6));
        
        for(int year=startYear, month=startMonth; (year==endYear && month<=endMonth) || (year<endYear) ; month++)
        {
            arrayDate.add(year+""+(month<10?"0"+month:month));
            
            // 12���� �Ǿ��ٸ� �⵵�� �����Ѵ�.
            if (month == 12)
            {
                year ++;
                month = 0;
            }
        }
        
        return (String [])arrayDate.toArray(new String[arrayDate.size()]);
    }
    
    /**
     * yyyy-MM, yyyyMM�� ��¥������ �ش���� ���������ڸ� ���� yyyy-MM-dd, yyyyMMdd�� �ٲ��ش�.
     * ex) 2018-07 -> 2018-07-31, 201807 -> 20180731
     * @author  js.lee
     * @version $Id: Exp $
     * @since   1.0
     * 
     * @param date
     * @return
     * @throws Exception
     */
    public static String plusLastDayOfMonth(String date) throws Exception
    {
    	boolean hyphen = false;
    	String result = "";
    	
    	if (date.contains("-")) {
    		date = date.replaceAll("-", "");
    		hyphen = true;
		}
    	
    	String year = date.substring(0,4);
    	String month = date.substring(4);
    	
    	String day = getLastDayOfMonth(year, month);
    	
    	if (hyphen) {
			result = year + "-" + month + "-" + day;
		} else{
			result = year + month + day;
		}
    	
    	return result;
    }    
    
    /**
     * Return Date(yyyyMMdd)
     * 
     * @param offset - UTC �������� �ش� ����ŭ �ð��� ������
     * @return String
     * @throws Exception 
     */
    public static String getTimeStamp(int offset)
    {
        DateService dateService = (DateService)CommonUtil.getBean("dateService");
        return dateService.getTimeStamp(offset, "yyyyMMdd", "DAY", 0);
    }
    /**
     * Return Date
     * 
     * @param offset - UTC �������� �ش� ����ŭ �ð��� ������
     * @param format - return date format (yyyy-MM-dd HH:mm:ss)
     * @return String
     * @throws Exception 
     */
    public static String getTimeStamp(int offset, String format)
    {
        DateService dateService = (DateService)CommonUtil.getBean("dateService");
        return dateService.getTimeStamp(offset, format, "DAY", 0);
    }
    /**
     * Return Date
     * 
     * @param offset - UTC �������� �ش� ����ŭ �ð��� ������
     * @param intervalType - one of YEAR, MONTH, WEEK, DAY, HOUR, MINUTE, SECOND
     * @param interval - size of interval
     * @return String
     * @throws Exception 
     */
    public static String getTimeStamp(int offset, String intervalType, int interval)
    {
        DateService dateService = (DateService)CommonUtil.getBean("dateService");
        return dateService.getTimeStamp(offset, "yyyyMMdd", intervalType, interval);
    }
    /**
     * Return Date
     * 
     * @param offset - UTC �������� �ش� ����ŭ �ð��� ������
     * @param format - return date format (yyyy-MM-dd HH:mm:ss)
     * @param intervalType - one of YEAR, MONTH, WEEK, DAY, HOUR, MINUTE, SECOND
     * @param interval - size of interval
     * @return String
     * @throws Exception 
     */
    public static String getTimeStamp(int offset, String format, String intervalType, int interval)
    {
        DateService dateService = (DateService)CommonUtil.getBean("dateService");
        return dateService.getTimeStamp(offset, format, intervalType, interval);
    }
    
}