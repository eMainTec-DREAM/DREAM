package dream.asset.loc.list.dto;

import common.bean.BaseDTO;

/**
 * 설비위치 공통
 * @author  kim21017
 * @version $Id: MaEqLocCommonDTO.java,v 1.0 2015/12/02 09:13:08 kim21017 Exp $
 * @since   1.0
 * 
 */
public class MaEqLocCommonDTO extends BaseDTO
{
	/** 위치분류ID */
	private String eqLocId 					= "";
	/** 필터 위치 */
	private String filterEqLocNo 			= "";
	/** 필터 위치명 */
	private String filterEqLocDesc 			= "";
	/** 필터 위치분류구분코드 */
	private String filterLevelType 			= "";
	/** 필터 위치분류구분명 */
	private String filterLevelTypeDesc 		= "";
	/** 필터 상위위치분류ID */
	private String filterPEqLocId 			= "";
	/** 필터 상위위치분류명 */
	private String filterPEqLocDesc 		= "";
	/** detail 상위 위치분류ID */
	private String detailPEqLocId 			= "";
	/** detail 상위 위치분류명 */
	private String detailPEqLocDesc 		= "";
	
	public String getDetailPEqLocId() {
		return detailPEqLocId;
	}
	public void setDetailPEqLocId(String detailPEqLocId) {
		this.detailPEqLocId = detailPEqLocId;
	}
	public String getDetailPEqLocDesc() {
		return detailPEqLocDesc;
	}
	public void setDetailPEqLocDesc(String detailPEqLocDesc) {
		this.detailPEqLocDesc = detailPEqLocDesc;
	}
	public String getEqLocId() {
		return eqLocId;
	}
	public void setEqLocId(String eqLocId) {
		this.eqLocId = eqLocId;
		super.setAuditKey(eqLocId);
	}
	public String getFilterEqLocNo() {
		return filterEqLocNo;
	}
	public void setFilterEqLocNo(String filterEqLocNo) {
		this.filterEqLocNo = filterEqLocNo;
	}
	public String getFilterEqLocDesc() {
		return filterEqLocDesc;
	}
	public void setFilterEqLocDesc(String filterEqLocDesc) {
		this.filterEqLocDesc = filterEqLocDesc;
	}
	
	public String getFilterLevelType() {
		return filterLevelType;
	}
	public void setFilterLevelType(String filterLevelType) {
		this.filterLevelType = filterLevelType;
	}
	public String getFilterLevelTypeDesc() {
		return filterLevelTypeDesc;
	}
	public void setFilterLevelTypeDesc(String filterLevelTypeDesc) {
		this.filterLevelTypeDesc = filterLevelTypeDesc;
	}
	public String getFilterPEqLocId() {
		return filterPEqLocId;
	}
	public void setFilterPEqLocId(String filterPEqLocId) {
		this.filterPEqLocId = filterPEqLocId;
	}
	public String getFilterPEqLocDesc() {
		return filterPEqLocDesc;
	}
	public void setFilterPEqLocDesc(String filterPEqLocDesc) {
		this.filterPEqLocDesc = filterPEqLocDesc;
	}
}
