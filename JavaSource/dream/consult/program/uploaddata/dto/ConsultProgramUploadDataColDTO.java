package dream.consult.program.uploaddata.dto;

import common.bean.BaseDTO;

/**
 * �÷� - DTO
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 *
 */
public class ConsultProgramUploadDataColDTO extends BaseDTO
{
    /**Key �����÷� ID */ 
    private String excelColId         = "";
    /** �������� ID */ 
    private String excelTabId        = "";
    
    /** ����*/
    private String ordNo              = "";    
    /** Excel �÷���*/
    private String excelColName       = "";    
    /** �÷���*/
    private String tableColName       = "";    
    /** Ÿ�� ID*/
    private String tableColTypeId       = "";    
    /** Ÿ��*/
    private String tableColType       = "";    
    /** ����*/
    private String tableColSize       = "";    
    /** �÷�����*/
    private String excelColComments   = "";    
    /** KEY�÷����� ID*/
    private String isKeyId              = "";    
    /** KEY�÷�����*/
    private String isKey              = "";   
    /** System�÷����� ID*/
    private String isSystemId              = "";    
    /** System�÷�����*/
    private String isSystem              = "";    
    /** Editable�÷����� ID*/
    private String isEditableId        = "";    
    /** Editable�÷�����*/
    private String isEditable        = "";    
    /** Display�÷����� ID*/
    private String isSystemDisplayId          = "";    
    /** Display�÷�����*/
    private String isSystemDisplay          = "";    
    /** ��뿩��*/
    private String isUse          = "";    
    /** ���*/
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
