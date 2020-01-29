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
	/**Key �������μ� ID */ 
    private String notiDeptId		= "";    
    
    /**���� ID */ 
    private String noticeId         = "";    
    /**�μ� ID */ 
    private String deptId         	= "";    
    /**�μ� NO */ 
    private String deptNo         	= "";    
    /**�μ� DESC */ 
    private String deptDesc         = "";    
    /**��� */ 
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
