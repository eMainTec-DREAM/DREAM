package dream.consult.program.uploaddata.dto;

import common.bean.BaseDTO;

/**
 * ���ε嵥��Ÿ ���� DTO
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 * 
 */
public class ConsultProgramUploadDataDTO extends BaseDTO
{
	/** ���� �������� NO */
    private String filterExcelTabNo       = "";
	/** ���� �������� NAME */
	private String filterExcelTabName 	= "";
	/** ���� ���̺� NAME */
    private String filterTableName         = "";
	/** ���� ���α׷��� */
	private String filterUploadPgmName  		= "";
	/** ���� Config Setting ��� */
	private String filterIsUseConfig  		= "";
	/** ��뿩�� */
	private String filterIsUse  		= "";
	
	/** �������� ID */
	private String excelTabId 			= "";
    /** �������� NO */
    private String excelTabNo      = "";
    /** ���������� */
    private String excelTabName    = "";
    /** ���̺�� */
    private String tableName        = "";
    /** ���α׷��� */
    private String uploadPgmName    = "";
    /** ���� ID */
    private String excelListStatusId  = "";
    /** ���� */
    private String excelListStatus  = "";
    /** Config Setting ��� */
    private String isUseConfig            = "";
    /** ��뿩�� */
    private String isUse            = "";
    /** ��� */
    private String remark            = "";
    /** ���� ��Ʈ�� */
    private String sheetName            = "";
	
    public String getSheetName()
    {
        return sheetName;
    }
    public void setSheetName(String sheetName)
    {
        this.sheetName = sheetName;
    }
    public String getFilterIsUseConfig()
    {
        return filterIsUseConfig;
    }
    public void setFilterIsUseConfig(String filterIsUseConfig)
    {
        this.filterIsUseConfig = filterIsUseConfig;
    }
    public String getIsUseConfig()
    {
        return isUseConfig;
    }
    public void setIsUseConfig(String isUseConfig)
    {
        this.isUseConfig = isUseConfig;
    }
    public String getExcelTabNo()
    {
        return excelTabNo;
    }
    public void setExcelTabNo(String excelTabNo)
    {
        this.excelTabNo = excelTabNo;
    }
    public String getExcelTabName()
    {
        return excelTabName;
    }
    public void setExcelTabName(String excelTabName)
    {
        this.excelTabName = excelTabName;
    }
    public String getTableName()
    {
        return tableName;
    }
    public void setTableName(String tableName)
    {
        this.tableName = tableName;
    }
    public String getUploadPgmName()
    {
        return uploadPgmName;
    }
    public void setUploadPgmName(String uploadPgmName)
    {
        this.uploadPgmName = uploadPgmName;
    }
    public String getExcelListStatusId()
    {
        return excelListStatusId;
    }
    public void setExcelListStatusId(String excelListStatusId)
    {
        this.excelListStatusId = excelListStatusId;
    }
    public String getExcelListStatus()
    {
        return excelListStatus;
    }
    public void setExcelListStatus(String excelListStatus)
    {
        this.excelListStatus = excelListStatus;
    }
    public String getIsUse()
    {
        return isUse;
    }
    public void setIsUse(String isUse)
    {
        this.isUse = isUse;
    }
    public String getRemark()
    {
        return remark;
    }
    public void setRemark(String remark)
    {
        this.remark = remark;
    }
    public String getFilterIsUse()
    {
        return filterIsUse;
    }
    public void setFilterIsUse(String filterIsUse)
    {
        this.filterIsUse = filterIsUse;
    }
    public String getExcelTabId()
    {
        return excelTabId;
    }
    public void setExcelTabId(String excelTabId)
    {
        this.excelTabId = excelTabId;
    }
    public String getFilterExcelTabNo()
    {
        return filterExcelTabNo;
    }
    public void setFilterExcelTabNo(String filterExcelTabNo)
    {
        this.filterExcelTabNo = filterExcelTabNo;
    }
    public String getFilterExcelTabName()
    {
        return filterExcelTabName;
    }
    public void setFilterExcelTabName(String filterExcelTabName)
    {
        this.filterExcelTabName = filterExcelTabName;
    }
    public String getFilterTableName()
    {
        return filterTableName;
    }
    public void setFilterTableName(String filterTableName)
    {
        this.filterTableName = filterTableName;
    }
    public String getFilterUploadPgmName()
    {
        return filterUploadPgmName;
    }
    public void setFilterUploadPgmName(String filterUploadPgmName)
    {
        this.filterUploadPgmName = filterUploadPgmName;
    }
}
