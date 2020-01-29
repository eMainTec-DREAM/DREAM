package dream.req.qna.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * ���� - ��  DTO
 * @author  kim21017
 * @version $Id: MaQnaAnsDetailDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class MaQnaAnsDetailDTO extends BaseDTO
{
	/** �亯ID */
	private String answerId 	= "";
	/** �亯No */
	private String answerNo 	= "";
	/** �亯���� */
	private String regDate 		= "";
	/** �μ�id */
	private String deptId 		= "";
	/** �μ��� */	
	private String deptDesc 	= "";
	/** �亯��id */
	private String userId 		= "";
	/** �亯�ڸ� */
	private String userDesc 	= "";
	/**���� */
	private String answerDesc 	= "";
	
	public String getAnswerId() {
		return answerId;
	}
	public void setAnswerId(String answerId) {
		this.answerId = answerId;
		super.setAuditKey(answerId);
	}
	public String getAnswerNo() {
		return answerNo;
	}
	public void setAnswerNo(String answerNo) {
		this.answerNo = answerNo;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = CommonUtil.convertDate(regDate);
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getDeptDesc() {
		return deptDesc;
	}
	public void setDeptDesc(String deptDesc) {
		this.deptDesc = deptDesc;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserDesc() {
		return userDesc;
	}
	public void setUserDesc(String userDesc) {
		this.userDesc = userDesc;
	}
	public String getAnswerDesc() {
		return answerDesc;
	}
	public void setAnswerDesc(String answerDesc) {
		this.answerDesc = answerDesc;
	}
}