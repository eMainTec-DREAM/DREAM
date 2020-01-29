package dream.part.pur.req.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * ��ǰ���� ���� DTO
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * 
 */
public class MaPtReqCommonDTO extends BaseDTO
{
    /** ����-��û��ȣ key */
    private String reqId        = "";
    /** ����-�μ�Id */
    private String filterDeptId        = "";
    
    /** ����-�μ��� */    
    private String filterDeptDesc      = "";    
    
    /** ����-��û������ */
    private String filterRegStartDate  = "";
    
    /** ����-��û������ */
    private String filterRegEndDate    = "";
    
    /** ����-�����ȣ */
    private String filterPartNo        = "";
    
    /** ����-ǰ�� */
    private String filterPartNameSize  = "";
    
    /** ����-��û��ȣ */
    private String filterReqNo         = "";
    
    /** ����-�ۼ��� Id */
    private String filterEnterId       = "";
    
    /** ����-�ۼ��� �� */
    private String filterEnterName     = "";
    
    /** ����-�ۼ����� */
    private String inputStatus     = "";
    
    /** ����-�ۼ����¸� */
    private String inputStatusDesc     = "";
	/** ���� ���� �������� */
	private String filterStartRecDate		= "";
	/** ���� �� �������� */
	private String filterEndRecDate			= "";
	/** ����-���� */
	private String filterPlant         = "";
	/** ����-����� */    
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
