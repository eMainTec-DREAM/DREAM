package intf.dream.android.malog.dao.oraImpl;

import java.util.Map;

import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import intf.dream.android.malog.dao.AnIfLogDAO;
/**
 *  dao
 * @author  
 * @version $Id: $
 * @since   1.0
 * @spring.bean id="anIfLogDAOTarget"
 * @spring.txbn id="anIfLogDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class AnIfLogDAOOraImpl extends BaseJdbcDaoSupportOra implements AnIfLogDAO
{
    public int saveLoginLog(Map map) {
    	QueryBuffer query = new QueryBuffer();

        query.append("INSERT INTO TALOGINCCLOG (									");
        query.append("	logincclog_id,				comp_no,						");
        query.append("	user_id,					login_date,						");
        query.append("	login_time,					login_terminal,					");
        query.append("	terminal_no													");
        query.append("	)															");
        query.append("select  SQALOGINCCLOG_ID.nextval as logincclog_id             ");
        query.append("          , a.comp_no                                         ");
        query.append("          , a.user_id                                         ");
        query.append("          , TO_CHAR(SYSDATE,'yyyymmdd') as login_date         ");
        query.append("          , sysdate as login_time                             ");
        query.append("          , ?    as terminal                                  ");
        query.append("          , ?    as terminalNo                                ");
        query.append("from tauser a                                                 ");
        query.append("where a.comp_no = ?                                           ");
        query.append("      and UPPER(a.user_no) = UPPER(?)                         ");

        
        Object [] object = new Object[]{
        		convertString(map.get("serviceType"))
        		,convertString(map.get("terminalNo"))
        		,convertString(map.get("compNo"))
        		,convertString(map.get("userNo"))
        };

        return getJdbcTemplate().update(query.toString(), object);

	}
}