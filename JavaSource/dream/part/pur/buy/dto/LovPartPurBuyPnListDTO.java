package dream.part.pur.buy.dto;

import common.bean.BaseDTO;

/**
 * �����û��ǰ ���� Lov DTO
 * @author  js.lee
 * @version $Id:$
 * @since   1.0
 */
public class LovPartPurBuyPnListDTO extends BaseDTO
{
	/** ��û��ȣ key */
	private String ptPnListId			= "";
	/** ��û��ȣ */
	private String filterPtPnListNo		= "";
	/** ��ǰ��ȣ */
	private String filterPartNo			= "";
	/** ��û���� */
	private String filterReqStartDate	= "";
	private String filterReqEndDate		= "";
	/** ���� Id */
	private String filterPlantId		= "";
	/** ����� */
	private String filterPlantDesc		= "";
	/** ��ǰ�� */
	private String filterPartNameSize	= "";
	/** ��û�� Id */
	private String filterUserId			= "";
	/** ��û�ڸ� */
	private String filterUserDesc		= "";
	/** ��û�μ� Id */
	private String filterDeptId			= "";
	/** ��û�μ��� */
	private String filterDeptDesc		= "";
	/** ���뵵 */
	private String filterUsage			= "";
	/** ��뼳�� Id */
	private String filterEquipId		= "";
	/** ��뼳��� */
	private String filterEquipDesc		= "";
	
	/** ��û���� */
	private String ptPnListStatus		= "";
	
	public String getPtPnListStatus() {
		return ptPnListStatus;
	}
	public void setPtPnListStatus(String ptPnListStatus) {
		this.ptPnListStatus = ptPnListStatus;
	}
	public String getPtPnListId() {
		return ptPnListId;
	}
	public void setPtPnListId(String ptPnListId) {
		this.ptPnListId = ptPnListId;
	}
	public String getFilterPtPnListNo() {
		return filterPtPnListNo;
	}
	public void setFilterPtPnListNo(String filterPtPnListNo) {
		this.filterPtPnListNo = filterPtPnListNo;
	}
	public String getFilterReqStartDate() {
		return filterReqStartDate;
	}
	public void setFilterReqStartDate(String filterReqStartDate) {
		this.filterReqStartDate = filterReqStartDate;
	}
	public String getFilterReqEndDate() {
		return filterReqEndDate;
	}
	public void setFilterReqEndDate(String filterReqEndDate) {
		this.filterReqEndDate = filterReqEndDate;
	}
	public String getFilterPlantId() {
		return filterPlantId;
	}
	public void setFilterPlantId(String filterPlantId) {
		this.filterPlantId = filterPlantId;
	}
	public String getFilterPlantDesc() {
		return filterPlantDesc;
	}
	public void setFilterPlantDesc(String filterPlantDesc) {
		this.filterPlantDesc = filterPlantDesc;
	}
	public String getFilterPartNameSize() {
		return filterPartNameSize;
	}
	public void setFilterPartNameSize(String filterPartNameSize) {
		this.filterPartNameSize = filterPartNameSize;
	}
	public String getFilterUserId() {
		return filterUserId;
	}
	public void setFilterUserId(String filterUserId) {
		this.filterUserId = filterUserId;
	}
	public String getFilterUserDesc() {
		return filterUserDesc;
	}
	public void setFilterUserDesc(String filterUserDesc) {
		this.filterUserDesc = filterUserDesc;
	}
	public String getFilterDeptId() {
		return filterDeptId;
	}
	public void setFilterDeptId(String filterDeptId) {
		this.filterDeptId = filterDeptId;
	}
	public String getFilterDeptDesc() {
		return filterDeptDesc;
	}
	public void setFilterDeptDesc(String filterDeptDesc) {
		this.filterDeptDesc = filterDeptDesc;
	}
	public String getFilterUsage() {
		return filterUsage;
	}
	public void setFilterUsage(String filterUsage) {
		this.filterUsage = filterUsage;
	}
	public String getFilterEquipId() {
		return filterEquipId;
	}
	public void setFilterEquipId(String filterEquipId) {
		this.filterEquipId = filterEquipId;
	}
	public String getFilterEquipDesc() {
		return filterEquipDesc;
	}
	public void setFilterEquipDesc(String filterEquipDesc) {
		this.filterEquipDesc = filterEquipDesc;
	}
	/** extCode1 */
    private String extCode1 	      = "";
    /** extCode2 */
    private String extCode2 	      = "";
    /** code type */
    private String codeType 	      = "";
    /** â�� ID */
    private String filterWId 	      = "";
    /** â�� DESC */
    private String filterWDesc 	      = "";
    /** ��ǰ ID */
    private String filterPartId       = "";
    /** ��ǰ NO */
    //private String filterPartNo       = "";
    /** ��ǰ DESC */
    private String filterPartDesc     = "";
    /** ��üǰ�� */
    private String filterVendorPtCode = "";
    
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
    public String getFilterWId()
    {
        return filterWId;
    }
    public void setFilterWId(String filterWId)
    {
        this.filterWId = filterWId;
    }
    public String getFilterWDesc()
    {
        return filterWDesc;
    }
    public void setFilterWDesc(String filterWDesc)
    {
        this.filterWDesc = filterWDesc;
    }
    public String getFilterPartId()
    {
        return filterPartId;
    }
    public void setFilterPartId(String filterPartId)
    {
        this.filterPartId = filterPartId;
    }
    public String getFilterPartNo()
    {
        return filterPartNo;
    }
    public void setFilterPartNo(String filterPartNo)
    {
        this.filterPartNo = filterPartNo;
    }
    public String getFilterPartDesc()
    {
        return filterPartDesc;
    }
    public void setFilterPartDesc(String filterPartDesc)
    {
        this.filterPartDesc = filterPartDesc;
    }
    public String getFilterVendorPtCode()
    {
        return filterVendorPtCode;
    }
    public void setFilterVendorPtCode(String filterVendorPtCode)
    {
        this.filterVendorPtCode = filterVendorPtCode;
    }
    
}
