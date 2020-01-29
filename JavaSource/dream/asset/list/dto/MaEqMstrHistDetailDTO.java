package dream.asset.list.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 설비변경이력 - 상세 DTO
 * @author  kim21017
 * @version $Id: MaEqMstrHistDetailDTO.java,v 1.0 2015/12/02 08:44:16 kim21017 Exp $
 * @since   1.0
 *
 */
public class MaEqMstrHistDetailDTO extends BaseDTO
{
	private String eqalthistId			= "";
	/** 설비ID */
	private String equipId 				= "";
	/** 설비번호 */
	private String itemNo 				= "";
	/** 설비명 */
	private String equipDesc 			= "";
	/** 위치명 */
	private String eqLocDesc 			= "";
	/** 종류명 */
	private String eqCtgDesc 			= "";
	/** 설비상태명 */
	private String eqStatusDesc 		= "";
	/** 설치일자 */
	private String setupDate 			= "";
	/** 기계능력 */
	private String capacity 			= "";
	/** 사용Utility */
	private String utilCapa 			= "";
	/** 내/외자 명 */
	private String plfTypeDesc 			= "";
	/** 구매금액 */
	private String buyAmt 				= "";
	/** 부서명 */
	private String deptDesc 			= "";
	/** 관리자(정) 명 */
	private String mainMngName 			= "";
	/** 관리자(부) 명 */
	private String subMngName 			= "";
	/** 법정설비여부 */
	private String isLawEq 				= "";
	
	
	public String getEqalthistId() {
		return eqalthistId;
	}
	public void setEqalthistId(String eqalthistId) {
		this.eqalthistId = eqalthistId;
	}
	public String getEquipId() {
		return equipId;
	}
	public void setEquipId(String equipId) {
		this.equipId = equipId;
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
	public String getEqLocDesc() {
		return eqLocDesc;
	}
	public void setEqLocDesc(String eqLocDesc) {
		this.eqLocDesc = eqLocDesc;
	}
	public String getEqCtgDesc() {
		return eqCtgDesc;
	}
	public void setEqCtgDesc(String eqCtgDesc) {
		this.eqCtgDesc = eqCtgDesc;
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
	public String getDeptDesc() {
		return deptDesc;
	}
	public void setDeptDesc(String deptDesc) {
		this.deptDesc = deptDesc;
	}
	public String getMainMngName() {
		return mainMngName;
	}
	public void setMainMngName(String mainMngName) {
		this.mainMngName = mainMngName;
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
}
