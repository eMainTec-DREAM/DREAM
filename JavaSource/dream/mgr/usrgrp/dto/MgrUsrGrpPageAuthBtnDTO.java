package dream.mgr.usrgrp.dto;

import common.bean.BaseDTO;
/**
 * ȭ����Ѽ������ǹ�ư����
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 *
 */
public class MgrUsrGrpPageAuthBtnDTO extends BaseDTO
{
	/**ȭ�� ID*/
	private String pageId                  = "";
	/**ȭ���ư ID*/
	private String pgbtnId                 = "";
	/**���ѱ׷� ID*/
	private String usrgrpId                = "";
	/**ȭ���ư���� ID*/
	private String ugpgbtnauId             = "";
	/**��ư NO*/
	private String buttonNo                = "";
	/**��ư��*/
	private String buttonName              = "";
	/**��ư��ġ*/
	private String buttonLoc               = "";
	/**���ѿ���*/
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
