package dream.message.send.dao;

import java.util.Map;

import common.spring.BaseJdbcDaoSupportIntf;
import dream.message.send.dto.MessageSendDTO;

public interface MessageSendDAO extends BaseJdbcDaoSupportIntf{

	public void insertMessageSendLog(MessageSendDTO messageSendDTO);
	public int countMsgServComp(Map map);
}
