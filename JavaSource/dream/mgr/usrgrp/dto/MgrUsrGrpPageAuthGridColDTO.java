package dream.mgr.usrgrp.dto;
import common.bean.BaseDTO;
/**
 * ȭ����Ѽ������ǹ�ư����
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 *
 */
public class MgrUsrGrpPageAuthGridColDTO extends BaseDTO
{
	/**ȭ�� ID*/
	private String pageId                  	= "";
	/**���ѱ׷� ID*/
	private String usrgrpId                	= "";
	/** �׸����÷� ���� ID (key) */
	private String pggridcolId 		        = "";
	/** �׸����÷� ID */
	private String columnId 		        = "";
	/** �׸����÷��� */
	private String labelDesc                = "";
	/**��������*/
	private String authLimitTypeId	        = "";
	private String authLimitTypeDesc        = "";
	/** ���� */
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
