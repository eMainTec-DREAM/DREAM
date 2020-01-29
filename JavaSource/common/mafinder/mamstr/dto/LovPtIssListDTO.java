package common.mafinder.mamstr.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * ����ǰ �˾� DTO
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 */
public class LovPtIssListDTO extends BaseDTO
{
    /** extCode1 */
    private String extCode1 	= "";
    /** extCode2 */
    private String extCode2 	= "";
    
    /** ���μ� id - ���� */
	private String filterIssueDept		   = "";
	/** ���μ� desc - ���� */
	private String filterIssueDeptDesc	   = "";
	/** ������� from - ���� */
	private String filterIssueDateFrom	   = "";
	/** ������� to - ���� */
	private String filterIssueDateTo	   = "";
	/** ǰ��/�԰� - ���� */
	private String filterPtNameSize	       = "";
	/** ������ id - ���� */
    private String filterRecBy             = "";
    /** ������ desc - ���� */
    private String filterRecByDesc         = "";
    /** ����ȣ - ���� */
    private String filterPtIssListId       = "";
    /** â�� id - ���� */
    private String filterWcodeId           = "";
    /** â�� desc - ���� */
    private String filterWname             = "";
    /** ���� id - ���� */
    private String filterPlant             = "";
    /** ���� desc - ���� */
    private String filterPlantDesc         = "";
    
    public String getExtCode1()
    {
        return extCode1;
    }
    public void setExtCode1(String extCode1)
    {
        this.extCode1 = extCode1;
    }
    public String getExtCode2()
    {
        return extCode2;
    }
    public void setExtCode2(String extCode2)
    {
        this.extCode2 = extCode2;
    }
    public String getFilterIssueDept()
    {
        return filterIssueDept;
    }
    public void setFilterIssueDept(String filterIssueDept)
    {
        this.filterIssueDept = filterIssueDept;
    }
    public String getFilterIssueDeptDesc()
    {
        return filterIssueDeptDesc;
    }
    public void setFilterIssueDeptDesc(String filterIssueDeptDesc)
    {
        this.filterIssueDeptDesc = filterIssueDeptDesc;
    }
    public String getFilterIssueDateFrom()
    {
        return filterIssueDateFrom;
    }
    public void setFilterIssueDateFrom(String filterIssueDateFrom)
    {
        this.filterIssueDateFrom = CommonUtil.convertDate(filterIssueDateFrom);
    }
    public String getFilterIssueDateTo()
    {
        return filterIssueDateTo;
    }
    public void setFilterIssueDateTo(String filterIssueDateTo)
    {
        this.filterIssueDateTo = CommonUtil.convertDate(filterIssueDateTo);
    }
    public String getFilterPtNameSize()
    {
        return filterPtNameSize;
    }
    public void setFilterPtNameSize(String filterPtNameSize)
    {
        this.filterPtNameSize = filterPtNameSize;
    }
    public String getFilterRecBy()
    {
        return filterRecBy;
    }
    public void setFilterRecBy(String filterRecBy)
    {
        this.filterRecBy = filterRecBy;
    }
    public String getFilterRecByDesc()
    {
        return filterRecByDesc;
    }
    public void setFilterRecByDesc(String filterRecByDesc)
    {
        this.filterRecByDesc = filterRecByDesc;
    }
    public String getFilterPtIssListId()
    {
        return filterPtIssListId;
    }
    public void setFilterPtIssListId(String filterPtIssListId)
    {
        this.filterPtIssListId = filterPtIssListId;
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
    
}
