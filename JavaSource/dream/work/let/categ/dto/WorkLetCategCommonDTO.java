package dream.work.let.categ.dto;

import common.bean.BaseDTO;
/**
 * 안전작업유형 List 공통 DTO
 * @author euna0207
 * @version $Id: WorkLetCategCommonDTO.java,v 1.0 2015/12/02 09:12:40 euna0207 Exp $
 * @since 1.0
 *
 */
public class WorkLetCategCommonDTO extends BaseDTO {

	/** 안전작업유형id (key값) */
	private String woLetCtgId =						"";
	
	/** 안전작업 표준점검항목 id */
	private String woLetCtgPointId =				"";
	
	/** 안전작업 유형번호 */
	private String woLetCtgNo =						"";
	
	/** Filter 제목 */
	private String filterDes =						"";
	
	/** Filter 안전작업유형 ID */
	private String filterWoLetCtgTypeId =			"";
	
	/** Filter 안전작업유형 */
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
