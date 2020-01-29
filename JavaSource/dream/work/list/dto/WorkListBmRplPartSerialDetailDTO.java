package dream.work.list.dto;

import common.bean.BaseDTO;

/**
 * 작업결과 부품Serial 상세 DTO
 * @author  kim21017
 * @version $Id: MaWoResultCraftDetailDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class WorkListBmRplPartSerialDetailDTO extends BaseDTO
{
	/** 비고 */
	private String remark = "";
	/** 제거 부품자산ID */
	private String outEquipId = "";
	/** 제거 Serial # */
	private String outSerialNo = "";
	/** 투입 부품자산ID */
	private String inEquipId = "";
	/** 투입 Serial # */
	private String inSerialNo = "";
	/** 부품번호(투입) */
	private String partId = "";
	/** 작업부품ID */
	private String wopartId = "";
	/** 작업부품시리얼ID */
	private String wopartsSerialId = "";
	/** 회사코드 */
	private String compNo = "";
	
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getOutEquipId() {
		return outEquipId;
	}
	public void setOutEquipId(String outEquipId) {
		this.outEquipId = outEquipId;
	}
	public String getOutSerialNo() {
		return outSerialNo;
	}
	public void setOutSerialNo(String outSerialNo) {
		this.outSerialNo = outSerialNo;
	}
	public String getInEquipId() {
		return inEquipId;
	}
	public void setInEquipId(String inEquipId) {
		this.inEquipId = inEquipId;
	}
	public String getInSerialNo() {
		return inSerialNo;
	}
	public void setInSerialNo(String inSerialNo) {
		this.inSerialNo = inSerialNo;
	}
	public String getPartId() {
		return partId;
	}
	public void setPartId(String partId) {
		this.partId = partId;
	}
	public String getWopartId() {
		return wopartId;
	}
	public void setWopartId(String wopartId) {
		this.wopartId = wopartId;
	}
	public String getWopartsSerialId() {
		return wopartsSerialId;
	}
	public void setWopartsSerialId(String wopartsSerialId) {
		this.wopartsSerialId = wopartsSerialId;
	}
	public String getCompNo() {
		return compNo;
	}
	public void setCompNo(String compNo) {
		this.compNo = compNo;
	}
}