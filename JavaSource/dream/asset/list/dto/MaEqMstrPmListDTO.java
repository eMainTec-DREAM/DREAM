package dream.asset.list.dto;

import common.bean.BaseDTO;

/**
 * ���� ���� ��� dto
 * @author  kim21017
 * @version $Id: MaEqMstrPmListDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class MaEqMstrPmListDTO extends BaseDTO
{
	/** ���� id */
	private String pmPointId 	= "";
	/** �۾����� id */
	private String woTypeId 	= "";
	/** �۾����� �� */
	private String woTypeDesc 	= "";
	/** �۾����� id */
	private String pmTypeId 	= "";
	/** �۾����� �� */
	private String pmTypeDesc 	= "";
	/** excepted �۾����� id */
    private String exceptedPmTypeId     = "";
	
    
	public String getExceptedPmTypeId()
    {
        return exceptedPmTypeId;
    }

    public void setExceptedPmTypeId(String exceptedPmTypeId)
    {
        this.exceptedPmTypeId = exceptedPmTypeId;
    }

    public String getWoTypeId() {
		return woTypeId;
	}

	public void setWoTypeId(String woTypeId) {
		this.woTypeId = woTypeId;
	}

	public String getWoTypeDesc() {
		return woTypeDesc;
	}

	public void setWoTypeDesc(String woTypeDesc) {
		this.woTypeDesc = woTypeDesc;
	}

	public String getPmTypeId() {
		return pmTypeId;
	}

	public void setPmTypeId(String pmTypeId) {
		this.pmTypeId = pmTypeId;
	}

	public String getPmTypeDesc() {
		return pmTypeDesc;
	}

	public void setPmTypeDesc(String pmTypeDesc) {
		this.pmTypeDesc = pmTypeDesc;
	}

	public String getPmPointId() {
		return pmPointId;
	}

	public void setPmPointId(String pmPointId) {
		this.pmPointId = pmPointId;
		super.setAuditKey(pmPointId);
	}
	
}