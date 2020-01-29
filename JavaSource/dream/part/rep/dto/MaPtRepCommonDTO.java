package dream.part.rep.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 부품수리 공통 DTO
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * 
 */
public class MaPtRepCommonDTO extends BaseDTO
{
    /** ID */
    private String ptRepairListId      = "";
    /** 기안번호(문서번호) */
    private String ptAppId             = "";
    /** 수리-기안품의Id */
    private String ptRprAppListId      = "";
    
    /** 필터-부서Id */
    private String filterDeptId        = "";
    /** 필터-부서명 */
    private String filterDeptDesc      = "";
    /** 필터-수리완료 시작일자 */
    private String filterStartDate     = "";
    /** 필터-수리완료 종료일자 */
    private String filterEndDate       = "";
    /** 필터-수리의뢰 시작일자 */
    private String filterReqStartDate  = "";
    /** 필터-수리의뢰 종료일자 */
    private String filterReqEndDate    = "";
    /** 필터-등록 시작일자 */
    private String filterRegStartDate  = "";
    /** 필터-등록 종료일자 */
    private String filterRegEndDate    = "";
    /** 필터-검수자Id */
    private String filterInspector     = "";
    /** 필터-검수자명 */
    private String filterInspectorName = "";
    /** 필터-품명/규격명 */
    private String filterPartNameSize  = "";
    /** 필터 사용설비 Id */
    private String filterEquipId                 = "";
    /** 필터 사용설비 명 */
    private String filterEquipDesc              = "";
    /** 필터-공장 ID */
    private String filterPlantId         	= "";
    /** 필터-공장 DESC */
    private String filterPlantDesc        	= "";
    /** 상태 - 작업현황에서 팝업 노출 시 사용 */
    private String ptRepStatus        = "";
    /** 상태명 - 작업현황에서 팝업 노출 시 사용 */
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
