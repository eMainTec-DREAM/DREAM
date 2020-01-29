package dream.consult.program.setting.upload.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.bean.BaseDTO;
/**
 * Excel Data Upload - ���� DTO
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 *
 */
public class ConsultPgmSettingUpdDTO extends BaseDTO
{
	/**skey ID*/
	private String skeyId 			= "";
	/**key�÷���*/
	private String keyColName        = "";
	/**Filter Excel Tab ID*/
	private String filterExcelTabId 	= "";
	/**Filter Excel Tab DESC*/
	private String filterExcelTabDesc 		= "";
	/**Filter ���̺�� */
	private String filterTableName 		= "";
	/**Filter ���α׷���*/
	private String filterUploadPgmName 		= "";
	/**Filter ������Ʈ��*/
	private String filterSheetName 		= "";
	/**Filter �������ε�Ÿ��*/
	private String filterExcelUploadType   = "";
	/**Filter ������ */
	private String filterStartDate 		= "";
	/**Filter ������*/
	private String filterEndDate 		= "";
	/**Filter �������� */
	private String filterIsSuccess 		= "";
	/**Excel Column List */
	private List excelColList = new ArrayList<Map>();
	/**excel tab ID*/
    private String excelTabId       = "";
    /**excel tab No*/
    private String excelTabNo       = "";
    /**���̺��*/
    private String tableName      = "";
    /**���α׷���*/
    private String uploadPgmName    = "";
    /**������Ʈ��*/
    private String sheetName        = "";
    /**�������ε�Ÿ��*/
    private String excelUploadType        = "";
    /**param map*/
    private Map paramMap = new HashMap();
	
    public String getExcelTabNo()
    {
        return excelTabNo;
    }
    public void setExcelTabNo(String excelTabNo)
    {
        this.excelTabNo = excelTabNo;
    }
    public String getExcelUploadType()
    {
        return excelUploadType;
    }
    public void setExcelUploadType(String excelUploadType)
    {
        this.excelUploadType = excelUploadType;
    }
    public String getFilterExcelUploadType()
    {
        return filterExcelUploadType;
    }
    public void setFilterExcelUploadType(String filterExcelUploadType)
    {
        this.filterExcelUploadType = filterExcelUploadType;
    }
    public String getFilterSheetName()
    {
        return filterSheetName;
    }
    public void setFilterSheetName(String filterSheetName)
    {
        this.filterSheetName = filterSheetName;
    }
    public String getSheetName()
    {
        return sheetName;
    }
    public void setSheetName(String sheetName)
    {
        this.sheetName = sheetName;
    }
    public String getExcelTabId()
    {
        return excelTabId;
    }
    public void setExcelTabId(String excelTabId)
    {
        this.excelTabId = excelTabId;
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
    public Map getParamMap()
    {
        return paramMap;
    }
    public void setParamMap(Map paramMap)
    {
        this.paramMap = paramMap;
    }
    public String getKeyColName()
    {
        return keyColName;
    }
    public void setKeyColName(String keyColName)
    {
        this.keyColName = keyColName;
    }
    public List getExcelColList()
    {
        return excelColList;
    }
    public void setExcelColList(List excelColList)
    {
        this.excelColList = excelColList;
    }
    public String getSkeyId()
    {
        return skeyId;
    }
    public void setSkeyId(String skeyId)
    {
        this.skeyId = skeyId;
    }
    public String getFilterExcelTabId()
    {
        return filterExcelTabId;
    }
    public void setFilterExcelTabId(String filterExcelTabId)
    {
        this.filterExcelTabId = filterExcelTabId;
    }
    public String getFilterExcelTabDesc()
    {
        return filterExcelTabDesc;
    }
    public void setFilterExcelTabDesc(String filterExcelTabDesc)
    {
        this.filterExcelTabDesc = filterExcelTabDesc;
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
    public String getFilterStartDate()
    {
        return filterStartDate;
    }
    public void setFilterStartDate(String filterStartDate)
    {
        this.filterStartDate = filterStartDate;
    }
    public String getFilterEndDate()
    {
        return filterEndDate;
    }
    public void setFilterEndDate(String filterEndDate)
    {
        this.filterEndDate = filterEndDate;
    }
    public String getFilterIsSuccess()
    {
        return filterIsSuccess;
    }
    public void setFilterIsSuccess(String filterIsSuccess)
    {
        this.filterIsSuccess = filterIsSuccess;
    }
	
}
