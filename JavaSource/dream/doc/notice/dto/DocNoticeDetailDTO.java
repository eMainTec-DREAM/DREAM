package dream.doc.notice.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * DocNotice Page - Detail DTO
 * @author youngjoo38
 * @version $Id: DocNoticeDetailDTO.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 *
 */
public class DocNoticeDetailDTO extends BaseDTO
{
    /**Key 공지 ID */ 
    private String noticeId            	= "";    
    
    /**공지 No */ 
    private String noticeNo            	= "";
    /**상태Id*/
    private String noticeStatusId	    = "";    
    /**상태Desc*/
    private String noticeStatus	       	= "";    
    /**제목 */
    private String title                = "";
    /**공지기한일자 */ 
    private String noticePeriod			= "";
    /**공지일자*/
    private String noticeDate			= "";    
    /**작성부서Id*/
    private String regDeptId            = "";    
    /**작성부서*/
    private String regDept              = "";    
    /**작성자 Id*/
    private String writeById		    = "";   
    /**작성자*/
    private String writeBy		        = "";   
    /**내용*/
    private String remark        		= "";
    
    
	public String getRegDeptId() {
		return regDeptId;
	}
	public void setRegDeptId(String regDeptId) {
		this.regDeptId = regDeptId;
	}
	public String getWriteById() {
		return writeById;
	}
	public void setWriteById(String writeById) {
		this.writeById = writeById;
	}
	public String getNoticeStatusId() {
		return noticeStatusId;
	}
	public void setNoticeStatusId(String noticeStatusId) {
		this.noticeStatusId = noticeStatusId;
	}
	public String getNoticeId() {
		return noticeId;
	}
	public void setNoticeId(String noticeId) {
		this.noticeId = noticeId;
		super.setAuditKey(noticeId);
	}
	public String getNoticeNo() {
		return noticeNo;
	}
	public void setNoticeNo(String noticeNo) {
		this.noticeNo = noticeNo;
	}
	public String getNoticeStatus() {
		return noticeStatus;
	}
	public void setNoticeStatus(String noticeStatus) {
		this.noticeStatus = noticeStatus;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getNoticePeriod() {
		return noticePeriod;
	}
	public void setNoticePeriod(String noticePeriod) {
		this.noticePeriod = CommonUtil.convertDate(noticePeriod);
	}
	public String getNoticeDate() {
		return noticeDate;
	}
	public void setNoticeDate(String noticeDate) {
		this.noticeDate = CommonUtil.convertDate(noticeDate);
	}
	public String getRegDept() {
		return regDept;
	}
	public void setRegDept(String regDept) {
		this.regDept = regDept;
	}
	public String getWriteBy() {
		return writeBy;
	}
	public void setWriteBy(String writeBy) {
		this.writeBy = writeBy;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}   
    
    
}
