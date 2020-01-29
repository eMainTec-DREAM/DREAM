package dream.asset.list.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 설비의 정기작업-교체부품  상세 DTO
 * @author  kim21017
 * @version $Id: MaEqMstrPmWorkPartDetailDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class MaEqMstrPmWorkPartDetailDTO extends BaseDTO
{
	/** 설비ID */
	private String equipId				= "";
	/** 예방작업ID */
	private String pmId					= "";
	/** 부품항목ID */
	private String pmPartId				= "";
	/** 부품ID */
	private String partId				= "";
	/** 부품NO */
	private String partNo				= "";
	/** 부품명 */
	private String partDesc				= "";
	/** 규격 */
	private String partSize				= "";
	/** 사용수량 */
	private String useQty				= "";
	
	
	public String getEquipId() {
		return equipId;
	}
	public void setEquipId(String equipId) {
		this.equipId = equipId;
	}
	public String getPmId() {
		return pmId;
	}
	public void setPmId(String pmId) {
		this.pmId = pmId;
	}
	public String getPmPartId() {
		return pmPartId;
	}
	public void setPmPartId(String pmPartId) {
		this.pmPartId = pmPartId;
	}
	public String getPartId() {
		return partId;
	}
	public void setPartId(String partId) {
		this.partId = partId;
	}
	public String getPartNo() {
		return partNo;
	}
	public void setPartNo(String partNo) {
		this.partNo = partNo;
	}
	public String getPartDesc() {
		return partDesc;
	}
	public void setPartDesc(String partDesc) {
		this.partDesc = partDesc;
	}
	public String getPartSize() {
		return partSize;
	}
	public void setPartSize(String partSize) {
		this.partSize = partSize;
	}
	public String getUseQty() {
		return useQty;
	}
	public void setUseQty(String useQty) {
		this.useQty = CommonUtil.convertMoney(useQty);
	}
	
}