package dream.consult.comp.wrkcal.dto;

import common.bean.BaseDTO;

/**
 * ȸ�缳�� - �� DTO
 * @author  kim21017
 * @version $Id: ConsultCompWrkcalDetailDTO.java,v 1.0 2015/12/02 08:44:16 kim21017 Exp $
 * @since   1.0
 *
 */
public class ConsultCompWrkcalDetailDTO extends BaseDTO
{

	/** ȸ�缳��ID */
	private String compNo 			= "";
	/** ���弳��ID */
	private String plantNo 			= "";
	/** ��� */
	private String plantDesc 		= "";
	/** ȸ��� */
	private String compDesc 		= "";
	/** �ٹ��޷�# */
	private String wrkcalListNo 	= "";
	/** �ٹ��޷¸� */
	private String wrkcalListDesc 	= "";
	/** ��� */
	private String remark 			= "";
	/** �ٹ��޷� ID */
	private String wrkcalListId 	= "";
	/** ��뿩�� */
	private String isUse 			= "";
	
	public String getIsUse() {
		return isUse;
	}
	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}
	public String getCompNo() {
		return compNo;
	}
	public void setCompNo(String compNo) {
		this.compNo = compNo;
	}
	public String getPlantNo() {
		return plantNo;
	}
	public void setPlantNo(String plantNo) {
		this.plantNo = plantNo;
	}
	public String getPlantDesc() {
		return plantDesc;
	}
	public void setPlantDesc(String plantDesc) {
		this.plantDesc = plantDesc;
	}
	public String getCompDesc() {
		return compDesc;
	}
	public void setCompDesc(String compDesc) {
		this.compDesc = compDesc;
	}
	public String getWrkcalListNo() {
		return wrkcalListNo;
	}
	public void setWrkcalListNo(String wrkcalListNo) {
		this.wrkcalListNo = wrkcalListNo;
	}
	public String getWrkcalListDesc() {
		return wrkcalListDesc;
	}
	public void setWrkcalListDesc(String wrkcalListDesc) {
		this.wrkcalListDesc = wrkcalListDesc;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getWrkcalListId() {
		return wrkcalListId;
	}
	public void setWrkcalListId(String wrkcalListId) {
		this.wrkcalListId = wrkcalListId;
	}
}
