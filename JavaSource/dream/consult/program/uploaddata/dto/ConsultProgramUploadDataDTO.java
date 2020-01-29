package dream.consult.program.uploaddata.dto;

import common.bean.BaseDTO;

/**
 * 업로드데이타 공통 DTO
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 * 
 */
public class ConsultProgramUploadDataDTO extends BaseDTO
{
	/** 필터 엑셀유형 NO */
    private String filterExcelTabNo       = "";
	/** 필터 엑셀유형 NAME */
	private String filterExcelTabName 	= "";
	/** 필터 테이블 NAME */
    private String filterTableName         = "";
	/** 필터 프로그램명 */
	private String filterUploadPgmName  		= "";
	/** 필터 Config Setting 사용 */
	private String filterIsUseConfig  		= "";
	/** 사용여부 */
	private String filterIsUse  		= "";
	
	/** 엑셀유형 ID */
	private String excelTabId 			= "";
    /** 엑셀유형 NO */
    private String excelTabNo      = "";
    /** 엑셀유형명 */
    private String excelTabName    = "";
    /** 테이블명 */
    private String tableName        = "";
    /** 프로그램명 */
    private String uploadPgmName    = "";
    /** 상태 ID */
    private String excelListStatusId  = "";
    /** 상태 */
    private String excelListStatus  = "";
    /** Config Setting 사용 */
    private String isUseConfig            = "";
    /** 사용여부 */
    private String isUse            = "";
    /** 비고 */
    private String remark            = "";
    /** 엑셀 시트명 */
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
