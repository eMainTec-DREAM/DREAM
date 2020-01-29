package dream.part.iss.emg.req.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;
/**
 * 출고요청 - 공통 DTO
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 *
 */
public class PartIssEmgReqCommonDTO extends BaseDTO
{
	/** 출고번호 */
    private String ptEmgIssListId	    = "";
	/**출고요청 ID*/
	private String ptEmgIssReqId 	    = "";
	/** 출고부서 ID */
    private String filterIssDeptId	    = "";
    /** 출고부서 명 */
    private String filterIssDeptDesc	= "";
    /** 출고일자 FROM */
    private String filterIssStartDate	= "";
    /** 출고일자 TO */
    private String filterIssEndDate	    = "";
    /** 수령자 ID */
    private String filterRecById	    = "";
    /** 수령자 명 */
    private String filterRecByDesc	    = "";
    /** 공장 ID */
    private String filterPlantId	    = "";
    /** 공장 명 */
    private String filterPlantDesc	    = "";
    /** 부품명/규격 */
    private String filterPartNameSize	= "";
    /** 출고상태 */
    private String filterIssStatus	    = "";
    /** 출고상태 명 */
    private String filterIssStatusDesc	= "";
    /** 출고No */
    private String filterIssNo	        = "";
    /** 코스트센터 ID */
    private String filterCtctrId	    = "";
    /** 코스트센터 명 */
    private String filterCtctrDesc	    = "";
    /** 설비 ID */
    private String filterEquipId	    = "";
    /** 설비 명 */
    private String filterEquipDesc	    = "";
    
    public String getPtEmgIssListId()
    {
        return ptEmgIssListId;
    }
    public void setPtEmgIssListId(String ptEmgIssListId)
    {
        this.ptEmgIssListId = ptEmgIssListId;
        super.setAuditKey(ptEmgIssListId);
    }
    public String getPtEmgIssReqId()
    {
        return ptEmgIssReqId;
    }
    public void setPtEmgIssReqId(String ptEmgIssReqId)
    {
        this.ptEmgIssReqId = ptEmgIssReqId;
        super.setAuditKey(ptEmgIssReqId);
    }
    public String getFilterIssDeptId()
    {
        return filterIssDeptId;
    }
    public void setFilterIssDeptId(String filterIssDeptId)
    {
        this.filterIssDeptId = filterIssDeptId;
    }
    public String getFilterIssDeptDesc()
    {
        return filterIssDeptDesc;
    }
    public void setFilterIssDeptDesc(String filterIssDeptDesc)
    {
        this.filterIssDeptDesc = filterIssDeptDesc;
    }
    public String getFilterIssStartDate()
    {
        return filterIssStartDate;
    }
    public void setFilterIssStartDate(String filterIssStartDate)
    {
        this.filterIssStartDate = CommonUtil.convertDate(filterIssStartDate);
    }
    public String getFilterIssEndDate()
    {
        return filterIssEndDate;
    }
    public void setFilterIssEndDate(String filterIssEndDate)
    {
        this.filterIssEndDate = CommonUtil.convertDate(filterIssEndDate);
    }
    public String getFilterRecById()
    {
        return filterRecById;
    }
    public void setFilterRecById(String filterRecById)
    {
        this.filterRecById = filterRecById;
    }
    public String getFilterRecByDesc()
    {
        return filterRecByDesc;
    }
    public void setFilterRecByDesc(String filterRecByDesc)
    {
        this.filterRecByDesc = filterRecByDesc;
    }
    public String getFilterPlantId()
    {
        return filterPlantId;
    }
    public void setFilterPlantId(String filterPlantId)
    {
        this.filterPlantId = filterPlantId;
    }
    public String getFilterPlantDesc()
    {
        return filterPlantDesc;
    }
    public void setFilterPlantDesc(String filterPlantDesc)
    {
        this.filterPlantDesc = filterPlantDesc;
    }
    public String getFilterPartNameSize()
    {
        return filterPartNameSize;
    }
    public void setFilterPartNameSize(String filterPartNameSize)
    {
        this.filterPartNameSize = filterPartNameSize;
    }
    public String getFilterIssStatus()
    {
        return filterIssStatus;
    }
    public void setFilterIssStatus(String filterIssStatus)
    {
        this.filterIssStatus = filterIssStatus;
    }
    public String getFilterIssStatusDesc()
    {
        return filterIssStatusDesc;
    }
    public void setFilterIssStatusDesc(String filterIssStatusDesc)
    {
        this.filterIssStatusDesc = filterIssStatusDesc;
    }
    public String getFilterIssNo()
    {
        return filterIssNo;
    }
    public void setFilterIssNo(String filterIssNo)
    {
        this.filterIssNo = filterIssNo;
    }
    public String getFilterCtctrId()
    {
        return filterCtctrId;
    }
    public void setFilterCtctrId(String filterCtctrId)
    {
        this.filterCtctrId = filterCtctrId;
    }
    public String getFilterCtctrDesc()
    {
        return filterCtctrDesc;
    }
    public void setFilterCtctrDesc(String filterCtctrDesc)
    {
        this.filterCtctrDesc = filterCtctrDesc;
    }
    public String getFilterEquipId()
    {
        return filterEquipId;
    }
    public void setFilterEquipId(String filterEquipId)
    {
        this.filterEquipId = filterEquipId;
    }
    public String getFilterEquipDesc()
    {
        return filterEquipDesc;
    }
    public void setFilterEquipDesc(String filterEquipDesc)
    {
        this.filterEquipDesc = filterEquipDesc;
    }
}
