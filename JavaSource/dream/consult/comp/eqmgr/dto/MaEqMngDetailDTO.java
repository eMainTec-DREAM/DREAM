package dream.consult.comp.eqmgr.dto;

import java.util.List;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 설비담당자변경 - 상세 DTO
 * @author  kim21017
 * @version $Id: MaEqMngDetailDTO.java,v 1.0 2015/12/02 08:44:16 kim21017 Exp $
 * @since   1.0
 *
 */
public class MaEqMngDetailDTO extends BaseDTO
{
	/** 설비ID */
	private String equipId 				= "";
	/** 설비번호 */
	private String itemNo 				= "";
	/** 설비명 */
	private String equipDesc 			= "";
	/** 위치ID */
	private String eqLocId 				= "";
	/** 위치명 */
	private String eqLocDesc 			= "";
	/** 종류ID */
	private String eqCtgId 				= "";
	/** 종류명 */
	private String eqCtgDesc 			= "";
	/** 제작사 */
	private String maker 				= "";
	/** 모델 */
	private String modelNo 				= "";
	/** 설비상태ID */
	private String eqStatusId 			= "";
	/** 설비상태명 */
	private String eqStatusDesc 		= "";
	/** 설치일자 */
	private String setupDate 			= "";
	/** 기계능력 */
	private String capacity 			= "";
	/** 사용Utility */
	private String utilCapa 			= "";
	/** 내/외자 id */
	private String plfTypeId 			= "";
	/** 내/외자 명 */
	private String plfTypeDesc 			= "";
	/** 구매금액 */
	private String buyAmt 				= "";
	/** 부서id */
	private String deptId 				= "";
	/** 부서명 */
	private String deptDesc 			= "";
	/** 관리자(정) id */
	private String mainMngId 			= "";
	/** 관리자(정) 명 */
	private String mainMngName 			= "";
	/** 관리자(부) id */
	private String subMngId 			= "";
	/** 관리자(부) 명 */
	private String subMngName 			= "";
	
	/** 새로운 관리자(정) id */
	private String newMainMngId 		= "";
	/** 새로운 관리자(정) 명 */
	private String newMainMngName 		= "";
	/** 새로운 관리자(부) id */
	private String newSubMngId 			= "";
	/** 새로운 관리자(부) 명 */
	private String newSubMngName 		= "";
	
	
	/** 법정설비여부 */
	private String isLawEq 				= "";
	/** 정렬순서 */
	private String ordNo 				= "";
	/** 특이사항 */
	private String remark 				= "";
	
	private List slideFileList         = null;

	
	public String getNewMainMngId() {
		return newMainMngId;
	}

	public void setNewMainMngId(String newMainMngId) {
		this.newMainMngId = newMainMngId;
	}

	public String getNewMainMngName() {
		return newMainMngName;
	}

	public void setNewMainMngName(String newMainMngName) {
		this.newMainMngName = newMainMngName;
	}

	public String getNewSubMngId() {
		return newSubMngId;
	}

	public void setNewSubMngId(String newSubMngId) {
		this.newSubMngId = newSubMngId;
	}

	public String getNewSubMngName() {
		return newSubMngName;
	}

	public void setNewSubMngName(String newSubMngName) {
		this.newSubMngName = newSubMngName;
	}

	public List getSlideFileList()
    {
        return slideFileList;
    }

    public void setSlideFileList(List slideFileList)
    {
        this.slideFileList = slideFileList;
    }

    public String getItemNo() {
		return itemNo;
	}

	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}

	public String getEquipId() {
		return equipId;
	}

	public void setEquipId(String equipId) {
		this.equipId = equipId;
	}

	public String getEquipDesc() {
		return equipDesc;
	}

	public void setEquipDesc(String equipDesc) {
		this.equipDesc = equipDesc;
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

	public String getMaker() {
		return maker;
	}

	public void setMaker(String maker) {
		this.maker = maker;
	}

	public String getModelNo() {
		return modelNo;
	}

	public void setModelNo(String modelNo) {
		this.modelNo = modelNo;
	}

	public String getEqStatusId() {
		return eqStatusId;
	}

	public void setEqStatusId(String eqStatusId) {
		this.eqStatusId = eqStatusId;
	}

	public String getEqStatusDesc() {
		return eqStatusDesc;
	}

	public void setEqStatusDesc(String eqStatusDesc) {
		this.eqStatusDesc = eqStatusDesc;
	}

	public String getSetupDate() {
		return setupDate;
	}

	public void setSetupDate(String setupDate) {
		this.setupDate = CommonUtil.convertDate(setupDate);
	}

	public String getCapacity() {
		return capacity;
	}

	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}

	public String getUtilCapa() {
		return utilCapa;
	}

	public void setUtilCapa(String utilCapa) {
		this.utilCapa = utilCapa;
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

	public String getBuyAmt() {
		return buyAmt;
	}

	public void setBuyAmt(String buyAmt) {
		this.buyAmt = CommonUtil.convertMoney(buyAmt);
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getDeptDesc() {
		return deptDesc;
	}

	public void setDeptDesc(String deptDesc) {
		this.deptDesc = deptDesc;
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

	public String getIsLawEq() {
		return isLawEq;
	}

	public void setIsLawEq(String isLawEq) {
		this.isLawEq = isLawEq;
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
	
}
