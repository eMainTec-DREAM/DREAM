package dream.mgr.manst.dto;

import common.bean.BaseDTO;

/**
 * 무정지대표라인 공통
 * @author  kim21017
 * @version $Id: MaNstGrpCommonDTO.java,v 1.0 2015/12/02 09:13:08 kim21017 Exp $
 * @since   1.0
 * 
 */
public class MaNstGrpCommonDTO extends BaseDTO
{
	/** 공장 No */
	private String popPlantNo 				= "";
	/** 과 No */
	private String popDeptNo 				= "";
	/** 라인 No */
	private String popLineNo 				= "";
	/** 메인라인 No */
	private String mainLineNo 				= "";
	/** 필터 년도 */
	private String filterYyyy	 			= "";
	/** 필터공장명 */
	private String filterPlant              = "";
	/** 피터 부서명 */
    private String filterDept               = "";
    /** 필터라인명 */
    private String filterLine               = "";
    /** 필터메인라인명 */
    private String filterMainLine           = "";
    
	/** 클릭했을 때 년도 */
	private String year						= "";
	
	
	public String getFilterPlant()
    {
        return filterPlant;
    }
    public void setFilterPlant(String filterPlant)
    {
        this.filterPlant = filterPlant;
    }
    public String getFilterDept()
    {
        return filterDept;
    }
    public void setFilterDept(String filterDept)
    {
        this.filterDept = filterDept;
    }
    public String getFilterLine()
    {
        return filterLine;
    }
    public void setFilterLine(String filterLine)
    {
        this.filterLine = filterLine;
    }
    public String getFilterMainLine()
    {
        return filterMainLine;
    }
    public void setFilterMainLine(String filterMainLine)
    {
        this.filterMainLine = filterMainLine;
    }
    public String getPopPlantNo() {
		return popPlantNo;
	}
	public void setPopPlantNo(String popPlantNo) {
		this.popPlantNo = popPlantNo;
	}
	public String getPopDeptNo() {
		return popDeptNo;
	}
	public void setPopDeptNo(String popDeptNo) {
		this.popDeptNo = popDeptNo;
	}
	public String getPopLineNo() {
		return popLineNo;
	}
	public void setPopLineNo(String popLineNo) {
		this.popLineNo = popLineNo;
	}
	public String getMainLineNo() {
		return mainLineNo;
	}
	public void setMainLineNo(String mainLineNo) {
		this.mainLineNo = mainLineNo;
	}
	public String getFilterYyyy() {
		return filterYyyy;
	}
	public void setFilterYyyy(String filterYyyy) {
		this.filterYyyy = filterYyyy;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
}
