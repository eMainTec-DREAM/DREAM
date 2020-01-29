package mobile.dream.org.wrkgrp.dto;

import common.bean.BaseDTO;

/**
 * 작업그룹  팝업 DTO
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public class OrgWkCtrLovListDTO extends BaseDTO
{
    /** 작업그룹 No */
    private String wkCtrNo     = "";
    /** 작업그룹 명 */
    private String wkCtrDesc   = "";
    /** extCode1 */
    private String extCode1 	= "";
    /** extCode2 */
    private String extCode2 	= "";
    /** code type */
    private String codeType 	= "";
    
    public String getWkCtrNo() {
		return wkCtrNo;
	}
	public void setWkCtrNo(String wkCtrNo) {
		this.wkCtrNo = wkCtrNo;
	}
	public String getWkCtrDesc() {
		return wkCtrDesc;
	}
	public void setWkCtrDesc(String wkCtrDesc) {
		this.wkCtrDesc = wkCtrDesc;
	}
	public String getExtCode1()
    {
        return extCode1;
    }
    public void setExtCode1(String extCode1)
    {
        this.extCode1 = extCode1;
    }
    public String getExtCode2()
    {
        return extCode2;
    }
    public void setExtCode2(String extCode2)
    {
        this.extCode2 = extCode2;
    }
    public String getCodeType()
    {
        return codeType;
    }
    public void setCodeType(String codeType)
    {
        this.codeType = codeType;
    }
    

}
