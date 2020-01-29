package dream.req.rpt.woreqrate.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 요청접수율(처리자) 상세 dto
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public class ReqRptWoReqRateDetailDTO extends BaseDTO
{
    /** 부서 id */
    private String deptId    = "";
    /** 부서 Desc */
    private String deptDesc    = "";
    /** 시작일 */
    private String startDate    = "";
    /** 종료일 */
    private String endDate    = "";
    /** 요청일기준 */
    private String isReqDate    = "";
    
    public String getIsReqDate()
    {
        return isReqDate;
    }
    public void setIsReqDate(String isReqDate)
    {
        this.isReqDate = isReqDate;
    }
    public String getDeptDesc()
    {
        return deptDesc;
    }
    public void setDeptDesc(String deptDesc)
    {
        this.deptDesc = deptDesc;
    }
    public String getDeptId()
    {
        return deptId;
    }
    public void setDeptId(String deptId)
    {
        this.deptId = deptId;
    }
    public String getStartDate()
    {
        return startDate;
    }
    public void setStartDate(String startDate)
    {
        this.startDate = CommonUtil.convertDate(startDate);
    }
    public String getEndDate()
    {
        return endDate;
    }
    public void setEndDate(String endDate)
    {
        this.endDate = CommonUtil.convertDate(endDate);
    }
    
}