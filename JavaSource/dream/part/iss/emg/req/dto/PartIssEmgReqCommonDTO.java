package dream.part.iss.emg.req.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;
/**
 * ����û - ���� DTO
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 *
 */
public class PartIssEmgReqCommonDTO extends BaseDTO
{
	/** ����ȣ */
    private String ptEmgIssListId	    = "";
	/**����û ID*/
	private String ptEmgIssReqId 	    = "";
	/** ���μ� ID */
    private String filterIssDeptId	    = "";
    /** ���μ� �� */
    private String filterIssDeptDesc	= "";
    /** ������� FROM */
    private String filterIssStartDate	= "";
    /** ������� TO */
    private String filterIssEndDate	    = "";
    /** ������ ID */
    private String filterRecById	    = "";
    /** ������ �� */
    private String filterRecByDesc	    = "";
    /** ���� ID */
    private String filterPlantId	    = "";
    /** ���� �� */
    private String filterPlantDesc	    = "";
    /** ��ǰ��/�԰� */
    private String filterPartNameSize	= "";
    /** ������ */
    private String filterIssStatus	    = "";
    /** ������ �� */
    private String filterIssStatusDesc	= "";
    /** ���No */
    private String filterIssNo	        = "";
    /** �ڽ�Ʈ���� ID */
    private String filterCtctrId	    = "";
    /** �ڽ�Ʈ���� �� */
    private String filterCtctrDesc	    = "";
    /** ���� ID */
    private String filterEquipId	    = "";
    /** ���� �� */
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
