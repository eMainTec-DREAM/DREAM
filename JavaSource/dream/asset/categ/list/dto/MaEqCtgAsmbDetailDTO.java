package dream.asset.categ.list.dto;

import common.bean.BaseDTO;

/**
 * 설비종류별 작업부위 상세 dto
 * @author  kim21017
 * @version $Id: MaEqCtgAsmbDetailDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class MaEqCtgAsmbDetailDTO extends BaseDTO
{
	/** 설비종류 id */
	private String eqCtgId		    = "";
	/** 설비종류별 작업부위 id */
	private String eqCtgAsmbId		= "";
	/** 설비종류별 작업부위 명*/
	private String eqCtgAsmbDesc	= "";
	/** 조회순서 */
	private String ordNo			= "";
	/** 부위설명 */
	private String remark			= "";
	/** 사용여부 */
	private String isUse			= "";
	
	/** 부위코드 */
	private String eqCtgAsmbNo		= "";
	/** 상위부위 ID */
	private String peqCtgAsmbId	    = "0";
	/** 상위부위 DESC */
	private String peqCtgAsmbDesc	= "";
	/** (종류별)공통부위여부 */
	private String isEqTypeAsmb	    = "";
	/** full desc */
	private String fullDesc	        = "";
	
    public String getEqCtgId()
    {
        return eqCtgId;
    }
    public void setEqCtgId(String eqCtgId)
    {
        this.eqCtgId = eqCtgId;
    }
    public String getFullDesc()
    {
        return fullDesc;
    }
    public void setFullDesc(String fullDesc)
    {
        this.fullDesc = fullDesc;
    }
    public String getPeqCtgAsmbId()
    {
        return peqCtgAsmbId;
    }
    public void setPeqCtgAsmbId(String peqCtgAsmbId)
    {
        this.peqCtgAsmbId = "".equals(peqCtgAsmbId)?"0":peqCtgAsmbId;
    }
    public String getPeqCtgAsmbDesc()
    {
        return peqCtgAsmbDesc;
    }
    public void setPeqCtgAsmbDesc(String peqCtgAsmbDesc)
    {
        this.peqCtgAsmbDesc = peqCtgAsmbDesc;
    }
    public String getIsEqTypeAsmb()
    {
        return isEqTypeAsmb;
    }
    public void setIsEqTypeAsmb(String isEqTypeAsmb)
    {
        this.isEqTypeAsmb = isEqTypeAsmb;
    }
    public String getEqCtgAsmbNo()
    {
        return eqCtgAsmbNo;
    }
    public void setEqCtgAsmbNo(String eqCtgAsmbNo)
    {
        this.eqCtgAsmbNo = eqCtgAsmbNo;
    }
    public String getEqCtgAsmbId() {
		return eqCtgAsmbId;
	}
	public void setEqCtgAsmbId(String eqCtgAsmbId) {
		this.eqCtgAsmbId = eqCtgAsmbId;
		super.setAuditKey(eqCtgAsmbId);
	}
	public String getEqCtgAsmbDesc() {
		return eqCtgAsmbDesc;
	}
	public void setEqCtgAsmbDesc(String eqCtgAsmbDesc) {
		this.eqCtgAsmbDesc = eqCtgAsmbDesc;
	}
	public String getOrdNo() {
		return ordNo;
	}
	public void setOrdNo(String ordNo) {
		this.ordNo = ordNo;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getIsUse() {
		return isUse;
	}
	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}
	
}