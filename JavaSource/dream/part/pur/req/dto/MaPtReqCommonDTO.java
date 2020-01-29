package dream.part.pur.req.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 부품수리 공통 DTO
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * 
 */
public class MaPtReqCommonDTO extends BaseDTO
{
    /** 필터-요청번호 key */
    private String reqId        = "";
    /** 필터-부서Id */
    private String filterDeptId        = "";
    
    /** 필터-부서명 */    
    private String filterDeptDesc      = "";    
    
    /** 필터-신청시작일 */
    private String filterRegStartDate  = "";
    
    /** 필터-신청종료일 */
    private String filterRegEndDate    = "";
    
    /** 필터-자재번호 */
    private String filterPartNo        = "";
    
    /** 필터-품명 */
    private String filterPartNameSize  = "";
    
    /** 필터-요청번호 */
    private String filterReqNo         = "";
    
    /** 필터-작성자 Id */
    private String filterEnterId       = "";
    
    /** 필터-작성자 명 */
    private String filterEnterName     = "";
    
    /** 필터-작성상태 */
    private String inputStatus     = "";
    
    /** 필터-작성상태명 */
    private String inputStatusDesc     = "";
	/** 필터 시작 접수일자 */
	private String filterStartRecDate		= "";
	/** 필터 끝 접수일자 */
	private String filterEndRecDate			= "";
	/** 필터-공장 */
	private String filterPlant         = "";
	/** 필터-공장명 */    
	private String filterPlantDesc     = "";    
    

	public String getFilterPlant()
    {
        return filterPlant;
    }
    public void setFilterPlant(String filterPlant)
    {
        this.filterPlant = filterPlant;
    }
    public String getFilterPlantDesc()
    {
        return filterPlantDesc;
    }
    public void setFilterPlantDesc(String filterPlantDesc)
    {
        this.filterPlantDesc = filterPlantDesc;
    }
    public String getFilterStartRecDate() {
		return filterStartRecDate;
	}
	public void setFilterStartRecDate(String filterStartRecDate) {
		this.filterStartRecDate = filterStartRecDate;
	}
	public String getFilterEndRecDate() {
		return filterEndRecDate;
	}
	public void setFilterEndRecDate(String filterEndRecDate) {
		this.filterEndRecDate = filterEndRecDate;
	}
    public String getReqId()
    {
        return reqId;
    }
    public void setReqId(String reqId)
    {
        this.reqId = reqId;
        super.setAuditKey(reqId);
    }
    public String getInputStatus()
    {
        return inputStatus;
    }
    public void setInputStatus(String inputStatus)
    {
        this.inputStatus = inputStatus;
    }
    public String getInputStatusDesc()
    {
        return inputStatusDesc;
    }
    public void setInputStatusDesc(String inputStatusDesc)
    {
        this.inputStatusDesc = inputStatusDesc;
    }
    public String getFilterDeptId()
    {
        return filterDeptId;
    }
    public void setFilterDeptId(String filterDeptId)
    {
        this.filterDeptId = filterDeptId;
    }
    public String getFilterDeptDesc()
    {
        return filterDeptDesc;
    }
    public void setFilterDeptDesc(String filterDeptDesc)
    {
        this.filterDeptDesc = filterDeptDesc;
    }
    public String getFilterRegStartDate()
    {
        return filterRegStartDate;
    }
    public void setFilterRegStartDate(String filterRegStartDate)
    {
        this.filterRegStartDate = CommonUtil.convertDate(filterRegStartDate);
    }
    public String getFilterRegEndDate()
    {
        return filterRegEndDate;
    }
    public void setFilterRegEndDate(String filterRegEndDate)
    {
        this.filterRegEndDate = CommonUtil.convertDate(filterRegEndDate);
    }
    public String getFilterPartNo()
    {
        return filterPartNo;
    }
    public void setFilterPartNo(String filterPartNo)
    {
        this.filterPartNo = filterPartNo;
    }
    public String getFilterPartNameSize()
    {
        return filterPartNameSize;
    }
    public void setFilterPartNameSize(String filterPartNameSize)
    {
        this.filterPartNameSize = filterPartNameSize;
    }
    public String getFilterReqNo()
    {
        return filterReqNo;
    }
    public void setFilterReqNo(String filterReqNo)
    {
        this.filterReqNo = filterReqNo;
    }
    public String getFilterEnterId()
    {
        return filterEnterId;
    }
    public void setFilterEnterId(String filterEnterId)
    {
        this.filterEnterId = filterEnterId;
    }
    public String getFilterEnterName()
    {
        return filterEnterName;
    }
    public void setFilterEnterName(String filterEnterName)
    {
        this.filterEnterName = filterEnterName;
    }
    
    
}
