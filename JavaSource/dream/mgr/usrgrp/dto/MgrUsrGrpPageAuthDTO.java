package dream.mgr.usrgrp.dto;

import common.bean.BaseDTO;
/**
 * ȭ����Ѽ���
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 *
 */
public class MgrUsrGrpPageAuthDTO extends BaseDTO
{
	/**filter ���ѱ׷� ID*/
	private String filterUsrgrpId          = "";
	/**filter ���ѱ׷��*/
	private String filterGroupName         = "";
	/**filter ���񽺱���*/
	private String filterServiceType       = "";
	/**filter ���񽺱��и�*/
	private String filterServiceTypeDesc   = "";
	/**filter ȭ�� ���ϸ�*/
	private String filterFileName          = "";
	/**filter ���ѿ���*/
	private String filterIsAuth            = "";
	/**ȭ�� ID*/
	private String pageId                  = "";
	/**���ѱ׷� ID*/
	private String usrgrpId                = "";
	/**ȭ����� ID*/
	private String ugpgauId                = "";
	/**���ѱ׷� NO*/
	private String usrgrpNo                = "";
	/**���ѱ׷��*/
	private String groupName               = "";
	/**ȭ�� ���ϸ�*/
	private String fileName                = "";
	/**ȭ���*/
	private String pageDesc                = "";
	/**���񽺱���*/
	private String serviceType             = "";
	/**���񽺱��и�*/
	private String serviceTypeDesc         = "";
	/**���ѿ���*/
	private String isAuth                  = "";
	/**���*/
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
