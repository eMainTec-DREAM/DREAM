package dream.consult.program.config.dto;

import common.bean.BaseDTO;

/**
 * �ý��� ȯ�溯�� - �� DTO
 * @author  kim21017
 * @version $Id: MaConfigDetailDTO.java,v 1.0 2015/12/02 08:44:16 kim21017 Exp $
 * @since   1.0
 *
 */
public class MaConfigDetailDTO extends BaseDTO
{
	/** config id */
	private String configId 		= "";
	/** config name */
	private String configName 		= "";
	/** config value */
	private String configValue 		= "";
	/** ȯ�溯�� ���� */
	private String configDesc 		= "";
	/** �ý��۰����� ��뿩�� */
	private String isSystem 		= "";
	
	
	public String getConfigId() {
		return configId;
	}
	public void setConfigId(String configId) {
		this.configId = configId;
	}
	public String getConfigName() {
		return configName;
	}
	public void setConfigName(String configName) {
		this.configName = configName;
	}
	public String getConfigValue() {
		return configValue;
	}
	public void setConfigValue(String configValue) {
		this.configValue = configValue;
	}
	public String getConfigDesc() {
		return configDesc;
	}
	public void setConfigDesc(String configDesc) {
		this.configDesc = configDesc;
	}
	public String getIsSystem() {
		return isSystem;
	}
	public void setIsSystem(String isSystem) {
		this.isSystem = isSystem;
	}
}
