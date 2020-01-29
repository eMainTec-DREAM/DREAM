package dream.mgr.usrgrp.dto;

import common.bean.BaseDTO;

/**
 * 권한 팝업 DTO
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public class LovUsrGrpListDTO extends BaseDTO
{
    /** 권한 Desc */
    private String usrGrpDesc     = "";
    /** extCode1 */
    private String extCode1 	= "";
    /** extCode2 */
    private String extCode2 	= "";
    /** code type */
    private String codeType 	= "";
    
	public String getUsrGrpDesc() {
		return usrGrpDesc;
	}
	public void setUsrGrpDesc(String usrGrpDesc) {
		this.usrGrpDesc = usrGrpDesc;
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
