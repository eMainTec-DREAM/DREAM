package dream.asset.list.dto;

import common.bean.BaseDTO;

/**
 * ������� ��� dto
 * @author  kim21017
 * @version $Id: MaEqMstrAsmbListDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class MaEqMstrAsmbListDTO extends BaseDTO
{
	/** ���� id */
	private String eqAsmbId 	= "";
    /** PM ID */
    private String pmId = "";
    /** ���������� id */
    private String eqCtgAsmbId 	= "";

	public String getEqCtgAsmbId()
    {
        return eqCtgAsmbId;
    }
    public void setEqCtgAsmbId(String eqCtgAsmbId)
    {
        this.eqCtgAsmbId = eqCtgAsmbId;
    }
    public String getEqAsmbId() {
		return eqAsmbId;
	}
	public void setEqAsmbId(String eqAsmbId) {
		this.eqAsmbId = eqAsmbId;
		super.setAuditKey(eqAsmbId);
	}
	public String getPmId() {
		return pmId;
	}
	public void setPmId(String pmId) {
		this.pmId = pmId;
	}
	
}