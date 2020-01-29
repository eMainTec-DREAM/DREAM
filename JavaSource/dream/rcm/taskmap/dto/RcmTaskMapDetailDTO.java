package dream.rcm.taskmap.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 질의 - 상세 DTO
 * @author  kim21017
 * @version $Id: RcmTaskMapDetailDTO.java,v 1.0 2015/12/02 08:44:16 kim21017 Exp $
 * @since   1.0
 *
 */
public class RcmTaskMapDetailDTO extends BaseDTO
{
	/** task ID */
	private String pmTaskMapListId 					= "";


	private String mapNo 					= "";
	
	private String mapName 					= "";
	
	private String isUse 					= "";
	
	private String regDate 					= "";

	private String remark 					= "";
	
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getPmTaskMapListId() {
		return pmTaskMapListId;
	}

	public void setPmTaskMapListId(String pmTaskMapListId) {
		this.pmTaskMapListId = pmTaskMapListId;
		super.setAuditKey(pmTaskMapListId);
	}

	public String getMapNo() {
		return mapNo;
	}

	public void setMapNo(String mapNo) {
		this.mapNo = mapNo;
	}

	public String getMapName() {
		return mapName;
	}

	public void setMapName(String mapName) {
		this.mapName = mapName;
	}

	public String getIsUse() {
		return isUse;
	}

	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = CommonUtil.convertDate(regDate) ;
	}

}
