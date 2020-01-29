package dream.consult.program.linkedfunc.dto;

import common.bean.BaseDTO;

/**
 * PgmLinkedFunc Page - Detail DTO
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 *
 */
public class ConsultPgmLinkedFuncDetailDTO extends BaseDTO
{
	/** Linked Function ID*/
    private String linkedFuncId      = "";
    /** 사용여부 ID*/
    private String isUseId           = "";
    /** 사용여부 */ 
    private String isUseDesc         = "";
    /** Linked Function No */ 
    private String linkedFuncNo	     = "";
    /** Linked Function 명 */ 
    private String linkedFuncDesc	 = "";
    /** Linked Function Method */
    private String method            = "";
    /** 비고 */
    private String remark            = "";
    
	public String getLinkedFuncId() {
		return linkedFuncId;
	}
	public void setLinkedFuncId(String linkedFuncId) {
		this.linkedFuncId = linkedFuncId;
	}
	public String getIsUseId() {
		return isUseId;
	}
	public void setIsUseId(String isUseId) {
		this.isUseId = isUseId;
	}
	public String getIsUseDesc() {
		return isUseDesc;
	}
	public void setIsUseDesc(String isUseDesc) {
		this.isUseDesc = isUseDesc;
	}
	public String getLinkedFuncNo() {
		return linkedFuncNo;
	}
	public void setLinkedFuncNo(String linkedFuncNo) {
		this.linkedFuncNo = linkedFuncNo;
	}
	public String getLinkedFuncDesc() {
		return linkedFuncDesc;
	}
	public void setLinkedFuncDesc(String linkedFuncDesc) {
		this.linkedFuncDesc = linkedFuncDesc;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
