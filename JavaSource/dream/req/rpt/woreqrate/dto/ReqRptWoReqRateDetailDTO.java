package dream.req.rpt.woreqrate.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * ��û������(ó����) �� dto
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public class ReqRptWoReqRateDetailDTO extends BaseDTO
{
    /** �μ� id */
    private String deptId    = "";
    /** �μ� Desc */
    private String deptDesc    = "";
    /** ������ */
    private String startDate    = "";
    /** ������ */
    private String endDate    = "";
    /** ��û�ϱ��� */
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