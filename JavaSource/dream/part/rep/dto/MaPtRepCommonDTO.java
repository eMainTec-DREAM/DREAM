package dream.part.rep.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * ��ǰ���� ���� DTO
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * 
 */
public class MaPtRepCommonDTO extends BaseDTO
{
    /** ID */
    private String ptRepairListId      = "";
    /** ��ȹ�ȣ(������ȣ) */
    private String ptAppId             = "";
    /** ����-���ǰ��Id */
    private String ptRprAppListId      = "";
    
    /** ����-�μ�Id */
    private String filterDeptId        = "";
    /** ����-�μ��� */
    private String filterDeptDesc      = "";
    /** ����-�����Ϸ� �������� */
    private String filterStartDate     = "";
    /** ����-�����Ϸ� �������� */
    private String filterEndDate       = "";
    /** ����-�����Ƿ� �������� */
    private String filterReqStartDate  = "";
    /** ����-�����Ƿ� �������� */
    private String filterReqEndDate    = "";
    /** ����-��� �������� */
    private String filterRegStartDate  = "";
    /** ����-��� �������� */
    private String filterRegEndDate    = "";
    /** ����-�˼���Id */
    private String filterInspector     = "";
    /** ����-�˼��ڸ� */
    private String filterInspectorName = "";
    /** ����-ǰ��/�԰ݸ� */
    private String filterPartNameSize  = "";
    /** ���� ��뼳�� Id */
    private String filterEquipId                 = "";
    /** ���� ��뼳�� �� */
    private String filterEquipDesc              = "";
    /** ����-���� ID */
    private String filterPlantId         	= "";
    /** ����-���� DESC */
    private String filterPlantDesc        	= "";
    /** ���� - �۾���Ȳ���� �˾� ���� �� ��� */
    private String ptRepStatus        = "";
    /** ���¸� - �۾���Ȳ���� �˾� ���� �� ��� */
    private String ptRepStatusDesc    = "";

    
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
	public String getFilterRegStartDate() {
		return filterRegStartDate;
	}
	public void setFilterRegStartDate(String filterRegStartDate) {
		this.filterRegStartDate = filterRegStartDate;
	}
	public String getFilterRegEndDate() {
		return filterRegEndDate;
	}
	public void setFilterRegEndDate(String filterRegEndDate) {
		this.filterRegEndDate = filterRegEndDate;
	}
	public String getPtRepStatusDesc() {
		return ptRepStatusDesc;
	}
	public void setPtRepStatusDesc(String ptRepStatusDesc) {
		this.ptRepStatusDesc = ptRepStatusDesc;
	}
	public String getPtAppId()
    {
        return ptAppId;
    }
    public void setPtAppId(String ptAppId)
    {
        this.ptAppId = ptAppId;
    }
    public String getPtRprAppListId()
    {
        return ptRprAppListId;
    }
    public void setPtRprAppListId(String ptRprAppListId)
    {
        this.ptRprAppListId = ptRprAppListId;
    }
    public String getPtRepStatus() 
    {
		return ptRepStatus;
	}
	public void setPtRepStatus(String ptRepStatus) 
	{
		this.ptRepStatus = ptRepStatus;
	}
	public String getFilterInspectorName()
    {
        return filterInspectorName;
    }
    public void setFilterInspectorName(String filterInspectorName)
    {
        this.filterInspectorName = filterInspectorName;
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
    public String getFilterInspector()
    {
        return filterInspector;
    }
    public void setFilterInspector(String filterInspector)
    {
        this.filterInspector = filterInspector;
    }
    public String getFilterPartNameSize()
    {
        return filterPartNameSize;
    }
    public void setFilterPartNameSize(String filterPartNameSize)
    {
        this.filterPartNameSize = filterPartNameSize;
    }
    public String getPtRepairListId()
    {
        return ptRepairListId;
    }
    public void setPtRepairListId(String ptRepairListId)
    {
        this.ptRepairListId = ptRepairListId;
        super.setAuditKey(ptRepairListId);
    }
    public String getFilterStartDate()
    {
        return filterStartDate;
    }
    public void setFilterStartDate(String filterStartDate)
    {
        this.filterStartDate = CommonUtil.convertDate(filterStartDate);
    }
    public String getFilterEndDate()
    {
        return filterEndDate;
    }
    public void setFilterEndDate(String filterEndDate)
    {
        this.filterEndDate = CommonUtil.convertDate(filterEndDate);
    }

}
