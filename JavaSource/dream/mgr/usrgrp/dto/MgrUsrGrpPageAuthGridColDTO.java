package dream.mgr.usrgrp.dto;
import common.bean.BaseDTO;
/**
 * 화면권한설정상세탭버튼권한
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 *
 */
public class MgrUsrGrpPageAuthGridColDTO extends BaseDTO
{
	/**화면 ID*/
	private String pageId                  	= "";
	/**권한그룹 ID*/
	private String usrgrpId                	= "";
	/** 그리드컬럼 권한 ID (key) */
	private String pggridcolId 		        = "";
	/** 그리드컬럼 ID */
	private String columnId 		        = "";
	/** 그리드컬럼명 */
	private String labelDesc                = "";
	/**권한제한*/
	private String authLimitTypeId	        = "";
	private String authLimitTypeDesc        = "";
	/** 권한 */
	private String ugpgridcolauId        	= "";
	private String gridId					= "";

	public String getGridId() {
		return gridId;
	}
	public void setGridId(String gridId) {
		this.gridId = gridId;
	}
	public String getUgpgridcolauId() {
		return ugpgridcolauId;
	}
	public void setUgpgridcolauId(String ugpgridcolauId) {
		this.ugpgridcolauId = ugpgridcolauId;
	}
	public String getColumnId() {
		return columnId;
	}
	public void setColumnId(String columnId) {
		this.columnId = columnId;
	}
	public String getPageId() {
		return pageId;
	}
	public void setPageId(String pageId) {
		this.pageId = pageId;
	}
	public String getUsrgrpId() {
		return usrgrpId;
	}
	public void setUsrgrpId(String usrgrpId) {
		this.usrgrpId = usrgrpId;
	}
	public String getPggridcolId() {
		return pggridcolId;
	}
	public void setPggridcolId(String pggridcolId) {
		this.pggridcolId = pggridcolId;
	}
	public String getLabelDesc() {
		return labelDesc;
	}
	public void setLabelDesc(String labelDesc) {
		this.labelDesc = labelDesc;
	}
	public String getAuthLimitTypeId() {
		return authLimitTypeId;
	}
	public void setAuthLimitTypeId(String authLimitTypeId) {
		this.authLimitTypeId = authLimitTypeId;
	}
	public String getAuthLimitTypeDesc() {
		return authLimitTypeDesc;
	}
	public void setAuthLimitTypeDesc(String authLimitTypeDesc) {
		this.authLimitTypeDesc = authLimitTypeDesc;
	}
}
