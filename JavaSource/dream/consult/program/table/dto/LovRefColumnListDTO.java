package dream.consult.program.table.dto;

import common.bean.BaseDTO;

/**
 * Ref테이블 팝업
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public class LovRefColumnListDTO extends BaseDTO
{
    /** 테이블 */
    private String table     = "";
    /** 테이블 명 */
    private String tableDesc   = "";
    /** extCode1 */
    private String extCode1 	= "";
    /** extCode2 */
    private String extCode2 	= "";
    /** code type */
    private String codeType 	= "";
    
    private String selectedTableId     = "";
    
	public String getSelectedTableId() {
		return selectedTableId;
	}
	public void setSelectedTableId(String selectedTableId) {
		this.selectedTableId = selectedTableId;
	}
	public String getTable() {
		return table;
	}
	public void setTable(String table) {
		this.table = table;
	}
	public String getTableDesc() {
		return tableDesc;
	}
	public void setTableDesc(String tableDesc) {
		this.tableDesc = tableDesc;
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
