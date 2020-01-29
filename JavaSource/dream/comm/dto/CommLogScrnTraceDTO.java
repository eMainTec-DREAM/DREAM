package dream.comm.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * �� DTO
 * @author  jung7126
 * @version $Id: CommRevPmDTO.java,v 1.0 2015/12/02 08:44:16 jung7126 Exp $
 * @since   1.0
 *
 */
public class CommLogScrnTraceDTO extends BaseDTO
{
    
	private String scrnTraceLogId = "";
	private String updDate = "";
	/** �Լ��� Ÿ�� */
    private String crudType = "";
    /** ������ Name */
    private String pageName = "";
    /** System key id */
    private String objectId = "";
    /** System key id */
    private String objectNo = "";
    /** ȭ�鼳�� */
    private String description = "";
    /** html ����Ÿ */
    private String contents = "";
    
    
    
    
    
	public String getScrnTraceLogId() {
		return scrnTraceLogId;
	}
	public void setScrnTraceLogId(String scrnTraceLogId) {
		this.scrnTraceLogId = scrnTraceLogId;
	}
	public String getUpdDate() {
		return updDate;
	}
	public void setUpdDate(String updDate) {
		this.updDate = updDate;
	}
	public String getCrudType() {
		return crudType;
	}
	public void setCrudType(String crudType) {
		this.crudType = crudType;
	}
	public String getPageName() {
		return pageName;
	}
	public void setPageName(String pageName) {
		this.pageName = pageName;
	}
	public String getObjectId() {
		return objectId;
	}
	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}
	public String getObjectNo() {
		return objectNo;
	}
	public void setObjectNo(String objectNo) {
		this.objectNo = objectNo;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
    
    
        
        
    
}
