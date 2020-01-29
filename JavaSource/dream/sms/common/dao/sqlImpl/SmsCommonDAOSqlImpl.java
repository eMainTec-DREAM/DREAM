package dream.sms.common.dao.sqlImpl;

import java.util.List;

import common.spring.BaseJdbcDaoSupportSql;
import common.util.DateUtil;
import common.util.QuerySqlBuffer;
import dream.sms.common.dao.SmsCommonDAO;

/**
 * SMS Common DAO
 * @author  kim21017
 * @version $Id: SmsCommonDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="smsCommonDAOTarget"
 * @spring.txbn id="smsCommonDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class SmsCommonDAOSqlImpl extends BaseJdbcDaoSupportSql implements SmsCommonDAO
{

	@Override
	public List findSmsMessageList() {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.append("SELECT										");
    	query.append("		messagelist_id	AS messageListId		");
    	query.append("		,description 	AS description			");
    	query.append("		,contents AS contents					");
    	query.append("		,dbo.SFA_GET_ONLY_NUMERIC(receivers) AS receivers	");
    	query.append("		,retry_cnt		AS retryCnt				");
    	query.append("FROM TAMESSAGELIST x							");
    	query.append("WHERE 1=1										");
    	query.append("AND method_type = 'SMS' 						");
    	query.append("AND message_status IN ('Z','N')				");
    	query.append("AND retry_cnt < 2								");
    	
    	return getJdbcTemplate().queryForList(query.toString());
    }

	@Override
	public int updateMailMessageList(String id, int code, String failMsg) {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.append("UPDATE TAMESSAGELIST SET							");
    	query.append("	retry_cnt			= isnull(retry_cnt,0) + 1	");
    	query.append("	,send_time			= ? 						");
    	query.append("	,message_status		= ? 						");
    	query.append("	,fail_msg			= ? 						");
    	query.append("WHERE messagelist_id	= ?							");
    	
    	Object[] objects = new Object[] {
    			 DateUtil.getDateTime()
    			 ,code==1?"Y":"N"
    			 ,failMsg
    			 ,id
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    }
	
}