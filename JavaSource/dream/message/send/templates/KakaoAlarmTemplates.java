package dream.message.send.templates;

import java.util.Map;

import common.bean.User;
import common.util.MessageUtil;

public class KakaoAlarmTemplates {
	private StringBuilder sb;
	
	public String REQ10_ko(Map data, User user){
		sb = new StringBuilder();
		sb.append("�ȳ��ϼ���.");
		sb.append("\n");
		sb.append("#{�ý��۸�}�� �۾��� ��û�Ǿ����ϴ�.");
		sb.append("\n");
		sb.append("\n");
		sb.append("����û��ȣ : #{��û��ȣ}");
		sb.append("\n");
		sb.append("������ : #{��û����}");
		sb.append("\n");
		sb.append("����û�� : #{��û�ڸ�}/#{��û����ȭ��ȣ}");
		sb.append("\n");
		sb.append("����û���� : #{��û����}");
		sb.append("\n");
		sb.append("������ : #{�����ȣ}/#{�����}");
		sb.append("\n");
		sb.append("������ : #{����}");
		sb.append("\n");
		sb.append("���켱���� : #{�켱����}");
		sb.append("\n");
		sb.append("���Ϸ������ : #{�Ϸ������}");
		sb.append("\n");
		sb.append("����û���� :");
		sb.append("\n");
		sb.append("#{��û����}");
		sb.append("\n");
		
		String contents = sb.toString();
		
		contents = contents.replaceAll("#\\{�ý��۸�\\}", MessageUtil.getMessage(user.getLocale(), "LABEL", "cmms"));
		contents = contents.replaceAll("#\\{��û��ȣ\\}", data.get("WOREQ_NO")==null?"":data.get("WOREQ_NO").toString());
		contents = contents.replaceAll("#\\{��û����\\}", data.get("WOREQ_TITLE")==null?"":data.get("WOREQ_TITLE").toString());
		contents = contents.replaceAll("#\\{��û�ڸ�\\}", data.get("REQ_EMP_NAME")==null?"":data.get("REQ_EMP_NAME").toString());
		contents = contents.replaceAll("#\\{��û����ȭ��ȣ\\}", data.get("REQ_PHONE")==null?"":data.get("REQ_PHONE").toString());
		contents = contents.replaceAll("#\\{��û����\\}", data.get("REQ_DATE")==null?"":data.get("REQ_DATE").toString());
		contents = contents.replaceAll("#\\{�����ȣ\\}", data.get("ITEM_NO")==null?"":data.get("ITEM_NO").toString());
		contents = contents.replaceAll("#\\{�����\\}", data.get("EQUIP_NAME")==null?"":data.get("EQUIP_NAME").toString());
		contents = contents.replaceAll("#\\{����\\}", data.get("MO_DESC")==null?"":data.get("MO_DESC").toString());
		contents = contents.replaceAll("#\\{�켱����\\}", data.get("REQ_PRIORITY")==null?"":data.get("REQ_PRIORITY").toString());
		contents = contents.replaceAll("#\\{�Ϸ������\\}", data.get("REQ_COM_DATE")==null?"":data.get("REQ_COM_DATE").toString());
		contents = contents.replaceAll("#\\{��û����\\}", data.get("REQUEST")==null?"":data.get("REQUEST").toString());
		
		return contents;
	}
}
