package mobile.dream.asset.list.dto;

import common.bean.BaseDTO;

/**
 * ���� �˾� DTO
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public class AssetListLovListDTO extends BaseDTO
{
    /** Search Code */
    private String itemNo       = "";
    /** Search Description */
    private String equipDesc    = "";
    /** ��ġid */
    private String eqLocId      = "";
    /** ��ġ�� */
    private String eqLocDesc    = "";
    /** ����id */
    private String eqCtgId      = "";
    /** ������ */
    private String eqCtgDesc    = "";
    /** �������񿩺� */
    private String isLawEq      = "";
    /** ��/���� ���� */
    private String plfTypeId    = "";
    /** ��/���� ���� */
    private String plfTypeDesc  = "";
	/** ������(��) id */
	private String mainMngId 	= "";
	/** ������(��) �� */
	private String mainMngName 	= "";
	/** ������(��) id */
	private String subMngId 	= "";
	/** ������(��) �� */
	private String subMngName 	= "";
    /** extCode1 */
    private String extCode1 	= "";
    /** extCode2 */
    private String extCode2 	= "";
    /** code type */
    private String codeType 	= "";
    /** �μ� */
    private String deptId 		= "";
    /** �μ��� */
    private String deptDesc		= "";
    private String eqStatusDesc		= "";
    private String eqStatusId		= "";
    
    
    
	public String getEqStatusDesc() {
		return eqStatusDesc;
	}
	public void setEqStatusDesc(String eqStatusDesc) {
		this.eqStatusDesc = eqStatusDesc;
	}
	public String getEqStatusId() {
		return eqStatusId;
	}
	public void setEqStatusId(String eqStatusId) {
		this.eqStatusId = eqStatusId;
	}
	public String getDeptDesc() {
		return deptDesc;
	}
	public void setDeptDesc(String deptDesc) {
		this.deptDesc = deptDesc;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getEqLocId() {
		return eqLocId;
	}
	public void setEqLocId(String eqLocId) {
		this.eqLocId = eqLocId;
	}
	public String getEqLocDesc() {
		return eqLocDesc;
	}
	public void setEqLocDesc(String eqLocDesc) {
		this.eqLocDesc = eqLocDesc;
	}
	public String getEqCtgId() {
		return eqCtgId;
	}
	public void setEqCtgId(String eqCtgId) {
		this.eqCtgId = eqCtgId;
	}
	public String getEqCtgDesc() {
		return eqCtgDesc;
	}
	public void setEqCtgDesc(String eqCtgDesc) {
		this.eqCtgDesc = eqCtgDesc;
	}
	public String getIsLawEq() {
		return isLawEq;
	}
	public void setIsLawEq(String isLawEq) {
		this.isLawEq = isLawEq;
	}
	public String getPlfTypeId() {
		return plfTypeId;
	}
	public void setPlfTypeId(String plfTypeId) {
		this.plfTypeId = plfTypeId;
	}
	public String getPlfTypeDesc() {
		return plfTypeDesc;
	}
	public void setPlfTypeDesc(String plfTypeDesc) {
		this.plfTypeDesc = plfTypeDesc;
	}
	public String getMainMngId() {
		return mainMngId;
	}
	public void setMainMngId(String mainMngId) {
		this.mainMngId = mainMngId;
	}
	public String getMainMngName() {
		return mainMngName;
	}
	public void setMainMngName(String mainMngName) {
		this.mainMngName = mainMngName;
	}
	public String getSubMngId() {
		return subMngId;
	}
	public void setSubMngId(String subMngId) {
		this.subMngId = subMngId;
	}
	public String getSubMngName() {
		return subMngName;
	}
	public void setSubMngName(String subMngName) {
		this.subMngName = subMngName;
	}
	public String getItemNo() {
		return itemNo;
	}
	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}
	public String getEquipDesc() {
		return equipDesc;
	}
	public void setEquipDesc(String equipDesc) {
		this.equipDesc = equipDesc;
	}
	public String getExtCode1() {
		return extCode1;
	}
	public void setExtCode1(String extCode1) {
		this.extCode1 = extCode1;
	}
	public String getExtCode2() {
		return extCode2;
	}
	public void setExtCode2(String extCode2) {
		this.extCode2 = extCode2;
	}
	public String getCodeType() {
		return codeType;
	}
	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}
    

}
