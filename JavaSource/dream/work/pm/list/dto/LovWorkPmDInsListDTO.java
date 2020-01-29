package dream.work.pm.list.dto;

import common.bean.BaseDTO;

/**
 * 점검항목 팝업 DTO
 * @author  euna0207
 * @version $Id:$
 * @since   1.0
 */
public class LovWorkPmDInsListDTO extends BaseDTO
{
	/** PM ID */
	private String pmId					= "";
	/** 설비종류별 점검항목코드 */
	private String eqCtgPmPointId 		= "";
	/** 설비종류코드 ID*/
	private String eqCtgId 				= "";
	/** 주기-기간 */
	private String cycle 				= "";
	/** 주기 ID - 년, 월, 주, 일 */
	private String periodTypeId			= "";
	/** 주기 Desc - 년, 월, 주, 일 */
	private String periodTypeDesc 		= "";
	/** 점검부위 ID */
	private String eqasmbId 			= "";
	/** 점검부위 Desc */
	private String eqasmbDesc 			= "";
	/** 점검항목 */
	private String checkPoint 			= "";
	/** 점검방법 */
	private String checkMethod 			= "";
	/** 적정기준 */
	private String fitBasis 			= "";
	/** 수치/판정기준 ID */
	private String checkTypeId 			= "";
	/** 수치/판정기준 Desc */
	private String checkTypeDesc 		= "";
	/** 하한 */
	private String checkMin				 = "";
	/** 기준 */
	private String checkBasisVal 		 = "";
	/** 상한 */
	private String checkMax 			 = "";
	/** 단위 */
	private String uom 					 = "";
	/** 비고 */
	private String remark 				 = "";
	/** 보관위치 */
	private String binNo 				 = "";
	
	
	
	public String getBinNo() {
		return binNo;
	}
	public void setBinNo(String binNo) {
		this.binNo = binNo;
	}
	public String getPmId() {
		return pmId;
	}
	public void setPmId(String pmId) {
		this.pmId = pmId;
	}
	public String getEqCtgPmPointId() {
		return eqCtgPmPointId;
	}
	public void setEqCtgPmPointId(String eqCtgPmPointId) {
		this.eqCtgPmPointId = eqCtgPmPointId;
	}
	public String getEqCtgId() {
		return eqCtgId;
	}
	public void setEqCtgId(String eqCtgId) {
		this.eqCtgId = eqCtgId;
	}
	public String getCycle() {
		return cycle;
	}
	public void setCycle(String cycle) {
		this.cycle = cycle;
	}
	public String getPeriodTypeId() {
		return periodTypeId;
	}
	public void setPeriodTypeId(String periodTypeId) {
		this.periodTypeId = periodTypeId;
	}
	public String getPeriodTypeDesc() {
		return periodTypeDesc;
	}
	public void setPeriodTypeDesc(String periodTypeDesc) {
		this.periodTypeDesc = periodTypeDesc;
	}
	public String getEqasmbId() {
		return eqasmbId;
	}
	public void setEqasmbId(String eqasmbId) {
		this.eqasmbId = eqasmbId;
	}
	public String getEqasmbDesc() {
		return eqasmbDesc;
	}
	public void setEqasmbDesc(String eqasmbDesc) {
		this.eqasmbDesc = eqasmbDesc;
	}
	public String getCheckPoint() {
		return checkPoint;
	}
	public void setCheckPoint(String checkPoint) {
		this.checkPoint = checkPoint;
	}
	public String getCheckMethod() {
		return checkMethod;
	}
	public void setCheckMethod(String checkMethod) {
		this.checkMethod = checkMethod;
	}
	public String getFitBasis() {
		return fitBasis;
	}
	public void setFitBasis(String fitBasis) {
		this.fitBasis = fitBasis;
	}
	public String getCheckTypeId() {
		return checkTypeId;
	}
	public void setCheckTypeId(String checkTypeId) {
		this.checkTypeId = checkTypeId;
	}
	public String getCheckTypeDesc() {
		return checkTypeDesc;
	}
	public void setCheckTypeDesc(String checkTypeDesc) {
		this.checkTypeDesc = checkTypeDesc;
	}
	public String getCheckMin() {
		return checkMin;
	}
	public void setCheckMin(String checkMin) {
		this.checkMin = checkMin;
	}
	public String getCheckBasisVal() {
		return checkBasisVal;
	}
	public void setCheckBasisVal(String checkBasisVal) {
		this.checkBasisVal = checkBasisVal;
	}
	public String getCheckMax() {
		return checkMax;
	}
	public void setCheckMax(String checkMax) {
		this.checkMax = checkMax;
	}
	public String getUom() {
		return uom;
	}
	public void setUom(String uom) {
		this.uom = uom;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
