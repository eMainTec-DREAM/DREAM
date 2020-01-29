package dream.asset.list.dto;

import common.bean.BaseDTO;
import common.bean.MwareConfig;
import common.util.CommonUtil;

/**
 * 설비마스터 - 상세 DTO
 * @author  kim21017
 * @version $Id: MaEqMstrDetailDTO.java,v 1.0 2015/12/02 08:44:16 kim21017 Exp $
 * @since   1.0
 *
 */
public class MaEqMstrMoldDetailDTO extends BaseDTO
{
	/** 설비ID */
	private String equipId 				= "";
	private String eqMoldAtype  		= "";
	private String eqMoldBtype  		= "";
	private String eqMoldCtype  		= "";
	private String material  			= MwareConfig.getEmptyFieldValue();
	private String method  				= MwareConfig.getEmptyFieldValue();
	private String structure  			= MwareConfig.getEmptyFieldValue();
	private String storage  			= MwareConfig.getEmptyFieldValue();
	private String setCnt  				= "";
	private String outPut  				= "";
	private String stepNo  				= "";
	private String ownerShip			= MwareConfig.getEmptyFieldValue();
	private String isDisUse  			= "";
	private String disUseDate  			= "";
	private String disUseAmt  			= "";
	private String disUseVendor  		= MwareConfig.getEmptyFieldValue();
	private String usePeriod  			= "";
	private String cavity  			    = MwareConfig.getEmptyFieldValue();
	private String eqMoldAtypeDesc  	= "";
	private String eqMoldBtypeDesc  	= "";
	private String eqMoldCtypeDesc  	= "";
	private String eqStrLocNo        	= "";
	
	
	
	
	public String getEqMoldBtypeDesc()
    {
        return eqMoldBtypeDesc;
    }
    public void setEqMoldBtypeDesc(String eqMoldBtypeDesc)
    {
        this.eqMoldBtypeDesc = eqMoldBtypeDesc;
    }
    public String getEqMoldCtypeDesc()
    {
        return eqMoldCtypeDesc;
    }
    public void setEqMoldCtypeDesc(String eqMoldCtypeDesc)
    {
        this.eqMoldCtypeDesc = eqMoldCtypeDesc;
    }
    public String getEqMoldCtype()
    {
        return eqMoldCtype;
    }
    public void setEqMoldCtype(String eqMoldCtype)
    {
        this.eqMoldCtype = eqMoldCtype;
    }
    public String getEqStrLocNo()
    {
        return eqStrLocNo;
    }
    public void setEqStrLocNo(String eqStrLocNo)
    {
        this.eqStrLocNo = eqStrLocNo;
    }
    public String getStepNo() {
		return stepNo;
	}
	public void setStepNo(String stepNo) {
		this.stepNo = CommonUtil.convertMoney(stepNo);;
	}
	public String getEqMoldAtypeDesc()
    {
        return eqMoldAtypeDesc;
    }
    public void setEqMoldAtypeDesc(String eqMoldAtypeDesc)
    {
        this.eqMoldAtypeDesc = eqMoldAtypeDesc;
    }
    public String getCavity() {
		return cavity;
	}
	public void setCavity(String cavity) {
		this.cavity = cavity;
	}
	public String getOwnerShip() {
		return ownerShip;
	}
	public void setOwnerShip(String ownerShip) {
		this.ownerShip = ownerShip;
	}
	public String getEquipId() {
		return equipId;
	}
	public void setEquipId(String equipId) {
		this.equipId = equipId;
	}
	public String getEqMoldAtype() {
		return eqMoldAtype;
	}
	public void setEqMoldAtype(String eqMoldAtype) {
		this.eqMoldAtype = eqMoldAtype;
	}
	public String getEqMoldBtype() {
		return eqMoldBtype;
	}
	public void setEqMoldBtype(String eqMoldBtype) {
		this.eqMoldBtype = eqMoldBtype;
	}
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		this.material = material;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getStructure() {
		return structure;
	}
	public void setStructure(String structure) {
		this.structure = structure;
	}
	public String getStorage() {
		return storage;
	}
	public void setStorage(String storage) {
		this.storage = storage;
	}
	public String getSetCnt() {
		return setCnt;
	}
	public void setSetCnt(String setCnt) {
		this.setCnt = CommonUtil.convertMoney(setCnt);
	}
	public String getOutPut() {
		return outPut;
	}
	public void setOutPut(String outPut) {
		this.outPut = CommonUtil.convertMoney(outPut);
	}
	public String getIsDisUse() {
		return isDisUse;
	}
	public void setIsDisUse(String isDisUse) {
		this.isDisUse = isDisUse;
	}
	public String getDisUseDate() {
		return disUseDate;
	}
	public void setDisUseDate(String disUseDate) {
		this.disUseDate = CommonUtil.convertDate(disUseDate);
	}
	public String getDisUseAmt() {
		return disUseAmt;
	}
	public void setDisUseAmt(String disUseAmt) {
		this.disUseAmt = CommonUtil.convertMoney(disUseAmt);
	}
	public String getDisUseVendor() {
		return disUseVendor;
	}
	public void setDisUseVendor(String disUseVendor) {
		this.disUseVendor = disUseVendor;
	}
	public String getUsePeriod() {
		return usePeriod;
	}
	public void setUsePeriod(String usePeriod) {
		this.usePeriod = CommonUtil.convertMoney(usePeriod);
	}
	
	




	
}
