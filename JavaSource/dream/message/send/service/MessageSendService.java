package dream.message.send.service;

import java.util.Map;

public interface MessageSendService {
	public String[] sendKakaoAlarm(Map map) throws Exception;
	public boolean checkMsgCompAuth(Map map) throws Exception;
}
