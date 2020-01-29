package dream.mgr.manst.dto;

import common.bean.BaseDTO;

/**
 * ��������ǥ���� ����
 * @author  kim21017
 * @version $Id: MaNstGrpCommonDTO.java,v 1.0 2015/12/02 09:13:08 kim21017 Exp $
 * @since   1.0
 * 
 */
public class MaNstGrpCommonDTO extends BaseDTO
{
	/** ���� No */
	private String popPlantNo 				= "";
	/** �� No */
	private String popDeptNo 				= "";
	/** ���� No */
	private String popLineNo 				= "";
	/** ���ζ��� No */
	private String mainLineNo 				= "";
	/** ���� �⵵ */
	private String filterYyyy	 			= "";
	/** ���Ͱ���� */
	private String filterPlant              = "";
	/** ���� �μ��� */
    private String filterDept               = "";
    /** ���Ͷ��θ� */
    private String filterLine               = "";
    /** ���͸��ζ��θ� */
    private String filterMainLine           = "";
    
	/** Ŭ������ �� �⵵ */
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
