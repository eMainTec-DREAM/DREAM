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
 * 메일수신자설정 - 상세 serviceimpl 
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
	
	
	
	//자동으로 실행되는 통합 메소드.
	public void sendMessageAll() throws Exception
    { 
		//메일보내기 설정이 되어있지 않다면 메일을 보내지 않습니다.
		if (!"Y".equals(MwareConfig.getIsUseMailService())){
			return;
		}
		//DTO 객체 선언
		MaMailDetailDTO maMailDetailDTO = new MaMailDetailDTO();
		MaMailCommonDTO maMailCommonDTO = new MaMailCommonDTO();
		
		//스케쥴러로 돌아가기 때문에 maillist_id 와 comp_no를 받는다. [0]:maillist_id, [1]:comp_no
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
        // return 0:정상, -1:메일서비스 미등록, -2:전송대상이없음, -3:보낼내용이없음(본문 script 실행시 0건)
		if (!"Y".equals(MwareConfig.getIsUseMailService())){
			return -1;
		}
		
		// 받는사람 메일주소 조회
		String[] receiveMailArr = maMailDetailDAO.findSendList(maMailDetailDTO);
		if (!(receiveMailArr.length>0)) {
			return -2;
		}
		
		// 본문 script 조회
		List resultList = maMailDetailDAO.sendMessageQuery(this.htmlToStr(maMailDetailDTO.getScript()));
		if (!(resultList.size()>0)) {
			return -3;
		}
		
		// DB조회 쿼리문 
		String title = maMailDetailDAO.findTitleMessageQuery(this.htmlToStr(maMailDetailDTO.getTitleScript()));
		String linkTitle = maMailDetailDAO.selectLinkTitle();
		
		// 메일 본문 내용(테이블 형식으로)
		String contents = MailUtil.getContents(title,linkTitle, resultList);
		
		// 회사 번호
		String compNo = maMailDetailDTO.getCompNo();
		
		// 보내는사람 메일주소
		String mailSenderId = MwareConfig.getMailSenderId();
		// 보내는 사람 메일 패스워드
		String mailSenderPwd = MwareConfig.getMailSenderPwd();
		
		MailUtil.sendMail(receiveMailArr, mailSenderId, mailSenderPwd, contents, title, compNo); 
		
		//메일결과 update
		maMailDetailDAO.updateMailSendTime(maMailDetailDTO);
		
		return 0;
    }
	
	
	public int sendMessage(MaMailDetailDTO maMailDetailDTO) throws Exception
    {   
	    String compNo = maMailDetailDTO.getCompNo();
	    MgrMessageTransDetailService mgrMessageTransDetailService = (MgrMessageTransDetailService) CommonUtil.getBean("mgrMessageTransDetailService", compNo);
        // return 0:정상, -1:메일서비스 미등록, -2:전송대상이없음, -3:보낼내용이없음(본문 script 실행시 0건)
		
		//해당 서비스를 시스템에서 사용하고 있는지 여부 판단 ( MAIL, SMS )
		if (!isUseService(maMailDetailDTO))return -1;
        
        //전송시간 update
		maMailDetailDAO.updateMailSendTime(maMailDetailDTO);
        
		String[] sendArr 			= null;	//수신자 리스트
		String[][] sendMultiArr 	= null;	//수신자(메일주소+사번) 리스트
		List resultList 			= null;		//contents에 들어갈 결과 list
		String contents 			= "";		//보낼 contents
		String title 				= "";			//보낼 title
		
		User user = new User();
		user.setCompNo(maMailDetailDTO.getCompNo());
		MgrMessageTransDetailDTO mgrMessageTransDetailDTO = new MgrMessageTransDetailDTO();
		
		//개인발송 시 
		if("I".equals(maMailDetailDTO.getMailScopeTypeId())){
			sendArr = maMailDetailDAO.findSendList(maMailDetailDTO);
			
			//contents, title 생성
			if("MAIL".equals(maMailDetailDTO.getMethodType())){
				resultList = maMailDetailDAO.sendMessageQuery(this.htmlToStr(maMailDetailDTO.getScript()));
				// DB조회 쿼리문 
				title = maMailDetailDAO.findTitleMessageQuery(this.htmlToStr(maMailDetailDTO.getTitleScript()));
				String linkTitle = maMailDetailDAO.selectLinkTitle();
				contents = MailUtil.getContents(title,linkTitle, resultList);
				
			}else if("SMS".equals(maMailDetailDTO.getMethodType())){
				contents = maMailDetailDTO.getScript();
				title = maMailDetailDTO.getScript();
			}
			
			//TAMAILUSR의 리스트를 조회해서 없으면 return
			if (sendArr == null || sendArr.length < 1) return -2;
			// 본문 script 조회후 보낼 내용이 없을 경우 return
			if (resultList == null || resultList.size() < 1 ) return -3;
			if ("".equals(contents)) return -3;
			
			//TAMESSAGELIST에 넣기.
			for(String sendId : sendArr){
				if("SMS".equals(maMailDetailDTO.getMethodType())) sendId = sendId.replaceAll("-", ""); // SMS면 - 기호 제거
				
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
		//단체발송 시
		else if("G".equals(maMailDetailDTO.getMailScopeTypeId())){
			//범위 리스트 받기.
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
					if("SMS".equals(maMailDetailDTO.getMethodType())) receiver = receiver.replaceAll("-", ""); // SMS면 - 기호 제거
					// email,내용,제목,받는사람사번
					allList.add(new String[]{receiver,contents,title,(sendMultiArr[0].length==1?null:sendMultiArr[i][1])});
					
				}
				
			}			
			if (allList == null || allList.size() < 1 ) return -3;
			else{
				//TAMESSAGELIST 넣기
				for(String[] messageArr : allList){
					//messageArr[0]-> 수신자, messageArr[1]->contents, messageArr[2]->title, messageArr[3]->수신자사번
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
	
	/*나중에 변경해줘야 하는 프로그램..*/
	private String htmlToStr(String orginData)
    {
        if (orginData == null) return "";
        
        orginData = orginData.replaceAll("& lt;", "<");
        orginData = orginData.replaceAll("& gt;", ">");

        return orginData;
    }
	/**
	 * 발송서비스 사용여부 판단.
	 * METHOD_TYPE 이 MAIL 이면 TACONFIG의 IS_USER_MAIL_SERVICE 값으로 판단.
	 * SMS 면 TACONFIG의 IS_SMS_MAIL_SERVICE값으로 판단.
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
