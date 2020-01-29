package dream.work.planappr.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 작업계획승인 공통 DTO
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 * 
 */
public class WorkPlanApprCommonDTO extends BaseDTO
{
	/** 작업계획승인 ID */
	private String woPlanApprId					= "";
	/** filter- 작성일자 시작일 */
	private String filterStartDate				= "";
	/** filter- 작성일자 종료일 */
	private String filterEndDate				= "";
	/** filter- 부서ID */
	private String filterDeptId					= "";
	/** filter- 부서명 */
	private String filterDeptDesc				= "";
	/** filter- 제목 */
	private String filterDesc					= "";
	/** filter- 공장ID */
	private String filterPlantId				= "";
	/** filter- 공장명 */
	private String filterPlantDesc				= "";
	/** 년 */
    private String yyyy                         = "";
    /** 년월 */
    private String yyyymm                       = "";
    /** 작업계획승인구분 */
    private String woplanapprType               = "";
	
    private String woType                       = "";
	
    
	public String getWoType()
    {
        return woType;
    }
    public void setWoType(String woType)
    {
        this.woType = woType;
    }
    public String getYyyy()
    {
        return yyyy;
    }
    public void setYyyy(String yyyy)
    {
        this.yyyy = yyyy;
    }
    public String getYyyymm()
    {
        return yyyymm;
    }
    public void setYyyymm(String yyyymm)
    {
        this.yyyymm = yyyymm;
    }
    public String getWoplanapprType()
    {
        return woplanapprType;
    }
    public void setWoplanapprType(String woplanapprType)
    {
        this.woplanapprType = woplanapprType;
    }
    public String getWoPlanApprId() {
		return woPlanApprId;
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
	public void setWoPlanApprId(String woPlanApprId) {
		this.woPlanApprId = woPlanApprId;
		super.setAuditKey(woPlanApprId);
	}
	public String getFilterStartDate() {
		return filterStartDate;
	}
	public void setFilterStartDate(String filterStartDate) {
		this.filterStartDate = CommonUtil.convertDate(filterStartDate);
	}
	public String getFilterEndDate() {
		return filterEndDate;
	}
	public void setFilterEndDate(String filterEndDate) {
		this.filterEndDate = CommonUtil.convertDate(filterEndDate);
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
	public String getFilterDesc() {
		return filterDesc;
	}
	public void setFilterDesc(String filterDesc) {
		this.filterDesc = filterDesc;
	}
	
}
