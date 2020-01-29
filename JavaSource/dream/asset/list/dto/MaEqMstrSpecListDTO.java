package dream.asset.list.dto;

import common.bean.BaseDTO;

/**
 * 설비제원(스펙) 목록 dto
 * @author  kim21017
 * @version $Id: MaEqMstrSpecListDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class MaEqMstrSpecListDTO extends BaseDTO
{
	/** 제원 id */
	private String eqSpecId 	= "";
	/** 종류별 제원 id */
	private String eqCtgSpecId 	= "";

	public String getEqCtgSpecId()
    {
        return eqCtgSpecId;
    }

    public void setEqCtgSpecId(String eqCtgSpecId)
    {
        this.eqCtgSpecId = eqCtgSpecId;
    }

    public String getEqSpecId() {
		return eqSpecId;
	}

	public void setEqSpecId(String eqSpecId) {
		this.eqSpecId = eqSpecId;
		super.setAuditKey(eqSpecId);
	}
	
}