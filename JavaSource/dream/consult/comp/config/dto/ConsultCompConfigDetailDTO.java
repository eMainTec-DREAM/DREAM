package dream.consult.comp.config.dto;

import common.bean.BaseDTO;

/**
 * 시스템 환경변수 - 상세 DTO
 * @author  syyang
 * @version $Id: ConsultCompConfigDetailDTO.java,v 1.0 2015/12/02 08:44:16 syyang Exp $
 * @since   1.0
 *
 */
public class ConsultCompConfigDetailDTO extends BaseDTO
{
	/** 회사 config id */
	private String compconfigId 		= "";
	/** 회사 config name */
	private String compconfigName 		= "";
	/** 회사 config value */
	private String compconfigValue 		= "";
	/** 회사 환경변수 설명 */
	private String compconfigDesc 		= "";
	/** 시스템관리자 사용여부 */
	private String isSystem 		= "";
	
	private String compNo = "";
	private String compDesc = "";
	
	
	
	public String getCompNo() {
		return compNo;
	}
	public void setCompNo(String compNo) {
		this.compNo = compNo;
	}
	public String getCompDesc() {
		return compDesc;
	}
	public void setCompDesc(String compDesc) {
		this.compDesc = compDesc;
	}
	public String getCompconfigId() {
		return compconfigId;
	}
	public void setCompconfigId(String compconfigId) {
		this.compconfigId = compconfigId;
	}
	public String getCompconfigName() {
		return compconfigName;
	}
	public void setCompconfigName(String compconfigName) {
		this.compconfigName = compconfigName;
	}
	public String getCompconfigValue() {
		return compconfigValue;
	}
	public void setCompconfigValue(String compconfigValue) {
		this.compconfigValue = compconfigValue;
	}
	public String getCompconfigDesc() {
		return compconfigDesc;
	}
	public void setCompconfigDesc(String compconfigDesc) {
		this.compconfigDesc = compconfigDesc;
	}
	public String getIsSystem() {
		return isSystem;
	}
	public void setIsSystem(String isSystem) {
		this.isSystem = isSystem;
	}
}
