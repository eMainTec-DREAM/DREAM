package dream.consult.program.dashboard.dto;

import common.bean.BaseDTO;

/**
 * ��ú��� Contents ���� DTO
 * @author  kim21017
 * @version $Id: ConsultPgmDashboardCommonDTO.java,v 1.0 2015/12/02 09:13:08 kim21017 Exp $
 * @since   1.0
 *
 */
public class ConsultPgmDashboardCommonDTO extends BaseDTO
{
	/** ID */
	private String dbContentsId 				= "";
	/** filter-���� */
	private String filterDbContentsDesc 		= "";
	/** filter-����ID */
	private String filterDbContentsTypeId		= "";
	/** filter-����DESC */
	private String filterDbContentsTypeDesc		= "";

	public String getDbContentsId() {
		return dbContentsId;
	}

	public void setDbContentsId(String dbContentsId) {
		this.dbContentsId = dbContentsId;
	}

	public String getFilterDbContentsDesc() {
		return filterDbContentsDesc;
	}

	public void setFilterDbContentsDesc(String filterDbContentsDesc) {
		this.filterDbContentsDesc = filterDbContentsDesc;
	}

	public String getFilterDbContentsTypeId() {
		return filterDbContentsTypeId;
	}

	public void setFilterDbContentsTypeId(String filterDbContentsTypeId) {
		this.filterDbContentsTypeId = filterDbContentsTypeId;
	}

	public String getFilterDbContentsTypeDesc() {
		return filterDbContentsTypeDesc;
	}

	public void setFilterDbContentsTypeDesc(String filterDbContentsTypeDesc) {
		this.filterDbContentsTypeDesc = filterDbContentsTypeDesc;
	}
	
}
