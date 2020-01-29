package dream.part.pur.buy.dto;

import common.bean.BaseDTO;

/**
 * 현장신청부품 선택 Lov DTO
 * @author  js.lee
 * @version $Id:$
 * @since   1.0
 */
public class LovPartPurBuyPnListDTO extends BaseDTO
{
	/** 요청번호 key */
	private String ptPnListId			= "";
	/** 요청번호 */
	private String filterPtPnListNo		= "";
	/** 부품번호 */
	private String filterPartNo			= "";
	/** 신청일자 */
	private String filterReqStartDate	= "";
	private String filterReqEndDate		= "";
	/** 공장 Id */
	private String filterPlantId		= "";
	/** 공장명 */
	private String filterPlantDesc		= "";
	/** 부품명 */
	private String filterPartNameSize	= "";
	/** 신청자 Id */
	private String filterUserId			= "";
	/** 신청자명 */
	private String filterUserDesc		= "";
	/** 신청부서 Id */
	private String filterDeptId			= "";
	/** 신청부서명 */
	private String filterDeptDesc		= "";
	/** 사용용도 */
	private String filterUsage			= "";
	/** 사용설비 Id */
	private String filterEquipId		= "";
	/** 사용설비명 */
	private String filterEquipDesc		= "";
	
	/** 요청상태 */
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
    /** 창고 ID */
    private String filterWId 	      = "";
    /** 창고 DESC */
    private String filterWDesc 	      = "";
    /** 부품 ID */
    private String filterPartId       = "";
    /** 부품 NO */
    //private String filterPartNo       = "";
    /** 부품 DESC */
    private String filterPartDesc     = "";
    /** 업체품번 */
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
