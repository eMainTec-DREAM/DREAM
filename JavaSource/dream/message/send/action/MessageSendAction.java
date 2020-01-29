package dream.message.send.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.google.gson.Gson;

import common.struts.NoneAuthAction;
import dream.message.send.form.MessageSendForm;
import dream.message.send.service.MessageSendService;

/**
 * 
 * @author kim21017
 * @struts:action path="/messageSend" name="messageSendForm"
 *                input="/dream/message/send/messageSend.jsp" scope="request"
 *                validate="false"
 */
public class MessageSendAction extends NoneAuthAction{
	
	public static final String SEND_KAKAO_ALARM 		= "SEND_KAKAO_ALARM";
	
	
	@Override
	protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

        ActionForward returnActionForward = null;
        MessageSendForm messageSendForm = (MessageSendForm)form;
        
        switch (messageSendForm.getServiceName())
        {
        	case MessageSendAction.SEND_KAKAO_ALARM:
        		sendKakaoAlarm(request, response, messageSendForm);
            	returnActionForward = mapping.findForward("jsonPage");
        	break;

            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }
        return returnActionForward;
	}
	private void sendKakaoAlarm(HttpServletRequest request, HttpServletResponse response, MessageSendForm messageSendForm) throws Exception
    {
		Map map = getMapData(request);
		MessageSendService messageSendService = (MessageSendService)getBean("messageSendService");
		map.put("RequestIp", request.getRemoteAddr());
		
		String[] resultData = messageSendService.sendKakaoAlarm(map);
		
		Map resultMap = new HashMap();
        resultMap.put("MessageStatus", resultData[0]);
        resultMap.put("FailMsg", resultData[1]);
        Gson gson = new Gson();
        
        String jsonString = gson.toJson(resultMap);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().print(jsonString);
    }
	
}
