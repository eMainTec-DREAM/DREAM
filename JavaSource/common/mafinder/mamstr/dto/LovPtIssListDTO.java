package common.mafinder.mamstr.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 출고부품 팝업 DTO
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
    
    /** 출고부서 id - 필터 */
	private String filterIssueDept		   = "";
	/** 출고부서 desc - 필터 */
	private String filterIssueDeptDesc	   = "";
	/** 출고일자 from - 필터 */
	private String filterIssueDateFrom	   = "";
	/** 출고일자 to - 필터 */
	private String filterIssueDateTo	   = "";
	/** 품명/규격 - 필터 */
	private String filterPtNameSize	       = "";
	/** 수령자 id - 필터 */
    private String filterRecBy             = "";
    /** 수령자 desc - 필터 */
    private String filterRecByDesc         = "";
    /** 출고번호 - 필터 */
    private String filterPtIssListId       = "";
    /** 창고 id - 필터 */
    private String filterWcodeId           = "";
    /** 창고 desc - 필터 */
    private String filterWname             = "";
    /** 공장 id - 필터 */
    private String filterPlant             = "";
    /** 공장 desc - 필터 */
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
