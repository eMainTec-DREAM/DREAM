package dream.consult.program.config.dto;

import common.bean.BaseDTO;

/**
 * 시스템 환경변수 - 상세 DTO
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
	/** 환경변수 설명 */
	private String configDesc 		= "";
	/** 시스템관리자 사용여부 */
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
