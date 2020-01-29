package dream.org.emp.dto;

import common.bean.BaseDTO;

/**
 * ���Ž�û ��ǰ   DTO
 * @author  kim21017
 * @version $Id: OrgEmpTrainListDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class OrgEmpTrainListDTO extends BaseDTO
{
	/** �����ûItem ID */
	private String empTrainListId 	= "";
	
	private String ptStkTakeStatus 	= "";

	
	public String getPtStkTakeStatus() {
		return ptStkTakeStatus;
	}

	public void setPtStkTakeStatus(String ptStkTakeStatus) {
		this.ptStkTakeStatus = ptStkTakeStatus;
	}

	public String getEmpTrainListId() {
		return empTrainListId;
	}

	public void setEmpTrainListId(String empTrainListId) {
		this.empTrainListId = empTrainListId;
		super.setAuditKey(empTrainListId);
	}
}