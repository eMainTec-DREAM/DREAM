package dream.consult.comp.config.dto;

import common.bean.BaseDTO;

/**
 * �ý��� ȯ�溯�� - �� DTO
 * @author  syyang
 * @version $Id: ConsultCompConfigDetailDTO.java,v 1.0 2015/12/02 08:44:16 syyang Exp $
 * @since   1.0
 *
 */
public class ConsultCompConfigDetailDTO extends BaseDTO
{
	/** ȸ�� config id */
	private String compconfigId 		= "";
	/** ȸ�� config name */
	private String compconfigName 		= "";
	/** ȸ�� config value */
	private String compconfigValue 		= "";
	/** ȸ�� ȯ�溯�� ���� */
	private String compconfigDesc 		= "";
	/** �ý��۰����� ��뿩�� */
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
