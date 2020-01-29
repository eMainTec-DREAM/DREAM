package dream.message.send.templates;

import java.util.Map;

import common.bean.User;
import common.util.MessageUtil;

public class KakaoAlarmTemplates {
	private StringBuilder sb;
	
	public String REQ10_ko(Map data, User user){
		sb = new StringBuilder();
		sb.append("안녕하세요.");
		sb.append("\n");
		sb.append("#{시스템명}에 작업이 요청되었습니다.");
		sb.append("\n");
		sb.append("\n");
		sb.append("▶요청번호 : #{요청번호}");
		sb.append("\n");
		sb.append("▶제목 : #{요청제목}");
		sb.append("\n");
		sb.append("▶요청자 : #{요청자명}/#{요청자전화번호}");
		sb.append("\n");
		sb.append("▶요청일자 : #{요청일자}");
		sb.append("\n");
		sb.append("▶설비 : #{설비번호}/#{설비명}");
		sb.append("\n");
		sb.append("▶현상 : #{현상}");
		sb.append("\n");
		sb.append("▶우선순위 : #{우선순위}");
		sb.append("\n");
		sb.append("▶완료희망일 : #{완료희망일}");
		sb.append("\n");
		sb.append("▶요청내용 :");
		sb.append("\n");
		sb.append("#{요청내용}");
		sb.append("\n");
		
		String contents = sb.toString();
		
		contents = contents.replaceAll("#\\{시스템명\\}", MessageUtil.getMessage(user.getLocale(), "LABEL", "cmms"));
		contents = contents.replaceAll("#\\{요청번호\\}", data.get("WOREQ_NO")==null?"":data.get("WOREQ_NO").toString());
		contents = contents.replaceAll("#\\{요청제목\\}", data.get("WOREQ_TITLE")==null?"":data.get("WOREQ_TITLE").toString());
		contents = contents.replaceAll("#\\{요청자명\\}", data.get("REQ_EMP_NAME")==null?"":data.get("REQ_EMP_NAME").toString());
		contents = contents.replaceAll("#\\{요청자전화번호\\}", data.get("REQ_PHONE")==null?"":data.get("REQ_PHONE").toString());
		contents = contents.replaceAll("#\\{요청일자\\}", data.get("REQ_DATE")==null?"":data.get("REQ_DATE").toString());
		contents = contents.replaceAll("#\\{설비번호\\}", data.get("ITEM_NO")==null?"":data.get("ITEM_NO").toString());
		contents = contents.replaceAll("#\\{설비명\\}", data.get("EQUIP_NAME")==null?"":data.get("EQUIP_NAME").toString());
		contents = contents.replaceAll("#\\{현상\\}", data.get("MO_DESC")==null?"":data.get("MO_DESC").toString());
		contents = contents.replaceAll("#\\{우선순위\\}", data.get("REQ_PRIORITY")==null?"":data.get("REQ_PRIORITY").toString());
		contents = contents.replaceAll("#\\{완료희망일\\}", data.get("REQ_COM_DATE")==null?"":data.get("REQ_COM_DATE").toString());
		contents = contents.replaceAll("#\\{요청내용\\}", data.get("REQUEST")==null?"":data.get("REQUEST").toString());
		
		return contents;
	}
}
