package dream.mgr.usrgrp.dto;

import common.bean.BaseDTO;
/**
 * ȭ����Ѽ��������Ǳ���
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 *
 */
public class MgrUsrGrpPageAuthTabDTO extends BaseDTO
{
	/**ȭ�� ID*/
	private String pageId                  = "";
	/**ȭ���� ID*/
	private String pgpageId                = "";
	/**���ѱ׷� ID*/
	private String usrgrpId                = "";
	/**ȭ���Ǳ��� ID*/
	private String ugpgpgauId              = "";
	/**�������� ID*/
	private String fileName                = "";
	/**�������� ��*/
	private String tabName                 = "";
	/**���ѿ���*/
	private String isAuth                  = "";
	/**���ѿ���*/
	private String pageAuth                = "";
	
    public String getPageId()
    {
        return pageId;
    }
    public void setPageId(String pageId)
    {
        this.pageId = pageId;
    }
    public String getPgpageId()
    {
        return pgpageId;
    }
    public void setPgpageId(String pgpageId)
    {
        this.pgpageId = pgpageId;
    }
    public String getUsrgrpId()
    {
        return usrgrpId;
    }
    public void setUsrgrpId(String usrgrpId)
    {
        this.usrgrpId = usrgrpId;
    }
    public String getUgpgpgauId()
    {
        return ugpgpgauId;
    }
    public void setUgpgpgauId(String ugpgpgauId)
    {
        this.ugpgpgauId = ugpgpgauId;
    }
    public String getFileName()
    {
        return fileName;
    }
    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }
    public String getTabName()
    {
        return tabName;
    }
    public void setTabName(String tabName)
    {
        this.tabName = tabName;
    }
    public String getIsAuth()
    {
        return isAuth;
    }
    public void setIsAuth(String isAuth)
    {
        this.isAuth = isAuth;
    }
    public String getPageAuth()
    {
        return pageAuth;
    }
    public void setPageAuth(String pageAuth)
    {
        this.pageAuth = pageAuth;
    }
	
}
