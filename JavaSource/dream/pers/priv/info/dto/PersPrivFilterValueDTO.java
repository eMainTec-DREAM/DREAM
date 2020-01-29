package dream.pers.priv.info.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * �� dto
 * @author  euna0207
 * @version $Id$
 * @since   1.0
 */
public class PersPrivFilterValueDTO extends BaseDTO
{
	/** ���� ID */
	private String usrFilterValueId = "";
	/** LIST */
	/** �������� */
	private String filterCreDate	= "";
	private String filterCreEndDate	= "";
	/** ���� */
	private String filterTitle		= "";
	
    /** DETAIL */
	/** �������� */
    private String creDate 			= "";
    private String creTime 			= "";
    /** ȭ�� */
    private String fileName 		= "";
    private String pageId 			= "";
    /** �⺻�� */
    private String isDefault 		= "";
    /** ���� */
    private String title 			= "";
    /** ����ڸ� */
    private String userName 		= "";
    /** �����ID */
    private String userNo 			= "";
    
    private String setValue			= "";
    
	public String getFilterCreDate() {
		return filterCreDate;
	}
	public void setFilterCreDate(String filterCreDate) {
		this.filterCreDate = CommonUtil.convertDate(filterCreDate);
	}
	public String getFilterCreEndDate() {
		return filterCreEndDate;
	}
	public void setFilterCreEndDate(String filterCreEndDate) {
		this.filterCreEndDate = CommonUtil.convertDate(filterCreEndDate);
	}
	public String getFilterTitle() {
		return filterTitle;
	}
	public void setFilterTitle(String filterTitle) {
		this.filterTitle = filterTitle;
	}
	public String getPageId() {
		return pageId;
	}
	public void setPageId(String pageId) {
		this.pageId = pageId;
	}
	public String getCreTime() {
		return creTime;
	}
	public void setCreTime(String creTime) {
		this.creTime = creTime;
	}
	public String getSetValue() {
		return setValue;
	}
	public void setSetValue(String setValue) {
		this.setValue = setValue;
	}
	public String getUsrFilterValueId() {
		return usrFilterValueId;
	}
	public void setUsrFilterValueId(String usrFilterValueId) {
		this.usrFilterValueId = usrFilterValueId;
	}
	public String getCreDate() {
		return creDate;
	}
	public void setCreDate(String creDate) {
		this.creDate = CommonUtil.convertDateTime(creDate);
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
    
    
}