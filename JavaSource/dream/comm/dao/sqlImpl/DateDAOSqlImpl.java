package dream.comm.dao.sqlImpl;

import java.text.SimpleDateFormat;
import java.util.Date;

import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
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
public class DateDAOSqlImpl extends BaseJdbcDaoSupportSql implements DateDAO
{
    
    @Override
    public String getTimeStamp(int timezone, String format, String intervalType, int interval)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT dbo.SFAGETDATE(?, ?, ?) ");
        
        Object[] objects = new Object[] {
                timezone
                ,intervalType
                ,interval
        };
        
        Date date = (Date) getJdbcTemplate().queryForObject(query.toString(), getObject(objects), Date.class);
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }
    
}