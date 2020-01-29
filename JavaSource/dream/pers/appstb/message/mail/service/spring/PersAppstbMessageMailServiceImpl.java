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
 * ���ϼ����ڼ��� - �� serviceimpl 
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
		
		if("REQWORK".equals(appReqDetailDTO.getApprType()) || "REQINVTWORK".equals(appReqDetailDTO.getApprType())) //�۾���û ���� ���� ���� ����
		{
			
			//C:����Ϸ�, D:����ݷ�, P:������, R:�����û[����Ϸ�, ����ݷ��̸� ����ڿ��� ���� ����, ������,�����û�̸� �ٸ� �����ڿ��� ������ ����]
			apprStatus = persAppstbMessageMailDAO.findApprStatus(appReqDetailDTO, user);
			
			if("C".equals(apprStatus) || "D".equals(apprStatus)) //����Ϸ� �Ǵ� ����ݷ�
	        {
			    if("N".equals(mgrMessageTransDetailService.isUseMessageCateg("APP20", "MAIL", user.getCompNo()))){
			        return 0;
			    }
				msgObjType = "APP20";
				receiveMailArr = persAppstbMessageMailDAO.findDrafterMailList(appReqDetailDTO, user); // ����ڿ��� ������ ������
				receiveEmpNoArr = persAppstbMessageMailDAO.findDrafterEmpNoList(appReqDetailDTO, user); // ����ڿ��� ������ ������
	        }else{
	            if("N".equals(mgrMessageTransDetailService.isUseMessageCateg("APP10", "MAIL", user.getCompNo()))){
                    return 0;
                }
	        	msgObjType = "APP10";
	        	receiveMailArr = persAppstbMessageMailDAO.findApproverMailList(appReqDetailDTO, user);//������ ������� ������ ����.
	        	receiveEmpNoArr = persAppstbMessageMailDAO.findApproverEmpNoList(appReqDetailDTO, user);//������ ������� ������ ����.
	        }
			if (receiveMailArr.length > 0){
				title = persAppstbMessageMailDAO.findTitle(appReqDetailDTO, "MESSAGE", "MSG0271", user);
				contents = setMailFormatWoReqDetail(persAppstbMessageMailDAO.selectWoReqDetail(appReqDetailDTO, user)
						                           ,persAppstbMessageMailDAO.selectApprovalList(appReqDetailDTO, user) );

				sendMail(receiveMailArr, receiveEmpNoArr, contents, title, user
                		,sendEmpNo ,recEmpNo, msgObjType, objId, objNo);
			}
        }else if("WORKORDER".equals(appReqDetailDTO.getApprType())) //�۾� ���� ���� ���� ����
        {
        	//C:����Ϸ�, D:����ݷ�, P:������, R:�����û[����Ϸ�, ����ݷ��̸� ����ڿ��� ���� ����, ������,�����û�̸� �ٸ� �����ڿ��� ������ ����]
			apprStatus = persAppstbMessageMailDAO.findApprStatus(appReqDetailDTO, user);
			
			if("C".equals(apprStatus) || "D".equals(apprStatus)) //����Ϸ� �Ǵ� ����ݷ�
	        {
			    if("N".equals(mgrMessageTransDetailService.isUseMessageCateg("APP20", "MAIL", user.getCompNo()))){
                    return 0;
                }
				msgObjType = "APP20";
				receiveMailArr = persAppstbMessageMailDAO.findDrafterMailList(appReqDetailDTO, user); // ����ڿ��� ������ ������
				receiveEmpNoArr = persAppstbMessageMailDAO.findDrafterEmpNoList(appReqDetailDTO, user); // ����ڿ��� ������ ������
	        }else{
	            if("N".equals(mgrMessageTransDetailService.isUseMessageCateg("APP10", "MAIL", user.getCompNo()))){
                    return 0;
                }
	        	msgObjType = "APP10";
	        	receiveMailArr = persAppstbMessageMailDAO.findApproverMailList(appReqDetailDTO, user);//������ ������� ������ ����.
	        	receiveEmpNoArr = persAppstbMessageMailDAO.findApproverEmpNoList(appReqDetailDTO, user);//������ ������� ������ ����.
	        }
			if (receiveMailArr.length > 0){
				title = persAppstbMessageMailDAO.findTitle(appReqDetailDTO, "MENU", "WORESULT", user);
				contents = setMailFormatWorkOrderDetail(persAppstbMessageMailDAO.selectWorkOrderDetail(appReqDetailDTO, user)
						                           ,persAppstbMessageMailDAO.selectApprovalList(appReqDetailDTO, user) );
				
				sendMail(receiveMailArr, receiveEmpNoArr, contents, title, user
                		,sendEmpNo ,recEmpNo, msgObjType, objId, objNo); 
			}
			
        }else if("PTSTKTAKE".equals(appReqDetailDTO.getApprType())) // ��ǰ�ǻ� ���� ���� ���� ���� ����
        {
        	// WRA : �����û  (���� �ø� �� ���ϸ�)
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
        }else if("PTBUYREQ".equals(appReqDetailDTO.getApprType())) // ����û������ ���� ���� ���� ���� ����
        {
			apprStatus = persAppstbMessageMailDAO.findApprStatus(appReqDetailDTO, user);
			
        	// WRA : �����û  (���� �ø� �� ���ϸ�)
        	if ("WRA".equals(appReqDetailDTO.getParentStatus())) {
        	    if("N".equals(mgrMessageTransDetailService.isUseMessageCateg("APP10", "MAIL", user.getCompNo()))){
                    return 0;
                }
        		msgObjType = "APP10";
        		receiveMailArr = persAppstbMessageMailDAO.findApproverMailList(appReqDetailDTO, user); //������ ������� ���� ����
        		receiveEmpNoArr = persAppstbMessageMailDAO.findApproverEmpNoList(appReqDetailDTO, user); //������ ������� ���� ����
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
	
	
	
	
	
	//�۾���û�� ���ϸ� ������..
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
					contents.append("<table width='100%' style='font-family:'���� ���','Malgun Gothic','����',Dotum,Arial,Helvetica,sans-serif; font-size:12px; margin:10px auto; border-collapse:collapse; border:1px solid #ccc;'>");
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
					contents.append("��û#");
					contents.append("</td>");
					contents.append("<td align='left' style=' padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
					contents.append(valueName);
					contents.append("</td>");
				}else if("WOREQ_STATUS".equals(keyName)){
                    contents.append("<td height='22' align='center' style='background-color:#eee; font-weight:bold; padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
                    contents.append("�������");
                    contents.append("</td>");
                    contents.append("<td align='left' style=' padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
                    contents.append(valueName);
                    contents.append("</td>");
                    contents.append("</tr>");
                }else if("WOREQ_TITLE".equals(keyName)){
                    contents.append("<tr height='22'>");
                    contents.append("<td height='22' align='center' style='background-color:#eee; font-weight:bold; padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
                    contents.append("����");
                    contents.append("</td>");
                    contents.append("<td colspan='3' align='left' style=' padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
                    contents.append(valueName);
                    contents.append("</td>");
                    contents.append("</tr>");
				}else if("EMP_NAME".equals(keyName)){
				    contents.append("<tr height='22'>");
					contents.append("<td height='22' align='center' style='background-color:#eee; font-weight:bold; padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
					contents.append("��û��");
					contents.append("</td>");
					contents.append("<td align='left' style=' padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
					contents.append(valueName);
					contents.append("</td>");
				}else if("REQ_PHONE".equals(keyName)){
                    contents.append("<td height='22' align='center' style='background-color:#eee; font-weight:bold; padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
                    contents.append("��û����ȭ");
                    contents.append("</td>");
                    contents.append("<td align='left' style=' padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
                    contents.append(valueName);
                    contents.append("</td>");
                    contents.append("</tr>");
				}else if("REQ_DATE".equals(keyName)){
					contents.append("<tr height='22'>");
					contents.append("<td height='22' align='center' style='background-color:#eee; font-weight:bold; padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
					contents.append("��û����");
					contents.append("</td>");
					contents.append("<td align='left' style=' padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
					contents.append(valueName);
					contents.append("</td>");
				}else if("REQ_COM_DATE".equals(keyName)){
					contents.append("<td height='22' align='center' style='background-color:#eee; font-weight:bold; padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
					contents.append("�Ϸ������");
					contents.append("</td>");
					contents.append("<td align='left' style=' padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
					contents.append(valueName);
					contents.append("</td>");
					contents.append("</tr>");
				}else if("REQ_PRIORITY".equals(keyName)){
					contents.append("<tr height='22'>");
					contents.append("<td height='22' align='center' style='background-color:#eee; font-weight:bold; padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
					contents.append("�켱����");
					contents.append("</td>");
					contents.append("<td align='left' style=' padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
					contents.append(valueName);
					contents.append("</td>");
				}else if("REC_NAME".equals(keyName)){
					contents.append("<td height='22' align='center' style='background-color:#eee; font-weight:bold; padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
					contents.append("�����");
					contents.append("</td>");
					contents.append("<td align='left' style=' padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
					contents.append(valueName);
					contents.append("</td>");
					contents.append("</tr>");
				}else if("EQUIP_NAME".equals(keyName)){
					contents.append("<tr height='22'>");
					contents.append("<td height='22' align='center' style='background-color:#eee; font-weight:bold; padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
					contents.append("����");
					contents.append("</td>");
					contents.append("<td colspan='3' align='left' style=' padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
					contents.append(valueName);
					contents.append("</td>");
					contents.append("</tr>");
				}else if("LOC_NAME".equals(keyName)){
					contents.append("<tr height='22'>");
					contents.append("<td height='22' align='center' style='background-color:#eee; font-weight:bold; padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
					contents.append("��  ġ");
					contents.append("</td>");
					contents.append("<td colspan='3' align='left' style=' padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
					contents.append(valueName);
					contents.append("</td>");
					contents.append("</tr>");
				}else if("REQUEST".equals(keyName)){
					contents.append("<tr height='22'>");
					contents.append("<td colspan='4'  height='22' align='center' style='background-color:#eee; font-weight:bold; padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
					contents.append("��û�󼼳���");
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
	//W/O  ���ϸ� ������..
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
					contents.append("<table width='100%' style='font-family:'���� ���','Malgun Gothic','����',Dotum,Arial,Helvetica,sans-serif; font-size:12px; margin:10px auto; border-collapse:collapse; border:1px solid #ccc;'>");
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
					contents.append("�����");
					contents.append("</td>");
					contents.append("<td align='left' style=' padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
					contents.append(valueName);
					contents.append("</td>");
					contents.append("</tr>");
				}else if("EQLOC".equals(keyName)){
					contents.append("<tr height='22'>");
					contents.append("<td height='22' align='center' style='background-color:#eee; font-weight:bold; padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
					contents.append("��ġ");
					contents.append("</td>");
					contents.append("<td align='left' style=' padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
					contents.append(valueName);
					contents.append("</td>");
				}else if("EQUIP".equals(keyName)){
					contents.append("<td height='22' align='center' style='background-color:#eee; font-weight:bold; padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
					contents.append("����");
					contents.append("</td>");
					contents.append("<td align='left' style=' padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
					contents.append(valueName);
					contents.append("</td>");
					contents.append("</tr>");
				}else if("START_DATE".equals(keyName)){
					contents.append("<tr height='22'>");
					contents.append("<td height='22' align='center' style='background-color:#eee; font-weight:bold; padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
					contents.append("�۾� ��������");
					contents.append("</td>");
					contents.append("<td align='left' style=' padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
					contents.append(valueName);
					contents.append("</td>");
				}else if("END_DATE".equals(keyName)){
					contents.append("<td height='22' align='center' style='background-color:#eee; font-weight:bold; padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
					contents.append("�۾� ��������");
					contents.append("</td>");
					contents.append("<td align='left' style=' padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
					contents.append(valueName);
					contents.append("</td>");
					contents.append("</tr>");
				}else if("WKOR_DATE".equals(keyName)){
					contents.append("<tr height='22'>");
					contents.append("<td height='22' align='center' style='background-color:#eee; font-weight:bold; padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
					contents.append("�۾�����");
					contents.append("</td>");
					contents.append("<td colspan='3' align='left' style=' padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
					contents.append(valueName);
					contents.append("</td>");
					contents.append("</tr>");
				}else if("PERFORM".equals(keyName)){
					contents.append("<tr height='22'>");
					contents.append("<td colspan='4'  height='22' align='center' style='background-color:#eee; font-weight:bold; padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
					contents.append("�󼼳���");
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
        contents.append("<table width='100%' style='font-family:'���� ���','Malgun Gothic','����',Dotum,Arial,Helvetica,sans-serif; font-size:12px; margin:20px 10px auto; border-collapse:collapse; border:1px solid #ccc;'>");
        contents.append("<caption style='text-align:left; padding:6px 10px; border-collapse:collapse; border:1px solid #ccc; font-size:14px; border:none; color:#000; font-weight:bold;'> ǰ�� </cation>");
        contents.append("<tbody>");
		for (int i=0;i<subListInfo.size(); i++){
			Map map = (Map) subListInfo.get(i);
			Set key = map.keySet();
			if(i==0)
			{
				contents.append("<tr height='22'>");
				contents.append("<th width='50' style='background-color:#eee; font-weight:bold; padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>ǰ��</th>");
				contents.append("<th width='100' style='background-color:#eee; font-weight:bold; padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>ǰ��</th>");
				contents.append("<th width='80' style='background-color:#eee; font-weight:bold; padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>����ó</th>");
				contents.append("<th width='80' style='background-color:#eee; font-weight:bold; padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>��</th>");
				contents.append("<th width='100' style='background-color:#eee; font-weight:bold; padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>�԰�</th>");
				contents.append("<th width='50' style='background-color:#eee; font-weight:bold; padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>����</th>");
				contents.append("<th width='50' style='background-color:#eee; font-weight:bold; padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>�ܰ�</th>");
				contents.append("<th width='50' style='background-color:#eee; font-weight:bold; padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>����</th>");
				contents.append("<th width='100' style='background-color:#eee; font-weight:bold; padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>���</th>");
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
	
	//������� ����
		public String setMailFormatApproval(List approvalList)
		{
	        QueryBuffer contents = new QueryBuffer();
	        contents.append("<table width='100%' style='font-family:'���� ���','Malgun Gothic','����',Dotum,Arial,Helvetica,sans-serif; font-size:12px; margin:20px 10px auto; border-collapse:collapse; border:1px solid #ccc;'>");
	        contents.append("<caption style='text-align:left; padding:6px 10px; border-collapse:collapse; border:1px solid #ccc; font-size:14px; border:none; color:#000; font-weight:bold;'> ����������Ȳ </cation>");
	        contents.append("<tbody>");
			for (int i=0;i<approvalList.size(); i++){
				Map map = (Map) approvalList.get(i);
				Set key = map.keySet();
				if(i==0)
				{
					contents.append("<tr height='22'>");
					contents.append("<th width='50' style='background-color:#eee; font-weight:bold; padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>����</th>");
					contents.append("<th width='50' style='background-color:#eee; font-weight:bold; padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>����</th>");
					contents.append("<th width='100' style='background-color:#eee; font-weight:bold; padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>������</th>");
					contents.append("<th width='80' style='background-color:#eee; font-weight:bold; padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>����</th>");
					contents.append("<th width='125' style='background-color:#eee; font-weight:bold; padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>�μ�</th>");
					contents.append("<th width='100' style='background-color:#eee; font-weight:bold; padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>�������</th>");
					contents.append("<th width='200' style='background-color:#eee; font-weight:bold; padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>�ð�</th>");
					contents.append("<th width='' style='background-color:#eee; font-weight:bold; padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>�ǰ�</th>");
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
	
	//��ǰ�ǻ� �� ���ϸ� ����
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
					contents.append("<table width='100%' style='font-family:'���� ���','Malgun Gothic','����',Dotum,Arial,Helvetica,sans-serif; font-size:12px; margin:10px auto; border-collapse:collapse; border:1px solid #ccc;'>");
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
					contents.append("�ǻ�#");
					contents.append("</td>");
					contents.append("<td align='left' style=' padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
					contents.append(valueName);
					contents.append("</td>");
				}else if("PTSTKTAKE_STATUS".equals(keyName)){
                    contents.append("<td height='22' align='center' style='background-color:#eee; font-weight:bold; padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
                    contents.append("�������");
                    contents.append("</td>");
                    contents.append("<td align='left' style=' padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
                    contents.append(valueName);
                    contents.append("</td>");
                    contents.append("</tr>");
                }else if("DESCRIPTION".equals(keyName)){
                    contents.append("<tr height='22'>");
                    contents.append("<td height='22' align='center' style='background-color:#eee; font-weight:bold; padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
                    contents.append("����");
                    contents.append("</td>");
                    contents.append("<td colspan='3' align='left' style=' padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
                    contents.append(valueName);
                    contents.append("</td>");
                    contents.append("</tr>");
				}else if("PLAN_DATE".equals(keyName)){
					contents.append("<tr height='22'>");
					contents.append("<td height='22' align='center' style='background-color:#eee; font-weight:bold; padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
					contents.append("��ȹ����");
					contents.append("</td>");
					contents.append("<td align='left' style=' padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
					contents.append(valueName);
					contents.append("</td>");
				}else if("ACT_DATE".equals(keyName)){
					contents.append("<td height='22' align='center' style='background-color:#eee; font-weight:bold; padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
					contents.append("��������");
					contents.append("</td>");
					contents.append("<td align='left' style=' padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
					contents.append(valueName);
					contents.append("</td>");
					contents.append("</tr>");
				}else if("WNAME".equals(keyName)){
					contents.append("<tr height='22'>");
					contents.append("<td height='22' align='center' style='background-color:#eee; font-weight:bold; padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
					contents.append("â���");
					contents.append("</td>");
					contents.append("<td align='left' style=' padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
					contents.append(valueName);
					contents.append("</td>");
				}else if("EMP_NAME".equals(keyName)){
					contents.append("<td height='22' align='center' style='background-color:#eee; font-weight:bold; padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
					contents.append("�ۼ���");
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
                  contents.append("<table width='100%' style='font-family:'���� ���','Malgun Gothic','����',Dotum,Arial,Helvetica,sans-serif; font-size:12px; margin:10px auto; border-collapse:collapse; border:1px solid #ccc;'>");
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
                  contents.append("��û��ȣ");
                  contents.append("</td>");
                  contents.append("<td align='left' style=' padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
                  contents.append(valueName);
                  contents.append("</td>");
              }else if("PTPRLISTSTATUSDESC".equals(keyName)){
                  contents.append("<td height='22' align='center' style='background-color:#eee; font-weight:bold; padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
                  contents.append("�ۼ�����");
                  contents.append("</td>");
                  contents.append("<td align='left' style=' padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
                  contents.append(valueName);
                  contents.append("</td>");
                  contents.append("</tr>");
              }else if("PTPRLISTDESC".equals(keyName)){
                  contents.append("<tr height='22'>");
                  contents.append("<td height='22' align='center' style='background-color:#eee; font-weight:bold; padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
                  contents.append("����");
                  contents.append("</td>");
                  contents.append("<td colspan='3' align='left' style=' padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
                  contents.append(valueName);
                  contents.append("</td>");
                  contents.append("</tr>");
              }else if("REQDATE".equals(keyName)){
                  contents.append("<tr height='22'>");
                  contents.append("<td height='22' align='center' style='background-color:#eee; font-weight:bold; padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
                  contents.append("��û����");
                  contents.append("</td>");
                  contents.append("<td align='left' style=' padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
                  contents.append(valueName);
                  contents.append("</td>");
              }else if("DEPTDESC".equals(keyName)){
                  contents.append("<td height='22' align='center' style='background-color:#eee; font-weight:bold; padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
                  contents.append("��û�μ�");
                  contents.append("</td>");
                  contents.append("<td align='left' style=' padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
                  contents.append(valueName);
                  contents.append("</td>");
                  contents.append("</tr>");
              }else if("REMARK".equals(keyName)){
                  contents.append("<tr height='22'>");
                  contents.append("<td colspan='4'  height='22' align='center' style='background-color:#eee; font-weight:bold; padding:6px 10px; border-collapse:collapse; border:1px solid #ccc;'>");
                  contents.append("���");
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