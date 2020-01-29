package dream.comm.service.spring;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

import javax.servlet.http.HttpSession;

import dream.comm.dao.DateDAO;
import dream.comm.service.DateService;

/**
 * Date ServiceImpl
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 *
 * @spring.bean id="dateServiceTarget"
 * @spring.txbn id="dateService"
 * @spring.property name="dateDAO" ref="dateDAO"
 */
public class DateServiceImpl implements DateService
{
    /** Date DAO */
    private DateDAO dateDAO = null;
    
    public DateDAO getDateDAO() {
		return dateDAO;
	}

	public void setDateDAO(DateDAO dateDAO) {
		this.dateDAO = dateDAO;
	}
	
    @Override
    public String getTimeStamp(int timezone, String format, String intervalType, int interval)
    {
        return dateDAO.getTimeStamp(timezone, format, intervalType, interval);
    }

    @Override
    public String getTimeGap(HttpSession session, long localTime) throws Exception
    {
        long timeGap = 0;
        try
        {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
            timeGap = sdf.parse(this.getTimeStamp(9, "yyyyMMddHHmmssSSS", "DAY", 0)).getTime()-localTime;
            session.setAttribute("timeGap", timeGap);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        return String.valueOf(timeGap);
    }
	
}

