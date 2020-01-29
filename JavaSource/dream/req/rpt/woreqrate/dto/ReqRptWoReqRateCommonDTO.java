package dream.req.rpt.woreqrate.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * ��û������(ó����) dto
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public class ReqRptWoReqRateCommonDTO extends BaseDTO
{
    /** ����(����) */
    private String filterStartDate    = "";
    /** ����(��) */
    private String filterEndDate    = "";
    /** �μ� */
    private String filterDeptId    = "";
    /** �μ� desc */
    private String filterDeptDesc    = "";
    /** ����-���� ID */
    private String filterPlantId            = "";
    /** ����-���� DESC */
    private String filterPlantDesc          = "";
    /** ����-��û�ϱ��� */
    private String filterIsReqDate          = "";
    /** ����-��û�ϱ��� DESC */
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