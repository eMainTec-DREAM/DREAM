package common.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import com.google.gson.Gson;

import common.bean.MwareConfig;
import common.bean.User;
import common.exception.SqlIgnoreException;
import dream.mgr.message.service.MgrMessageTransDetailService;

public class MessageSendUtil {
	
	public static void sendKakaoAlarm(String messageObjectType, String objectId, User user){
		if("Y".equals(MwareConfig.getIsUseKakaoAlarmService())){
			MgrMessageTransDetailService mgrMessageTransDetailService = (MgrMessageTransDetailService) CommonUtil.getBean("mgrMessageTransDetailService", user);
			try {
				mgrMessageTransDetailService.makeKakaoMessage(messageObjectType, objectId, "KAKAO_ALARM", user);
			} catch (SqlIgnoreException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public static String[] sendKakaoAlarmWithInfoBank(Map dataMap){
		
		String[] returnData = new String[2];
		
		//http call
		try {
			URL url = new URL(MwareConfig.getKakaoAlarmApiUrl());
			HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
			
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
			con.setRequestProperty("Accept", "application/json");
			con.setRequestProperty("X-IB-Client-Id", MwareConfig.getKakaoAlarmId());
			con.setRequestProperty("X-IB-Client-Passwd", MwareConfig.getKakaoAlarmPw());
			con.setConnectTimeout(10000);
			con.setReadTimeout(10000);
			con.setDoOutput(true);
			
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			
			Map msgMap = new HashMap<>();
			msgMap.put("to", dataMap.get("Receivers").toString());//������ ��ȣ  ���� ��ȭ ��ȣ ǥ�� ���� �߼� (i.e, 82101231234)
			msgMap.put("content", dataMap.get("Contents").toString());//�޽��� ����
			
			Map attrMap = new HashMap<>();
			attrMap.put("sender_key", MwareConfig.getKakaoAlarmSenderKey());//īī�� ä�� �߼� Ű 
			attrMap.put("template_code", CommonUtil.convertString(dataMap.get("MessageObjectType"))+"_"+CommonUtil.convertString(dataMap.get("Lang")));//���ø� �ڵ� 
			attrMap.put("response_method", "push");//��û ���� ���� ��� (push, realtime, polling) 
			
			List<Map> buttonList = new ArrayList<Map>();
			Map buttonMap = new HashMap<>();
			buttonMap.put("type", "WL");
			if("ko".equals(CommonUtil.convertString(dataMap.get("Lang"))))buttonMap.put("name", "Ȯ���ϱ�");
			else buttonMap.put("name", "Connect to Dream");
			
			buttonMap.put("url_mobile", CommonUtil.convertString(dataMap.get("PageUrl")));
			buttonMap.put("url_pc", CommonUtil.convertString(dataMap.get("PageUrl")));
			buttonList.add(buttonMap);
			
			Map attachmentMap = new HashMap<>();
			attachmentMap.put("button", buttonList);
			attrMap.put("attachment", attachmentMap);
			
			Map contentsMap = new HashMap();
			contentsMap.put("msg_type", "AL"); //�޽��� Ÿ�� (FT: ģ�� ��, AL: �˸� ��) 
			contentsMap.put("mt_failover", "N");//���ڷ� Failover���� (Y/N) 
			contentsMap.put("msg_data", msgMap); //�޽��� �߼� �ʼ� ������ 
			contentsMap.put("msg_attr", attrMap);//�޽��� Ÿ�� �� �Ӽ� ������ 
			
			
	        Gson gson = new Gson();
	        String jsonString = gson.toJson(contentsMap);
	        System.out.println(jsonString);
			
			wr.write(jsonString.getBytes("UTF-8"));
			wr.flush();
			wr.close();
			
			int responseCode = con.getResponseCode();
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream())); 
			String inputLine; 
			StringBuffer response = new StringBuffer(); 
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine); 
				} 
			in.close(); // print result 

			if(responseCode == 200){
				returnData[0] = "SUCCESS";
				returnData[1] = "REQUEST DATA:"+jsonString+"\nRESPONSE DATA:"+response.toString();
			}else{
				returnData[0] = "ERROR";
				returnData[1] = "REQUEST DATA:"+jsonString+"\nRESPONSE DATA:"+response.toString();
			}
		} catch (MalformedURLException e) {
			returnData[0] = "ERROR MalformedURL";
			returnData[1] = e.getMessage();
		}catch (IOException e) {
			returnData[0] = "ERROR IO";
			returnData[1] = e.getMessage();
		}catch (Exception e) {
			returnData[0] = "ERROR ETC";
			returnData[1] = e.getMessage();
		}
		
		return returnData;
	}

}
