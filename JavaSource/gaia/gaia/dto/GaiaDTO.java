/**
 * 
 */
package gaia.gaia.dto;

import common.bean.BaseDTO;

/**
 * Gaia DTO
 * @author  jung7126
 * @version $Id: LoginDTO.java,v 1.1 2013/08/30 09:15:04 javaworker Exp $
 * @since   1.0
 *
 */
public class GaiaDTO
        extends BaseDTO
{
    /** User Login UserID */
    private String userNo    = "";
    private String vUserNo    = "";
    /** User Login UserIPassWord */
    private String passWord  = "";
    /** Locale */
    private String locale = null;
    /** 공장코드 */
    private String compNo = null;   
    /** Locale */
    private String localeDesc   = "";
    private String valid    = "";
    
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