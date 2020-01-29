package dream.work.list.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 점검내용 - Detail DTO
 * @author youngjoo38
 * @version $Id: WorkListPointDetailDTO.java,v 1.0 2017/08/28 12:37:40 youngjoo38 Exp $
 * @since 1.0
 *
 */
public class WorkListPointDetailDTO extends BaseDTO
{ 
    
    /**Key 순회점검 작업결과 ID */ 
    private String pmPtrlRsltPointId            = "";
    
    /** 점검항목Id(점검내용) */
    private String pmPointId                    = "";    
    /** 점검항목Desc(점검내용) */
    private String pmPointDesc                  = "";    
    /** 점검시간 (날짜+시간)*/
    private String ptrlComDateTime              = "";    
    /** 점검시간 (날짜)*/
    private String ptrlComTime                  = "";    
    /** 점검시간 (시간)*/
    private String ptrlComDate                  = "";    
    /** 점검결과ID */
    private String pmPointRsltStatusId          = "";    
    /** 점검결과DESC */
    private String pmPointRsltStatusDesc        = "";    
    /** 점검자ID */
    private String ptrlInspectorId              = "";    
    /** 점검자Desc */
    private String ptrlInspectorDesc            = "";   
    /** 비고 */
    private String remark                       = "";
    
    /** PM 순회점검 결과 ID */
    private String pmPtrlRsltListId             = "";    
    /** 순회순서 */
    private String ptrlStepNum                  = "";    
    /** 순회지역Id */
    private String ptrlAreaId                   = "";    
    /** 순회지역Desc */
    private String ptrlAreaDesc                 = "";    
    /** 점검순서 */
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
