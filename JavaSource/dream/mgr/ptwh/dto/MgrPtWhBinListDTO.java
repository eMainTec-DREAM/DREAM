package dream.mgr.ptwh.dto;

import common.bean.BaseDTO;

/**
 * ��ǰâ�� ������ġ - List DTO
 * 
 * @author cjscjs9
 * @version $Id:$
 * @since 1.0
 */
public class MgrPtWhBinListDTO extends BaseDTO
{
	/** â�� ID */
	private String wcodeId		= "";
    /** â�� ����� ID */
    private String ptBinListId		= "";
    
	public String getWcodeId() {
		return wcodeId;
	}
	public void setWcodeId(String wcodeId) {
		this.wcodeId = wcodeId;
	}
	public String getPtBinListId() {
		return ptBinListId;
	}
	public void setPtBinListId(String ptBinListId) {
		this.ptBinListId = ptBinListId;
		super.setAuditKey(ptBinListId);
	}
}
