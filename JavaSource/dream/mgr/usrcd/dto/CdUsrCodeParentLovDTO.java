package dream.mgr.usrcd.dto;

import common.bean.BaseDTO;
/**
 * ������ڵ� �θ� LOV DTO
 * @author kim21017
 * @version $Id: CdUsrCodeParentLovDTO.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 *
 */
public class CdUsrCodeParentLovDTO extends BaseDTO
{
	/** ������ڵ�*/
	private String cdUsrMId				= "";
	/** ������ڵ� dir type*/
	private String dirType				= "";
	/** ���� - ������ڵ� dir type*/
	private String filterDirType		= "";
	/** ���� - ������ڵ� �� */
	private String filterCdUsrMDesc		= "";
	
	public String getCdUsrMId() {
		return cdUsrMId;
	}
	public void setCdUsrMId(String cdUsrMId) {
		this.cdUsrMId = cdUsrMId;
	}
	public String getDirType() {
		return dirType;
	}
	public void setDirType(String dirType) {
		this.dirType = dirType;
	}
	public String getFilterDirType() {
		return filterDirType;
	}
	public void setFilterDirType(String filterDirType) {
		this.filterDirType = filterDirType;
	}
	public String getFilterCdUsrMDesc() {
		return filterCdUsrMDesc;
	}
	public void setFilterCdUsrMDesc(String filterCdUsrMDesc) {
		this.filterCdUsrMDesc = filterCdUsrMDesc;
	}
	
}
