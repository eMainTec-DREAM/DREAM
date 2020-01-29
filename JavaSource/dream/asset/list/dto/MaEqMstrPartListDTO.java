package dream.asset.list.dto;

import common.bean.BaseDTO;

/**
 * 설비 구성자재 목록 dto
 * @author  kim21017
 * @version $Id: MaEqMstrPartListDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class MaEqMstrPartListDTO extends BaseDTO
{
	/** 구성자재 id */
	private String eqPartId 	= "";
	/** 설비종류별자재ID */
    private String eqCtgPartId      = "";
    /** 설비종류별자재ID(일괄등록) */
    private String eqCtgPartIds     = "";
    /** 설비종류별자재명(일괄등록) */
    private String eqCtgPartDescs   = "";

	public String getEqCtgPartId()
    {
        return eqCtgPartId;
    }

    public void setEqCtgPartId(String eqCtgPartId)
    {
        this.eqCtgPartId = eqCtgPartId;
    }

    public String getEqPartId() {
		return eqPartId;
	}

	public void setEqPartId(String eqPartId) {
		this.eqPartId = eqPartId;
		super.setAuditKey(eqPartId);
	}

    public String getEqCtgPartIds()
    {
        return eqCtgPartIds;
    }

    public void setEqCtgPartIds(String eqCtgPartIds)
    {
        this.eqCtgPartIds = eqCtgPartIds;
    }

    public String getEqCtgPartDescs()
    {
        return eqCtgPartDescs;
    }

    public void setEqCtgPartDescs(String eqCtgPartDescs)
    {
        this.eqCtgPartDescs = eqCtgPartDescs;
    }
	
}