package dream.mgr.cccd.dto;

import common.bean.BaseDTO;

/**
 * CP�˻� �˾� DTO
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public class LovCtCtrListDTO extends BaseDTO
{
    /** CP Code */
    private String ctCtrNo     = "";
    /** CP �� */
    private String ctCtrDesc   = "";
    /** extCode1 */
    private String extCode1 	= "";
    /** extCode2 */
    private String extCode2 	= "";
    /** code type */
    private String codeType 	= "";
    
    /** ���� ID */
    private String plantId      = "";
    /** ���� DESC */
    private String plantDesc    = "";
    
    public String getCtCtrNo() {
		return ctCtrNo;
	}
	public String getPlantId() {
		return plantId;
	}
	public void setPlantId(String plantId) {
		this.plantId = plantId;
	}
	public String getPlantDesc() {
		return plantDesc;
	}
	public void setPlantDesc(String plantDesc) {
		this.plantDesc = plantDesc;
	}
	public void setCtCtrNo(String ctCtrNo) {
		this.ctCtrNo = ctCtrNo;
	}
	public String getCtCtrDesc() {
		return ctCtrDesc;
	}
	public void setCtCtrDesc(String ctCtrDesc) {
		this.ctCtrDesc = ctCtrDesc;
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
