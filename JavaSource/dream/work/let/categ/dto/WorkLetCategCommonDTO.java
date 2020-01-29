package dream.work.let.categ.dto;

import common.bean.BaseDTO;
/**
 * �����۾����� List ���� DTO
 * @author euna0207
 * @version $Id: WorkLetCategCommonDTO.java,v 1.0 2015/12/02 09:12:40 euna0207 Exp $
 * @since 1.0
 *
 */
public class WorkLetCategCommonDTO extends BaseDTO {

	/** �����۾�����id (key��) */
	private String woLetCtgId =						"";
	
	/** �����۾� ǥ�������׸� id */
	private String woLetCtgPointId =				"";
	
	/** �����۾� ������ȣ */
	private String woLetCtgNo =						"";
	
	/** Filter ���� */
	private String filterDes =						"";
	
	/** Filter �����۾����� ID */
	private String filterWoLetCtgTypeId =			"";
	
	/** Filter �����۾����� */
	private String filterWoLetCtgTypeDesc =			"";

	public String getWoLetCtgId() {
		return woLetCtgId;
	}

	public void setWoLetCtgId(String woLetCtgId) {
		this.woLetCtgId = woLetCtgId;
		super.setAuditKey(woLetCtgId);
	}

	public String getWoLetCtgPointId() {
		return woLetCtgPointId;
	}

	public void setWoLetCtgPointId(String woLetCtgPointId) {
		this.woLetCtgPointId = woLetCtgPointId;
	}

	public String getWoLetCtgNo() {
		return woLetCtgNo;
	}

	public void setWoLetCtgNo(String woLetCtgNo) {
		this.woLetCtgNo = woLetCtgNo;
	}

	public String getFilterDes() {
		return filterDes;
	}

	public void setFilterDes(String filterDes) {
		this.filterDes = filterDes;
	}

	public String getFilterWoLetCtgTypeId() {
		return filterWoLetCtgTypeId;
	}

	public void setFilterWoLetCtgTypeId(String filterWoLetCtgTypeId) {
		this.filterWoLetCtgTypeId = filterWoLetCtgTypeId;
	}

	public String getFilterWoLetCtgTypeDesc() {
		return filterWoLetCtgTypeDesc;
	}

	public void setFilterWoLetCtgTypeDesc(String filterWoLetCtgTypeDesc) {
		this.filterWoLetCtgTypeDesc = filterWoLetCtgTypeDesc;
	}

	
}
