package common.bean;

import java.io.Serializable;

import common.util.CommonUtil;

/**
 * 모든 DTO 의 상의 DTO 공통으로 사용되는 field는 이곳에 추가한다. 
 * @author javaworker
 * @version $Id: BaseDTO.java,v 1.1 2013/08/30 09:13:54 javaworker Exp $
 * @since 1.0
 */
public class BaseDTO implements Serializable
{
    /** Grid 상태컬럼 */
    protected char dtStatus;
    /** ENTER BY */
    protected String enterBy;
    
    /** 사용자 언어 */
    private String userLang = "";
    /** 회사코드 */
    private String compNo = "";
    /** IF DATA */
    private String data      = "";
    /** dhtmlx grid paging */
    private String firstRow = "";
    
    /** 조회시 Max DB Count 만큼 조회 */
    private boolean isLoadMaxCount = false;
    
    private String orderBy  = "";
    
    private String direction    = "";
    /** Login User Info */
    private User loginUser	= null;
    /** Mobile Common Search Text Field */
    private String searchText = "";
    
    /** MultiSelect ID */
	private String multiKey 	= "";
	/** MultiSelect DESC */
	private String multiDesc 	= "";

	/** Grid Updaet Status */
	private String nativeeditor	= "";
	
	private String auditKey    = "";

	
	/** Enhance Field */
	
	private String Z01        = "";
	
	private String Z02        = "";
	
	private String Z03        = "";
	
	private String Z04        = "";
	
	private String Z05        = "";
	
	private String Z06        = "";
	
	private String Z07        = "";
	
	private String Z08        = "";
	
	private String Z09        = "";
	
	private String Z10        = "";
	
	private String Z11        = "";
	
	private String Z12        = "";
	
	private String Z13        = "";
	
	private String Z14        = "";
	
	private String Z15        = "";
	
	private String Z16        = "";
	
	private String Z17        = "";
	
	private String Z18        = "";
	
	private String Z19        = "";
	/**
	 * 날짜형 
	 */
	private String Z20        = "";
	
	private String Z21        = "";
	
	private String Z22        = "";
	
	private String Z23        = "";

	private String Z24        = "";
	/**
	 * 숫자형 
	 */
	private String Z25        = "";
	
	private String Z26        = "";
	
	private String Z27        = "";
	
	private String Z28        = "";
	
	private String Z29        = "";
	
	private String Z30        = "";

    public String getAuditKey()
    {
        return auditKey;
    }
    public void setAuditKey(String auditKey)
    {
        this.auditKey = auditKey;
    }
    public String getNativeeditor() {
		return nativeeditor;
	}
	public void setNativeeditor(String nativeeditor) {
		this.nativeeditor = nativeeditor;
	}
	
	public String getMultiKey() {
		return multiKey;
	}

	public void setMultiKey(String multiKey) {
		this.multiKey = multiKey;
	}

	public String getMultiDesc() {
		return multiDesc;
	}

	public void setMultiDesc(String multiDesc) {
		this.multiDesc = multiDesc;
	}

	public User getLoginUser() {
		return loginUser;
	}

	public void setLoginUser(User loginUser) {
		this.loginUser = loginUser;
	}

	
	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	public String getOrderBy()
    {
        return orderBy;
    }

    public void setOrderBy(String orderBy)
    {
        this.orderBy = orderBy;
    }

    public String getDirection()
    {
        return direction;
    }

    public void setDirection(String direction)
    {
        this.direction = direction;
    }

    public String getFirstRow()
    {
        return firstRow;
    }

    public void setFirstRow(String firstRow)
    {
        this.firstRow = firstRow;
    }

    public boolean getIsLoadMaxCount()
    {
        return isLoadMaxCount;
    }

    public void setIsLoadMaxCount(boolean isLoadMaxCount)
    {
        this.isLoadMaxCount = isLoadMaxCount;
    }
    public char getDtStatus()
    {
        return dtStatus;
    }

    public void setDtStatus(char dtStatus)
    {
        this.dtStatus = dtStatus;
    }

    public String getEnterBy()
    {
        return enterBy;
    }

    public void setEnterBy(String enterBy)
    {
        this.enterBy = enterBy;
    }

    public String getUserLang()
    {
        return userLang;
    }

    public void setUserLang(String userLang)
    {
        this.userLang = userLang;
    }

	public String getCompNo() 
	{
		return compNo;
	}

	public void setCompNo(String compNo) 
	{
		this.compNo = compNo;
	}
    public String getZ01()
    {
        return Z01;
    }
    public void setZ01(String z01)
    {
        Z01 = z01;
    }
    public String getZ02()
    {
        return Z02;
    }
    public void setZ02(String z02)
    {
        Z02 = z02;
    }
    public String getZ03()
    {
        return Z03;
    }
    public void setZ03(String z03)
    {
        Z03 = z03;
    }
    public String getZ04()
    {
        return Z04;
    }
    public void setZ04(String z04)
    {
        Z04 = z04;
    }
    public String getZ05()
    {
        return Z05;
    }
    public void setZ05(String z05)
    {
        Z05 = z05;
    }
    public String getZ06()
    {
        return Z06;
    }
    public void setZ06(String z06)
    {
        Z06 = z06;
    }
    public String getZ07()
    {
        return Z07;
    }
    public void setZ07(String z07)
    {
        Z07 = z07;
    }
    public String getZ08()
    {
        return Z08;
    }
    public void setZ08(String z08)
    {
        Z08 = z08;
    }
    public String getZ09()
    {
        return Z09;
    }
    public void setZ09(String z09)
    {
        Z09 = z09;
    }
    public String getZ10()
    {
        return Z10;
    }
    public void setZ10(String z10)
    {
        Z10 = z10;
    }
    public String getZ11()
    {
        return Z11;
    }
    public void setZ11(String z11)
    {
        Z11 = z11;
    }
    public String getZ12()
    {
        return Z12;
    }
    public void setZ12(String z12)
    {
        Z12 = z12;
    }
    public String getZ13()
    {
        return Z13;
    }
    public void setZ13(String z13)
    {
        Z13 = z13;
    }
    public String getZ14()
    {
        return Z14;
    }
    public void setZ14(String z14)
    {
        Z14 = z14;
    }
    public String getZ15()
    {
        return Z15;
    }
    public void setZ15(String z15)
    {
        Z15 = z15;
    }
    public String getZ16()
    {
        return Z16;
    }
    public void setZ16(String z16)
    {
        Z16 = z16;
    }
    public String getZ17()
    {
        return Z17;
    }
    public void setZ17(String z17)
    {
        Z17 = z17;
    }
    public String getZ18()
    {
        return Z18;
    }
    public void setZ18(String z18)
    {
        Z18 = z18;
    }
    public String getZ19()
    {
        return Z19;
    }
    public void setZ19(String z19)
    {
        Z19 = z19;
    }
    public String getZ20()
    {
        return Z20;
    }
    public void setZ20(String z20)
    {
        Z20 = z20;
    }
    public String getZ21()
    {
        return Z21;
    }
    public void setZ21(String z21)
    {
        Z21 = CommonUtil.convertDate(z21);
    }
    public String getZ22()
    {
        return Z22;
    }
    public void setZ22(String z22)
    {
        Z22 = CommonUtil.convertDate(z22);
    }
    public String getZ23()
    {
        return Z23;
    }
    public void setZ23(String z23)
    {
        Z23 = CommonUtil.convertDate(z23);
    }
    public String getZ24()
    {
        return Z24;
    }
    public void setZ24(String z24)
    {
        Z24 = CommonUtil.convertDate(z24);
    }
    public String getZ25()
    {
        return Z25;
    }
    public void setZ25(String z25)
    {
        Z25 = CommonUtil.convertDate(z25);
    }
    public String getZ26()
    {
        return Z26;
    }
    public void setZ26(String z26)
    {
        Z26 = CommonUtil.convertMoney(z26);
    }
    public String getZ27()
    {
        return Z27;
    }
    public void setZ27(String z27)
    {
        Z27 = CommonUtil.convertMoney(z27);
    }
    public String getZ28()
    {
        return Z28;
    }
    public void setZ28(String z28)
    {
        Z28 = CommonUtil.convertMoney(z28);
    }
    public String getZ29()
    {
        return Z29;
    }
    public void setZ29(String z29)
    {
        Z29 = CommonUtil.convertMoney(z29);
    }
    public String getZ30()
    {
        return Z30;
    }
    public void setZ30(String z30)
    {
        Z30 = CommonUtil.convertMoney(z30);
    }
    
	
}
