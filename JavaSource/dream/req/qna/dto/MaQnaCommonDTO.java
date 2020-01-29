package dream.req.qna.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 질의 공통 DTO
 * @author  kim21017
 * @version $Id: MaQnaCommonDTO.java,v 1.0 2015/12/02 09:13:08 kim21017 Exp $
 * @since   1.0
 * 
 */
public class MaQnaCommonDTO extends BaseDTO
{
	/** 징의 id */
	private String questionId 				= "";
	/** 필터 질의번호 */
	private String filterQuestionNo			= "";
	/** 필터 시작 작성일자 */
	private String filterStartRegDate		= "";
	/** 필터 끝 작성일자 */
	private String filterEndRegDate			= "";
	/** 필터 제목 */
	private String filterQuestionTitle		= "";
	/** 필터 작성자id  */
	private String filterUserId				= "";
	/** 필터 작성자명 */
	private String filterUserDesc			= "";
	/** 필터 부서id  */
	private String filterDeptId				= "";
	/** 필터 부서명 */
	private String filterDeptDesc			= "";
	
	public String getQuestionId() {
		return questionId;
	}
	public void setQuestionId(String questionId) {
		this.questionId = questionId;
		super.setAuditKey(questionId);
	}
	public String getFilterQuestionNo() {
		return filterQuestionNo;
	}
	public void setFilterQuestionNo(String filterQuestionNo) {
		this.filterQuestionNo = filterQuestionNo;
	}
	public String getFilterStartRegDate() {
		return filterStartRegDate;
	}
	public void setFilterStartRegDate(String filterStartRegDate) {
		this.filterStartRegDate = CommonUtil.convertDate(filterStartRegDate);
	}
	public String getFilterEndRegDate() {
		return filterEndRegDate;
	}
	public void setFilterEndRegDate(String filterEndRegDate) {
		this.filterEndRegDate = CommonUtil.convertDate(filterEndRegDate);
	}
	public String getFilterQuestionTitle() {
		return filterQuestionTitle;
	}
	public void setFilterQuestionTitle(String filterQuestionTitle) {
		this.filterQuestionTitle = filterQuestionTitle;
	}
	public String getFilterUserId() {
		return filterUserId;
	}
	public void setFilterUserId(String filterUserId) {
		this.filterUserId = filterUserId;
	}
	public String getFilterUserDesc() {
		return filterUserDesc;
	}
	public void setFilterUserDesc(String filterUserDesc) {
		this.filterUserDesc = filterUserDesc;
	}
	public String getFilterDeptId() {
		return filterDeptId;
	}
	public void setFilterDeptId(String filterDeptId) {
		this.filterDeptId = filterDeptId;
	}
	public String getFilterDeptDesc() {
		return filterDeptDesc;
	}
	public void setFilterDeptDesc(String filterDeptDesc) {
		this.filterDeptDesc = filterDeptDesc;
	}
}
