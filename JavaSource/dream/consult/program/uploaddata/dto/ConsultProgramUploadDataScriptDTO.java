package dream.consult.program.uploaddata.dto;

import common.bean.BaseDTO;

/**
 * ¿¢¼¿ ÂüÁ¶µ¥ÀÌÅ¸ - DTO
 * @author ghlee
 * @version $Id$
 * @since 1.0
 *
 */
public class ConsultProgramUploadDataScriptDTO extends BaseDTO
{
    /**Key ¿¢¼¿ ÂüÁ¶µ¥ÀÌÅ¸ Script ID */ 
    private String excelExScriptId   = "";
    /** ¿¢¼¿À¯Çü ID */ 
    private String excelTabId        = "";
    
    /** ¼ø¼­*/
    private String ordNo             = "";
    /** Á¦¸ñ*/
    private String description       = "";
    /** Sheet¸í*/
    private String sheetName         = "";
    /** »ç¿ë¿©ºÎ*/
    private String isUse             = "";
    /** script*/
    private String script            = "";
    /** ºñ°í*/
    private String remark            = "";
    
    public String getExcelExScriptId()
    {
        return excelExScriptId;
    }
    public void setExcelExScriptId(String excelExScriptId)
    {
        this.excelExScriptId = excelExScriptId;
    }
    public String getExcelTabId()
    {
        return excelTabId;
    }
    public void setExcelTabId(String excelTabId)
    {
        this.excelTabId = excelTabId;
    }
    public String getOrdNo()
    {
        return ordNo;
    }
    public void setOrdNo(String ordNo)
    {
        this.ordNo = ordNo;
    }
    public String getDescription()
    {
        return description;
    }
    public void setDescription(String description)
    {
        this.description = description;
    }
    public String getSheetName()
    {
        return sheetName;
    }
    public void setSheetName(String sheetName)
    {
        this.sheetName = sheetName;
    }
    public String getIsUse()
    {
        return isUse;
    }
    public void setIsUse(String isUse)
    {
        this.isUse = isUse;
    }
    public String getScript()
    {
        return script;
    }
    public void setScript(String script)
    {
        this.script = script;
    }
    public String getRemark()
    {
        return remark;
    }
    public void setRemark(String remark)
    {
        this.remark = remark;
    }
}
