package dream.asset.rpt.lcc.usdept.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * ����TOP(���μ�) dto
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public class AssetRptLccUsDeptCommonDTO extends BaseDTO
{
    /** ����(����) */
    private String filterStartDate    	= "";
    /** ����(��) */
    private String filterEndDate    	= "";
    /** ����-���� ID */
    private String filterPlantId        = "";
    /** ����-���� DESC */
    private String filterPlantDesc      = "";
    /** ���μ� */
    private String filterUsageDept      = "";
    /** ���μ��� */
    private String filterUsageDeptDesc  = "";

    /** Filter �۾��ð� */
    private String filterWorkTime            = "";
    /** Filter �۾��ð� �ε�ȣ */
    private String filterWorkConOper         = "";    
    /** Filter �۾��ð� �ε�ȣ�� */
    private String filterWorkConOperDesc     = "";
    /** Filter ����ð� */
    private String filterEqDnTime            = "";
    /** Filter ����ð� �ε�ȣ */
    private String filterEqDnConOper         = "";    
    /** Filter ����ð� �ε�ȣ�� */
    private String filterEqDnConOperDesc     = "";
    /** Filter ������  */
    private String filterEqGrade             = "";
    /** Filter �����޸� */
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
    /** Daewoong ���� ID */
    private String filterDwSeparation		 = "";
    /** Daewoong ���и� */
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