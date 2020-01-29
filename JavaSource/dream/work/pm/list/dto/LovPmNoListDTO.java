package dream.work.pm.list.dto;

import common.bean.BaseDTO;

/**
 * 예방점검검색 팝업 DTO
 * @author  kim21017
 * @version $Id: LovPmNoListDTO.java,v 1.1 2016/01/18 09:12:12 kim21017 Exp $
 * @since   1.0
 */
public class LovPmNoListDTO extends BaseDTO
{
	/** PM ID */
	private String pmId			= "";
	/** PM번호 */
	private String pmNo		    = "";
	/** PM명 */
	private String pmDesc		= "";
	/** 설비 */
    private String equipId      ="";
    /** 설비명 */
    private String equipDesc    = "";
    /** 부서ID */
    private String deptId	 	= "";
    /** 부서명 */
    private String deptDesc	 	= "";
    /** extCode1 */
    private String extCode1 	= "";
    /** extCode2 */
    private String extCode2 	= "";
    /** code type */
    private String codeType 	= "";
    
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
	public String getPmId() {
		return pmId;
	}
	public void setPmId(String pmId) {
		this.pmId = pmId;
	}
	public String getPmNo() {
		return pmNo;
	}
	public void setPmNo(String pmNo) {
		this.pmNo = pmNo;
	}
	public String getPmDesc() {
		return pmDesc;
	}
	public void setPmDesc(String pmDesc) {
		this.pmDesc = pmDesc;
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
