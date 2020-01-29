package dream.work.rpt.dailyeng.dto;

import common.bean.BaseDTO;

/**
 * ��������뷮(�Ϻ�) dto
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public class WorkRptDailyEngCommonDTO extends BaseDTO
{
    /** ����(����) */
    private String filterStartDate    		= "";
    /** ����(��) */
    private String filterEndDate    		= "";
    /** ��ȸ���� */
    private String filterSeparation    		= "";
    /** ��ȸ���� desc */
    private String filterSeparationDesc    	= "";
    /** ���� */
    private String filterPlant    			= "";
    /** ���� desc */
    private String filterPlantDesc    		= "";
	
	public String getFilterPlant() {
		return filterPlant;
	}
	public void setFilterPlant(String filterPlant) {
		this.filterPlant = filterPlant;
	}
	public String getFilterPlantDesc() {
		return filterPlantDesc;
	}
	public void setFilterPlantDesc(String filterPlantDesc) {
		this.filterPlantDesc = filterPlantDesc;
	}
	public String getFilterStartDate()
    {
        return filterStartDate;
    }
    public void setFilterStartDate(String filterStartDate)
    {
        this.filterStartDate = filterStartDate;
    }
    public String getFilterEndDate()
    {
        return filterEndDate;
    }
    public void setFilterEndDate(String filterEndDate)
    {
        this.filterEndDate = filterEndDate;
    }
    public String getFilterSeparation()
    {
        return filterSeparation;
    }
    public void setFilterSeparation(String filterSeparation)
    {
        this.filterSeparation = filterSeparation;
    }
    public String getFilterSeparationDesc()
    {
        return filterSeparationDesc;
    }
    public void setFilterSeparationDesc(String filterSeparationDesc)
    {
        this.filterSeparationDesc = filterSeparationDesc;
    }
	
}