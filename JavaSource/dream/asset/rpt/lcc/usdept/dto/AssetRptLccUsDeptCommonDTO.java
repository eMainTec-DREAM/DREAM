package dream.asset.rpt.lcc.usdept.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 고장TOP(사용부서) dto
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public class AssetRptLccUsDeptCommonDTO extends BaseDTO
{
    /** 일자(시작) */
    private String filterStartDate    	= "";
    /** 일자(끝) */
    private String filterEndDate    	= "";
    /** 필터-공장 ID */
    private String filterPlantId        = "";
    /** 필터-공장 DESC */
    private String filterPlantDesc      = "";
    /** 사용부서 */
    private String filterUsageDept      = "";
    /** 사용부서명 */
    private String filterUsageDeptDesc  = "";

    /** Filter 작업시간 */
    private String filterWorkTime            = "";
    /** Filter 작업시간 부등호 */
    private String filterWorkConOper         = "";    
    /** Filter 작업시간 부등호명 */
    private String filterWorkConOperDesc     = "";
    /** Filter 고장시간 */
    private String filterEqDnTime            = "";
    /** Filter 고장시간 부등호 */
    private String filterEqDnConOper         = "";    
    /** Filter 고장시간 부등호명 */
    private String filterEqDnConOperDesc     = "";
    /** Filter 설비등급  */
    private String filterEqGrade             = "";
    /** Filter 설비등급명 */
    private String filterEqGradeDesc         = "";
    
    
    public String getFilterEqGrade()
    {
        return filterEqGrade;
    }
    public void setFilterEqGrade(String filterEqGrade)
    {
        this.filterEqGrade = filterEqGrade;
    }
    public String getFilterEqGradeDesc()
    {
        return filterEqGradeDesc;
    }
    public void setFilterEqGradeDesc(String filterEqGradeDesc)
    {
        this.filterEqGradeDesc = filterEqGradeDesc;
    }
    /** Daewoong 구분 ID */
    private String filterDwSeparation		 = "";
    /** Daewoong 구분명 */
    private String filterDwSeparationDesc	 = "";
    
    public String getFilterDwSeparation() {
		return filterDwSeparation;
	}
	public String getFilterWorkTime() {
		return filterWorkTime;
	}
	public void setFilterWorkTime(String filterWorkTime) {
		this.filterWorkTime = filterWorkTime;
	}
	public String getFilterWorkConOper() {
		return filterWorkConOper;
	}
	public void setFilterWorkConOper(String filterWorkConOper) {
		this.filterWorkConOper = filterWorkConOper;
	}
	public String getFilterWorkConOperDesc() {
		return filterWorkConOperDesc;
	}
	public void setFilterWorkConOperDesc(String filterWorkConOperDesc) {
		this.filterWorkConOperDesc = filterWorkConOperDesc;
	}
	public String getFilterEqDnTime() {
		return filterEqDnTime;
	}
	public void setFilterEqDnTime(String filterEqDnTime) {
		this.filterEqDnTime = filterEqDnTime;
	}
	public String getFilterEqDnConOper() {
		return filterEqDnConOper;
	}
	public void setFilterEqDnConOper(String filterEqDnConOper) {
		this.filterEqDnConOper = filterEqDnConOper;
	}
	public String getFilterEqDnConOperDesc() {
		return filterEqDnConOperDesc;
	}
	public void setFilterEqDnConOperDesc(String filterEqDnConOperDesc) {
		this.filterEqDnConOperDesc = filterEqDnConOperDesc;
	}
	public void setFilterDwSeparation(String filterDwSeparation) {
		this.filterDwSeparation = filterDwSeparation;
	}
	public String getFilterDwSeparationDesc() {
		return filterDwSeparationDesc;
	}
	public void setFilterDwSeparationDesc(String filterDwSeparationDesc) {
		this.filterDwSeparationDesc = filterDwSeparationDesc;
	}
	public String getFilterUsageDept()
    {
        return filterUsageDept;
    }
    public void setFilterUsageDept(String filterUsageDept)
    {
        this.filterUsageDept = filterUsageDept;
    }
    public String getFilterUsageDeptDesc()
    {
        return filterUsageDeptDesc;
    }
    public void setFilterUsageDeptDesc(String filterUsageDeptDesc)
    {
        this.filterUsageDeptDesc = filterUsageDeptDesc;
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