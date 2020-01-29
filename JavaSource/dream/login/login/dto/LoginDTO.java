/**
 * 
 */
package dream.login.login.dto;

import common.bean.BaseDTO;

/**
 * Login DTO
 * @author  javaworker
 * @version $Id: LoginDTO.java,v 1.1 2013/08/30 09:15:04 javaworker Exp $
 * @since   1.0
 *
 */
public class LoginDTO
        extends BaseDTO
{
    /** User Login UserID */
    private String userNo    = "";
    private String vUserNo    = "";
    /** User Login UserIPassWord */
    private String passWord  = "";
    private String newPassWord  = "";
    private String validPassWord  = "";
    private String otp = "";
    /** Locale */
    private String locale = null;
    /** 회사코드 */
    private String compNo = null;   
    /** 회사Desc */
    private String compDesc = ""; 
    /** Locale */
    private String localeDesc   = "";
    private String valid    = "";
    
    /** device ID */
    private String deviceId = "";
    /** service type */
    private String serviceType = "";
    /** Password Secret */
    private String pWord	= "";
    /** local date */
    private long localTime;
    
    public long getLocalTime()
    {
        return localTime;
    }
    public void setLocalTime(long localTime)
    {
        this.localTime = localTime;
    }
    public String getOtp()
    {
        return otp;
    }
    public void setOtp(String otp)
    {
        this.otp = otp;
    }
    public String getpWord() {
		return pWord;
	}
	public void setpWord(String pWord) {
		this.pWord = pWord;
	}
	public String getServiceType() {
		return serviceType;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
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
	public String getCompDesc() {
		return compDesc;
	}
	public void setCompDesc(String compDesc) {
		this.compDesc = compDesc;
	}
}