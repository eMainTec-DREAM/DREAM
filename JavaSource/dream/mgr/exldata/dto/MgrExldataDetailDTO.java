package dream.mgr.exldata.dto;

import java.util.HashMap;
import java.util.Map;

import common.bean.BaseDTO;

/**
 * Excel Data Upload - Detail DTO
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 *
 */
public class MgrExldataDetailDTO extends BaseDTO
{
	/**skey ID*/
	private String skeyId 		= "";
	/**excel tab ID*/
    private String excelTabId       = "";
	/**테이블명*/
    private String tableName      = "";
    /**프로그램명*/
    private String uploadPgmName    = "";
    /**param map*/
    private Map paramMap = new HashMap();
    
    
    
    public String getUploadPgmName()
    {
        return uploadPgmName;
    }
    public void setUploadPgmName(String uploadPgmName)
    {
        this.uploadPgmName = uploadPgmName;
    }
    public String getExcelTabId()
    {
        return excelTabId;
    }
    public void setExcelTabId(String excelTabId)
    {
        this.excelTabId = excelTabId;
    }
    public Map getParamMap()
    {
        return paramMap;
    }
    public void setParamMap(Map paramMap)
    {
        this.paramMap = paramMap;
    }
    public String getSkeyId()
    {
        return skeyId;
    }
    public void setSkeyId(String skeyId)
    {
        this.skeyId = skeyId;
        super.setAuditKey(skeyId);
    }
    public String getTableName()
    {
        return tableName;
    }
    public void setTableName(String tableName)
    {
        this.tableName = tableName;
    }
    
}
