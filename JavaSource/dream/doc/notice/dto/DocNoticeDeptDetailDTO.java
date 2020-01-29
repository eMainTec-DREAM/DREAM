package dream.doc.notice.dto;

import common.bean.BaseDTO;

/**
 * DocNoticeDept - Detail DTO
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 *
 */
public class DocNoticeDeptDetailDTO extends BaseDTO
{ 
	/**Key 공지대상부서 ID */ 
    private String notiDeptId		= "";    
    
    /**공지 ID */ 
    private String noticeId         = "";    
    /**부서 ID */ 
    private String deptId         	= "";    
    /**부서 NO */ 
    private String deptNo         	= "";    
    /**부서 DESC */ 
    private String deptDesc         = "";    
    /**비고 */ 
    private String remark  	        = "";
    
    
	public String getDeptNo() {
		return deptNo;
	}
	public void setDeptNo(String deptNo) {
		this.deptNo = deptNo;
	}
	public String getNotiDeptId() {
		return notiDeptId;
	}
	public void setNotiDeptId(String notiDeptId) {
		this.notiDeptId = notiDeptId;
		super.setAuditKey(notiDeptId);
	}
	public String getNoticeId() {
		return noticeId;
	}
	public void setNoticeId(String noticeId) {
		this.noticeId = noticeId;
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}    
}
