package dream.req.qna.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 질의 - 상세 DTO
 * @author  kim21017
 * @version $Id: MaQnaDetailDTO.java,v 1.0 2015/12/02 08:44:16 kim21017 Exp $
 * @since   1.0
 *
 */
public class MaQnaDetailDTO extends BaseDTO
{
	/** 질의ID */
	private String questionId 					= "";
	/** 질의No */
	private String questionNo 					= "";
	/** 작성일자 */
	private String regDate 						= "";
	/** 부서id */
	private String deptId 						= "";
	/** 부서명 */	
	private String deptDesc 					= "";
	/** 작성자id */
	private String userId 						= "";
	/** 작성자명 */
	private String userDesc 					= "";
	/** 제목 */
	private String questionTitle 				= "";
	/** 내용 */
	private String questionDesc 				= "";
	public String getQuestionId() {
		return questionId;
	}
	public void setQuestionId(String questionId) {
		this.questionId = questionId;
		super.setAuditKey(questionId);
	}
	public String getQuestionNo() {
		return questionNo;
	}
	public void setQuestionNo(String questionNo) {
		this.questionNo = questionNo;
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
	public String getQuestionTitle() {
		return questionTitle;
	}
	public void setQuestionTitle(String questionTitle) {
		this.questionTitle = questionTitle;
	}
	public String getQuestionDesc() {
		return questionDesc;
	}
	public void setQuestionDesc(String questionDesc) {
		this.questionDesc = questionDesc;
	}
}
