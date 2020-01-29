package dream.asset.rpt.eqpartprecon.dto;

import common.bean.BaseDTO;

/**
 * AssetRptEqPartPreCon Page - detail DTO
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 *
 */
public class AssetRptEqPartPreConDetailDTO extends BaseDTO
{
    /** ��ǰ ID */ 
    private String partId                    = "";
    /** ��ǰ DESC */ 
    private String partDesc                  = "";
    /** ���� ID */ 
    private String equipId                   = "";
    /** ���� DESC */ 
    private String equipDesc                 = "";
    /** ���� ID */ 
    private String plantId                   = "";
    /** ��ǰ�߿䵵 */ 
    private String partGrade                 = "";

    
	public String getPartDesc() {
		return partDesc;
	}

	public void setPartDesc(String partDesc) {
		this.partDesc = partDesc;
	}

	public String getEquipDesc() {
		return equipDesc;
	}

	public void setEquipDesc(String equipDesc) {
		this.equipDesc = equipDesc;
	}

	public String getPartGrade() {
		return partGrade;
	}

	public void setPartGrade(String partGrade) {
		this.partGrade = partGrade;
	}

	public String getPlantId() {
		return plantId;
	}

	public void setPlantId(String plantId) {
		this.plantId = plantId;
	}

	public String getPartId() {
		return partId;
	}

	public void setPartId(String partId) {
		this.partId = partId;
	}

	public String getEquipId() {
		return equipId;
	}

	public void setEquipId(String equipId) {
		this.equipId = equipId;
	}
    
}
