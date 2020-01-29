package dream.scheduler.autoschedule.service.spring;

import java.util.List;
import java.util.Map;

import com.sap.mw.jco.JCO;

import common.bean.MwareConfig;
import common.exception.SqlIgnoreException;
import common.util.MailUtil;
import dream.scheduler.autoschedule.dao.MaBatchDAO;
import dream.scheduler.autoschedule.service.MaBatchService;

/**
 * Batch 서비스 구현
 * 
 * @author kim21017
 * @version $Id: MaBatchServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * @spring.bean id="maBatchServiceTarget"
 * @spring.txbn id="maBatchService"
 * @spring.property name="maBatchDAO" ref="maBatchDAO"
 */
public class MaBatchServiceImpl implements MaBatchService {

	
	JCO.Client mConnection;
	JCO.Repository mRepository;
	
	JCO.Function function = null;
	JCO.Table codes = null;

	private MaBatchDAO maBatchDAO = null;

	public MaBatchDAO getMaBatchDAO() {
		return maBatchDAO;
	}

	public void setMaBatchDAO(MaBatchDAO maBatchDAO) {
		this.maBatchDAO = maBatchDAO;
	}

	public void SP_PM_MAKE_SCHEDULE_BYALL(String compNo, String userNo) throws Exception{
		maBatchDAO.SP_PM_MAKE_SCHEDULE_BYALL(compNo, userNo);
	}

	public void SP_PM_MAKE_TO_ALLSCHED(String compNo, String userNo) throws Exception{
		maBatchDAO.SP_PM_MAKE_TO_ALLSCHED(compNo, userNo);
	}

	public void SP_MAKE_USE_MONITORING(String compNo, String userNo) throws Exception{
		maBatchDAO.SP_MAKE_USE_MONITORING(compNo, userNo);
	}

	public void SP_PM_MAKE_TAMTPOINT(String compNo, String userNo) throws Exception{
		maBatchDAO.SP_PM_MAKE_TAMTPOINT(compNo, userNo);
	}

	public void SP_MAKE_TAINVESTAMT(String compNo, String userNo) throws Exception{
		maBatchDAO.SP_MAKE_TAINVESTAMT(compNo, userNo);
	}

	public void SP_MAKE_TALNWRKTIME(String compNo, String userNo) throws Exception{
		maBatchDAO.SP_MAKE_TALNWRKTIME(compNo, userNo);
	}

	public void SP_SETDEFAULT_DUMYDAYS(String compNo, String userNo) throws Exception{
		maBatchDAO.SP_SETDEFAULT_DUMYDAYS(compNo, userNo);
	}

	public void SP_KPI_MAKE_TAKPIDLOCDN(String compNo, String userNo) throws Exception{
		maBatchDAO.SP_KPI_MAKE_TAKPIDLOCDN(compNo, userNo);
	}

	public void SP_KPI_MAKE_TAKPIMLOCDN(String compNo, String userNo) throws Exception{
		maBatchDAO.SP_KPI_MAKE_TAKPIMLOCDN(compNo, userNo);
	}

	public void SP_KPI_MAKE_TAKPIDLOCCTGDN(String compNo, String userNo) throws Exception{
		maBatchDAO.SP_KPI_MAKE_TAKPIDLOCCTGDN(compNo, userNo);
	}

	public void SP_KPI_MAKE_TAKPIMLOCCTGDN(String compNo, String userNo) throws Exception{
		maBatchDAO.SP_KPI_MAKE_TAKPIMLOCCTGDN(compNo, userNo);
	}

	public void SP_KPI_MAKE_TAKPIMMPOINT(String compNo, String userNo) throws Exception{
		maBatchDAO.SP_KPI_MAKE_TAKPIMMPOINT(compNo, userNo);
	}

	public void SP_KPI_MAKE_TAKPIWMPOINT(String compNo, String userNo) throws Exception{
		maBatchDAO.SP_KPI_MAKE_TAKPIWMPOINT(compNo, userNo);
	}

	public void SP_KPI_MAKE_TAKPIMEDU(String compNo, String userNo) throws Exception{
		maBatchDAO.SP_KPI_MAKE_TAKPIMEDU(compNo, userNo);
	}

	public void SP_KPI_MAKE_TAPTMONTHLYSTOCK(String compNo, String userNo) throws Exception{
		maBatchDAO.SP_KPI_MAKE_TAPTMONTHLYSTOCK(compNo, userNo);
	}

	public void SP_IF_UPD_TAPARTS(String compNo, String userNo){
		maBatchDAO.SP_IF_UPD_TAPARTS(compNo, userNo);
	}
	
	public void SP_IF_UPD_TXERPPRPOLIST(String compNo, String userNo){
		maBatchDAO.SP_IF_UPD_TXERPPRPOLIST(compNo, userNo);
	}

	public void SP_IF_UPD_TXLNWRKTIME(String compNo, String userNo){
		maBatchDAO.SP_IF_UPD_TXLNWRKTIME(compNo, userNo);
	}

	public void SP_IF_UPD_TXLNNTWRKTIME(String compNo, String userNo){
		maBatchDAO.SP_IF_UPD_TXLNNTWRKTIME(compNo, userNo);
	}

	public String convertString(Object obj) {
		String result = String.valueOf(obj);
		if (result == "null")
			result = "";

		return result;
	}

	public void SP_WOMAKE_4WP_BYALL(String compNo, String userNo) throws Exception {
		maBatchDAO.SP_WOMAKE_4WP_BYALL(compNo);
	}

	public void COMP_INTERFACE(String compNo, String userNo) throws Exception {
		try{
			/** 대웅제약 프로시저 */
			maBatchDAO.DAEWOONG_IF_POP_EQ(compNo, userNo);
		}catch(SqlIgnoreException se) {}
		try {
			maBatchDAO.DAEWOONG_IF_POP_EQACT(compNo, userNo);
		} catch (SqlIgnoreException e) {}
		try {
			maBatchDAO.DAEWOONG_IF_UPD_USER(compNo, userNo);
		} catch (SqlIgnoreException e) {}
		/** 오뚜기라면 프로시저 */
		try {
			maBatchDAO.SP_IF_UPD_CTCTR(compNo,userNo);
		} catch (SqlIgnoreException e) {}
		try {
			maBatchDAO.SP_IF_UPD_EMP(compNo,userNo);
		} catch (SqlIgnoreException e) {}
		try {
			maBatchDAO.SP_IF_UPD_VENDOR(compNo,userNo);
		} catch (SqlIgnoreException e) {}
		
		// 패스워드 없는 유저 패스워드 생성 -> 기본적으로 user_no와 같게 만들어줌.
		List list = maBatchDAO.NULL_PASSWORD_USER(compNo);
		if(list != null){
			for(Object resultMap : list)
	        {
	            Map resultM = (Map)resultMap;
	            maBatchDAO.SET_PASSWORD(compNo, String.valueOf(resultM.get("USER_NO")));
	        }// end for
		}// end if
	}
	public void SP_IF_UPD_CTCTR(String compNo, String userNo) throws Exception{
		//CP
		maBatchDAO.SP_IF_UPD_CTCTR(compNo,userNo);
	}
	public void SP_IF_UPD_DEPT(String compNo, String userNo) throws Exception{
		//부서
		maBatchDAO.SP_IF_UPD_DEPT(compNo,userNo);
	}
	public void SP_IF_UPD_EMP(String compNo, String userNo) throws Exception{
		//사원
		maBatchDAO.SP_IF_UPD_EMP(compNo,userNo);
	}
	public void SP_IF_UPD_VENDOR(String compNo, String userNo) throws Exception{
		//거래처
		maBatchDAO.SP_IF_UPD_VENDOR(compNo,userNo);
	}

	@Override
	public void sendMailMessageList(String compNo, String userNo) throws Exception {
		// 메일서비스 사용여부가 Y인경우
		if ("Y".equals(MwareConfig.getIsUseMailService())){
			//MAIL 리스트 
			List sendMailList = maBatchDAO.findMailMessageList(compNo);
			MailThread mailTask = new MailThread(sendMailList);
			new Thread(mailTask,"MailThread").start();
		}
	}
	
	class MailThread implements Runnable {
		private List list;
		
		public MailThread(List list) {
			this.list = list;
		}
		
		@Override
		public void run() {
		    
			if(list!=null && list.size() >0){
			    for(Object object : list)
                {
			        Map resultMap = (Map)object;
			        
			        maBatchDAO.updateMailMessageRunning(String.valueOf(resultMap.get("MESSAGELISTID")));
                }
				for(Object object : list)
				{
					Map resultMap = (Map)object;
					StringBuffer sb = new StringBuffer();
					int resultCode = 0;
					//메일 주소 확인(틀리면 -1)
//					resultCode = CommonUtil.isValidEmailAddress(String.valueOf(resultMap.get("RECEIVERS")))?resultCode:-1;
					//내용 확인 (없으면 -2)
					resultCode = !"".equals(String.valueOf(resultMap.get("CONTENTS")))?resultCode:-2;
					//제목 확인 (없으면 -3)
					resultCode = !"".equals(String.valueOf(resultMap.get("DESCRIPTION")))?resultCode:-3;
					//첨부파일 첨부 (object_id : messagelistId, object_type : MESSAGE)
					List fileList = maBatchDAO.getMessageAttachment(String.valueOf(resultMap.get("MESSAGELISTID")), String.valueOf(resultMap.get("COMPNO")), "MESSAGE");
					
					if(resultCode == 0){
						synchronized (this) {
							try {
								resultCode = 
										MailUtil.sendMail(String.valueOf(resultMap.get("RECEIVERS"))
										, MwareConfig.getMailSenderId()
										, MwareConfig.getMailSenderPwd()
										, String.valueOf(resultMap.get("CONTENTS"))
										, String.valueOf(resultMap.get("DESCRIPTION"))
										, ""
										, fileList);
							} catch (Exception e) {
								sb.append(e.getMessage());
							}finally {
								sb.append(setMailErrorCode(resultCode));
							}
						}
					}
					
					maBatchDAO.updateMailMessageList(String.valueOf(resultMap.get("MESSAGELISTID")), resultCode, sb.toString());
				}
			}
		}
	}
	private String setMailErrorCode(int code){
		String returnCode = "";
		switch (code) {
		case 1: //성공
			returnCode = "SUCCESS SEND MAIL";
			break;
		case -1:
			returnCode = "EMAIL ADDRESS INVALID";
			break;
		case -2:
			returnCode = "CONTENTS EMPTY";
			break;
		case -3:
			returnCode = "DESCRIPTION EMPTY";
			break;
		case -4:
			returnCode = "AddressException In Make Mail Contents";
			break;
		case -5:
			returnCode = "MessagingException In Make Mail Contents";
			break;
		case -6:
			returnCode = "NoSuchProviderException In Send Mail";
			break;
		case -7:
			returnCode = "MessagingException In Send Mail";
			break;
		default:
			
			break;
		}
		return returnCode;
	}
}
