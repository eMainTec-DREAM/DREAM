package dream.asset.list.dto;

import common.bean.BaseDTO;

/**
 * 설비 점검 목록 dto
 * @author  kim21017
 * @version $Id: MaEqMstrPmListDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class MaEqMstrPmListDTO extends BaseDTO
{
	/** 점검 id */
	private String pmPointId 	= "";
	/** 작업종류 id */
	private String woTypeId 	= "";
	/** 작업종류 명 */
	private String woTypeDesc 	= "";
	/** 작업형태 id */
	private String pmTypeId 	= "";
	/** 작업형태 명 */
	private String pmTypeDesc 	= "";
	/** excepted 작업형태 id */
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