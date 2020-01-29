package mobile.dream.mapm.mains.dto;

import common.bean.BaseDTO;

/**
 * 예방점검 공통 DTO
 * @author  jung7126
 * @version $Id: MaPmInsCommonDTO.java,v 1.0 2015/12/02 09:13:08 jung7126 Exp $
 * @since   1.0
 * 
 */
public class MaPmInsCommonDTO extends BaseDTO
{
	/** Search Text */
	private String searchText 	= "";
	/** PM ID */
	private String pmId			= "";
	/** PM번호 */
	private String pmNo		    = "";
	/** PM명 */
	private String pmDesc		= "";
	/** 작업형태 */
	private String pmType		= "";
	/** 작업형태명 */
	private String pmTypeDesc   = "";
	/** 작업종류 */
	private String woType		= "";
	/** 작업종류명 */
	private String woTypeDesc   = "";
	
	
	public String getSearchText() {
		return searchText;
	}
	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}
	public String getPmId() {
		return pmId;
	}
	public void setPmId(String pmId) {
		this.pmId = pmId;
	}
	public String getPmNo() {
		return pmNo;
	}
	public void setPmNo(String pmNo) {
		this.pmNo = pmNo;
	}
	public String getPmDesc() {
		return pmDesc;
	}
	public void setPmDesc(String pmDesc) {
		this.pmDesc = pmDesc;
	}
	public String getPmType() {
		return pmType;
	}
	public void setPmType(String pmType) {
		this.pmType = pmType;
	}
	public String getPmTypeDesc() {
		return pmTypeDesc;
	}
	public void setPmTypeDesc(String pmTypeDesc) {
		this.pmTypeDesc = pmTypeDesc;
	}
	public String getWoType() {
		return woType;
	}
	public void setWoType(String woType) {
		this.woType = woType;
	}
	public String getWoTypeDesc() {
		return woTypeDesc;
	}
	public void setWoTypeDesc(String woTypeDesc) {
		this.woTypeDesc = woTypeDesc;
	}
}
