package dream.req.qna.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 질의 - 상세  DTO
 * @author  kim21017
 * @version $Id: MaQnaAnsDetailDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class MaQnaAnsDetailDTO extends BaseDTO
{
	/** 답변ID */
	private String answerId 	= "";
	/** 답변No */
	private String answerNo 	= "";
	/** 답변일자 */
	private String regDate 		= "";
	/** 부서id */
	private String deptId 		= "";
	/** 부서명 */	
	private String deptDesc 	= "";
	/** 답변자id */
	private String userId 		= "";
	/** 답변자명 */
	private String userDesc 	= "";
	/**내용 */
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