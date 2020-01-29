package dream.comm.form;

import common.struts.BaseForm;

import dream.comm.dto.CommLogScrnTraceDTO;

/**
 * Date Form
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="dateForm"
 */
public class DateForm extends BaseForm
{
    int interval        = 0;
    String intervalType = "DAY";
    int timezone        = 0;
    String format       = "yyyy-MM-dd";
    long localTime      = 0;
    
    public long getLocalTime()
    {
        return localTime;
    }
    public void setLocalTime(long localTime)
    {
        this.localTime = localTime;
    }
    public int getInterval()
    {
        return interval;
    }
    public void setInterval(int interval)
    {
        this.interval = interval;
    }
    public String getIntervalType()
    {
        return intervalType;
    }
    public void setIntervalType(String intervalType)
    {
        this.intervalType = intervalType;
    }
    public int getTimezone()
    {
        return timezone;
    }
    public void setTimezone(int timezone)
    {
        this.timezone = timezone;
    }
    public String getFormat()
    {
        return format;
    }
    public void setFormat(String format)
    {
        this.format = format;
    }
    
}
