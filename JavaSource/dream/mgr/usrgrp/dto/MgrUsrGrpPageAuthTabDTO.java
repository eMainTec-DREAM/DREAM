package dream.mgr.usrgrp.dto;

import common.bean.BaseDTO;
/**
 * 화면권한설정상세탭탭권한
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 *
 */
public class MgrUsrGrpPageAuthTabDTO extends BaseDTO
{
	/**화면 ID*/
	private String pageId                  = "";
	/**화면탭 ID*/
	private String pgpageId                = "";
	/**권한그룹 ID*/
	private String usrgrpId                = "";
	/**화면탭권한 ID*/
	private String ugpgpgauId              = "";
	/**탭페이지 ID*/
	private String fileName                = "";
	/**탭페이지 명*/
	private String tabName                 = "";
	/**권한여부*/
	private String isAuth                  = "";
	/**권한여부*/
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
