package intf.dream.ant.log.dao.sqlImpl;

import java.util.Map;

import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import intf.dream.ant.log.dao.AntLogDAO;
/**
 * dao
 * @author  
 * @version $Id: $
 * @since   1.0
 * @spring.bean id="antLogDAOTarget"
 * @spring.txbn id="antLogDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class AntLogDAOSqlImpl extends BaseJdbcDaoSupportSql implements AntLogDAO
{
    public int saveLoginLog(Map map) {
    	QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("INSERT INTO TALOGINCCLOG (									");
        query.append("	logincclog_id,				comp_no,						");
        query.append("	user_id,					login_date,						");
        query.append("	login_time,					login_terminal,					");
        query.append("	terminal_no													");
        query.append("	)															");
        query.append("select  next value for SQALOGINCCLOG_ID as logincclog_id		");
        query.append("          , a.comp_no                                         ");
        query.append("          , a.user_id                                         ");
        query.append("          , CONVERT(VARCHAR, GETDATE(), 112) as login_date	");
        query.append("          , GETDATE() as login_time                           ");
        query.append("          , ?    as terminal                                  ");
        query.append("          , ?    as terminalNo                                ");
        query.append("from tauser a                                                 ");
        query.append("where a.comp_no = ?                                           ");
        query.append("      and UPPER(a.user_no) = UPPER(?)                         ");

        
        Object [] objects = new Object[]{
        		convertString(map.get("serviceType"))
        		,convertString(map.get("terminalNo"))
        		,convertString(map.get("compNo"))
        		,convertString(map.get("userNo"))
        };

        return getJdbcTemplate().update(query.toString(), getObject(objects));

	}
}