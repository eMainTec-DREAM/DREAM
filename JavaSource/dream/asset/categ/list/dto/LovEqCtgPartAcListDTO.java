package dream.asset.categ.list.dto;

import common.bean.BaseDTO;

/**
 * ������������ǰ �˾� DTO
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public class LovEqCtgPartAcListDTO extends BaseDTO
{
    /**   ��ǰ ID */
    private String partId            = "";
    /**   ��ǰ �ڵ� */
    private String partNo            = "";
    /**   ����ǰ��� */
    private String partNameSize      = "";
    /**   �������� ID */
    private String eqCtgId           = "";
    /**   ������� ID */
    private String eqCtgAsmbId       = "";
    /**   ��������� */
    private String eqCtgAsmbDesc     = "";
    /**   ��뿩�� */
    private String isUse             = "";
	/** PM ID */
	private String pmId				= "";
    
	
	public String getPmId() {
		return pmId;
	}
	public void setPmId(String pmId) {
		this.pmId = pmId;
	}
	public String getPartId() {
		return partId;
	}
	public void setPartId(String partId) {
		this.partId = partId;
	}
	public String getPartNo() {
		return partNo;
	}
	public void setPartNo(String partNo) {
		this.partNo = partNo;
	}
	public String getPartNameSize() {
		return partNameSize;
	}
	public void setPartNameSize(String partNameSize) {
		this.partNameSize = partNameSize;
	}
	public String getEqCtgId() {
		return eqCtgId;
	}
	public void setEqCtgId(String eqCtgId) {
		this.eqCtgId = eqCtgId;
	}
	public String getIsUse() {
		return isUse;
	}
	public String getEqCtgAsmbId() {
		return eqCtgAsmbId;
	}
	public void setEqCtgAsmbId(String eqCtgAsmbId) {
		this.eqCtgAsmbId = eqCtgAsmbId;
	}
	public String getEqCtgAsmbDesc() {
		return eqCtgAsmbDesc;
	}
	public void setEqCtgAsmbDesc(String eqCtgAsmbDesc) {
		this.eqCtgAsmbDesc = eqCtgAsmbDesc;
	}
	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}
}
