package dream.part.iss.emg.req.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;
import common.util.DateUtil;

/**
 * ������ - �� DTO
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 */
public class PartIssEmgReqPartsDetailDTO extends BaseDTO
{
	/** ������ID */
	private String ptEmgIssListId = "";
	/** ���ǰ�� */
	private String partId = "";
	/** ������� */
	private String partGrade = "";
	/** ������� desc */
	private String partGradeDesc = "";
	/** ������ */
	private String issueQty = "";
	/** ����� */
	private String stockQty = "";
	/** ��� */
	private String remark = "";
	/** ���ǰ��No */
	private String partNo	= "";
	/** ���ǰ��� */
	private String partDesc	= "";
	/**�����*/
    private String reqDate                   = "";
    /**â�� ID*/
    private String wcodeId                   = "";
    /**�ڽ�Ʈ���� ID*/
    private String ctctrId                   = "";
    /**����â�� ID*/
    private String toWcodeId                 = "";
    /**������*/
    private String recBy                     = "";
    /**���� ID*/
    private String equipId                   = "";
    /**���� ID*/
    private String plantId                   = "";
    /**�����*/
    private String reqBy                     = "";
    /**���μ�*/
    private String reqDept                   = "";
    /** ����û id */
    private String ptEmgIssReqId = "";
    /** ������Ϸ����[�����,���Ϸ�] */
    private String ptemgissStatus = "";
    /** ����� */
    private String ptissType = "";
    /** �۾��������[W:������,C:����Ϸ�] */
    private String ptemgTaskStatus = "";
    
    public String getStockQty()
    {
        return stockQty;
    }
    public void setStockQty(String stockQty)
    {
        this.stockQty = stockQty;
    }
    public String getReqDate()
    {
        return reqDate;
    }
    public void setReqDate(String reqDate)
    {
        this.reqDate = CommonUtil.convertDate(reqDate);
    }
    public String getCtctrId()
    {
        return ctctrId;
    }
    public void setCtctrId(String ctctrId)
    {
        this.ctctrId = ctctrId;
    }
    public String getToWcodeId()
    {
        return toWcodeId;
    }
    public void setToWcodeId(String toWcodeId)
    {
        this.toWcodeId = toWcodeId;
    }
    public String getRecBy()
    {
        return recBy;
    }
    public void setRecBy(String recBy)
    {
        this.recBy = recBy;
    }
    public String getEquipId()
    {
        return equipId;
    }
    public void setEquipId(String equipId)
    {
        this.equipId = equipId;
    }
    public String getPlantId()
    {
        return plantId;
    }
    public void setPlantId(String plantId)
    {
        this.plantId = plantId;
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
    public String getWcodeId()
    {
        return wcodeId;
    }
    public void setWcodeId(String wcodeId)
    {
        this.wcodeId = wcodeId;
    }
    public String getPtemgissStatus()
    {
        return ptemgissStatus;
    }
    public void setPtemgissStatus(String ptemgissStatus)
    {
        this.ptemgissStatus = ptemgissStatus;
    }
    public String getPtissType()
    {
        return ptissType;
    }
    public void setPtissType(String ptissType)
    {
        this.ptissType = ptissType;
    }
    public String getPtemgTaskStatus()
    {
        return ptemgTaskStatus;
    }
    public void setPtemgTaskStatus(String ptemgTaskStatus)
    {
        this.ptemgTaskStatus = ptemgTaskStatus;
    }
    public String getPtEmgIssListId()
    {
        return ptEmgIssListId;
    }
    public void setPtEmgIssListId(String ptEmgIssListId)
    {
        this.ptEmgIssListId = ptEmgIssListId;
        super.setAuditKey(ptEmgIssListId);
    }
    public String getPartId()
    {
        return partId;
    }
    public void setPartId(String partId)
    {
        this.partId = partId;
    }
    public String getPartGrade()
    {
        return partGrade;
    }
    public void setPartGrade(String partGrade)
    {
        this.partGrade = partGrade;
    }
    public String getPartGradeDesc()
    {
        return partGradeDesc;
    }
    public void setPartGradeDesc(String partGradeDesc)
    {
        this.partGradeDesc = partGradeDesc;
    }
    public String getIssueQty()
    {
        return issueQty;
    }
    public void setIssueQty(String issueQty)
    {
        this.issueQty = issueQty;
    }
    public String getRemark()
    {
        return remark;
    }
    public void setRemark(String remark)
    {
        this.remark = remark;
    }
    public String getPartNo()
    {
        return partNo;
    }
    public void setPartNo(String partNo)
    {
        this.partNo = partNo;
    }
    public String getPartDesc()
    {
        return partDesc;
    }
    public void setPartDesc(String partDesc)
    {
        this.partDesc = partDesc;
    }
    public String getPtEmgIssReqId()
    {
        return ptEmgIssReqId;
    }
    public void setPtEmgIssReqId(String ptEmgIssReqId)
    {
        this.ptEmgIssReqId = ptEmgIssReqId;
    }
}
