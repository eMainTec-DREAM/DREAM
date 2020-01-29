package dream.req.rpt.woreqrate.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 요청접수율(처리자) dto
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public class ReqRptWoReqRateCommonDTO extends BaseDTO
{
    /** 일자(시작) */
    private String filterStartDate    = "";
    /** 일자(끝) */
    private String filterEndDate    = "";
    /** 부서 */
    private String filterDeptId    = "";
    /** 부서 desc */
    private String filterDeptDesc    = "";
    /** 필터-공장 ID */
    private String filterPlantId            = "";
    /** 필터-공장 DESC */
    private String filterPlantDesc          = "";
    /** 필터-요청일기준 */
    private String filterIsReqDate          = "";
    /** 필터-요청일기준 DESC */
    private String filterIsReqDateDesc      = "";
    
    
    public String getFilterIsReqDate()
    {
        return filterIsReqDate;
    }
    public void setFilterIsReqDate(String filterIsReqDate)
    {
        this.filterIsReqDate = filterIsReqDate;
    }
    public String getFilterIsReqDateDesc()
    {
        return filterIsReqDateDesc;
    }
    public void setFilterIsReqDateDesc(String filterIsReqDateDesc)
    {
        this.filterIsReqDateDesc = filterIsReqDateDesc;
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
	
}