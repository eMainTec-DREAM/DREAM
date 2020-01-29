package dream.mgr.usrgrp.dto;

import common.bean.BaseDTO;
/**
 * 화면권한설정상세탭버튼권한
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 *
 */
public class MgrUsrGrpPageAuthBtnDTO extends BaseDTO
{
	/**화면 ID*/
	private String pageId                  = "";
	/**화면버튼 ID*/
	private String pgbtnId                 = "";
	/**권한그룹 ID*/
	private String usrgrpId                = "";
	/**화면버튼권한 ID*/
	private String ugpgbtnauId             = "";
	/**버튼 NO*/
	private String buttonNo                = "";
	/**버튼명*/
	private String buttonName              = "";
	/**버튼위치*/
	private String buttonLoc               = "";
	/**권한여부*/
	private String isAuth                  = "";
	
    public String getPageId()
    {
        return pageId;
    }
    public void setPageId(String pageId)
    {
        this.pageId = pageId;
    }
    public String getPgbtnId()
    {
        return pgbtnId;
    }
    public void setPgbtnId(String pgbtnId)
    {
        this.pgbtnId = pgbtnId;
    }
    public String getUsrgrpId()
    {
        return usrgrpId;
    }
    public void setUsrgrpId(String usrgrpId)
    {
        this.usrgrpId = usrgrpId;
    }
    public String getUgpgbtnauId()
    {
        return ugpgbtnauId;
    }
    public void setUgpgbtnauId(String ugpgbtnauId)
    {
        this.ugpgbtnauId = ugpgbtnauId;
    }
    public String getButtonNo()
    {
        return buttonNo;
    }
    public void setButtonNo(String buttonNo)
    {
        this.buttonNo = buttonNo;
    }
    public String getButtonName()
    {
        return buttonName;
    }
    public void setButtonName(String buttonName)
    {
        this.buttonName = buttonName;
    }
    public String getButtonLoc()
    {
        return buttonLoc;
    }
    public void setButtonLoc(String buttonLoc)
    {
        this.buttonLoc = buttonLoc;
    }
    public String getIsAuth()
    {
        return isAuth;
    }
    public void setIsAuth(String isAuth)
    {
        this.isAuth = isAuth;
    }
	
}
