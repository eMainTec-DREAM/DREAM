package dream.work.list.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * ���˳��� - Detail DTO
 * @author youngjoo38
 * @version $Id: WorkListPointDetailDTO.java,v 1.0 2017/08/28 12:37:40 youngjoo38 Exp $
 * @since 1.0
 *
 */
public class WorkListPointDetailDTO extends BaseDTO
{ 
    
    /**Key ��ȸ���� �۾���� ID */ 
    private String pmPtrlRsltPointId            = "";
    
    /** �����׸�Id(���˳���) */
    private String pmPointId                    = "";    
    /** �����׸�Desc(���˳���) */
    private String pmPointDesc                  = "";    
    /** ���˽ð� (��¥+�ð�)*/
    private String ptrlComDateTime              = "";    
    /** ���˽ð� (��¥)*/
    private String ptrlComTime                  = "";    
    /** ���˽ð� (�ð�)*/
    private String ptrlComDate                  = "";    
    /** ���˰��ID */
    private String pmPointRsltStatusId          = "";    
    /** ���˰��DESC */
    private String pmPointRsltStatusDesc        = "";    
    /** ������ID */
    private String ptrlInspectorId              = "";    
    /** ������Desc */
    private String ptrlInspectorDesc            = "";   
    /** ��� */
    private String remark                       = "";
    
    /** PM ��ȸ���� ��� ID */
    private String pmPtrlRsltListId             = "";    
    /** ��ȸ���� */
    private String ptrlStepNum                  = "";    
    /** ��ȸ����Id */
    private String ptrlAreaId                   = "";    
    /** ��ȸ����Desc */
    private String ptrlAreaDesc                 = "";    
    /** ���˼��� */
    private String pointType                    = "";
    
    
    public String getPmPointId()
    {
        return pmPointId;
    }
    public void setPmPointId(String pmPointId)
    {
        this.pmPointId = pmPointId;
    }
    public String getPtrlAreaId()
    {
        return ptrlAreaId;
    }
    public void setPtrlAreaId(String ptrlAreaId)
    {
        this.ptrlAreaId = ptrlAreaId;
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
    public String getPmPointDesc()
    {
        return pmPointDesc;
    }
    public void setPmPointDesc(String pmPointDesc)
    {
        this.pmPointDesc = pmPointDesc;
    }
    public String getPtrlComDateTime()
    {
        return ptrlComDateTime;
    }
    public void setPtrlComDateTime(String ptrlComDateTime)
    {
        this.ptrlComDateTime = ptrlComDateTime;
    }
    public String getPtrlComDate()
    {
        return ptrlComDate;
    }
    public void setPtrlComDate(String ptrlComDate)
    {
        this.ptrlComDate = CommonUtil.convertDate(ptrlComDate);
    }
    public String getPtrlComTime()
    {
        return ptrlComTime;
    }
    public void setPtrlComTime(String ptrlComTime)
    {
        this.ptrlComTime = CommonUtil.convertTime(ptrlComTime);
    }
    public String getPmPointRsltStatusId()
    {
        return pmPointRsltStatusId;
    }
    public void setPmPointRsltStatusId(String pmPointRsltStatusId)
    {
        this.pmPointRsltStatusId = pmPointRsltStatusId;
    }
    public String getPmPointRsltStatusDesc()
    {
        return pmPointRsltStatusDesc;
    }
    public void setPmPointRsltStatusDesc(String pmPointRsltStatusDesc)
    {
        this.pmPointRsltStatusDesc = pmPointRsltStatusDesc;
    }
    public String getPtrlInspectorId()
    {
        return ptrlInspectorId;
    }
    public void setPtrlInspectorId(String ptrlInspectorId)
    {
        this.ptrlInspectorId = ptrlInspectorId;
    }
    public String getPtrlInspectorDesc()
    {
        return ptrlInspectorDesc;
    }
    public void setPtrlInspectorDesc(String ptrlInspectorDesc)
    {
        this.ptrlInspectorDesc = ptrlInspectorDesc;
    }
    public String getRemark()
    {
        return remark;
    }
    public void setRemark(String remark)
    {
        this.remark = remark;
    }
    public String getPmPtrlRsltListId()
    {
        return pmPtrlRsltListId;
    }
    public void setPmPtrlRsltListId(String pmPtrlRsltListId)
    {
        this.pmPtrlRsltListId = pmPtrlRsltListId;
    }
    public String getPtrlStepNum()
    {
        return ptrlStepNum;
    }
    public void setPtrlStepNum(String ptrlStepNum)
    {
        this.ptrlStepNum = ptrlStepNum;
    }
    public String getPtrlAreaDesc()
    {
        return ptrlAreaDesc;
    }
    public void setPtrlAreaDesc(String ptrlAreaDesc)
    {
        this.ptrlAreaDesc = ptrlAreaDesc;
    }
    public String getPointType()
    {
        return pointType;
    }
    public void setPointType(String pointType)
    {
        this.pointType = pointType;
    }  
}
