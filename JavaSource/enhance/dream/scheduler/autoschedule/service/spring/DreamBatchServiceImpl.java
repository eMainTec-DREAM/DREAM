package enhance.dream.scheduler.autoschedule.service.spring;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;

import common.bean.MwareConfig;
import common.bean.User;
import common.util.CommonUtil;
import common.util.StringUtil;
import dream.part.list.service.PartListImgLinkUrlService;
import dream.sms.common.service.SmsCommonService;
import enhance.dream.coolsms.Coolsms;
import enhance.dream.scheduler.autoschedule.service.DreamBatchService;

/**
 * Dream ȸ�� ���� ���� ����
 * 
 * @author kim21017
 * @version $Id: DreamBatchServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp
 *          $
 * @since 1.0
 * @spring.bean id="dreamBatchServiceTarget"
 * @spring.txbn id="dreamBatchService"
 * @spring.property name="smsCommonService" ref="smsCommonService"
 */
public class DreamBatchServiceImpl implements DreamBatchService {
	private SmsCommonService smsCommonService = null;

	public SmsCommonService getSmsCommonService() {
		return smsCommonService;
	}


	public void setSmsCommonService(SmsCommonService smsCommonService) {
		this.smsCommonService = smsCommonService;
	}


	@Override
	public void sendSmsMessageList() throws Exception {
		// SMS ���� ��뿩�ΰ� Y�ΰ��
		if("Y".equals(MwareConfig.getIsUseSmsService())){
			//SMS ����Ʈ
			List sendSmsList = smsCommonService.findSmsMessageList();
			SendSmsThread smsTask = new SendSmsThread(sendSmsList);
			
			new Thread(smsTask,"SmsThread").start();
		}
	}
	
	
	class SendSmsThread implements Runnable {
		private List list;
		
		public SendSmsThread(List list) {
			this.list = list;
		}
		
		@Override
		public void run() {
			if(list!=null && list.size() >0){
				for(Object object : list)
				{
					/*
			         * TACONFIG ���� �޾ƿ;��մϴ�.
			         */
			        String api_key = MwareConfig.getSmsApiKey();
			        String api_secret = MwareConfig.getSmsApiSecret();
			        String send_number = MwareConfig.getSmsSendNumber();
			        
			        String sms_type = "sms";
			        
			        Coolsms coolsms = new Coolsms(api_key, api_secret);
			        
					Map resultMap = (Map)object;
					StringBuffer sb = new StringBuffer();
					int resultCode = 0;
					
					//���� Ȯ�� (������ -1)
					resultCode = !"".equals(String.valueOf(resultMap.get("CONTENTS")))?0:-1;
					//80Bytes �ʰ��� lms�� ��ȯ
					if(resultCode==0 && String.valueOf(resultMap.get("CONTENTS")).getBytes().length>80){
						sms_type = "lms";
					}
					
					if(resultCode == 0){
						HashMap<String, String> set = new HashMap<String, String>();
				        set.put("to", String.valueOf(resultMap.get("RECEIVERS"))); // ���Ź�ȣ
				        set.put("from", send_number); // �߽Ź�ȣ 
				        set.put("text", String.valueOf(resultMap.get("CONTENTS"))); // ���ڳ���
				        set.put("type", sms_type); // ���� Ÿ��
				        
			        	JSONObject result  = coolsms.send(set);
			        	if ((Boolean) result.get("status") == true) {
			        		resultCode = 1;
				        	sb.append("SUCCESS SEND "+sms_type);
			        	}else{
							resultCode = -2;
				        	sb.append("FAIL SEND "+sms_type +"\n");
				        	sb.append("ERROR CODE: "+result.get("code") +"\n");
				        	sb.append("FAIL SEND "+result.get("message") +"\n");
			        	}
					}
					smsCommonService.updateMailMessageList(String.valueOf(resultMap.get("MESSAGELISTID")), resultCode, sb.toString());
				}
			}
		}
	}
	
	@Override
	public void getImageFromUrl() throws Exception
	{
	    PartListImgLinkUrlService partListImgLinkUrlService;
	    User user = CommonUtil.getUser();
	    List<Map> companies = MwareConfig.getCompanies();
	    for(Map company:companies){
	        String compNo = StringUtil.valueOf(company.get("CODE"));
	        user.setCompNo(compNo);
	        
	        //��ǰ������ �̹��� ���ε�
	        partListImgLinkUrlService = (PartListImgLinkUrlService) CommonUtil.getBean("partListImgLinkUrlService", user);
	        partListImgLinkUrlService.getImageAll(user);
	        
	        //���񸶽��� �̹��� ���ε�
	    }
	}
}
