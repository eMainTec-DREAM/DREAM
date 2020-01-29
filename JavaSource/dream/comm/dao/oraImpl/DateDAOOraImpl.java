package dream.comm.dao.oraImpl;

import java.text.SimpleDateFormat;
import java.util.Date;

import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.comm.dao.DateDAO;

/**
 * »ó¼¼ dao
 * 
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * @spring.bean id="dateDAOTarget"
 * @spring.txbn id="dateDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class DateDAOOraImpl extends BaseJdbcDaoSupportOra implements DateDAO
{

    @Override
    public String getTimeStamp(int timezone, String format, String intervalType, int interval)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT SFAGETDATE(?, ?, ?) ");
        query.append("FROM DUAL                  ");
        
        Object[] objects = new Object[] {
                timezone
                ,intervalType
                ,interval
        };
        
        Date date = (Date) getJdbcTemplate().queryForObject(query.toString(), objects, Date.class);
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }
    
}