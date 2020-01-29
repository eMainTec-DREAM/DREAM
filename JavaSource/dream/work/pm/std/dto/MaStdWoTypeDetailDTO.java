package dream.work.pm.std.dto;

import common.bean.BaseDTO;

/**
 * 표준항목 - 상세 DTO
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 */
public class MaStdWoTypeDetailDTO extends BaseDTO
{
	/** 점검Id */
	private String stWrkWorkId				= "";
	/** 점검순서 */
	private String stWrkId 					= "";
	/** 작업종류 */
	private String woType 					= "";
	/** 작업종류 */
	private String woTypeDesc 				= "";
	/** 작업형태 */
	private String pmType 					= "";
	/** 작업형태 */
	private String pmTypeDesc 				= "";
	/** 설명 */
	private String description 				= "";
	/** 점검순서 */
	private String remark 					= "";
	
	private String empty 					= "";
	
	public String getPmType() {
		return pmType;
	}

	public void setPmType(String pmType) {
		this.pmType = pmType;
	}

	public String getPmTypeDesc() {
		return pmTypeDesc;
	}

	public void setPmTypeDesc(String pmTypeDesc) {
		this.pmTypeDesc = pmTypeDesc;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEmpty() {
		return empty;
	}

	public void setEmpty(String empty) {
		this.empty = empty;
	}

	public String getStWrkWorkId() {
		return stWrkWorkId;
	}

	public void setStWrkWorkId(String stWrkWorkId) {
		this.stWrkWorkId = stWrkWorkId;
	}

	public String getStWrkId() {
		return stWrkId;
	}

	public void setStWrkId(String stWrkId) {
		this.stWrkId = stWrkId;
	}

	public String getWoType() {
		return woType;
	}

	public void setWoType(String woType) {
		this.woType = woType;
	}

	public String getWoTypeDesc() {
		return woTypeDesc;
	}

	public void setWoTypeDesc(String woTypeDesc) {
		this.woTypeDesc = woTypeDesc;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
