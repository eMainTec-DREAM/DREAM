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
    /**Key ��ȸ���� �۾���� ID */ 
    private String pmPtrlRsltPointId              = "";
    /**�����׸� ID */ 
    private String pmPointId                      = "";
    /**���� ID */ 
    private String pmSchedStatus                  = "";
    
    /**Filter PM ��ȸ���� ��� ID */
    private String filterPmPtrlRsltListId         = "";    
    /**Filter ��ȸ���� */
    private String filterPtrlStepNum              = "";    
    /**Filter ��ȸ����Id */
    private String filterPtrlAreaId               = "";    
    /**Filter ��ȸ����Desc */
    private String filterPtrlAreaDesc             = "";    
    /**Filter ���˼��� */
    private String filterPointType              = "";    
    /**Filter ���˳��� */
    private String filterPmPointDesc              = "";    
    /**Filter ���˽ð� (��¥+�ð�)*/
    private String filterPtrlComTime              = "";    
    /**Filter ���˰��ID */
    private String filterPmPointRsltStatusId      = "";    
    /**Filter ���˰��DESC */
    private String filterPmPointRsltStatusDesc    = "";    
    /**Filter ������ID */
    private String filterPtrlInspectorId          = "";    
    /**Filter ������Desc */
    private String filterPtrlInspectorDesc        = "";    
    /**Filter ��� */
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
