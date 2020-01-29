package dream.part.rpt.mapartrptmonthly.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 부품수불부 요약 DTO
 * @author  euna0207
 * @version $Id: PartRptMonthlyStockListDTO.java,v 1.0 2015/12/02 09:13:08 euna0207 Exp $
 * @since   1.0
 * 
 */
public class PartRptMonthlyStockListDTO extends BaseDTO
{
	/** 필터- 창고Id */
	private String filterWcodeId 			= "";
	/** 필터- 창고Desc */
	private String filterWcodeDesc 			= "";
	/** 필터-년월Desc */
	private String filterYearMonthDesc 		= "";
	/** 부품Id */
	private String filterPartsDesc			= "";
	/** 부품Desc */
	private String filterPartsId			= "";
	
	public String getFilterWcodeDesc() {
		return filterWcodeDesc;
	}
	public void setFilterWcodeDesc(String filterWcodeDesc) {
		this.filterWcodeDesc = filterWcodeDesc;
	}
	public String getFilterWcodeId() {
		return filterWcodeId;
	}
	public void setFilterWcodeId(String filterWcodeId) {
		this.filterWcodeId = filterWcodeId;
	}
	public String getFilterYearMonthDesc() {
		return filterYearMonthDesc;
	}
	public void setFilterYearMonthDesc(String filterYearMonthDesc) {
		this.filterYearMonthDesc = CommonUtil.convertDate(filterYearMonthDesc);
	}
	public String getFilterPartsDesc() {
		return filterPartsDesc;
	}
	public void setFilterPartsDesc(String filterPartsDesc) {
		this.filterPartsDesc = filterPartsDesc;
	}
	public String getFilterPartsId() {
		return filterPartsId;
	}
	public void setFilterPartsId(String filterPartsId) {
		this.filterPartsId = filterPartsId;
	}
}
