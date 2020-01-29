package dream.mgr.usrgrp.dto;

import common.bean.BaseDTO;
/**
 * 화면권한설정
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 *
 */
public class MgrUsrGrpPageAuthDTO extends BaseDTO
{
	/**filter 권한그룹 ID*/
	private String filterUsrgrpId          = "";
	/**filter 권한그룹명*/
	private String filterGroupName         = "";
	/**filter 서비스구분*/
	private String filterServiceType       = "";
	/**filter 서비스구분명*/
	private String filterServiceTypeDesc   = "";
	/**filter 화면 파일명*/
	private String filterFileName          = "";
	/**filter 권한여부*/
	private String filterIsAuth            = "";
	/**화면 ID*/
	private String pageId                  = "";
	/**권한그룹 ID*/
	private String usrgrpId                = "";
	/**화면권한 ID*/
	private String ugpgauId                = "";
	/**권한그룹 NO*/
	private String usrgrpNo                = "";
	/**권한그룹명*/
	private String groupName               = "";
	/**화면 파일명*/
	private String fileName                = "";
	/**화면명*/
	private String pageDesc                = "";
	/**서비스구분*/
	private String serviceType             = "";
	/**서비스구분명*/
	private String serviceTypeDesc         = "";
	/**권한여부*/
	private String isAuth                  = "";
	/**비고*/
	private String remark                  = "";
	
    public String getFilterUsrgrpId()
    {
        return filterUsrgrpId;
    }
    public void setFilterUsrgrpId(String filterUsrgrpId)
    {
        this.filterUsrgrpId = filterUsrgrpId;
    }
    public String getFilterGroupName()
    {
        return filterGroupName;
    }
    public void setFilterGroupName(String filterGroupName)
    {
        this.filterGroupName = filterGroupName;
    }
    public String getFilterServiceType()
    {
        return filterServiceType;
    }
    public void setFilterServiceType(String filterServiceType)
    {
        this.filterServiceType = filterServiceType;
    }
    public String getFilterServiceTypeDesc()
    {
        return filterServiceTypeDesc;
    }
    public void setFilterServiceTypeDesc(String filterServiceTypeDesc)
    {
        this.filterServiceTypeDesc = filterServiceTypeDesc;
    }
    public String getFilterFileName()
    {
        return filterFileName;
    }
    public void setFilterFileName(String filterFileName)
    {
        this.filterFileName = filterFileName;
    }
    public String getFilterIsAuth()
    {
        return filterIsAuth;
    }
    public void setFilterIsAuth(String filterIsAuth)
    {
        this.filterIsAuth = filterIsAuth;
    }
    public String getPageId()
    {
        return pageId;
    }
    public void setPageId(String pageId)
    {
        this.pageId = pageId;
    }
    public String getUsrgrpId()
    {
        return usrgrpId;
    }
    public void setUsrgrpId(String usrgrpId)
    {
        this.usrgrpId = usrgrpId;
    }
    public String getUgpgauId()
    {
        return ugpgauId;
    }
    public void setUgpgauId(String ugpgauId)
    {
        this.ugpgauId = ugpgauId;
    }
    public String getUsrgrpNo()
    {
        return usrgrpNo;
    }
    public void setUsrgrpNo(String usrgrpNo)
    {
        this.usrgrpNo = usrgrpNo;
    }
    public String getGroupName()
    {
        return groupName;
    }
    public void setGroupName(String groupName)
    {
        this.groupName = groupName;
    }
    public String getFileName()
    {
        return fileName;
    }
    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }
    public String getPageDesc()
    {
        return pageDesc;
    }
    public void setPageDesc(String pageDesc)
    {
        this.pageDesc = pageDesc;
    }
    public String getServiceType()
    {
        return serviceType;
    }
    public void setServiceType(String serviceType)
    {
        this.serviceType = serviceType;
    }
    public String getServiceTypeDesc()
    {
        return serviceTypeDesc;
    }
    public void setServiceTypeDesc(String serviceTypeDesc)
    {
        this.serviceTypeDesc = serviceTypeDesc;
    }
    public String getIsAuth()
    {
        return isAuth;
    }
    public void setIsAuth(String isAuth)
    {
        this.isAuth = isAuth;
    }
    public String getRemark()
    {
        return remark;
    }
    public void setRemark(String remark)
    {
        this.remark = remark;
    }
	
}
