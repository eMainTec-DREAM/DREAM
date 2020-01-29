package dream.mgr.msgrec.dto;

import common.bean.BaseDTO;

/**
 * �޽��� ���ż��� List Page - ���� DTO
 * @author youngjoo38 
 * @version $Id$
 * @since 1.0
 *
 */
public class MgrMsgRecCommonDTO extends BaseDTO
{
	/** �޼���Ÿ�� ȸ�缳�� id (key) */
	private String msgCompSetId 			= "";
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
    
	public String getMsgCompSetId() {
		return msgCompSetId;
	}
	public void setMsgCompSetId(String msgCompSetId) {
		this.msgCompSetId = msgCompSetId;
	}
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
}
