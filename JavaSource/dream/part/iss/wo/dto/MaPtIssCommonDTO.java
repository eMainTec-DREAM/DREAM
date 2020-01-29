package dream.part.iss.wo.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 자재출고확정 공통 DTO
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * 
 */
public class MaPtIssCommonDTO extends BaseDTO
{
    /** 작업일자 From */
    private String startDateFrom       = "";
    /** 작업일자 To */
    private String startDateTo          = "";
    /** 부서 */
    private String deptId               = "";
    /** 부서명 */
    private String deptDesc             = "";
    /** 설비 */
    private String equipId              = "";
    /** 설비명 */
    private String equipDesc            = "";
    /** 부품 */
    private String partId               = "";
    /** 부품명 */
    private String partDesc             = "";
    /** 작업명 */
    private String woDesc               = "";
    /** 작업번호 */
    private String woNo                 = "";
    /** 작업자 */
    private String empId                = "";
    /** 작업자명 */
    private String empDesc              = "";
    /** 출고일자 from */
    private String issDateFrom          = "";
    /** 출고일자 To */
    private String issDateTo            = "";
    /** 출고상태 */
    private String issStatus            = "";
    /** 출고상태명 */
    private String issStatusDesc        = "";
    /** KEY */
    private String ptisslistId          = "";
    
	/** ID */
	private String wcodeId             = "";
	private String partGrade           = "";
	
	/** 필터-창고Id */
	private String filterWcodeId       = "";
	/** 필터-창고명 */
	private String filterWname         = "";
	/** 필터-부품번호 */
	private String filterPartNo        = "";
	/** 필터-부품번명 */
	private String filterPartDesc      = "";
	/** 필터-부품번명 */
	private String filterPtSize        = "";
	/** 필터-제작사 */
	private String filterMaker         = "";
	/** 필터-모델 */
	private String filterModel         = "";
	/** Is_serial_part */
	private String isSerialPart        = "";

	/** 필터-공장 ID */
    private String filterPlantId         	= "";
    /** 필터-공장 DESC */
    private String filterPlantDesc        	= "";

    private String filterPtissTypeId		= "";
    private String filterPtissTypeDesc		= "";
    
    /** 필터-출고부서 ID */
    private String filterIssueDeptId        = "";
    /** 필터-출고부서 DESC */
    private String filterIssueDeptDesc      = "";
    /** 필터-수령자 ID */
    private String filterRecById            = "";
    /** 필터-수령자 DESC */
    private String filterRecByDesc          = "";
    /** 필터-출고번호 */
    private String filterPtIssListNo        = "";
    
    public String getFilterIssueDeptId()
    {
        return filterIssueDeptId;
    }
    public void setFilterIssueDeptId(String filterIssueDeptId)
    {
        this.filterIssueDeptId = filterIssueDeptId;
    }
    public String getFilterIssueDeptDesc()
    {
        return filterIssueDeptDesc;
    }
    public void setFilterIssueDeptDesc(String filterIssueDeptDesc)
    {
        this.filterIssueDeptDesc = filterIssueDeptDesc;
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
    public String getFilterPtIssListNo()
    {
        return filterPtIssListNo;
    }
    public void setFilterPtIssListNo(String filterPtIssListNo)
    {
        this.filterPtIssListNo = filterPtIssListNo;
    }
    public String getFilterPtissTypeDesc() {
		return filterPtissTypeDesc;
	}
	public void setFilterPtissTypeDesc(String filterPtissTypeDesc) {
		this.filterPtissTypeDesc = filterPtissTypeDesc;
	}
	public String getFilterPtissTypeId() {
		return filterPtissTypeId;
	}
	public void setFilterPtissTypeId(String filterPtissTypeId) {
		this.filterPtissTypeId = filterPtissTypeId;
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
	public String getIsSerialPart()
    {
        return isSerialPart;
    }
    public void setIsSerialPart(String isSerialPart)
    {
        this.isSerialPart = isSerialPart;
    }
    public String getPtisslistId()
    {
        return ptisslistId;
    }
    public void setPtisslistId(String ptisslistId)
    {
        this.ptisslistId = ptisslistId;
        super.setAuditKey(ptisslistId);
    }
    public String getStartDateFrom()
    {
        return startDateFrom;
    }
    public void setStartDateFrom(String startDateFrom)
    {
        this.startDateFrom = CommonUtil.convertDate(startDateFrom);
    }
    public String getStartDateTo()
    {
        return startDateTo;
    }
    public void setStartDateTo(String startDateTo)
    {
        this.startDateTo = CommonUtil.convertDate(startDateTo);
    }
    public String getDeptId()
    {
        return deptId;
    }
    public void setDeptId(String deptId)
    {
        this.deptId = deptId;
    }
    public String getDeptDesc()
    {
        return deptDesc;
    }
    public void setDeptDesc(String deptDesc)
    {
        this.deptDesc = deptDesc;
    }
    public String getEquipId()
    {
        return equipId;
    }
    public void setEquipId(String equipId)
    {
        this.equipId = equipId;
    }
    public String getEquipDesc()
    {
        return equipDesc;
    }
    public void setEquipDesc(String equipDesc)
    {
        this.equipDesc = equipDesc;
    }
    public String getPartDesc()
    {
        return partDesc;
    }
    public void setPartDesc(String partDesc)
    {
        this.partDesc = partDesc;
    }
    public String getWoDesc()
    {
        return woDesc;
    }
    public void setWoDesc(String woDesc)
    {
        this.woDesc = woDesc;
    }
    public String getWoNo()
    {
        return woNo;
    }
    public void setWoNo(String woNo)
    {
        this.woNo = woNo;
    }
    public String getEmpId()
    {
        return empId;
    }
    public void setEmpId(String empId)
    {
        this.empId = empId;
    }
    public String getEmpDesc()
    {
        return empDesc;
    }
    public void setEmpDesc(String empDesc)
    {
        this.empDesc = empDesc;
    }
    public String getIssDateFrom()
    {
        return issDateFrom;
    }
    public void setIssDateFrom(String issDateFrom)
    {
        this.issDateFrom = CommonUtil.convertDate(issDateFrom);
    }
    public String getIssDateTo()
    {
        return issDateTo;
    }
    public void setIssDateTo(String issDateTo)
    {
        this.issDateTo = CommonUtil.convertDate(issDateTo);
    }
    public String getIssStatus()
    {
        return issStatus;
    }
    public void setIssStatus(String issStatus)
    {
        this.issStatus = issStatus;
    }
    public String getIssStatusDesc()
    {
        return issStatusDesc;
    }
    public void setIssStatusDesc(String issStatusDesc)
    {
        this.issStatusDesc = issStatusDesc;
    }
    public String getWcodeId()
    {
        return wcodeId;
    }
    public void setWcodeId(String wcodeId)
    {
        this.wcodeId = wcodeId;
    }
    public String getPartId()
    {
        return partId;
    }
    public void setPartId(String partId)
    {
        this.partId = partId;
    }
    public String getPartGrade()
    {
        return partGrade;
    }
    public void setPartGrade(String partGrade)
    {
        this.partGrade = partGrade;
    }
    public String getFilterWcodeId()
    {
        return filterWcodeId;
    }
    public void setFilterWcodeId(String filterWcodeId)
    {
        this.filterWcodeId = filterWcodeId;
    }

    public String getFilterWname()
    {
        return filterWname;
    }
    public void setFilterWname(String filterWname)
    {
        this.filterWname = filterWname;
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
    public String getFilterPtSize()
    {
        return filterPtSize;
    }
    public void setFilterPtSize(String filterPtSize)
    {
        this.filterPtSize = filterPtSize;
    }
    public String getFilterMaker()
    {
        return filterMaker;
    }
    public void setFilterMaker(String filterMaker)
    {
        this.filterMaker = filterMaker;
    }
    public String getFilterModel()
    {
        return filterModel;
    }
    public void setFilterModel(String filterModel)
    {
        this.filterModel = filterModel;
    }

}
