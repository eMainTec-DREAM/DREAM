package dream.consult.program.msg.dto;

import common.bean.BaseDTO;

/**
 * �޽��� ����(����, SMS) ���� DTO
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 *
 */
public class ConsultPgmMsgCommonDTO extends BaseDTO
{
	/** �޼���Ÿ�� id (key) */
	private String msgCategId 				= "";
	
    /** Filter �޽��� �������� ID */
    private String filterMsgObjType 		= "";
    /** Filter �޽��� �������� DESC */
    private String filterMsgObjTypeDesc 	= "";
    /** Filter ���� ��뿩�� ID */
    private String filterIsMailUse 			= "";
    /** Filter ���� ��뿩�� DESC */
    private String filterIsMailUseDesc 		= "";
    /** Filter SMS ��뿩�� ID */
    private String filterIsSmsUse 			= "";
    /** Filter SMS ��뿩�� DESC */
    private String filterIsSmsUseDesc 		= "";
    /** Filter ��뿩�� ID */
    private String filterIsUse 				= "";
    /** Filter ��뿩�� DESC */
    private String filterIsUseDesc 			= "";
    
	public String getFilterMsgObjType() {
		return filterMsgObjType;
	}
	public void setFilterMsgObjType(String filterMsgObjType) {
		this.filterMsgObjType = filterMsgObjType;
	}
	public String getFilterMsgObjTypeDesc() {
		return filterMsgObjTypeDesc;
	}
	public void setFilterMsgObjTypeDesc(String filterMsgObjTypeDesc) {
		this.filterMsgObjTypeDesc = filterMsgObjTypeDesc;
	}
	public String getFilterIsMailUse() {
		return filterIsMailUse;
	}
	public void setFilterIsMailUse(String filterIsMailUse) {
		this.filterIsMailUse = filterIsMailUse;
	}
	public String getFilterIsMailUseDesc() {
		return filterIsMailUseDesc;
	}
	public void setFilterIsMailUseDesc(String filterIsMailUseDesc) {
		this.filterIsMailUseDesc = filterIsMailUseDesc;
	}
	public String getFilterIsSmsUse() {
		return filterIsSmsUse;
	}
	public void setFilterIsSmsUse(String filterIsSmsUse) {
		this.filterIsSmsUse = filterIsSmsUse;
	}
	public String getFilterIsSmsUseDesc() {
		return filterIsSmsUseDesc;
	}
	public void setFilterIsSmsUseDesc(String filterIsSmsUseDesc) {
		this.filterIsSmsUseDesc = filterIsSmsUseDesc;
	}
	public String getFilterIsUse() {
		return filterIsUse;
	}
	public void setFilterIsUse(String filterIsUse) {
		this.filterIsUse = filterIsUse;
	}
	public String getFilterIsUseDesc() {
		return filterIsUseDesc;
	}
	public void setFilterIsUseDesc(String filterIsUseDesc) {
		this.filterIsUseDesc = filterIsUseDesc;
	}
	public String getMsgCategId() {
		return msgCategId;
	}
	public void setMsgCategId(String msgCategId) {
		this.msgCategId = msgCategId;
	}
}