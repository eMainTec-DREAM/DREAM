package dream.part.rec.dto;

import common.bean.BaseDTO;

/**
 * 구매입고 item - 상세  DTO
 * @author  kim21017
 * @version $Id: MaPtRecSerialDetailDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class MaPtRecSerialDetailDTO extends BaseDTO
{
	/** 시리얼No */
	private String serialNo 		= "";
	/** 시리얼 key */
	private String serialId 		= "";
	
	private String partId 		= "";
	/** 비고 */
	private String remark 		= "";
	
	public String getPartId() {
		return partId;
	}
	public void setPartId(String partId) {
		this.partId = partId;
	}
	public String getSerialId() {
		return serialId;
	}
	public void setSerialId(String serialId) {
		this.serialId = serialId;
	}
	public String getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	

}