package dream.message.send.service.spring;

import java.util.Map;

import common.util.CommonUtil;
import common.util.MessageSendUtil;
import common.util.QueryBuffer;
import dream.message.send.dao.MessageSendDAO;
import dream.message.send.dto.MessageSendDTO;
import dream.message.send.service.MessageSendService;

/**
 * serviceimpl
 * @author 
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="messageSendServiceTarget"
 * @spring.txbn id="messageSendService"
 * @spring.property name="messageSendDAO" ref="messageSendDAO"
 */
public class MessageSendServiceImpl implements MessageSendService{
	
	private MessageSendDAO messageSendDAO = null;

	public MessageSendDAO getMessageSendDAO() {
		return messageSendDAO;
	}
	public void setMessageSendDAO(MessageSendDAO messageSendDAO) {
		this.messageSendDAO = messageSendDAO;
	}



	@Override
	public String[] sendKakaoAlarm(Map map) throws Exception {
		String[] returnData = new String[2];
		
		//인증 정보 확인
		if(!checkMsgCompAuth(map)){
			return new String[]{"ERROR AUTH","Authorization Failed"};
		}
		
		//인포 뱅크 알림톡 API 호출
		returnData = MessageSendUtil.sendKakaoAlarmWithInfoBank(map);
		
		//로그 쌓기.
		MessageSendDTO messageSendDTO =createMessageSendDTO(map);
		messageSendDTO.setMessageStatus("SUCCESS".equals(returnData[0])?"Y":"N");
		messageSendDTO.setFailMsg(returnData[1]);
		messageSendDAO.insertMessageSendLog(messageSendDTO);
		
		return returnData;
	}

	@Override
	public boolean checkMsgCompAuth(Map map) throws Exception {
		int count = messageSendDAO.countMsgServComp(map);
		return count>0;
	}
	
	private MessageSendDTO createMessageSendDTO(Map data){
		String msgSendServListId = messageSendDAO.getNextSequence("SQAMSGSENDSERVLIST_ID");
		MessageSendDTO messageSendDTO = new MessageSendDTO();
		
		messageSendDTO.setMsgSendServListId(msgSendServListId);
		messageSendDTO.setProgramType(CommonUtil.convertString(data.get("ProgramType")));
		messageSendDTO.setCompNo(CommonUtil.convertString(data.get("CompNo")));
		messageSendDTO.setCompName(CommonUtil.convertString(data.get("CompName")));
		messageSendDTO.setSendEmpNo(CommonUtil.convertString(data.get("SendEmpNo")));
		messageSendDTO.setSendEmpName(CommonUtil.convertString(data.get("SendEmpName")));
		messageSendDTO.setRecEmpNo(CommonUtil.convertString(data.get("RecEmpNo")));
		messageSendDTO.setRecEmpName(CommonUtil.convertString(data.get("RecEmpName")));
		messageSendDTO.setMessageObjectType(CommonUtil.convertString(data.get("MessageObjectType")));
		messageSendDTO.setObjectId(CommonUtil.convertString(data.get("ObjectId")));
		messageSendDTO.setObjectNo(CommonUtil.convertString(data.get("ObjectNo")));
		messageSendDTO.setSenders(CommonUtil.convertString(data.get("Senders")));
		messageSendDTO.setDescription(CommonUtil.convertString(data.get("Description")));
		messageSendDTO.setReceivers(CommonUtil.convertString(data.get("Receivers")));
		messageSendDTO.setContents(CommonUtil.convertString(data.get("Contents")));
		messageSendDTO.setMethodType(CommonUtil.convertString(data.get("MethodType")));
		messageSendDTO.setKeyValue(CommonUtil.convertString(data.get("KeyValue")));
		messageSendDTO.setPageUrl(CommonUtil.convertString(data.get("PageUrl")));
		messageSendDTO.setLang(CommonUtil.convertString(data.get("Lang")));
		
		return messageSendDTO;
	}
	
	
}
