package dream.part.iss.emg.req.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 부품출고 - Detail DTO
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 *
 */
public class PartIssEmgReqDetailDTO extends BaseDTO
{
    /**출고요청 ID*/
    private String ptEmgIssReqId             = "";
    /**출고 No*/
    private String ptEmgIssReqNo             = "";
    /**출고요청 상태*/
    private String ptEmgIssReqStatus         = "";
    /**출고요청 상태 desc*/
    private String ptEmgIssReqStatusDesc     = "";
    /**출고일*/
    private String reqDate                   = "";
    /**창고 ID*/
    private String wcodeId                   = "";
    /**창고명*/
    private String wcodeName                 = "";
    /**코스트센터 ID*/
    private String ctctrId                   = "";
    /**코스트센터명*/
    private String ctctrDesc                 = "";
    /**보관창고 ID*/
    private String toWcodeId                 = "";
    /**보관창고명*/
    private String toWcodeName               = "";
    /**수령자*/
    private String recBy                     = "";
    /**수령자 desc*/
    private String recByDesc                 = "";
    /**설비 ID*/
    private String equipId                   = "";
    /**설비명*/
    private String equipDesc                 = "";
    /**공장 ID*/
    private String plantId                   = "";
    /**공장명*/
    private String plantDesc                 = "";
    /**비고*/
    private String remark                    = "";
    /**출고자*/
    private String reqBy                     = "";
    /**출고부서*/
    private String reqDept                   = "";
    
    public String getPtEmgIssReqId()
    {
        return ptEmgIssReqId;
    }
    public void setPtEmgIssReqId(String ptEmgIssReqId)
    {
        this.ptEmgIssReqId = ptEmgIssReqId;
        super.setAuditKey(ptEmgIssReqId);
    }
    public String getPtEmgIssReqNo()
    {
        return ptEmgIssReqNo;
    }
    public void setPtEmgIssReqNo(String ptEmgIssReqNo)
    {
        this.ptEmgIssReqNo = ptEmgIssReqNo;
    }
    public String getPtEmgIssReqStatus()
    {
        return ptEmgIssReqStatus;
    }
    public void setPtEmgIssReqStatus(String ptEmgIssReqStatus)
    {
        this.ptEmgIssReqStatus = ptEmgIssReqStatus;
    }
    public String getPtEmgIssReqStatusDesc()
    {
        return ptEmgIssReqStatusDesc;
    }
    public void setPtEmgIssReqStatusDesc(String ptEmgIssReqStatusDesc)
    {
        this.ptEmgIssReqStatusDesc = ptEmgIssReqStatusDesc;
    }
    public String getReqDate()
    {
        return reqDate;
    }
    public void setReqDate(String reqDate)
    {
        this.reqDate = CommonUtil.convertDate(reqDate);
    }
    public String getWcodeId()
    {
        return wcodeId;
    }
    public void setWcodeId(String wcodeId)
    {
        this.wcodeId = wcodeId;
    }
    public String getWcodeName()
    {
        return wcodeName;
    }
    public void setWcodeName(String wcodeName)
    {
        this.wcodeName = wcodeName;
    }
    public String getCtctrId()
    {
        return ctctrId;
    }
    public void setCtctrId(String ctctrId)
    {
        this.ctctrId = ctctrId;
    }
    public String getCtctrDesc()
    {
        return ctctrDesc;
    }
    public void setCtctrDesc(String ctctrDesc)
    {
        this.ctctrDesc = ctctrDesc;
    }
    public String getToWcodeId()
    {
        return toWcodeId;
    }
    public void setToWcodeId(String toWcodeId)
    {
        this.toWcodeId = toWcodeId;
    }
    public String getToWcodeName()
    {
        return toWcodeName;
    }
    public void setToWcodeName(String toWcodeName)
    {
        this.toWcodeName = toWcodeName;
    }
    public String getRecBy()
    {
        return recBy;
    }
    public void setRecBy(String recBy)
    {
        this.recBy = recBy;
    }
    public String getRecByDesc()
    {
        return recByDesc;
    }
    public void setRecByDesc(String recByDesc)
    {
        this.recByDesc = recByDesc;
    }
    public String getEquipId()
    {
        return equipId;
    }
    public void setEquipId(String equipId)
    {
        this.equipId = equipId;
    }
    public String getEquipDesc()
    {
        return equipDesc;
    }
    public void setEquipDesc(String equipDesc)
    {
        this.equipDesc = equipDesc;
    }
    public String getPlantId()
    {
        return plantId;
    }
    public void setPlantId(String plantId)
    {
        this.plantId = plantId;
    }
    public String getPlantDesc()
    {
        return plantDesc;
    }
    public void setPlantDesc(String plantDesc)
    {
        this.plantDesc = plantDesc;
    }
    public String getRemark()
    {
        return remark;
    }
    public void setRemark(String remark)
    {
        this.remark = remark;
    }
    public String getReqBy()
    {
        return reqBy;
    }
    public void setReqBy(String reqBy)
    {
        this.reqBy = reqBy;
    }
    public String getReqDept()
    {
        return reqDept;
    }
    public void setReqDept(String reqDept)
    {
        this.reqDept = reqDept;
    }
}
