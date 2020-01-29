package dream.consult.program.config.dto;

import common.bean.BaseDTO;

/**
 * �ý��� ȯ�溯�� ���� DTO
 * @author  kim21017
 * @version $Id: MaConfigCommonDTO.java,v 1.0 2015/12/02 09:13:08 kim21017 Exp $
 * @since   1.0
 * 
 */
public class MaConfigCommonDTO extends BaseDTO
{
	/** config id */
	private String configId 		= "";
	/** ���� ���� */
	private String filterDesc 		= "";
	
	
	public String getConfigId() {
		return configId;
	}
	public void setConfigId(String configId) {
		this.configId = configId;
	}
	public String getFilterDesc() {
		return filterDesc;
	}
	public void setFilterDesc(String filterDesc) {
		this.filterDesc = filterDesc;
	}
}
