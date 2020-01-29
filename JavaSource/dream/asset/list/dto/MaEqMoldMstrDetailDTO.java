package dream.asset.list.dto;

import common.bean.BaseDTO;
import common.bean.MwareConfig;
import common.util.CommonUtil;

/**
 * ���񸶽��� - ���� �� DTO
 * @author  kim21017
 * @version $Id: MaEqMoldMstrDetailDTO.java,v 1.0 2015/12/02 08:44:16 kim21017 Exp $
 * @since   1.0
 *
 */
public class MaEqMoldMstrDetailDTO extends BaseDTO
{
	/** ����ID */
	private String equipId 					= "";
	/** ��������(A)  */
	private String eqMoldAtype 				= "";
	/** ��������(A)  */
	private String eqMoldAtypeDesc 			= "";
	/** ��������(B) */
	private String eqMoldBtype 				= "";
	/** ��������(B) */
	private String eqMoldBtypeDesc 			= "";
	/** ��������(C) */
	private String eqMoldCtype 				= "";
	/** ��������(C) */
	private String eqMoldCtypeDesc 			= "";
	/** �۾��׷� Id */
	private String wkctrId					= "";
	/** �۾��׷� Desc */
	private String wkctrDesc				= "";
	
	private String cavity  			    = MwareConfig.getEmptyFieldValue();
	private String storage  			= MwareConfig.getEmptyFieldValue();
	private String setCnt  				= "";
	private String outPut  				= "";
	private String ownerShip			= MwareConfig.getEmptyFieldValue();
	private String usePeriod  			= "";
	private String isDisUse  			= "";
	private String disUseAmt  			= "";
	private String material  			= MwareConfig.getEmptyFieldValue();
	private String method  				= MwareConfig.getEmptyFieldValue();
	private String structure  			= MwareConfig.getEmptyFieldValue();
	private String stepNo  				= "";
	private String disUseVendor  		= MwareConfig.getEmptyFieldValue();
	private String eqStrLocNo        	= "";
	private String disusedDate        	= "";
	
	public String getDisusedDate() {
		return disusedDate;
	}
	public void setDisusedDate(String disusedDate) {
		this.disusedDate = CommonUtil.convertDate(disusedDate);
	}
	public String getCavity() {
		return cavity;
	}
	public void setCavity(String cavity) {
		this.cavity = cavity;
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
		this.outPut = outPut;
	}
	public String getOwnerShip() {
		return ownerShip;
	}
	public void setOwnerShip(String ownerShip) {
		this.ownerShip = ownerShip;
	}
	public String getUsePeriod() {
		return usePeriod;
	}
	public void setUsePeriod(String usePeriod) {
		this.usePeriod = usePeriod;
	}
	public String getIsDisUse() {
		return isDisUse;
	}
	public void setIsDisUse(String isDisUse) {
		this.isDisUse = isDisUse;
	}
	public String getDisUseAmt() {
		return disUseAmt;
	}
	public void setDisUseAmt(String disUseAmt) {
		this.disUseAmt = CommonUtil.convertMoney(disUseAmt);
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
	public String getStepNo() {
		return stepNo;
	}
	public void setStepNo(String stepNo) {
		this.stepNo = stepNo;
	}
	public String getDisUseVendor() {
		return disUseVendor;
	}
	public void setDisUseVendor(String disUseVendor) {
		this.disUseVendor = disUseVendor;
	}
	public String getEqStrLocNo() {
		return eqStrLocNo;
	}
	public void setEqStrLocNo(String eqStrLocNo) {
		this.eqStrLocNo = eqStrLocNo;
	}
	public String getEqMoldCtype() {
		return eqMoldCtype;
	}
	public void setEqMoldCtype(String eqMoldCtype) {
		this.eqMoldCtype = eqMoldCtype;
	}
	public String getEqMoldCtypeDesc() {
		return eqMoldCtypeDesc;
	}
	public void setEqMoldCtypeDesc(String eqMoldCtypeDesc) {
		this.eqMoldCtypeDesc = eqMoldCtypeDesc;
	}
	public String getWkctrId() {
		return wkctrId;
	}
	public void setWkctrId(String wkctrId) {
		this.wkctrId = wkctrId;
	}
	public String getWkctrDesc() {
		return wkctrDesc;
	}
	public void setWkctrDesc(String wkctrDesc) {
		this.wkctrDesc = wkctrDesc;
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
	public String getEqMoldAtypeDesc() {
		return eqMoldAtypeDesc;
	}
	public void setEqMoldAtypeDesc(String eqMoldAtypeDesc) {
		this.eqMoldAtypeDesc = eqMoldAtypeDesc;
	}
	public String getEqMoldBtype() {
		return eqMoldBtype;
	}
	public void setEqMoldBtype(String eqMoldBtype) {
		this.eqMoldBtype = eqMoldBtype;
	}
	public String getEqMoldBtypeDesc() {
		return eqMoldBtypeDesc;
	}
	public void setEqMoldBtypeDesc(String eqMoldBtypeDesc) {
		this.eqMoldBtypeDesc = eqMoldBtypeDesc;
	}
	
}
