package dream.message.send.dao.sqlImpl;

import java.util.Map;

import common.spring.BaseJdbcDaoSupportSql;
import common.util.CommonUtil;
import common.util.DateUtil;
import common.util.QueryBuffer;
import common.util.QuerySqlBuffer;
import dream.message.send.dao.MessageSendDAO;
import dream.message.send.dto.MessageSendDTO;

/**
 * Message Transfer Page - Detail DAO implements
 * @author syyang
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="messageSendDAOTarget"
 * @spring.txbn id="messageSendDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MessageSendDAOSqlImpl extends BaseJdbcDaoSupportSql implements MessageSendDAO{

	@Override
	public void insertMessageSendLog(MessageSendDTO messageSendDTO) {
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		query.append("INSERT INTO TAMSGSENDSERVLIST(									");
		query.append("	MSGSENDSERVLIST_ID, PROGRAM_TYPE, COMP_NO, COMP_NAME			");
		query.append("	, SEND_EMP_NO, SEND_EMP_NAME, REC_EMP_NO, REC_EMP_NAME			");
		query.append("	, MESSAGE_OBJECT_TYPE, OBJECT_ID, OBJECT_NO, SENDERS			");
		query.append("	, DESCRIPTION, RECEIVERS, CONTENTS, METHOD_TYPE					");
		query.append("	, MESSAGE_STATUS, RETRY_CNT, CRE_TIME, SEND_TIME				");
		query.append("	, FAIL_MSG, PAGE_URL, LANG										");
		query.append(") VALUES ( 														");
		query.append("	?,?,?,?															");
		query.append("	,?,?,?,?														");
		query.append("	,?,?,?,?														");
		query.append("	,?,?,?,?														");
		query.append("	,?,?,?,?														");
		query.append("	,?,?,?															");
		query.append(") 																");
		
		Object[] objects = new Object[] {
				messageSendDTO.getMsgSendServListId()
				,messageSendDTO.getProgramType()
				,messageSendDTO.getCompNo()
				,messageSendDTO.getCompName()
				,messageSendDTO.getSendEmpNo()
				,messageSendDTO.getSendEmpName()
				,messageSendDTO.getRecEmpNo()
				,messageSendDTO.getRecEmpName()
				,messageSendDTO.getMessageObjectType()
				,messageSendDTO.getObjectId()
				,messageSendDTO.getObjectNo()
				,messageSendDTO.getSenders()
				,messageSendDTO.getDescription()
				,messageSendDTO.getReceivers()
				,messageSendDTO.getContents()
				,messageSendDTO.getMethodType()
				,messageSendDTO.getMessageStatus()
				,"0"
				,DateUtil.getDateTime()
				,DateUtil.getDateTime()
				,messageSendDTO.getFailMsg()
				,messageSendDTO.getPageUrl()
				,messageSendDTO.getLang()
    	};
		
    	this.getJdbcTemplate().update(query.toString(), getObject(objects));
	}

	@Override
	public int countMsgServComp(Map map) {

		String compNo = CommonUtil.convertString(map.get("CompNo"));
		String requestIp = CommonUtil.convertString(map.get("RequestIp"));
		String keyValue = CommonUtil.convertString(map.get("KeyValue"));
		String today = DateUtil.getDate();
		
		QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT COUNT(1)                          ");
        query.append("FROM TAMSGSENDSERVCOMP                   ");
        query.append("WHERE 1=1                                ");
        query.append("AND comp_no = '"+compNo+"'               ");
        query.append("AND addr like '%"+requestIp+"%'          ");
        query.append("AND key_value = '"+keyValue+"'           ");
        query.append("AND service_from <= '"+today+"'          ");
        query.append("AND service_to >= '"+today+"'            ");
        
        String count = QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
        
        return Integer.parseInt(count);
	
	}
	
	

}
