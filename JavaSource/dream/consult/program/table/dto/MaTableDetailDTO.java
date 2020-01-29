package dream.consult.program.table.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * ������ ���̺� - �� DTO
 * @author  kim21017
 * @version $Id: MaTableDetailDTO.java,v 1.0 2015/12/02 08:44:16 kim21017 Exp $
 * @since   1.0
 *
 */
public class MaTableDetailDTO extends BaseDTO
{
	/** ������ ���̺�������ID */
	private String tableMId 			= "";
	/** ���̺� */
	private String tableNo 			    = "";
	/** ���̺�� */
	private String tableDesc 			= "";
	/** ������ */
	private String creBy 			    = "";
	/** ������ */
	private String creByDesc	        = "";
	/** �������� */
	private String creDate 			    = "";
	/** �󼼼��� */
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
