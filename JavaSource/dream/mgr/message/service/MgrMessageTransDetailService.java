package dream.mgr.message.service;

import java.io.File;
import java.util.List;
import java.util.Map;

import common.bean.User;
import common.exception.SqlIgnoreException;
import dream.mgr.message.dto.MgrMessageTransCommonDTO;
import dream.mgr.message.dto.MgrMessageTransDetailDTO;

/**
 * Message Transfer Page - Detail Service
 * @author syyang
 * @version $Id$
 * @since 1.0
 */
public interface MgrMessageTransDetailService
{    
	/**
	 * FIND DETAIL
	 * @param mgrMessageTransCommonDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public MgrMessageTransDetailDTO findDetail(MgrMessageTransCommonDTO mgrMessageTransCommonDTO, User user) throws Exception;
	/**
	 * INSERT 
	 * @param mgrMessageTransDetailDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public int insertDetail(MgrMessageTransDetailDTO mgrMessageTransDetailDTO, User user) throws Exception;
	
	public String insertDetail(String managerMailId, String contents, String title, User user ,String sendEmpNo, String recEmpNo, String msgObjType, String methodType, String objectId,  String objectNo);
	public void insertKakaoAlarmRequestLog(String messageListId, String managerMailId, String contents, String title, User user ,String sendEmpNo, String recEmpNo, String msgObjType, String methodType, String objectId,  String objectNo);
	//메일 전송
    public String sendMail(String messageObjectType, String objectId, String methodType, User user) throws SqlIgnoreException;
    public void makeKakaoMessage(String messageObjectType, String objectId, String methodType, User user)throws SqlIgnoreException;
    
    //결재 시 호출
    public String APP(String apprListId, String methodType, User user) throws SqlIgnoreException;
    //결재대기시 대기자에게 전송
    public String APP10(Map apprList, String methodType, User user) throws SqlIgnoreException;
    //결재완료시 기안자에게 전송
    public String APP20(Map apprList, String methodType, User user) throws SqlIgnoreException;
    //작업요청시 접수자(부)에게 전송
    public String REQ10(String objectId, String methodType, User user) throws SqlIgnoreException;
    //작업불가,접수시 요청자에게 전송
    public String RQC10(String objectId, String methodType, User user) throws SqlIgnoreException;
    //접수자 변경 시 변경된 접수자에게 전송
    public String RQC20(String objectId, String methodType, User user) throws SqlIgnoreException;
    //작업완료시 요청자에게 전송
    public String WRK10(String objectId, String methodType, User user) throws SqlIgnoreException;
    //작업완료시 작업부서 전원에게 전송
    public String WRK20(String objectId, String methodType, User user) throws SqlIgnoreException;
    //현장구매신청 시 구매 담당자에게 전송
    public String PPR10(String objectId, String methodType, User user) throws SqlIgnoreException;
    //구매신청시 발주담당자에게 전송
    public String PRI10(String objectId, String methodType, User user) throws SqlIgnoreException;
    //출고처리시 수령자에게 전송
    public String ISS10(String objectId, String methodType, User user) throws SqlIgnoreException;
    //QNA완료시 요청자에게 전송
    public String QNA20(String objectId, String methodType, User user) throws SqlIgnoreException;
    //오류발생시 담당자에게 전송
    public String ERR10(String objectId, String methodType, User user) throws SqlIgnoreException;
    //사용자계정 등록시 사용자에게 전송
    public String USR10(String objectId, String methodType, User user) throws SqlIgnoreException;
    //사용자 비밀번호 리셋시 사용자에게 전송
    public String USR20(String objectId, String methodType, User user) throws SqlIgnoreException;
    
    public String isUseMessageCateg(String messageObjectType, String methodType, String compNo) throws Exception;
    
    public String isUseMessageEmp(String messageObjectType, String methodType, String compNo, String empId) throws Exception;
    
    public List getEmp(Map map, User user) throws Exception;
    
    public File getFile(String messageObjectType, User user);
}
