package dream.work.pm.std.dto;

import common.bean.BaseDTO;

/**
 * 표준항목 - 상세 DTO
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 */
public class MaStdWorkDetailDTO extends BaseDTO
{
	/** 점검Id */
	private String stWrkWorkPrcId		= "";
	
	private String stWrkWorkId			= "";

	private String stWrkId				= "";
	
	private String stepNum				= "";
	
	private String woDesc				= "";
	
	private String remark				= "";

	public String getStWrkWorkPrcId() {
		return stWrkWorkPrcId;
	}

	public void setStWrkWorkPrcId(String stWrkWorkPrcId) {
		this.stWrkWorkPrcId = stWrkWorkPrcId;
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

	public String getStepNum() {
		return stepNum;
	}

	public void setStepNum(String stepNum) {
		this.stepNum = stepNum;
	}

	public String getWoDesc() {
		return woDesc;
	}

	public void setWoDesc(String woDesc) {
		this.woDesc = woDesc;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
