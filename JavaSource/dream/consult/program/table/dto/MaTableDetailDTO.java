package dream.consult.program.table.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 데이터 테이블 - 상세 DTO
 * @author  kim21017
 * @version $Id: MaTableDetailDTO.java,v 1.0 2015/12/02 08:44:16 kim21017 Exp $
 * @since   1.0
 *
 */
public class MaTableDetailDTO extends BaseDTO
{
	/** 데이터 테이블유형상세ID */
	private String tableMId 			= "";
	/** 테이블 */
	private String tableNo 			    = "";
	/** 테이블명 */
	private String tableDesc 			= "";
	/** 생성자 */
	private String creBy 			    = "";
	/** 생성자 */
	private String creByDesc	        = "";
	/** 생성일자 */
	private String creDate 			    = "";
	/** 상세설명 */
	private String detailDesc 			= "";
	
	
	
	public String getCreByDesc() {
		return creByDesc;
	}
	public void setCreByDesc(String creByDesc) {
		this.creByDesc = creByDesc;
	}
	public String getTableMId() {
		return tableMId;
	}
	public void setTableMId(String tableMId) {
		this.tableMId = tableMId;
	}
	
	public String getTableNo() {
		return tableNo;
	}
	public void setTableNo(String tableNo) {
		this.tableNo = tableNo;
	}
	public String getTableDesc() {
		return tableDesc;
	}
	public void setTableDesc(String tableDesc) {
		this.tableDesc = tableDesc;
	}
	public String getCreBy() {
		return creBy;
	}
	public void setCreBy(String creBy) {
		this.creBy = creBy;
	}
	public String getCreDate() {
		return creDate;
	}
	public void setCreDate(String creDate) {
		this.creDate = CommonUtil.convertDate(creDate);
	}
	public String getDetailDesc() {
		return detailDesc;
	}
	public void setDetailDesc(String detailDesc) {
		this.detailDesc = detailDesc;
	}
	
	
}
