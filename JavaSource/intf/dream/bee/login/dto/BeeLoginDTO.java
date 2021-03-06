package intf.dream.bee.login.dto;

import common.bean.BaseDTO;

/**
 * Android Login DTO
 * @author  javaworker
 * @version $Id: BeeLoginDTO.java,v 1.1 2013/08/30 09:15:04 javaworker Exp $
 * @since   1.0
 *
 */
public class BeeLoginDTO
        extends BaseDTO
{
    /** User Login UserID */
    private String userNo    = "";
    private String vUserNo    = "";
    /** User Login UserIPassWord */
    private String passWord  = "";
    private String newPassWord  = "";
    private String validPassWord  = "";
    /** Locale */
    private String locale = null;
    /** �����ڵ� */
    private String compNo = null;   
    /** Locale */
    private String localeDesc   = "";
    private String valid    = "";
    
    public String getNewPassWord() {
		return newPassWord;
	}
	public void setNewPassWord(String newPassWord) {
		this.newPassWord = newPassWord;
	}
	public String getValidPassWord() {
		return validPassWord;
	}
	public void setValidPassWord(String validPassWord) {
		this.validPassWord = validPassWord;
	}
	public String getValid() {
		return valid;
	}
	public void setValid(String valid) {
		this.valid = valid;
	}
	public String getvUserNo() {
		return vUserNo;
	}
	public void setvUserNo(String vUserNo) {
		this.vUserNo = vUserNo;
	}
	public String getLocaleDesc()
    {
        return localeDesc;
    }
    public void setLocaleDesc(String localeDesc)
    {
        this.localeDesc = localeDesc;
    }
    public String getCompNo() {
		return compNo;
	}
	public void setCompNo(String compNo) {
		this.compNo = compNo;
	}
    public String getUserNo()
    {
        return userNo;
    }
    public void setUserNo(String userNo)
    {
        this.userNo = userNo;
    }
    public String getPassWord()
    {
        return passWord;
    }
    public void setPassWord(String passWord)
    {
        this.passWord = passWord;
    }
    public String getLocale()
    {
        return locale;
    }
    public void setLocale(String locale)
    {
        this.locale = locale;
    }
}