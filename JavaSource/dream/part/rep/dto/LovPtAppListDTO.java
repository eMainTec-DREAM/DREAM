package dream.part.rep.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 수리기안품의서 팝업 DTO
 * @author  ssong
 * @version $Id:  $
 * @since   1.0
 */
public class LovPtAppListDTO extends BaseDTO
{
    /** 품의번호 */
    private String ptAppId 		= "";
    /** 제목 */
    private String title 		= "";
    /** 접수일자 */
    private String recDate 		= "";
    /** extCode1 */
    private String extCode1 	= "";
    /** extCode2 */
    private String extCode2 	= "";
    /** code type */
    private String codeType 	= "";
    
    public String getPtAppId()
    {
        return ptAppId;
    }
    public void setPtAppId(String ptAppId)
    {
        this.ptAppId = ptAppId;
    }
    public String getTitle()
    {
        return title;
    }
    public void setTitle(String title)
    {
        this.title = title;
    }
    public String getRecDate()
    {
        return recDate;
    }
    public void setRecDate(String recDate)
    {
        this.recDate =  CommonUtil.convertDate(recDate);
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
