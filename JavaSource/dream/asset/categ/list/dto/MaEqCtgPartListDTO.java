package dream.asset.categ.list.dto;

import common.bean.BaseDTO;

/**
 * 설비종류별 부품 목록 dto
 * @author  kim21017
 * @version $Id: MaEqCtgPartListDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class MaEqCtgPartListDTO extends BaseDTO
{
	/** 설비종류별 부품 id */
	private String eqCtgPartId 	= "";

	/** (종류별)공통부위여부 */
    private String isEqTypePart = "";
    
	public String getEqCtgPartId() {
		return eqCtgPartId;
	}

	public String getIsEqTypePart()
    {
        return isEqTypePart;
    }

    public void setIsEqTypePart(String isEqTypePart)
    {
        this.isEqTypePart = isEqTypePart;
    }

    public void setEqCtgPartId(String eqCtgPartId) {
		this.eqCtgPartId = eqCtgPartId;
	}
	
	
}