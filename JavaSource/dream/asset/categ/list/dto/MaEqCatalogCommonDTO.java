package dream.asset.categ.list.dto;

import common.bean.BaseDTO;

/**
 * 설비종류 공통 DTO
 * @author  kim21017
 * @version $Id: MaEqCatalogCommonDTO.java,v 1.0 2015/12/02 09:13:08 kim21017 Exp $
 * @since   1.0
 * 
 */
public class MaEqCatalogCommonDTO extends BaseDTO
{
	/** 설비종류ID */
	private String eqCtgId				= "";
	/** 설비구분ID */
	private String eqTypeId				= "";
	/** 설비구분명 */
	private String eqTypeDesc			= "";
	/** 상위설비종류ID */
	private String filterPeqCtgId		= "";
	/** 상위설비종류명 */
	private String filterPeqCtgDesc		= "";
	/** 종류명 */
	private String filterEqCtgDesc		= "";
	/** 생성시 detail에 부모 종류id */
	private String detailPctgId			= "";
	/** 생성시 detail에 부모 종류명  */
	private String detailPctgDesc		= "";
	
	public String getDetailPctgId() {
		return detailPctgId;
	}
	public void setDetailPctgId(String detailPctgId) {
		this.detailPctgId = detailPctgId;
	}
	public String getDetailPctgDesc() {
		return detailPctgDesc;
	}
	public void setDetailPctgDesc(String detailPctgDesc) {
		this.detailPctgDesc = detailPctgDesc;
	}
	public String getEqTypeDesc() {
		return eqTypeDesc;
	}
	public void setEqTypeDesc(String eqTypeDesc) {
		this.eqTypeDesc = eqTypeDesc;
	}
	public String getEqTypeId() {
		return eqTypeId;
	}
	public void setEqTypeId(String eqTypeId) {
		this.eqTypeId = eqTypeId;
	}
	public String getFilterEqCtgDesc() {
		return filterEqCtgDesc;
	}
	public void setFilterEqCtgDesc(String filterEqCtgDesc) {
		this.filterEqCtgDesc = filterEqCtgDesc;
	}
	public String getEqCtgId() {
		return eqCtgId;
	}
	public void setEqCtgId(String eqCtgId) {
		this.eqCtgId = eqCtgId;
		super.setAuditKey(eqCtgId);
	}
	public String getFilterPeqCtgId() {
		return filterPeqCtgId;
	}
	public void setFilterPeqCtgId(String filterPeqCtgId) {
		this.filterPeqCtgId = filterPeqCtgId;
	}
	public String getFilterPeqCtgDesc() {
		return filterPeqCtgDesc;
	}
	public void setFilterPeqCtgDesc(String filterPeqCtgDesc) {
		this.filterPeqCtgDesc = filterPeqCtgDesc;
	}
}
