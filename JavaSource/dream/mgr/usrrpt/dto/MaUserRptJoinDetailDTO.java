package dream.mgr.usrrpt.dto;

import common.bean.BaseDTO;

/**
 * 상세 dto
 * @author  ssong
 * @version 
 * @since   1.0
 */
public class MaUserRptJoinDetailDTO extends BaseDTO
{
	/** 값 */
	private String conValue = "";
	/** 오른쪽 왼쪽 컬럼명 */
	private String rcolumnName = "";
	/** 오른쪽 테이블컬럼 ID */
	private String rtabcolId = "";
	/** 오른쪽 테이블 */
	private String rtableName = "";
	/** 오른쪽 테이블컬럼 ID */
	private String rtableId = "";
	
	private String rusrrpttabId	= "";
	
	private String lusrrpttabId 	= "";
	/** 컬럼,값구분 */
	private String colValueType = "";
	/** 컬럼,값구분값 */
	private String colValueTypeDesc = "";
	/** 조건 연산자 */
	private String tabConOperator = "";
	
	private String tabConOperatorDesc = "";
	/** 왼쪽 컬럼명 */
	private String lcolumnName = "";
	/** 왼쪽 테이블컬럼 ID */
	private String ltabcolId = "";
	/** 왼쪽 테이블 */
	private String ltableName = "";
	/** 왼쪽 테이블컬럼 ID */
	private String ltableId = "";
	/** 순서 */
	private String stepNum = "";
	/** 사용자 Report 리스트 id */
	private String usrrptlistId = "";
	/** 사용자REPORT 테이블ID */
	private String usrrpttabId = "";
	/** 사용자REPORT 조인조건ID */
	private String usrrptjoinId = "";
	/** 사용자REPORT 조인 Type Desc */
	private String joinTypeDesc = "";
	/** 회사코드 */
	private String compNo = "";
	
	
	public String getRusrrpttabId() {
		return rusrrpttabId;
	}
	public void setRusrrpttabId(String rusrrpttabId) {
		this.rusrrpttabId = rusrrpttabId;
	}
	public String getLusrrpttabId() {
		return lusrrpttabId;
	}
	public void setLusrrpttabId(String lusrrpttabId) {
		this.lusrrpttabId = lusrrpttabId;
	}
	public String getTabConOperatorDesc() {
		return tabConOperatorDesc;
	}
	public void setTabConOperatorDesc(String tabConOperatorDesc) {
		this.tabConOperatorDesc = tabConOperatorDesc;
	}
	public String getColValueTypeDesc() {
		return colValueTypeDesc;
	}
	public void setColValueTypeDesc(String colValueTypeDesc) {
		this.colValueTypeDesc = colValueTypeDesc;
	}
	public String getJoinTypeDesc() {
		return joinTypeDesc;
	}
	public void setJoinTypeDesc(String joinTypeDesc) {
		this.joinTypeDesc = joinTypeDesc;
	}
	public String getConValue() {
		return conValue;
	}
	public void setConValue(String conValue) {
		this.conValue = conValue;
	}
	public String getRcolumnName() {
		return rcolumnName;
	}
	public void setRcolumnName(String rcolumnName) {
		this.rcolumnName = rcolumnName;
	}
	public String getRtabcolId() {
		return rtabcolId;
	}
	public void setRtabcolId(String rtabcolId) {
		this.rtabcolId = rtabcolId;
	}
	public String getRtableName() {
		return rtableName;
	}
	public void setRtableName(String rtableName) {
		this.rtableName = rtableName;
	}
	public String getRtableId() {
		return rtableId;
	}
	public void setRtableId(String rtableId) {
		this.rtableId = rtableId;
	}
	public String getColValueType() {
		return colValueType;
	}
	public void setColValueType(String colValueType) {
		this.colValueType = colValueType;
	}
	public String getTabConOperator() {
		return tabConOperator;
	}
	public void setTabConOperator(String tabConOperator) {
		this.tabConOperator = tabConOperator;
	}
	public String getLcolumnName() {
		return lcolumnName;
	}
	public void setLcolumnName(String lcolumnName) {
		this.lcolumnName = lcolumnName;
	}
	public String getLtabcolId() {
		return ltabcolId;
	}
	public void setLtabcolId(String ltabcolId) {
		this.ltabcolId = ltabcolId;
	}
	public String getLtableName() {
		return ltableName;
	}
	public void setLtableName(String ltableName) {
		this.ltableName = ltableName;
	}
	public String getLtableId() {
		return ltableId;
	}
	public void setLtableId(String ltableId) {
		this.ltableId = ltableId;
	}
	public String getStepNum() {
		return stepNum;
	}
	public void setStepNum(String stepNum) {
		this.stepNum = stepNum;
	}
	public String getUsrrptlistId() {
		return usrrptlistId;
	}
	public void setUsrrptlistId(String usrrptlistId) {
		this.usrrptlistId = usrrptlistId;
	}
	public String getUsrrpttabId() {
		return usrrpttabId;
	}
	public void setUsrrpttabId(String usrrpttabId) {
		this.usrrpttabId = usrrpttabId;
	}
	public String getUsrrptjoinId() {
		return usrrptjoinId;
	}
	public void setUsrrptjoinId(String usrrptjoinId) {
		this.usrrptjoinId = usrrptjoinId;
	}
	public String getCompNo() {
		return compNo;
	}
	public void setCompNo(String compNo) {
		this.compNo = compNo;
	}
	
   }