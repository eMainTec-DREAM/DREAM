package dream.mgr.ptwh.dto;

import common.bean.BaseDTO;

/**
 * 부품창고 보관위치 - List DTO
 * 
 * @author cjscjs9
 * @version $Id:$
 * @since 1.0
 */
public class MgrPtWhBinListDTO extends BaseDTO
{
	/** 창고 ID */
	private String wcodeId		= "";
    /** 창고 담당자 ID */
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
