package dream.consult.program.uploaddata.dto;

import common.bean.BaseDTO;

/**
 * 컬럼 - DTO
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 *
 */
public class ConsultProgramUploadDataColDTO extends BaseDTO
{
    /**Key 엑셀컬럼 ID */ 
    private String excelColId         = "";
    /** 엑셀유형 ID */ 
    private String excelTabId        = "";
    
    /** 순서*/
    private String ordNo              = "";    
    /** Excel 컬럼명*/
    private String excelColName       = "";    
    /** 컬럼명*/
    private String tableColName       = "";    
    /** 타입 ID*/
    private String tableColTypeId       = "";    
    /** 타입*/
    private String tableColType       = "";    
    /** 길이*/
    private String tableColSize       = "";    
    /** 컬럼설명*/
    private String excelColComments   = "";    
    /** KEY컬럼여부 ID*/
    private String isKeyId              = "";    
    /** KEY컬럼여부*/
    private String isKey              = "";   
    /** System컬럼여부 ID*/
    private String isSystemId              = "";    
    /** System컬럼여부*/
    private String isSystem              = "";    
    /** Editable컬럼여부 ID*/
    private String isEditableId        = "";    
    /** Editable컬럼여부*/
    private String isEditable        = "";    
    /** Display컬럼여부 ID*/
    private String isSystemDisplayId          = "";    
    /** Display컬럼여부*/
    private String isSystemDisplay          = "";    
    /** 사용여부*/
    private String isUse          = "";    
    /** 비고*/
    private String remark             = "";
    
    public String getTableColTypeId()
    {
        return tableColTypeId;
    }
    public void setTableColTypeId(String tableColTypeId)
    {
        this.tableColTypeId = tableColTypeId;
    }
    public String getIsKeyId()
    {
        return isKeyId;
    }
    public void setIsKeyId(String isKeyId)
    {
        this.isKeyId = isKeyId;
    }
    public String getIsSystemId()
    {
        return isSystemId;
    }
    public void setIsSystemId(String isSystemId)
    {
        this.isSystemId = isSystemId;
    }
    public String getIsSystem()
    {
        return isSystem;
    }
    public void setIsSystem(String isSystem)
    {
        this.isSystem = isSystem;
    }
    public String getIsEditableId()
    {
        return isEditableId;
    }
    public void setIsEditableId(String isEditableId)
    {
        this.isEditableId = isEditableId;
    }
    public String getIsEditable()
    {
        return isEditable;
    }
    public void setIsEditable(String isEditable)
    {
        this.isEditable = isEditable;
    }
    public String getIsSystemDisplayId()
    {
        return isSystemDisplayId;
    }
    public void setIsSystemDisplayId(String isSystemDisplayId)
    {
        this.isSystemDisplayId = isSystemDisplayId;
    }
    public String getIsSystemDisplay()
    {
        return isSystemDisplay;
    }
    public void setIsSystemDisplay(String isSystemDisplay)
    {
        this.isSystemDisplay = isSystemDisplay;
    }
    public String getIsUse()
    {
        return isUse;
    }
    public void setIsUse(String isUse)
    {
        this.isUse = isUse;
    }
    public String getExcelColId()
    {
        return excelColId;
    }
    public void setExcelColId(String excelColId)
    {
        this.excelColId = excelColId;
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
    public String getExcelColName()
    {
        return excelColName;
    }
    public void setExcelColName(String excelColName)
    {
        this.excelColName = excelColName;
    }
    public String getTableColName()
    {
        return tableColName;
    }
    public void setTableColName(String tableColName)
    {
        this.tableColName = tableColName;
    }
    public String getTableColType()
    {
        return tableColType;
    }
    public void setTableColType(String tableColType)
    {
        this.tableColType = tableColType;
    }
    public String getTableColSize()
    {
        return tableColSize;
    }
    public void setTableColSize(String tableColSize)
    {
        this.tableColSize = tableColSize;
    }
    public String getExcelColComments()
    {
        return excelColComments;
    }
    public void setExcelColComments(String excelColComments)
    {
        this.excelColComments = excelColComments;
    }
    public String getIsKey()
    {
        return isKey;
    }
    public void setIsKey(String isKey)
    {
        this.isKey = isKey;
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
