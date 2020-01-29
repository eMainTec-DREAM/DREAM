package dream.work.list.dto;

import common.bean.BaseDTO;

/**
 * WorkListPoint Page - DTO
 * @author youngjoo38
 * @version $Id: WorkListPointListDTO.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 *
 */
public class WorkListPointListDTO extends BaseDTO
{
    /**Key 순회점검 작업결과 ID */ 
    private String pmPtrlRsltPointId              = "";
    /**점검항목 ID */ 
    private String pmPointId                      = "";
    /**상태 ID */ 
    private String pmSchedStatus                  = "";
    
    /**Filter PM 순회점검 결과 ID */
    private String filterPmPtrlRsltListId         = "";    
    /**Filter 순회순서 */
    private String filterPtrlStepNum              = "";    
    /**Filter 순회지역Id */
    private String filterPtrlAreaId               = "";    
    /**Filter 순회지역Desc */
    private String filterPtrlAreaDesc             = "";    
    /**Filter 점검순서 */
    private String filterPointType              = "";    
    /**Filter 점검내용 */
    private String filterPmPointDesc              = "";    
    /**Filter 점검시간 (날짜+시간)*/
    private String filterPtrlComTime              = "";    
    /**Filter 점검결과ID */
    private String filterPmPointRsltStatusId      = "";    
    /**Filter 점검결과DESC */
    private String filterPmPointRsltStatusDesc    = "";    
    /**Filter 점검자ID */
    private String filterPtrlInspectorId          = "";    
    /**Filter 점검자Desc */
    private String filterPtrlInspectorDesc        = "";    
    /**Filter 비고 */
    private String filterRemark                   = "";

    public String getPmPointId()
    {
        return pmPointId;
    }
    public void setPmPointId(String pmPointId)
    {
        this.pmPointId = pmPointId;
    }
    public String getPmSchedStatus()
    {
        return pmSchedStatus;
    }
    public void setPmSchedStatus(String pmSchedStatus)
    {
        this.pmSchedStatus = pmSchedStatus;
    }
    public String getPmPtrlRsltPointId()
    {
        return pmPtrlRsltPointId;
    }
    public void setPmPtrlRsltPointId(String pmPtrlRsltPointId)
    {
        this.pmPtrlRsltPointId = pmPtrlRsltPointId;
        super.setAuditKey(pmPtrlRsltPointId);
    }
    public String getFilterPmPtrlRsltListId()
    {
        return filterPmPtrlRsltListId;
    }
    public void setFilterPmPtrlRsltListId(String filterPmPtrlRsltListId)
    {
        this.filterPmPtrlRsltListId = filterPmPtrlRsltListId;
    }
    public String getFilterPtrlStepNum()
    {
        return filterPtrlStepNum;
    }
    public void setFilterPtrlStepNum(String filterPtrlStepNum)
    {
        this.filterPtrlStepNum = filterPtrlStepNum;
    }
    public String getFilterPtrlAreaId()
    {
        return filterPtrlAreaId;
    }
    public void setFilterPtrlAreaId(String filterPtrlAreaId)
    {
        this.filterPtrlAreaId = filterPtrlAreaId;
    }
    public String getFilterPtrlAreaDesc()
    {
        return filterPtrlAreaDesc;
    }
    public void setFilterPtrlAreaDesc(String filterPtrlAreaDesc)
    {
        this.filterPtrlAreaDesc = filterPtrlAreaDesc;
    }
    public String getFilterPointType()
    {
        return filterPointType;
    }
    public void setFilterPointType(String filterPointType)
    {
        this.filterPointType = filterPointType;
    }
    public String getFilterPmPointDesc()
    {
        return filterPmPointDesc;
    }
    public void setFilterPmPointDesc(String filterPmPointDesc)
    {
        this.filterPmPointDesc = filterPmPointDesc;
    }
    public String getFilterPtrlComTime()
    {
        return filterPtrlComTime;
    }
    public void setFilterPtrlComTime(String filterPtrlComTime)
    {
        this.filterPtrlComTime = filterPtrlComTime;
    }
    public String getFilterPmPointRsltStatusId()
    {
        return filterPmPointRsltStatusId;
    }
    public void setFilterPmPointRsltStatusId(String filterPmPointRsltStatusId)
    {
        this.filterPmPointRsltStatusId = filterPmPointRsltStatusId;
    }
    public String getFilterPmPointRsltStatusDesc()
    {
        return filterPmPointRsltStatusDesc;
    }
    public void setFilterPmPointRsltStatusDesc(String filterPmPointRsltStatusDesc)
    {
        this.filterPmPointRsltStatusDesc = filterPmPointRsltStatusDesc;
    }
    public String getFilterPtrlInspectorId()
    {
        return filterPtrlInspectorId;
    }
    public void setFilterPtrlInspectorId(String filterPtrlInspectorId)
    {
        this.filterPtrlInspectorId = filterPtrlInspectorId;
    }
    public String getFilterPtrlInspectorDesc()
    {
        return filterPtrlInspectorDesc;
    }
    public void setFilterPtrlInspectorDesc(String filterPtrlInspectorDesc)
    {
        this.filterPtrlInspectorDesc = filterPtrlInspectorDesc;
    }
    public String getFilterRemark()
    {
        return filterRemark;
    }
    public void setFilterRemark(String filterRemark)
    {
        this.filterRemark = filterRemark;
    }

}
