package dream.part.list.dto;

import common.bean.BaseDTO;

/**
 * �����׸� �˾� DTO
 * @author  euna0207
 * @version $Id:$
 * @since   1.0
 */
public class LovPartListBinListDTO extends BaseDTO
{
	
	/** ��� */
	private String ptBinListId 				 = "";
	/** ��� */
	private String wcodId 				 = "";
	/** ��� */
	private String remark 				 = "";
	/** ��� */
	private String binNo				 = "";
	
	
	public String getPtBinListId() {
		return ptBinListId;
	}
	public void setPtBinListId(String ptBinListId) {
		this.ptBinListId = ptBinListId;
	}
	public String getWcodId() {
		return wcodId;
	}
	public void setWcodId(String wcodId) {
		this.wcodId = wcodId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getBinNo() {
		return binNo;
	}
	public void setBinNo(String binNo) {
		this.binNo = binNo;
	}
	
	
	

}
