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
	//���� ����
    public String sendMail(String messageObjectType, String objectId, String methodType, User user) throws SqlIgnoreException;
    public void makeKakaoMessage(String messageObjectType, String objectId, String methodType, User user)throws SqlIgnoreException;
    
    //���� �� ȣ��
    public String APP(String apprListId, String methodType, User user) throws SqlIgnoreException;
    //������� ����ڿ��� ����
    public String APP10(Map apprList, String methodType, User user) throws SqlIgnoreException;
    //����Ϸ�� ����ڿ��� ����
    public String APP20(Map apprList, String methodType, User user) throws SqlIgnoreException;
    //�۾���û�� ������(��)���� ����
    public String REQ10(String objectId, String methodType, User user) throws SqlIgnoreException;
    //�۾��Ұ�,������ ��û�ڿ��� ����
    public String RQC10(String objectId, String methodType, User user) throws SqlIgnoreException;
    //������ ���� �� ����� �����ڿ��� ����
    public String RQC20(String objectId, String methodType, User user) throws SqlIgnoreException;
    //�۾��Ϸ�� ��û�ڿ��� ����
    public String WRK10(String objectId, String methodType, User user) throws SqlIgnoreException;
    //�۾��Ϸ�� �۾��μ� �������� ����
    public String WRK20(String objectId, String methodType, User user) throws SqlIgnoreException;
    //���屸�Ž�û �� ���� ����ڿ��� ����
    public String PPR10(String objectId, String methodType, User user) throws SqlIgnoreException;
    //���Ž�û�� ���ִ���ڿ��� ����
    public String PRI10(String objectId, String methodType, User user) throws SqlIgnoreException;
    //���ó���� �����ڿ��� ����
    public String ISS10(String objectId, String methodType, User user) throws SqlIgnoreException;
    //QNA�Ϸ�� ��û�ڿ��� ����
    public String QNA20(String objectId, String methodType, User user) throws SqlIgnoreException;
    //�����߻��� ����ڿ��� ����
    public String ERR10(String objectId, String methodType, User user) throws SqlIgnoreException;
    //����ڰ��� ��Ͻ� ����ڿ��� ����
    public String USR10(String objectId, String methodType, User user) throws SqlIgnoreException;
    //����� ��й�ȣ ���½� ����ڿ��� ����
    public String USR20(String objectId, String methodType, User user) throws SqlIgnoreException;
    
    public String isUseMessageCateg(String messageObjectType, String methodType, String compNo) throws Exception;
    
    public String isUseMessageEmp(String messageObjectType, String methodType, String compNo, String empId) throws Exception;
    
    public List getEmp(Map map, User user) throws Exception;
    
    public File getFile(String messageObjectType, User user);
}
