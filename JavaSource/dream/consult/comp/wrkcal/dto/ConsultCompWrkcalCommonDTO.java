package dream.consult.comp.wrkcal.dto;

import common.bean.BaseDTO;

/**
 * 회사설정 공통 DTO
 * @author  kim21017
 * @version $Id: ConsultCompWrkcalCommonDTO.java,v 1.0 2015/12/02 09:13:08 kim21017 Exp $
 * @since   1.0
 *
 */
public class ConsultCompWrkcalCommonDTO extends BaseDTO
{
	/** 회사설정 ID */
	private String compNo 				= "";
	/** 근무달력 ID */
	private String wrkcalListId 		= "";
	/** 필터 회사설정 No */
	private String filterCompNo 		= "";
	/** 필터 회사명 */
	private String filterCompDesc 		= "";
	/** 필터 회사설정 No */
	private String filterWrkcalNo 		= "";
	
	public String getFilterCompDesc() {
		return filterCompDesc;
	}
	public void setFilterCompDesc(String filterCompDesc) {
		this.filterCompDesc = filterCompDesc;
	}
	public String getCompNo() {
		return compNo;
	}
	public void setCompNo(String compNo) {
		this.compNo = compNo;
	}
	public String getFilterCompNo() {
		return filterCompNo;
	}
	public void setFilterCompNo(String filterCompNo) {
		this.filterCompNo = filterCompNo;
	}
	public String getFilterWrkcalNo() {
		return filterWrkcalNo;
	}
	public void setFilterWrkcalNo(String filterWrkcalNo) {
		this.filterWrkcalNo = filterWrkcalNo;
	}
	public String getWrkcalListId() {
		return wrkcalListId;
	}
	public void setWrkcalListId(String wrkcalListId) {
		this.wrkcalListId = wrkcalListId;
	}
	
}
