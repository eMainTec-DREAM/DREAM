package dream.part.list.dto;

import common.bean.BaseDTO;

/**
 * 부품창고 보관위치 - List DTO
 * 
 * @author cjscjs9
 * @version $Id:$
 * @since 1.0
 */
public class PartListBinListDTO extends BaseDTO
{
	/** 창고 ID */
	private String wcodeId			= "";
    /** 창고 담당자 ID */
    private String ptBinListId		= "";
    /** 창고 담당자 ID */
    private String ptwhBinNoId		= "";
    /** part ID */
    private String partId			= "";
    /** 재고등급 */
    private String partGrade		= "";
    
    
    
	public String getPartGrade() {
		return partGrade;
	}
	public void setPartGrade(String partGrade) {
		this.partGrade = partGrade;
	}
	public String getPartId() {
		return partId;
	}
	public void setPartId(String partId) {
		this.partId = partId;
	}
	public String getPtwhBinNoId() {
		return ptwhBinNoId;
	}
	public void setPtwhBinNoId(String ptwhBinNoId) {
		this.ptwhBinNoId = ptwhBinNoId;
	}
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
