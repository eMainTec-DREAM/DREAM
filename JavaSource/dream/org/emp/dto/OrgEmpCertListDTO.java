package dream.org.emp.dto;

import common.bean.BaseDTO;

/**
 * 备概脚没 何前   DTO
 * @author  kim21017
 * @version $Id: OrgEmpCertListDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class OrgEmpCertListDTO extends BaseDTO
{
	/** 磊犁夸没Item ID */
	private String empCertListId 	= "";
	
	private String ptStkTakeStatus 	= "";

	
	public String getPtStkTakeStatus() {
		return ptStkTakeStatus;
	}

	public void setPtStkTakeStatus(String ptStkTakeStatus) {
		this.ptStkTakeStatus = ptStkTakeStatus;
	}

	public String getEmpCertListId() {
		return empCertListId;
	}

	public void setEmpCertListId(String empCertListId) {
		this.empCertListId = empCertListId;
		super.setAuditKey(empCertListId);
	}

	
	
	
}