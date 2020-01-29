package dream.asset.list.dto;

import common.bean.BaseDTO;

/**
 * ���� �������� ��� dto
 * @author  kim21017
 * @version $Id: MaEqMstrPartListDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class MaEqMstrPartListDTO extends BaseDTO
{
	/** �������� id */
	private String eqPartId 	= "";
	/** ��������������ID */
    private String eqCtgPartId      = "";
    /** ��������������ID(�ϰ����) */
    private String eqCtgPartIds     = "";
    /** ���������������(�ϰ����) */
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