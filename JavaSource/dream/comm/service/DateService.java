package dream.comm.service;

import javax.servlet.http.HttpSession;

/**
 * Date Service
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 *
 */
public interface DateService
{
    String getTimeStamp(int timezone, String format, String intervalType, int interval);

    String getTimeGap(HttpSession session, long localTime) throws Exception;
    
}
