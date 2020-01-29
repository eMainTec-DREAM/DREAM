package dream.mgr.mail.service.spring;

import java.util.ArrayList;
import java.util.List;

import common.bean.MwareConfig;
import common.bean.User;
import common.util.CommonUtil;
import common.util.MailUtil;
import dream.mgr.mail.dao.MaMailDetailDAO;
import dream.mgr.mail.dto.MaMailCommonDTO;
import dream.mgr.mail.dto.MaMailDetailDTO;
import dream.mgr.mail.service.MaMailDetailService;
import dream.mgr.message.dto.MgrMessageTransDetailDTO;
import dream.mgr.message.service.MgrMessageTransDetailService;

/**
 * ���ϼ����ڼ��� - �� serviceimpl 
 * @author  kim21017
 * @version $Id: MaMailDetailServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maMailDetailServiceTarget"
 * @spring.txbn id="maMailDetailService"
 * @spring.property name="maMailDetailDAO" ref="maMailDetailDAO"
 */
public class MaMailDetailServiceImpl implements MaMailDetailService
{
    private MaMailDetailDAO maMailDetailDAO = null;

	public MaMailDetailDAO getMaMailDetailDAO() {
		return maMailDetailDAO;
	}

	public void setMaMailDetailDAO(MaMailDetailDAO maMailDetailDAO) {
		this.maMailDetailDAO = maMailDetailDAO;
	}

	public MaMailDetailDTO findDetail(MaMailCommonDTO maMailCommonDTO)throws Exception
    {
        return maMailDetailDAO.findDetail(maMailCommonDTO);
    }
	
	public int updateDetail(MaMailDetailDTO maMailDetailDTO) throws Exception
    {        
        return maMailDetailDAO.updateDetail(maMailDetailDTO);
        
    }
	
	public int insertDetail(MaMailDetailDTO maMailDetailDTO) throws Exception
    {        
        return maMailDetailDAO.insertDetail(maMailDetailDTO);
    }
	
	
	
	//�ڵ����� ����Ǵ� ���� �޼ҵ�.
	public void sendMessageAll() throws Exception
    { 
		//���Ϻ����� ������ �Ǿ����� �ʴٸ� ������ ������ �ʽ��ϴ�.
		if (!"Y".equals(MwareConfig.getIsUseMailService())){
			return;
		}
		//DTO ��ü ����
		MaMailDetailDTO maMailDetailDTO = new MaMailDetailDTO();
		MaMailCommonDTO maMailCommonDTO = new MaMailCommonDTO();
		
		//�����췯�� ���ư��� ������ maillist_id �� comp_no�� �޴´�. [0]:maillist_id, [1]:comp_no
		String[][] mailServiceList = maMailDetailDAO.findAllMailingList(maMailDetailDTO);
		
		if (mailServiceList.length > 0){
			for(int i=0; i<mailServiceList.length; i++){
				maMailCommonDTO.setMailListId(mailServiceList[i][0]);
				maMailCommonDTO.setCompNo(mailServiceList[i][1]);
				maMailDetailDTO = maMailDetailDAO.findDetail(maMailCommonDTO);
				maMailDetailDTO.setCompNo(mailServiceList[i][1]);
				sendMessage(maMailDetailDTO);
			}
		}
		
    }
	
	public int sendMessageCheck(MaMailDetailDTO maMailDetailDTO) throws Exception
    {   
        // return 0:����, -1:���ϼ��� �̵��, -2:���۴���̾���, -3:���������̾���(���� script ����� 0��)
		if (!"Y".equals(MwareConfig.getIsUseMailService())){
			return -1;
		}
		
		// �޴»�� �����ּ� ��ȸ
		String[] receiveMailArr = maMailDetailDAO.findSendList(maMailDetailDTO);
		if (!(receiveMailArr.length>0)) {
			return -2;
		}
		
		// ���� script ��ȸ
		List resultList = maMailDetailDAO.sendMessageQuery(this.htmlToStr(maMailDetailDTO.getScript()));
		if (!(resultList.size()>0)) {
			return -3;
		}
		
		// DB��ȸ ������ 
		String title = maMailDetailDAO.findTitleMessageQuery(this.htmlToStr(maMailDetailDTO.getTitleScript()));
		String linkTitle = maMailDetailDAO.selectLinkTitle();
		
		// ���� ���� ����(���̺� ��������)
		String contents = MailUtil.getContents(title,linkTitle, resultList);
		
		// ȸ�� ��ȣ
		String compNo = maMailDetailDTO.getCompNo();
		
		// �����»�� �����ּ�
		String mailSenderId = MwareConfig.getMailSenderId();
		// ������ ��� ���� �н�����
		String mailSenderPwd = MwareConfig.getMailSenderPwd();
		
		MailUtil.sendMail(receiveMailArr, mailSenderId, mailSenderPwd, contents, title, compNo); 
		
		//���ϰ�� update
		maMailDetailDAO.updateMailSendTime(maMailDetailDTO);
		
		return 0;
    }
	
	
	public int sendMessage(MaMailDetailDTO maMailDetailDTO) throws Exception
    {   
	    String compNo = maMailDetailDTO.getCompNo();
	    MgrMessageTransDetailService mgrMessageTransDetailService = (MgrMessageTransDetailService) CommonUtil.getBean("mgrMessageTransDetailService", compNo);
        // return 0:����, -1:���ϼ��� �̵��, -2:���۴���̾���, -3:���������̾���(���� script ����� 0��)
		
		//�ش� ���񽺸� �ý��ۿ��� ����ϰ� �ִ��� ���� �Ǵ� ( MAIL, SMS )
		if (!isUseService(maMailDetailDTO))return -1;
        
        //���۽ð� update
		maMailDetailDAO.updateMailSendTime(maMailDetailDTO);
        
		String[] sendArr 			= null;	//������ ����Ʈ
		String[][] sendMultiArr 	= null;	//������(�����ּ�+���) ����Ʈ
		List resultList 			= null;		//contents�� �� ��� list
		String contents 			= "";		//���� contents
		String title 				= "";			//���� title
		
		User user = new User();
		user.setCompNo(maMailDetailDTO.getCompNo());
		MgrMessageTransDetailDTO mgrMessageTransDetailDTO = new MgrMessageTransDetailDTO();
		
		//���ι߼� �� 
		if("I".equals(maMailDetailDTO.getMailScopeTypeId())){
			sendArr = maMailDetailDAO.findSendList(maMailDetailDTO);
			
			//contents, title ����
			if("MAIL".equals(maMailDetailDTO.getMethodType())){
				resultList = maMailDetailDAO.sendMessageQuery(this.htmlToStr(maMailDetailDTO.getScript()));
				// DB��ȸ ������ 
				title = maMailDetailDAO.findTitleMessageQuery(this.htmlToStr(maMailDetailDTO.getTitleScript()));
				String linkTitle = maMailDetailDAO.selectLinkTitle();
				contents = MailUtil.getContents(title,linkTitle, resultList);
				
			}else if("SMS".equals(maMailDetailDTO.getMethodType())){
				contents = maMailDetailDTO.getScript();
				title = maMailDetailDTO.getScript();
			}
			
			//TAMAILUSR�� ����Ʈ�� ��ȸ�ؼ� ������ return
			if (sendArr == null || sendArr.length < 1) return -2;
			// ���� script ��ȸ�� ���� ������ ���� ��� return
			if (resultList == null || resultList.size() < 1 ) return -3;
			if ("".equals(contents)) return -3;
			
			//TAMESSAGELIST�� �ֱ�.
			for(String sendId : sendArr){
				if("SMS".equals(maMailDetailDTO.getMethodType())) sendId = sendId.replaceAll("-", ""); // SMS�� - ��ȣ ����
				
				mgrMessageTransDetailDTO.setMessageId(maMailDetailDAO.getNextSequence("SQAMESSAGELIST_ID"));
				mgrMessageTransDetailDTO.setDescription(title);
				mgrMessageTransDetailDTO.setContents(contents);
				mgrMessageTransDetailDTO.setReceiver(sendId);
				mgrMessageTransDetailDTO.setMethodTypeId(maMailDetailDTO.getMethodType());
				mgrMessageTransDetailDTO.setMsgStatusId("Z");
				mgrMessageTransDetailDTO.setRetryCnt("0");
				mgrMessageTransDetailDTO.setSendEmpNo("ADMIN");
				mgrMessageTransDetailDTO.setMsgObjType("MAL01");
//				mgrMessageTransDetailDTO.setRecEmpNo(recEmpNo);
//				mgrMessageTransDetailDTO.setObjectId(objectId);
//				mgrMessageTransDetailDTO.setObjectNo(objectNo);
				mgrMessageTransDetailService.insertDetail(mgrMessageTransDetailDTO, user);
			}
		}
		//��ü�߼� ��
		else if("G".equals(maMailDetailDTO.getMailScopeTypeId())){
			//���� ����Ʈ �ޱ�.
			String[] scopeArr = maMailDetailDAO.getStringArrayFromScript(maMailDetailDTO.getScopeScript());
			if (scopeArr == null || scopeArr.length < 1) return -2;
			
			List<String[]> allList = new ArrayList<>();
			
			for(String scope : scopeArr){
				sendMultiArr = maMailDetailDAO.getMultiStringArrayFromScript(this.htmlToStr(maMailDetailDTO.getTargetScript().replaceAll("\\$\\$parameter\\$\\$", scope)));
				if (sendMultiArr == null || sendMultiArr.length < 1) continue;
				
				if("MAIL".equals(maMailDetailDTO.getMethodType())){
					resultList = maMailDetailDAO.sendMessageQuery(this.htmlToStr(maMailDetailDTO.getScript().replaceAll("\\$\\$parameter\\$\\$", scope)));
					if (resultList == null || resultList.size() < 1 ) continue;
					
					title = maMailDetailDAO.findTitleMessageQuery(this.htmlToStr(maMailDetailDTO.getTitleScript().replaceAll("\\$\\$parameter\\$\\$", scope)));
					String linkTitle = maMailDetailDAO.selectLinkTitle();
					contents = MailUtil.getContents(title,linkTitle, resultList);
				}else if("SMS".equals(maMailDetailDTO.getMethodType())){
					contents = maMailDetailDTO.getScript();
					title = maMailDetailDTO.getScript();
					if("".equals(contents))continue;
				}
				
				for (int i = 0; i < sendMultiArr.length; i++) {
					String receiver = sendMultiArr[i][0];
					if("SMS".equals(maMailDetailDTO.getMethodType())) receiver = receiver.replaceAll("-", ""); // SMS�� - ��ȣ ����
					// email,����,����,�޴»�����
					allList.add(new String[]{receiver,contents,title,(sendMultiArr[0].length==1?null:sendMultiArr[i][1])});
					
				}
				
			}			
			if (allList == null || allList.size() < 1 ) return -3;
			else{
				//TAMESSAGELIST �ֱ�
				for(String[] messageArr : allList){
					//messageArr[0]-> ������, messageArr[1]->contents, messageArr[2]->title, messageArr[3]->�����ڻ��
					mgrMessageTransDetailDTO.setMessageId(maMailDetailDAO.getNextSequence("SQAMESSAGELIST_ID"));
					mgrMessageTransDetailDTO.setReceiver(messageArr[0]);
					mgrMessageTransDetailDTO.setContents(messageArr[1]);
					mgrMessageTransDetailDTO.setDescription(messageArr[2]);
					mgrMessageTransDetailDTO.setMethodTypeId(maMailDetailDTO.getMethodType());
					mgrMessageTransDetailDTO.setMsgStatusId("Z");
					mgrMessageTransDetailDTO.setRetryCnt("0");
					mgrMessageTransDetailDTO.setSendEmpNo("ADMIN");
					mgrMessageTransDetailDTO.setRecEmpNo(messageArr[3]);
	                mgrMessageTransDetailDTO.setMsgObjType("MAL01");
//	                mgrMessageTransDetailDTO.setObjectId(objectId);
//	                mgrMessageTransDetailDTO.setObjectNo(objectNo);
					mgrMessageTransDetailService.insertDetail(mgrMessageTransDetailDTO, user);
				}
			}
			
		}
		
		return 0;
    }
	
	/*���߿� ��������� �ϴ� ���α׷�..*/
	private String htmlToStr(String orginData)
    {
        if (orginData == null) return "";
        
        orginData = orginData.replaceAll("& lt;", "<");
        orginData = orginData.replaceAll("& gt;", ">");

        return orginData;
    }
	/**
	 * �߼ۼ��� ��뿩�� �Ǵ�.
	 * METHOD_TYPE �� MAIL �̸� TACONFIG�� IS_USER_MAIL_SERVICE ������ �Ǵ�.
	 * SMS �� TACONFIG�� IS_SMS_MAIL_SERVICE������ �Ǵ�.
	 * @param maMailDetailDTO
	 * @return
	 */
	private boolean isUseService(MaMailDetailDTO maMailDetailDTO){
	    String compNo = maMailDetailDTO.getCompNo();
		boolean result = false;
		try{
		    switch (maMailDetailDTO.getMethodType()) {
		        case "MAIL":
		            if("Y".equals(MwareConfig.getIsUseMailService())){
		                MgrMessageTransDetailService mgrMessageTransDetailService = (MgrMessageTransDetailService) CommonUtil.getBean("mgrMessageTransDetailService", compNo);
		                if("Y".equals(mgrMessageTransDetailService.isUseMessageCateg("MAL01", "MAIL", compNo))){
		                    result = true;
		                }
		            }
		            break;
		        case "SMS":
		            result = "Y".equals(MwareConfig.getIsUseSmsService())?true:false;
		            break;
		    }
		} catch(Exception e){
		    e.printStackTrace();
		}
		return result;
	}

	@Override
	public int updateMailSendTime(MaMailDetailDTO maMailDetailDTO) throws Exception {
		return maMailDetailDAO.updateDetail(maMailDetailDTO);
	}

}
