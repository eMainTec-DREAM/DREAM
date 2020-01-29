package dream.pers.appstb.message.mail.service.spring;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import common.bean.MwareConfig;
import common.bean.User;
import common.util.CommonUtil;
import common.util.QueryBuffer;
import dream.mgr.message.dto.MgrMessageTransDetailDTO;
import dream.mgr.message.service.MgrMessageTransDetailService;
import dream.pers.appreq.dto.AppReqDetailDTO;
import dream.pers.appstb.message.mail.dao.PersAppstbMessageMailDAO;
import dream.pers.appstb.message.mail.service.PersAppstbMessageMailService;

/**
 * 메일수신자설정 - 상세 serviceimpl 
 * @author  kim21017
 * @version $Id: PersAppstbMessageMailServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="persAppstbMessageMailServiceTarget"
 * @spring.txbn id="persAppstbMessageMailService"
 * @spring.property name="persAppstbMessageMailDAO" ref="persAppstbMessageMailDAO"
 * @spring.property name="mgrMessageTransDetailService" ref="mgrMessageTransDetailService"
 */
@Deprecated
public class PersAppstbMessageMailServiceImpl implements PersAppstbMessageMailService
{
    private PersAppstbMessageMailDAO persAppstbMessageMailDAO = null;
    private MgrMessageTransDetailService mgrMessageTransDetailService = null;
	
	public PersAppstbMessageMailDAO getPersAppstbMessageMailDAO() {
		return persAppstbMessageMailDAO;
	}

	public void setPersAppstbMessageMailDAO(
			PersAppstbMessageMailDAO persAppstbMessageMailDAO) {
		this.persAppstbMessageMailDAO = persAppstbMessageMailDAO;
	}

	public MgrMessageTransDetailService getMgrMessageTransDetailService()
    {
        return mgrMessageTransDetailService;
    }

    public void setMgrMessageTransDetailService(
            MgrMessageTransDetailService mgrMessageTransDetailService)
    {
        this.mgrMessageTransDetailService = mgrMessageTransDetailService;
    }

    public int sendMessageMail(AppReqDetailDTO appReqDetailDTO, User user) throws Exception
    {   
        MgrMessageTransDetailService mgrMessageTransDetailService = (MgrMessageTransDetailService) CommonUtil.getBean("mgrMessageTransDetailService", user);
		String[] receiveMailArr = new String[0];
		String[] receiveEmpNoArr = new String[0];
		String title = "Title";
		String contents = "Contents";
		String apprStatus = "";  
		if (!"Y".equals(MwareConfig.getIsUseMailService())){
			//System.out.println("isUseMailService.................................................\n\n");
			return 0;
		}
		
		String sendEmpNo = user.getEmpNo();
		String recEmpNo = "";
		String msgObjType = "";
		String objId = appReqDetailDTO.getApprlistId();
		String objNo = appReqDetailDTO.getApprlistId();
		
		if("REQWORK".equals(appReqDetailDTO.getApprType()) || "REQINVTWORK".equals(appReqDetailDTO.getApprType())) //작업요청 관련 메일 서비스 시작
		{
			
			//C:결재완료, D:결재반려, P:결재중, R:결재요청[결재완료, 결재반려이면 기안자에게 메일 전송, 결재중,결재요청이면 다름 결재자에게 메일을 보냄]
			apprStatus = persAppstbMessageMailDAO.findApprStatus(appReqDetailDTO, user);
			
			if("C".equals(apprStatus) || "D".equals(apprStatus)) //결재완료 또는 결재반려
	        {
			    if("N".equals(mgrMessageTransDetailService.isUseMessageCateg("APP20", "MAIL", user.getCompNo()))){
			        return 0;
			    }
				msgObjType = "APP20";
				receiveMailArr = persAppstbMessageMailDAO.findDrafterMailList(appReqDetailDTO, user); // 기안자에게 메일을 보내고
				receiveEmpNoArr = persAppstbMessageMailDAO.findDrafterEmpNoList(appReqDetailDTO, user); // 기안자에게 메일을 보내고
	        }else{
	            if("N".equals(mgrMessageTransDetailService.isUseMessageCateg("APP10", "MAIL", user.getCompNo()))){
                    return 0;
                }
	        	msgObjType = "APP10";
	        	receiveMailArr = persAppstbMessageMailDAO.findApproverMailList(appReqDetailDTO, user);//결재대기 사람에게 메일을 보냄.
	        	receiveEmpNoArr = persAppstbMessageMailDAO.findApproverEmpNoList(appReqDetailDTO, user);//결재대기 사람에게 메일을 보냄.
	        }
			if (receiveMailArr.length > 0){
				title = persAppstbMessageMailDAO.findTitle(appReqDetailDTO, "MESSAGE", "MSG0271", user);
				contents = setMailFormatWoReqDetail(persAppstbMessageMailDAO.selectWoReqDetail(appReqDetailDTO, user)
						                           ,persAppstbMessageMailDAO.selectApprovalList(appReqDetailDTO, user) );

				sendMail(receiveMailArr, receiveEmpNoArr, contents, title, user
                		,sendEmpNo ,recEmpNo, msgObjType, objId, objNo);
			}
        }else if("WORKORDER".equals(appReqDetailDTO.getApprType())) //작업 관련 메일 서비스 시작
        {
        	//C:결재완료, D:결재반려, P:결재중, R:결재요청[결재완료, 결재반려이면 기안자에게 메일 전송, 결재중,결재요청이면 다름 결재자에게 메일을 보냄]
			apprStatus = persAppstbMessageMailDAO.findApprStatus(appReqDetailDTO, user);
			
			if("C".equals(apprStatus) || "D".equals(apprStatus)) //결재완료 또는 결재반려
	        {
			    if("N".equals(mgrMessageTransDetailService.isUseMessageCateg("APP20", "MAIL", user.getCompNo()))){
                    return 0;
                }
				msgObjType = "APP20";
				receiveMailArr = persAppstbMessageMailDAO.findDrafterMailList(appReqDetailDTO, user); // 기안자에게 메일을 보내고
				receiveEmpNoArr = persAppstbMessageMailDAO.findDrafterEmpNoList(appReqDetailDTO, user); // 기안자에게 메일을 보내고
	        }else{
	            if("N".equals(mgrMessageTransDetailService.isUseMessageCateg("APP10", "MAIL", user.getCompNo()))){
                    return 0;
                }
	        	msgObjType = "APP10";
	        	receiveMailArr = persAppstbMessageMailDAO.findApproverMailList(appReqDetailDTO, user);//결재대기 사람에게 메일을 보냄.
	        	receiveEmpNoArr = persAppstbMessageMailDAO.findApproverEmpNoList(appReqDetailDTO, user);//결재대기 사람에게 메일을 보냄.
	        }
			if (receiveMailArr.length > 0){
				title = persAppstbMessageMailDAO.findTitle(appReqDetailDTO, "MENU", "WORESULT", user);
				contents = setMailFormatWorkOrderDetail(persAppstbMessageMailDAO.selectWorkOrderDetail(appReqDetailDTO, user)
						                           ,persAppstbMessageMailDAO.selectApprovalList(appReqDetailDTO, user) );
				
				sendMail(receiveMailArr, receiveEmpNoArr, contents, title, user
                		,sendEmpNo ,recEmpNo, msgObjType, objId, objNo); 
			}
			
        }else if("PTSTKTAKE".equals(appReqDetailDTO.getApprType())) // 부품실사 결재 관련 메일 서비스 시작
        {
        	// WRA : 결재요청  (결재 올릴 때 메일링)
        	if ("WRA".equals(appReqDetailDTO.getParentStatus())) {
        	    if("N".equals(mgrMessageTransDetailService.isUseMessageCateg("APP10", "MAIL", user.getCompNo()))){
                    return 0;
                }
        		msgObjType = "APP10";
        		receiveMailArr = persAppstbMessageMailDAO.findApproverMailList(appReqDetailDTO, user);
        		receiveEmpNoArr = persAppstbMessageMailDAO.findApproverEmpNoList(appReqDetailDTO, user);
			}
			if (receiveMailArr.length > 0){
				title = persAppstbMessageMailDAO.findTitle(appReqDetailDTO, "MENU", "PTADJ", user);
				contents = setMailFormatPartAdjStkTakeDetail(persAppstbMessageMailDAO.selectPartAdjStkTakeDetail(appReqDetailDTO, user));
				sendMail(receiveMailArr, receiveEmpNoArr, contents, title, user
                		,sendEmpNo ,recEmpNo, msgObjType, objId, objNo); 
			}
        }else if("PTBUYREQ".equals(appReqDetailDTO.getApprType())) // 구매청구집계 결재 관련 메일 서비스 시작
        {
			apprStatus = persAppstbMessageMailDAO.findApprStatus(appReqDetailDTO, user);
			
        	// WRA : 결재요청  (결재 올릴 때 메일링)
        	if ("WRA".equals(appReqDetailDTO.getParentStatus())) {
        	    if("N".equals(mgrMessageTransDetailService.isUseMessageCateg("APP10", "MAIL", user.getCompNo()))){
                    return 0;
                }
        		msgObjType = "APP10";
        		receiveMailArr = persAppstbMessageMailDAO.findApproverMailList(appReqDetailDTO, user); //결재대기 사람에게 메일 보냄
        		receiveEmpNoArr = persAppstbMessageMailDAO.findApproverEmpNoList(appReqDetailDTO, user); //결재대기 사람에게 메일 보냄
			}
			if (receiveMailArr.length > 0){
				title = persAppstbMessageMailDAO.findTitle(appReqDetailDTO, "LABEL", "buyReq", user);
				contents = setMailFormatPtBuyReqDetail(persAppstbMessageMailDAO.selectPtBuyReqDetail(appReqDetailDTO, user)
													,persAppstbMessageMailDAO.selectPtBuyReqItemList(appReqDetailDTO, user)
													,persAppstbMessageMailDAO.selectApprovalList(appReqDetailDTO, user));
				sendMail(receiveMailArr, receiveEmpNoArr, contents, title, user
                		,sendEmpNo ,recEmpNo, msgObjType, objId, objNo); 
			}
        }
		return 0;
    }
	
	
	
	
	
	//작업요청상세 메일링 포멧팅..
	public String setMailFormatWoReqDetail(List detailInfo, List approvalList)
	{
        QueryBuffer contents = new QueryBuffer();
        
        contents.append("<html><head></head><table width='100%' ><tr><td width='100%' >");
		
		for (int i=0;i<detailInfo.size(); i++){
			Map map = (Map) detailInfo.get(i);
			Set key = map.keySet();
			
			for(Iterator it = key.iterator(); it.hasNext();)
			{
				String keyName = (String)it.next();
				String valueName = String.valueOf(map.get(keyName));
				if("TITLE".equals(keyName)){
					contents.append("<table width='100%' style='font-family:'맑은 고딕','Malgun Gothic','돋움',Dotum,Arial,Helvetica,sans-serif; font-size:12px; margin:10px auto; border-collapse:collapse; border:1px solid #ccc;'>");
					contents.append("<caption style='padding:6px 10px; border-collapse:collapse; border:1px solid #ccc; font-size:20px; border:none; color:#000; font-weight:bold;'>");
					contents.append(valueName);
					contents.append("</caption>");
					contents.append("<tbody>");
					contents.append("<tr height='18'>");
					contents.append("<td colspan='4'  align='right' style=' padding:6px 10px; border-collapse:collapse; border:0px solid #ccc;'> ");
					contents.append("<a target='_blank' style='font-size:12px;' href='" + MwareConfig.getDreamUrl() + "'>");
					contents.append("Login to Dream");
					contents.append("</a></td>");
					contents.append("</tr>");
				}else if("WOREQ_NO".equals(keyName)){
					contents.append("<tr height='22'>");
					contents.append("<td height='22' align='center' style='background-color:#eee; font-weight:bold; padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
					contents.append("요청#");
					contents.append("</td>");
					contents.append("<td align='left' style=' padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
					contents.append(valueName);
					contents.append("</td>");
				}else if("WOREQ_STATUS".equals(keyName)){
                    contents.append("<td height='22' align='center' style='background-color:#eee; font-weight:bold; padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
                    contents.append("진행상태");
                    contents.append("</td>");
                    contents.append("<td align='left' style=' padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
                    contents.append(valueName);
                    contents.append("</td>");
                    contents.append("</tr>");
                }else if("WOREQ_TITLE".equals(keyName)){
                    contents.append("<tr height='22'>");
                    contents.append("<td height='22' align='center' style='background-color:#eee; font-weight:bold; padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
                    contents.append("제목");
                    contents.append("</td>");
                    contents.append("<td colspan='3' align='left' style=' padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
                    contents.append(valueName);
                    contents.append("</td>");
                    contents.append("</tr>");
				}else if("EMP_NAME".equals(keyName)){
				    contents.append("<tr height='22'>");
					contents.append("<td height='22' align='center' style='background-color:#eee; font-weight:bold; padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
					contents.append("요청자");
					contents.append("</td>");
					contents.append("<td align='left' style=' padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
					contents.append(valueName);
					contents.append("</td>");
				}else if("REQ_PHONE".equals(keyName)){
                    contents.append("<td height='22' align='center' style='background-color:#eee; font-weight:bold; padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
                    contents.append("요청자전화");
                    contents.append("</td>");
                    contents.append("<td align='left' style=' padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
                    contents.append(valueName);
                    contents.append("</td>");
                    contents.append("</tr>");
				}else if("REQ_DATE".equals(keyName)){
					contents.append("<tr height='22'>");
					contents.append("<td height='22' align='center' style='background-color:#eee; font-weight:bold; padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
					contents.append("요청일자");
					contents.append("</td>");
					contents.append("<td align='left' style=' padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
					contents.append(valueName);
					contents.append("</td>");
				}else if("REQ_COM_DATE".equals(keyName)){
					contents.append("<td height='22' align='center' style='background-color:#eee; font-weight:bold; padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
					contents.append("완료희망일");
					contents.append("</td>");
					contents.append("<td align='left' style=' padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
					contents.append(valueName);
					contents.append("</td>");
					contents.append("</tr>");
				}else if("REQ_PRIORITY".equals(keyName)){
					contents.append("<tr height='22'>");
					contents.append("<td height='22' align='center' style='background-color:#eee; font-weight:bold; padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
					contents.append("우선순위");
					contents.append("</td>");
					contents.append("<td align='left' style=' padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
					contents.append(valueName);
					contents.append("</td>");
				}else if("REC_NAME".equals(keyName)){
					contents.append("<td height='22' align='center' style='background-color:#eee; font-weight:bold; padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
					contents.append("담당자");
					contents.append("</td>");
					contents.append("<td align='left' style=' padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
					contents.append(valueName);
					contents.append("</td>");
					contents.append("</tr>");
				}else if("EQUIP_NAME".equals(keyName)){
					contents.append("<tr height='22'>");
					contents.append("<td height='22' align='center' style='background-color:#eee; font-weight:bold; padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
					contents.append("설비");
					contents.append("</td>");
					contents.append("<td colspan='3' align='left' style=' padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
					contents.append(valueName);
					contents.append("</td>");
					contents.append("</tr>");
				}else if("LOC_NAME".equals(keyName)){
					contents.append("<tr height='22'>");
					contents.append("<td height='22' align='center' style='background-color:#eee; font-weight:bold; padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
					contents.append("위  치");
					contents.append("</td>");
					contents.append("<td colspan='3' align='left' style=' padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
					contents.append(valueName);
					contents.append("</td>");
					contents.append("</tr>");
				}else if("REQUEST".equals(keyName)){
					contents.append("<tr height='22'>");
					contents.append("<td colspan='4'  height='22' align='center' style='background-color:#eee; font-weight:bold; padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
					contents.append("요청상세내용");
					contents.append("</td>");
					contents.append("<tr height='200'>");
					contents.append("<td colspan='4' valign='top'  align='left' style=' padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
					contents.append(valueName);
					contents.append("</td>");
					contents.append("</tr>");
				}else{
				    contents.append("<tbody>");
				    contents.append("</TABLE>");
				}
			}
		}
		
		contents.append("</td></tr>");
		contents.append("<table width='100%' ><tr><td width='100%' >");
		contents.append(setMailFormatApproval(approvalList));
		contents.append("</td></tr></table></html>");
		
		return contents.toString();
	}
	//W/O  메일링 포멧팅..
	public String setMailFormatWorkOrderDetail(List detailInfo, List approvalList)
	{
        QueryBuffer contents = new QueryBuffer();
        
        contents.append("<html><head></head><table width='100%' ><tr><td width='100%' >");
		
		for (int i=0;i<detailInfo.size(); i++){
			Map map = (Map) detailInfo.get(i);
			Set key = map.keySet();
			
			for(Iterator it = key.iterator(); it.hasNext();)
			{
				String keyName = (String)it.next();
				String valueName = String.valueOf(map.get(keyName));
				if("TITLE".equals(keyName)){
					contents.append("<table width='100%' style='font-family:'맑은 고딕','Malgun Gothic','돋움',Dotum,Arial,Helvetica,sans-serif; font-size:12px; margin:10px auto; border-collapse:collapse; border:1px solid #ccc;'>");
					contents.append("<caption style='padding:6px 10px; border-collapse:collapse; border:1px solid #ccc; font-size:20px; border:none; color:#000; font-weight:bold;'>");
					contents.append(valueName);
					contents.append("</caption>");
					contents.append("<tbody>");
					contents.append("<tr height='18'>");
					contents.append("<td colspan='4'  align='right' style=' padding:6px 10px; border-collapse:collapse; border:0px solid #ccc;'> ");
					contents.append("<a target='_blank' style='font-size:12px;' href='" + MwareConfig.getDreamUrl() + "'>");
					contents.append("Login to Dream");
					contents.append("</a></td>");
					contents.append("</tr>");
				
				}else if("WO_NO".equals(keyName)){
					contents.append("<tr height='22'>");
					contents.append("<td height='22' align='center' style='background-color:#eee; font-weight:bold; padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
					contents.append("W/O #");
					contents.append("</td>");
					contents.append("<td align='left' style=' padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
					contents.append(valueName);
					contents.append("</td>");
				}else if("EMP_NAME".equals(keyName)){
					contents.append("<td height='22' align='center' style='background-color:#eee; font-weight:bold; padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
					contents.append("담당자");
					contents.append("</td>");
					contents.append("<td align='left' style=' padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
					contents.append(valueName);
					contents.append("</td>");
					contents.append("</tr>");
				}else if("EQLOC".equals(keyName)){
					contents.append("<tr height='22'>");
					contents.append("<td height='22' align='center' style='background-color:#eee; font-weight:bold; padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
					contents.append("위치");
					contents.append("</td>");
					contents.append("<td align='left' style=' padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
					contents.append(valueName);
					contents.append("</td>");
				}else if("EQUIP".equals(keyName)){
					contents.append("<td height='22' align='center' style='background-color:#eee; font-weight:bold; padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
					contents.append("설비");
					contents.append("</td>");
					contents.append("<td align='left' style=' padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
					contents.append(valueName);
					contents.append("</td>");
					contents.append("</tr>");
				}else if("START_DATE".equals(keyName)){
					contents.append("<tr height='22'>");
					contents.append("<td height='22' align='center' style='background-color:#eee; font-weight:bold; padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
					contents.append("작업 시작일자");
					contents.append("</td>");
					contents.append("<td align='left' style=' padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
					contents.append(valueName);
					contents.append("</td>");
				}else if("END_DATE".equals(keyName)){
					contents.append("<td height='22' align='center' style='background-color:#eee; font-weight:bold; padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
					contents.append("작업 종료일자");
					contents.append("</td>");
					contents.append("<td align='left' style=' padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
					contents.append(valueName);
					contents.append("</td>");
					contents.append("</tr>");
				}else if("WKOR_DATE".equals(keyName)){
					contents.append("<tr height='22'>");
					contents.append("<td height='22' align='center' style='background-color:#eee; font-weight:bold; padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
					contents.append("작업일자");
					contents.append("</td>");
					contents.append("<td colspan='3' align='left' style=' padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
					contents.append(valueName);
					contents.append("</td>");
					contents.append("</tr>");
				}else if("PERFORM".equals(keyName)){
					contents.append("<tr height='22'>");
					contents.append("<td colspan='4'  height='22' align='center' style='background-color:#eee; font-weight:bold; padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
					contents.append("상세내용");
					contents.append("</td>");
					contents.append("<tr height='200'>");
					contents.append("<td colspan='4' valign='top'  align='left' style=' padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
					contents.append(valueName);
					contents.append("</td>");
					contents.append("</tr>");
					
				}else{
				    contents.append("<tbody>");
				    contents.append("</TABLE>");
				}
			}
		}
		
		contents.append("</td></tr>");
		contents.append("<table width='100%' ><tr><td width='100%' >");
		contents.append(setMailFormatApproval(approvalList));
		contents.append("</td></tr></table></html>");
		
		return contents.toString();
	}
	
	public String setMailFormatItemList(List subListInfo)
	{
        QueryBuffer contents = new QueryBuffer();
        contents.append("<table width='100%' style='font-family:'맑은 고딕','Malgun Gothic','돋움',Dotum,Arial,Helvetica,sans-serif; font-size:12px; margin:20px 10px auto; border-collapse:collapse; border:1px solid #ccc;'>");
        contents.append("<caption style='text-align:left; padding:6px 10px; border-collapse:collapse; border:1px solid #ccc; font-size:14px; border:none; color:#000; font-weight:bold;'> 품목 </cation>");
        contents.append("<tbody>");
		for (int i=0;i<subListInfo.size(); i++){
			Map map = (Map) subListInfo.get(i);
			Set key = map.keySet();
			if(i==0)
			{
				contents.append("<tr height='22'>");
				contents.append("<th width='50' style='background-color:#eee; font-weight:bold; padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>품번</th>");
				contents.append("<th width='100' style='background-color:#eee; font-weight:bold; padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>품명</th>");
				contents.append("<th width='80' style='background-color:#eee; font-weight:bold; padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>구입처</th>");
				contents.append("<th width='80' style='background-color:#eee; font-weight:bold; padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>모델</th>");
				contents.append("<th width='100' style='background-color:#eee; font-weight:bold; padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>규격</th>");
				contents.append("<th width='50' style='background-color:#eee; font-weight:bold; padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>수량</th>");
				contents.append("<th width='50' style='background-color:#eee; font-weight:bold; padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>단가</th>");
				contents.append("<th width='50' style='background-color:#eee; font-weight:bold; padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>가격</th>");
				contents.append("<th width='100' style='background-color:#eee; font-weight:bold; padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>비고</th>");
				contents.append("</tr>");
			}
			contents.append("<tr height='22'>");
			for(Iterator it = key.iterator(); it.hasNext();)
			{
				String keyName = (String)it.next();
				String valueName = String.valueOf(map.get(keyName));
				if("PARTNO".equals(keyName)){
					contents.append("<td height='22' align='left' style='padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
					contents.append(valueName);
					contents.append("</td>");
				}else if("PARTDESC".equals(keyName)){
					contents.append("<td height='22' align='left' style='padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
					contents.append(valueName);
					contents.append("</td>");
				}else if("SELLER".equals(keyName)){
					contents.append("<td height='22' align='left' style='padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
					contents.append(valueName);
					contents.append("</td>");
				}else if("MODEL".equals(keyName)){
					contents.append("<td height='22' align='left' style='padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
					contents.append(valueName);
					contents.append("</td>");
				}else if("PTSIZE".equals(keyName)){
					contents.append("<td height='22' align='left' style='padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
					contents.append(valueName);
					contents.append("</td>");
				}else if("RECQTY".equals(keyName)){
					contents.append("<td height='22' align='left' style='padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
					contents.append(valueName);
					contents.append("</td>");
				}else if("UNITPRICE".equals(keyName)){
					contents.append("<td height='22' align='left' style='padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
					contents.append(valueName);
					contents.append("</td>");
				}else if("TOTALPRICE".equals(keyName)){
					contents.append("<td height='22' align='left' style='padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
					contents.append(valueName);
					contents.append("</td>");
				}else if("REMARK".equals(keyName)){
					contents.append("<td height='22' align='left' style='padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
					contents.append(valueName);
					contents.append("</td>");
				}
			}
			contents.append("</tr>");
		}
		contents.append("</tbody>");
		contents.append("</table>");
		
		return contents.toString();
	}
	
	//결재라인 셋팅
		public String setMailFormatApproval(List approvalList)
		{
	        QueryBuffer contents = new QueryBuffer();
	        contents.append("<table width='100%' style='font-family:'맑은 고딕','Malgun Gothic','돋움',Dotum,Arial,Helvetica,sans-serif; font-size:12px; margin:20px 10px auto; border-collapse:collapse; border:1px solid #ccc;'>");
	        contents.append("<caption style='text-align:left; padding:6px 10px; border-collapse:collapse; border:1px solid #ccc; font-size:14px; border:none; color:#000; font-weight:bold;'> 결재진행현황 </cation>");
	        contents.append("<tbody>");
			for (int i=0;i<approvalList.size(); i++){
				Map map = (Map) approvalList.get(i);
				Set key = map.keySet();
				if(i==0)
				{
					contents.append("<tr height='22'>");
					contents.append("<th width='50' style='background-color:#eee; font-weight:bold; padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>순서</th>");
					contents.append("<th width='50' style='background-color:#eee; font-weight:bold; padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>구분</th>");
					contents.append("<th width='100' style='background-color:#eee; font-weight:bold; padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>결재자</th>");
					contents.append("<th width='80' style='background-color:#eee; font-weight:bold; padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>직급</th>");
					contents.append("<th width='125' style='background-color:#eee; font-weight:bold; padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>부서</th>");
					contents.append("<th width='100' style='background-color:#eee; font-weight:bold; padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>진행상태</th>");
					contents.append("<th width='200' style='background-color:#eee; font-weight:bold; padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>시간</th>");
					contents.append("<th width='' style='background-color:#eee; font-weight:bold; padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>의견</th>");
					contents.append("</tr>");
				}
				contents.append("<tr height='22'>");
				for(Iterator it = key.iterator(); it.hasNext();)
				{
					String keyName = (String)it.next();
					String valueName = String.valueOf(map.get(keyName));
					if("REMARK".equals(keyName)){
						contents.append("<td height='22' align='left' style='padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
						contents.append(valueName);
						contents.append("</td>");
					}else{
						contents.append("<td height='22' align='center' style='padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
						contents.append(valueName);
						contents.append("</td>");
					}
				}
				contents.append("</tr>");
			}
			contents.append("</tbody>");
			contents.append("</table>");
			
			return contents.toString();
		}
	
	//부품실사 상세 메일링 포맷
	public String setMailFormatPartAdjStkTakeDetail(List detailInfo)
	{
        QueryBuffer contents = new QueryBuffer();
        
        contents.append("<html><head></head><table width='100%' ><tr><td width='100%' >");
		
		for (int i=0;i<detailInfo.size(); i++){
			Map map = (Map) detailInfo.get(i);
			Set key = map.keySet();
			
			for(Iterator it = key.iterator(); it.hasNext();)
			{
				String keyName = (String)it.next();
				String valueName = String.valueOf(map.get(keyName));
				if("TITLE".equals(keyName)){
					contents.append("<table width='100%' style='font-family:'맑은 고딕','Malgun Gothic','돋움',Dotum,Arial,Helvetica,sans-serif; font-size:12px; margin:10px auto; border-collapse:collapse; border:1px solid #ccc;'>");
					contents.append("<caption style='padding:6px 10px; border-collapse:collapse; border:1px solid #ccc; font-size:20px; border:none; color:#000; font-weight:bold;'>");
					contents.append(valueName);
					contents.append("</caption>");
					contents.append("<tbody>");
					contents.append("<tr height='18'>");
					contents.append("<td colspan='4'  align='right' style=' padding:6px 10px; border-collapse:collapse; border:0px solid #ccc;'> ");
					contents.append("<a target='_blank' style='font-size:12px;' href='" + MwareConfig.getDreamUrl() + "'>");
					contents.append("Login to Dream");
					contents.append("</a></td>");
					contents.append("</tr>");
				}else if("PTSTKTAKELIST_NO".equals(keyName)){
					contents.append("<tr height='22'>");
					contents.append("<td height='22' align='center' style='background-color:#eee; font-weight:bold; padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
					contents.append("실사#");
					contents.append("</td>");
					contents.append("<td align='left' style=' padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
					contents.append(valueName);
					contents.append("</td>");
				}else if("PTSTKTAKE_STATUS".equals(keyName)){
                    contents.append("<td height='22' align='center' style='background-color:#eee; font-weight:bold; padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
                    contents.append("진행상태");
                    contents.append("</td>");
                    contents.append("<td align='left' style=' padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
                    contents.append(valueName);
                    contents.append("</td>");
                    contents.append("</tr>");
                }else if("DESCRIPTION".equals(keyName)){
                    contents.append("<tr height='22'>");
                    contents.append("<td height='22' align='center' style='background-color:#eee; font-weight:bold; padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
                    contents.append("제목");
                    contents.append("</td>");
                    contents.append("<td colspan='3' align='left' style=' padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
                    contents.append(valueName);
                    contents.append("</td>");
                    contents.append("</tr>");
				}else if("PLAN_DATE".equals(keyName)){
					contents.append("<tr height='22'>");
					contents.append("<td height='22' align='center' style='background-color:#eee; font-weight:bold; padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
					contents.append("계획일자");
					contents.append("</td>");
					contents.append("<td align='left' style=' padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
					contents.append(valueName);
					contents.append("</td>");
				}else if("ACT_DATE".equals(keyName)){
					contents.append("<td height='22' align='center' style='background-color:#eee; font-weight:bold; padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
					contents.append("실행일자");
					contents.append("</td>");
					contents.append("<td align='left' style=' padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
					contents.append(valueName);
					contents.append("</td>");
					contents.append("</tr>");
				}else if("WNAME".equals(keyName)){
					contents.append("<tr height='22'>");
					contents.append("<td height='22' align='center' style='background-color:#eee; font-weight:bold; padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
					contents.append("창고명");
					contents.append("</td>");
					contents.append("<td align='left' style=' padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
					contents.append(valueName);
					contents.append("</td>");
				}else if("EMP_NAME".equals(keyName)){
					contents.append("<td height='22' align='center' style='background-color:#eee; font-weight:bold; padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
					contents.append("작성자");
					contents.append("</td>");
					contents.append("<td align='left' style=' padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
					contents.append(valueName);
					contents.append("</td>");
					contents.append("</tr>");
				}else{
				    contents.append("</tbody>");
				    contents.append("</TABLE>");
				}
			}
		}
		
		contents.append("</td></tr>");
		contents.append("</html>");
		
		return contents.toString();
	}
	
	
	 //mailing formating
   	public String setMailFormatPtBuyReqDetail(List detailInfo, List subListInfo, List approvalList)
   	{
      QueryBuffer contents = new QueryBuffer();
      
      contents.append("<html><head></head><table width='100%' ><tr><td width='100%' >");
      
      for (int i=0;i<detailInfo.size(); i++){
          Map map = (Map) detailInfo.get(i);
          Set key = map.keySet();
          
          for(Iterator it = key.iterator(); it.hasNext();)
          {
              String keyName = (String)it.next();
              String valueName = "";
              if (!"".equals(map.get(keyName))&& map.get(keyName) != null) {
              	valueName = String.valueOf(map.get(keyName));
				}
              if("TITLE".equals(keyName)){
                  contents.append("<table width='100%' style='font-family:'맑은 고딕','Malgun Gothic','돋움',Dotum,Arial,Helvetica,sans-serif; font-size:12px; margin:10px auto; border-collapse:collapse; border:1px solid #ccc;'>");
                  contents.append("<caption style='padding:6px 10px; border-collapse:collapse; border:1px solid #ccc; font-size:20px; border:none; color:#000; font-weight:bold;'>");
                  contents.append(valueName);
                  contents.append("</caption>");
                  contents.append("<tbody>");
                  contents.append("<tr height='18'>");
                  contents.append("<td colspan='4'  align='right' style=' padding:6px 10px; border-collapse:collapse; border:0px solid #ccc;'> ");
                  contents.append("<a target='_blank' style='font-size:12px;' href='" + MwareConfig.getDreamUrl() + "'>");
                  contents.append("Login to Dream");
                  contents.append("</a></td>");
                  contents.append("</tr>");
              }else if("PTPRLISTNO".equals(keyName)){
                  contents.append("<tr height='22'>");
                  contents.append("<td height='22' align='center' style='background-color:#eee; font-weight:bold; padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
                  contents.append("요청번호");
                  contents.append("</td>");
                  contents.append("<td align='left' style=' padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
                  contents.append(valueName);
                  contents.append("</td>");
              }else if("PTPRLISTSTATUSDESC".equals(keyName)){
                  contents.append("<td height='22' align='center' style='background-color:#eee; font-weight:bold; padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
                  contents.append("작성상태");
                  contents.append("</td>");
                  contents.append("<td align='left' style=' padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
                  contents.append(valueName);
                  contents.append("</td>");
                  contents.append("</tr>");
              }else if("PTPRLISTDESC".equals(keyName)){
                  contents.append("<tr height='22'>");
                  contents.append("<td height='22' align='center' style='background-color:#eee; font-weight:bold; padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
                  contents.append("제목");
                  contents.append("</td>");
                  contents.append("<td colspan='3' align='left' style=' padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
                  contents.append(valueName);
                  contents.append("</td>");
                  contents.append("</tr>");
              }else if("REQDATE".equals(keyName)){
                  contents.append("<tr height='22'>");
                  contents.append("<td height='22' align='center' style='background-color:#eee; font-weight:bold; padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
                  contents.append("신청일자");
                  contents.append("</td>");
                  contents.append("<td align='left' style=' padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
                  contents.append(valueName);
                  contents.append("</td>");
              }else if("DEPTDESC".equals(keyName)){
                  contents.append("<td height='22' align='center' style='background-color:#eee; font-weight:bold; padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
                  contents.append("신청부서");
                  contents.append("</td>");
                  contents.append("<td align='left' style=' padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
                  contents.append(valueName);
                  contents.append("</td>");
                  contents.append("</tr>");
              }else if("REMARK".equals(keyName)){
                  contents.append("<tr height='22'>");
                  contents.append("<td colspan='4'  height='22' align='center' style='background-color:#eee; font-weight:bold; padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
                  contents.append("비고");
                  contents.append("</td>");
                  contents.append("<tr height='200'>");
                  contents.append("<td colspan='4' valign='top'  align='left' style=' padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
                  contents.append(valueName);
                  contents.append("</td>");
                  contents.append("</tr>");
              }else{
//                  contents.append("<tbody>");
//                  contents.append("</TABLE>");
              }
          }
      }
      contents.append("<tbody>");
      contents.append("</TABLE>");
      contents.append("</td></tr>");
      contents.append("<table width='100%' ><tr><td width='100%' >");
      contents.append(setMailFormatItemList(subListInfo));
      contents.append("</td></tr></table>");
      contents.append("<table width='100%' ><tr><td width='100%' >");
      contents.append(setMailFormatApproval(approvalList));
      contents.append("</td></tr></table></html>");
      contents.append("</html>");
      
      return contents.toString();
   	}
   	
	private int sendMail(String[] receiveMailArr, String[] receiveEmpNoArr, String contents, String title, User user
    		, String sendEmpNo, String recEmpNo, String msgObjType, String objectId,  String objectNo) throws Exception
	{
	    int cnt = 0;
	    MgrMessageTransDetailDTO mgrMessageTransDetailDTO = new MgrMessageTransDetailDTO();
	    for(String receiver:receiveMailArr) {
	        mgrMessageTransDetailDTO.setMessageId(persAppstbMessageMailDAO.getNextSequence("sqamessagelist_id"));
            mgrMessageTransDetailDTO.setDescription(title);
            mgrMessageTransDetailDTO.setContents(contents);
            mgrMessageTransDetailDTO.setReceiver(receiver);
            mgrMessageTransDetailDTO.setMethodTypeId("MAIL");
            mgrMessageTransDetailDTO.setMsgStatusId("Z");

            mgrMessageTransDetailDTO.setSendEmpNo(sendEmpNo);
            mgrMessageTransDetailDTO.setRecEmpNo(receiveEmpNoArr[cnt]);
            mgrMessageTransDetailDTO.setMsgObjType(msgObjType);
            mgrMessageTransDetailDTO.setObjectId(objectId);
            mgrMessageTransDetailDTO.setObjectNo(objectNo);
            
            cnt += mgrMessageTransDetailService.insertDetail(mgrMessageTransDetailDTO, user);
	    }
	    return cnt;
	}
}