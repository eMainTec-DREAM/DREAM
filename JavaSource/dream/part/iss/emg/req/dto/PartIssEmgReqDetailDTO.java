package dream.part.iss.emg.req.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * ��ǰ��� - Detail DTO
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 *
 */
public class PartIssEmgReqDetailDTO extends BaseDTO
{
    /**����û ID*/
    private String ptEmgIssReqId             = "";
    /**��� No*/
    private String ptEmgIssReqNo             = "";
    /**����û ����*/
    private String ptEmgIssReqStatus         = "";
    /**����û ���� desc*/
    private String ptEmgIssReqStatusDesc     = "";
    /**�����*/
    private String reqDate                   = "";
    /**â�� ID*/
    private String wcodeId                   = "";
    /**â���*/
    private String wcodeName                 = "";
    /**�ڽ�Ʈ���� ID*/
    private String ctctrId                   = "";
    /**�ڽ�Ʈ���͸�*/
    private String ctctrDesc                 = "";
    /**����â�� ID*/
    private String toWcodeId                 = "";
    /**����â���*/
    private String toWcodeName               = "";
    /**������*/
    private String recBy                     = "";
    /**������ desc*/
    private String recByDesc                 = "";
    /**���� ID*/
    private String equipId                   = "";
    /**�����*/
    private String equipDesc                 = "";
    /**���� ID*/
    private String plantId                   = "";
    /**�����*/
    private String plantDesc                 = "";
    /**���*/
    private String remark                    = "";
    /**�����*/
    private String reqBy                     = "";
    /**���μ�*/
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
