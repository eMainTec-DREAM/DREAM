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
     * 일정기간 이전[+]/이후[-] Date object를 반환한다.
     * 
     * @param ai_day
     *            일수 차이
     * @return Date
     */
    private static Date get(int ai_day)
    {
        // 변수선언 및 초기화.
        Calendar cal = Calendar.getInstance();
        // 일정기간 이전/이후 계산.
        if (ai_day != 0)
            cal.add(Calendar.DATE, ai_day);
        return cal.getTime();
    }

    /**
     * 현재일시를 특정형식(yyyyMMddHHmmss)으로 반환
     * 
     * @param as_format
     * @return String
     */
    public static String getDateTime(String as_format)
    {
        // 변수선언 및 초기화.
        Date date = get();
        SimpleDateFormat dateFormat = new SimpleDateFormat(as_format);
        return dateFormat.format(date);
    }

    /**
     * 일정기간 이전[+]/이후[-] 일시를 특정형식(yyyyMMddHHmmss)으로 반환 한다.
     * 
     * @param as_format
     * @param ai_day
     * @return String
     */
    public static String getDateTime(String as_format, int ai_day)
    {
        // 변수선언 및 초기화.
        Date date = get(ai_day);
        SimpleDateFormat dateFormat = new SimpleDateFormat(as_format);
        return dateFormat.format(date);
    }
    
    /**
     * Date를 특정형식(yyyyMMddHHmmss)으로 반환 한다.
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
     * 현재일시를 yyyyMMddHHmmss 형식으로 반환한다.
     * 
     * @return String
     */
    public static String getDateTime()
    {
        return getDateTime("yyyyMMddHHmmss");
    }

    /**
     * 일정기간 이전[+]/이후[-] 일시(yyyyMMddHHmmss)를 반환 한다.
     * 
     * @param ai_day
     * @return String
     */
    public static String getDateTime(int ai_day)
    {
        return getDateTime("yyyyMMddHHmmss", ai_day);
    }

    /**
     * 분까지 현재일시(yyyyMMddHHmm)를 반환한다.
     * 
     * @return String
     */
    public static String getDateMin()
    {
        return getDateTime("yyyyMMddHHmm");
    }

    /**
     * 분까지 일정기간 이전[+]/이후[-] 일시(yyyyMMddHHmmss)를 반환한다.
     * 
     * @param ai_day
     * @return String
     */
    public static String getDateMin(int ai_day)
    {
        return getDateTime("yyyyMMddHHmm", ai_day);
    }

    /**
     * 시까지 현재일시(yyyyMMddHH)를 반환한다.
     * 
     * @return String
     */
    public static String getDateHour()
    {
        return getDateTime("yyyyMMddHH");
    }

    /**
     * 시까지 일정기간 이전[+]/이후[-] 일시(yyyyMMddHH)를 반환한다
     * 
     * @param ai_day
     * @return String
     */
    public static String getDateHour(int ai_day)
    {
        return getDateTime("yyyyMMddHH", ai_day);
    }

    /**
     * 현재일자(yyyyMMdd)를 반환한다
     * 
     * @return String
     */
    public static String getDate()
    {
        return getDateTime("yyyyMMdd");
    }

    /**
     * 일정기간 이전[+]/이후[-] 의 일자(yyyyMMdd)를 반환한다.
     * 
     * @param ai_day
     * @return String
     */
    public static String getDate(int ai_day)
    {
        return getDateTime("yyyyMMdd", ai_day);
    }

    /**
     * 현재년도를 반환한다.
     * 
     * @return String
     */
    public static String getYear()
    {
        return getDateTime("yyyy");
    }

    /**
     * 일정기간 이전[+]/이후[-]의 년도를 반환한다.
     * 
     * @param ai_day
     * @return String
     */
    public static String getYear(int ai_day)
    {
        return getDateTime("yyyy", ai_day);
    }

    /**
     * 현재 월을 반환한다.
     * 
     * @return String
     */
    public static String getMonth()
    {
        return getDateTime("MM");
    }

    /**
     * 일정기간 이전[+]/이후[-]의 월을 반환한다.
     * 
     * @param ai_day
     * @return String
     */
    public static String getMonth(int ai_day)
    {
        return getDateTime("MM", ai_day);
    }

    /**
     * 현재 일을 반환한다.
     * 
     * @return String
     */
    public static String getDay()
    {
        return getDateTime("dd");
    }

    /**
     * 일정기간 이전[+]/이후[-]의 일을 반환한다.
     * 
     * @param ai_day
     * @return String
     */
    public static String getDay(int ai_day)
    {
        return getDateTime("dd", ai_day);
    }

    /**
     * 현재 시간을 반환한다.(24시간 기준)
     * 
     * @return String
     */
    public static String getHour()
    {
        return getDateTime("HH");
    }

    /**
     * 현재 분을 반환한다.
     * 
     * @return String
     */
    public static String getMinute()
    {
        return getDateTime("mm");
    }

    /**
     * 현재 초를 반환한다.
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
     * 년에 해당하는 첫번째 월요일을 일자로 배열로 1~12월 까지 리턴한다.
     * @author  javaworker
     * @version $Id: DateUtil.java,v 1.3 2014/04/04 07:39:11 pochul2423 Exp $
     * @since   1.0
     * 
     * @param year
     * @return [0] : 월의 첫번째 월요일 일자 년월일 8자리
     *         [1] : 월의 첫번째 월요일의 주(번째 주) 
     */
    public static String [][] getFirstMonday(String year)
    {
        // 년의 첫번째 주를 사용하지 않는다.
        return DateUtil.getFirstMonday(year, true);
    }
    
    /**
     * 년에 해당하는 첫번째 월요일을 일자로 배열로 1~12월 까지 리턴한다.
     * @author  javaworker
     * @version $Id: DateUtil.java,v 1.3 2014/04/04 07:39:11 pochul2423 Exp $
     * @since   1.0
     * 
     * @param year
     * @return [0] : 월의 첫번째 월요일 일자 년월일 8자리
     *         [1] : 월의 첫번째 월요일의 주(번째 주) 
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
            yearFirstMonday[i-1][0] = getFirstMonday(year, i+"", disableFirstWeek);    // 월의 첫번째 월요일 날짜(8자리)
            yearFirstMonday[i-1][1] = getWeekOfYear(yearFirstMonday[i-1][0]);          // 첫번째 월요일 년의 몇번째 주
        }
   
        return yearFirstMonday;
    }
 
    /**
     * 년, 월에 해당하는 첫번째 월요일을 
     * 일자로 리턴한다. 
     * @author  javaworker
     * @version $Id: DateUtil.java,v 1.3 2014/04/04 07:39:11 pochul2423 Exp $
     * @since   1.0
     * 
     * @param year : 4자리 년도
     * @param month : 2자리 월 01 ~ 12
     * @return 4자리의 월일이다. ex) 0105
     */
    public static String getFirstMonday(String year, String month)
    {
        // 첫번째 주를 쓰지 않게 한다.
        
        /*
         * 첫째주를 쓰게한다.
         * 수정 : 2005.12.28
         * - javaworker
         */
        return DateUtil.getFirstMonday(year, month, false);
    }
    
    /**
     * 년, 월에 해당하는 첫번째 월요일을 
     * 일자로 리턴한다. 
     * @author  javaworker
     * @version $Id: DateUtil.java,v 1.3 2014/04/04 07:39:11 pochul2423 Exp $
     * @since   1.0
     * 
     * @param year : 4자리 년도
     * @param month : 2자리 월 01 ~ 12
     * @return 4자리의 월일이다. ex) 0105
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
            // 리턴할때 8자리로 맞추어주기 위해서  무조건 2자리로 한다.
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
        // month는 0 base 이다. ex) 0 -> 1월, 5 -> 6월
        cal.set(iYear, iMonth-1, 1);
        //============================================
        
        // 1:일, 2:월, 3:화, 4:수, 5:목, 6:금, 7:토
        int iWeek = cal.get(Calendar.DAY_OF_WEEK);
        
        // 해당 년, 월의 1일의 요일
        switch(iWeek)
        {
            case Calendar.SUNDAY:
                // 일요일 + 1 = 월
                firstMondayDate = year+month+"02";
                
                // 첫번째 주를 안쓰는 경우
                if (disableFirstWeek)
                {
                    // 1월 1일인 경우 첫주는 안쓰므로 + 8일 한다. 담주 월요일
                    if ("0101".equals(month+"01"))
                    {
                        firstMondayDate = year+month+"09";
                    }
                }
                
                break;
            case Calendar.MONDAY:
                // 월요일 = 월
                firstMondayDate = year+month+"01";
                
                // 첫번째 주를 안쓰는 경우
                if (disableFirstWeek)
                {
                    // 1월 1일인 경우 첫주는 안쓰므로 + 7일 한다.
                    if ("0101".equals(month+"01"))
                    {
                        firstMondayDate = year+month+"08";
                    }
                }
                
                break;
            case Calendar.TUESDAY:
                // 화요일 + 6 = 월
                firstMondayDate = year+month+"07";
                break;
            case Calendar.WEDNESDAY:
                // 수요일 + 5 = 월
                firstMondayDate = year+month+"06";
                break;
            case Calendar.THURSDAY:
                // 목요일 + 4 = 월
                firstMondayDate = year+month+"05";
                break;
            case Calendar.FRIDAY:                
                // 금요일 + 3 = 월
                firstMondayDate = year+month+"04";
                break;
            case Calendar.SATURDAY:
                // 토요일 + 2 = 월
                firstMondayDate = year+month+"03";
                break;
        }
        
        // 현재 년,월의 첫번째 월요일을 리턴한다.
        return firstMondayDate;
    }
    
    /**
     * 년, 월에 해당하는 첫번째 요일을 
     * 일자로 리턴한다. 
     * @author  javaworker
     * @version $Id: DateUtil.java,v 1.3 2014/04/04 07:39:11 pochul2423 Exp $
     * @since   1.0
     * 
     * @param year : 4자리 년도
     * @param month : 2자리 월 01 ~ 12
     * @return 4자리의 월일이다. ex) 0105
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
            // 리턴할때 8자리로 맞추어주기 위해서  무조건 2자리로 한다.
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
        // month는 0 base 이다. ex) 0 -> 1월, 5 -> 6월
        cal.set(iYear, iMonth-1, 1);
        //============================================
        
        // 1:일, 2:월, 3:화, 4:수, 5:목, 6:금, 7:토
        int iWeek = cal.get(Calendar.DAY_OF_WEEK) - 1;
        
        return iWeek+"";
    }
    
    /**
     * 해당 년, 월의 첫번째 주가 년의 몇번째 주차인지 계산하여 리턴한다.
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
     * 해당 일자(년월일)에 해당하는 
     * 주가 몇번째 주인지 계산한다.
     * @author  javaworker
     * @version $Id: DateUtil.java,v 1.3 2014/04/04 07:39:11 pochul2423 Exp $
     * @since   1.0
     * 
     * @param yyyyMMdd 년월일
     * @return
     */
    public static String getWeekOfYear(String yyyyMMdd)
    {
        if (yyyyMMdd == null || yyyyMMdd.length() != 8)
        {
            return null;            
        }

        // 년, 월, 일로 분리한다.
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
        // month는 0 base 이다.
        cal.set(iYear, iMonth-1, iDay);

        int weekOfYear = cal.get(Calendar.WEEK_OF_YEAR);
        
        // 53주차인 경우 1주차로 표시된다. - 보정이 필요한다.
        if (iMonth == 12 && weekOfYear == 1)
        {
            // 12월이고 주차가 1인경우는 53주차이다.
            weekOfYear = 53;
        }
        
        // 해당 년도의 몇번째 주인지 계산
        return weekOfYear + "";
    }
    
    /**
     * 해당 일자(년월일)에 해당하는 
     * 주가 월 기준 몇번째 주인지 계산한다.
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param yyyyMMdd 년월일
     * @return
     */
    public static int getWeekOfMonth(String yyyyMMdd)
    {
        if (yyyyMMdd == null || yyyyMMdd.length() != 8)
        {
            return -1;            
        }

        // 년, 월, 일로 분리한다.
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
        // month는 0 base 이다.
        cal.set(iYear, iMonth-1, iDay);
        return cal.get(Calendar.WEEK_OF_MONTH);
    }
    
    /**
     * 해당 주차 를 
     * 해당주의 월요일 날짜를 리턴한다.
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

        // month는 0 base 이다.
        int iMonth = cal.get(Calendar.MONTH)+1;
        int iDay = cal.get(Calendar.DAY_OF_MONTH);
        
        // 월과 일을 2자리로 맞추어 준다.
        String result = year + (iMonth<10?"0"+iMonth:iMonth+"") + (iDay<10?"0"+iDay:iDay+"");
        
        return result;
    }
    
    /**
     * 해당 주차 를 
     * 해당주의 토요일 날짜를 리턴한다.
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

        // month는 0 base 이다.
        int iMonth = cal.get(Calendar.MONTH)+1;
        int iDay = cal.get(Calendar.DAY_OF_MONTH);
        
        // 월과 일을 2자리로 맞추어 준다.
        String result = year + (iMonth<10?"0"+iMonth:iMonth+"") + (iDay<10?"0"+iDay:iDay+"");
        
        return result;
    }
    
    /**
     * 해당 주차 를 
     * 해당주의 일요일 날짜를 리턴한다.(주차의 시작이 일요일인 경우)
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

        // month는 0 base 이다.
        int iMonth = cal.get(Calendar.MONTH)+1;
        int iDay = cal.get(Calendar.DAY_OF_MONTH);
        
        // 월과 일을 2자리로 맞추어 준다.
        String result = year + (iMonth<10?"0"+iMonth:iMonth+"") + (iDay<10?"0"+iDay:iDay+"");
        
        return result;
    }
    
    /**
     * 해당 주차 를 
     * 해당주의 일요일 날짜를 리턴한다.(주차의 시작이 월요일인 경우)
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

        // month는 0 base 이다.
        int iMonth = cal.get(Calendar.MONTH)+1;
        int iDay = cal.get(Calendar.DAY_OF_MONTH);
        
        // 월과 일을 2자리로 맞추어 준다.
        String result = year + (iMonth<10?"0"+iMonth:iMonth+"") + (iDay<10?"0"+iDay:iDay+"");
        
        return result;
    }

    /**
     * 두 날짜의 사이 값을 구한다.
     * @author  javaworker
     * @version $Id: DateUtil.java,v 1.3 2014/04/04 07:39:11 pochul2423 Exp $
     * @since   1.0
     * 
     * @param startDate : 시작일
     * @param endDate   : 끝일
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
    	
    	//끝나는 시간
        Calendar endDay = new GregorianCalendar( ); 
        endDay.set(Integer.parseInt(endDateTime.substring(0, 4)), Integer.parseInt(endDateTime.substring(4,6))-1, 
                Integer.parseInt(endDateTime.substring(6,8)), Integer.parseInt(endDateTime.substring(8,10)), 
                Integer.parseInt(endDateTime.substring(10,12)));
        
      //시작시간이 없으면 끝나는 시간에서 5분 전을 시작시간으로 입력
        GregorianCalendar startDay = new GregorianCalendar( ); 
        
        startDay.set(Integer.parseInt(startDateTime.substring(0, 4)), Integer.parseInt(startDateTime.substring(4,6))-1, 
                Integer.parseInt(startDateTime.substring(6,8)), Integer.parseInt(startDateTime.substring(8,10)), 
                Integer.parseInt(startDateTime.substring(10,12)));
        
        workTime = ((endDay.getTimeInMillis()- startDay.getTimeInMillis())/60000) + "";
        if(workTime == "" || "0".equals(workTime)) workTime = "1";
              
       return workTime;
    }
    
    /**
     * originDate 에 해당 days 값을 더한다.
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
     * originDate 에 해당 days 값을 더한다.
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
        // month는 0 base 이다.
        int iMonth = tempCal.get(Calendar.MONTH)+1;
        int iDay = tempCal.get(Calendar.DAY_OF_MONTH);
        
        return iYear + (iMonth<10?"0"+iMonth:iMonth+"") + (iDay<10?"0"+iDay:iDay+"");
    }
    /**
     * originDate에 해당 월을 더한다.
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
        // month는 0 base 이다.
        int iMonth = tempCal.get(Calendar.MONTH)+1;
        int iDay = tempCal.get(Calendar.DAY_OF_MONTH);
        
        return iYear + (iMonth<10?"0"+iMonth:iMonth+"") + (iDay<10?"0"+iDay:iDay+"");
    }
    
    /**
     * 해당 년에 짝수, 홀수 달의 
     * 주차(년기준)를 배열로 리턴한다.
     * @author  javaworker
     * @version $Id: DateUtil.java,v 1.3 2014/04/04 07:39:11 pochul2423 Exp $
     * @since   1.0
     * 
     * @param year
     * @param odd : true[홀수]
     *              false[짝수]
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
            // 홀수 달이므로 1달 부터
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
        // 셋팅된 arrayList를 String [] 바꿔서 셋팅한다.
        evenWeek = new String[weekList.size()];
        
        for (int i=0; i<evenWeek.length; i++)
        {
            evenWeek[i] = (String)weekList.get(i);
        }
        
        return evenWeek;
    }
    
    /**
     * 해당 년/월의 마지막 날을  
     * 리턴한다.
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
     * 년, 월에 해당하는 주차(년기준)를 리턴한다. 
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

        // 해당 년, 월의 첫째 주차(년 기준)를 구한다.
        String tempWeek = getFirstMondayWeek(year, month);
        
        // 해당년, 다음달의 첫째 주차(년 기준)을 구한다.
        String nextMonthWeek = getFirstMondayWeek(year, (iMonth+1)+"");
        if ("12".equals(month))
        {
            nextMonthWeek = "53";   // 12월이면 다음달 주차를 53로 하여 52주차까지 셋팅되게 한다.
        }
        
        
        int iThisMonthWeek = Integer.parseInt(tempWeek);
        int iNextMonthWeek = Integer.parseInt(nextMonthWeek);
        
        String [] resultWeek = new String[iNextMonthWeek - iThisMonthWeek]; 
        
        // 지금 해당 월의 주차(년 기준) 배열로 저장한다.
        for (int i=iThisMonthWeek, z=0; i<iNextMonthWeek; i++, z++)
        {
            resultWeek[z] = i+"";
        }
        
        return resultWeek;
    }
    
    /**
     * 인자(8자리 년월일) 에 해당하는 요일을 리턴한다.
     * @author  mentor
     * @version $Id: DateUtil.java,v 1.3 2014/04/04 07:39:11 pochul2423 Exp $
     * @since   1.0
     * 
     * @param yyyyMMdd : 년월일
     * @return  1:일, 2:월, 3:화, 4:수, 5:목, 6:금, 7:토
     * @throws Exception
     */
    public static int getDayOfWeek(String yyyyMMdd)
    {
        if (yyyyMMdd == null || yyyyMMdd.length() != 8)
        {
            return 0;            
        }

        // 년, 월, 일로 분리한다.
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
     * 인자(8자리 년월일) 에 해당하는 요일을 리턴한다.
     * @author  javaworker
     * @version $Id: DateUtil.java,v 1.3 2014/04/04 07:39:11 pochul2423 Exp $
     * @since   1.0
     * 
     * @param yyyyMMdd : 년월일
     * @return  SUN:일, MON:월, TUE:화, WED:수, THU:목, FRI:금, SAT:토
     * @throws Exception
     */
    public static String getDayOfWeekStr(String yyyyMMdd)
    {
        String dayWeek = "";
        
        // 1:일, 2:월, 3:화, 4:수, 5:목, 6:금, 7:토
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
     * 인자(8자리 년월일) 에 해당하는 요일을 리턴한다.
     * @author  javaworker
     * @version $Id: DateUtil.java,v 1.3 2014/04/04 07:39:11 pochul2423 Exp $
     * @since   1.0
     * 
     * @param yyyyMMdd : 년월일
     * @return  SUN:일, MON:월, TUE:화, WED:수, THU:목, FRI:금, SAT:토
     * @throws Exception
     */
    public static String getDayOfWeekLangStr(String yyyyMMdd,String lang)
    {
        String dayWeek = "";
        
        // 1:일, 2:월, 3:화, 4:수, 5:목, 6:금, 7:토
        int iDayWeek = DateUtil.getDayOfWeek(yyyyMMdd);
        switch(iDayWeek)
        {
            case 1:
            	if ("ko".equals(lang)) {
            		dayWeek = "일";
				}else
                dayWeek = "SUN";
                break;
            case 2:
            	if ("ko".equals(lang)) {
            		dayWeek = "월";
				}else
                dayWeek = "MON";
                break;
            case 3:
            	if ("ko".equals(lang)) {
            		dayWeek = "화";
				}else
                dayWeek = "TUE";
                break;
            case 4:
            	if ("ko".equals(lang)) {
            		dayWeek = "수";
				}else
                dayWeek = "WED";
                break;
            case 5:
            	if ("ko".equals(lang)) {
            		dayWeek = "목";
				}else
                dayWeek = "THU";
                break;
            case 6:
            	if ("ko".equals(lang)) {
            		dayWeek = "금";
				}else
                dayWeek = "FRI";
                break;
            case 7:
            	if ("ko".equals(lang)) {
            		dayWeek = "토";
				}else
                dayWeek = "SAT";
                break;
        }
        
        return dayWeek;
    }
    
    /**
     * 두 날짜를 비교하여 fromDate 가 작거나 같으면 true
     * 크면 false를 리턴한다.
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
     * 두 날짜를 sign[부등호]로 비교하여 
     * firstDate sign secondDate 이 조건이 참으면 true, 아니면 false 를 리턴한다.
     * ex) compareDate('20040101', '>', '20040104') ==> 20040101 > 20040104
     * @author  javaworker
     * @version $Id: DateUtil.java,v 1.3 2014/04/04 07:39:11 pochul2423 Exp $
     * @since   1.0
     * 
     * @param firstDate : 비교일자
     * @param sign : 부등호
     * @param secondDate : 비교일자
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
            throw new Exception("[" + firstDate + ":" + secondDate + "] 비교날짜의 형식이 일치하지 않습니다.");
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
        
        throw new Exception("[" + sign + "] 알수없는 부등호 형식 입니다.");
    }
    
    /**
     * 해당 년도의 마지막 월요일 주차를 리턴한다.
     * 마지막 주차중 월요일이 끼어있는 주차를 리턴한다.
     * 예). 마지막 주차가 53주차인 경우 53주차에 월요일이 없다면 52주차를 리턴한다.   
     * @author  javaworker
     * @version $Id: DateUtil.java,v 1.3 2014/04/04 07:39:11 pochul2423 Exp $
     * @since   1.0
     * 
     * @param year
     * @return
     */
    public static String lastMondayWeekOfYear(String year) throws Exception
    {
        // 해당년도의 마지막 일자는 12월 31일이 이다.
        String lastDayOfYear = year+"1231";
        
        // 마지막 일자가 무슨 요일인지 구한다.
        int dayOfWeek = DateUtil.getDayOfWeek(lastDayOfYear);
        
        /*
         * 해당 년도의 마지막 주차가 53이고
         * 마지막 일자 요일이 일요일 이라면 52주차이다.
         * 마지막 일자 요일이 일요일이 아니라면 53주차이다.
         * 해당 년도의 마지막 주차가 52주차이고
         * 마지막 일자가 일요일이 아니라면 52주차이다.
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
     * 두 날짜 사이의 날짜들을 배열로 리턴한다.
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
     * 두 날짜 사이의 월을 배열로 리턴한다.
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
            
            // 12월이 되었다면 년도를 증가한다.
            if (month == 12)
            {
                year ++;
                month = 0;
            }
        }
        
        return (String [])arrayDate.toArray(new String[arrayDate.size()]);
    }
    
    /**
     * yyyy-MM, yyyyMM인 날짜포맷을 해당달의 마지막일자를 더해 yyyy-MM-dd, yyyyMMdd로 바꿔준다.
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
     * @param offset - UTC 기준으로 해당 값만큼 시간이 조정됨
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
     * @param offset - UTC 기준으로 해당 값만큼 시간이 조정됨
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
     * @param offset - UTC 기준으로 해당 값만큼 시간이 조정됨
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
     * @param offset - UTC 기준으로 해당 값만큼 시간이 조정됨
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