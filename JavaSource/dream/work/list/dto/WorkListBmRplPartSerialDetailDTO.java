package dream.work.list.dto;

import common.bean.BaseDTO;

/**
 * �۾���� ��ǰSerial �� DTO
 * @author  kim21017
 * @version $Id: MaWoResultCraftDetailDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class WorkListBmRplPartSerialDetailDTO extends BaseDTO
{
	/** ��� */
	private String remark = "";
	/** ���� ��ǰ�ڻ�ID */
	private String outEquipId = "";
	/** ���� Serial # */
	private String outSerialNo = "";
	/** ���� ��ǰ�ڻ�ID */
	private String inEquipId = "";
	/** ���� Serial # */
	private String inSerialNo = "";
	/** ��ǰ��ȣ(����) */
	private String partId = "";
	/** �۾���ǰID */
	private String wopartId = "";
	/** �۾���ǰ�ø���ID */
	private String wopartsSerialId = "";
	/** ȸ���ڵ� */
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