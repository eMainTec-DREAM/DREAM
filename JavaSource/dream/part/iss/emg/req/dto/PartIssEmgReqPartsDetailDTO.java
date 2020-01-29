package dream.part.iss.emg.req.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;
import common.util.DateUtil;

/**
 * 긴급출고 - 상세 DTO
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 */
public class PartIssEmgReqPartsDetailDTO extends BaseDTO
{
	/** 긴급출고ID */
	private String ptEmgIssListId = "";
	/** 출고품목 */
	private String partId = "";
	/** 자재상태 */
	private String partGrade = "";
	/** 자재상태 desc */
	private String partGradeDesc = "";
	/** 출고수량 */
	private String issueQty = "";
	/** 현재고 */
	private String stockQty = "";
	/** 비고 */
	private String remark = "";
	/** 출고품목No */
	private String partNo	= "";
	/** 출고품목명 */
	private String partDesc	= "";
	/**출고일*/
    private String reqDate                   = "";
    /**창고 ID*/
    private String wcodeId                   = "";
    /**코스트센터 ID*/
    private String ctctrId                   = "";
    /**보관창고 ID*/
    private String toWcodeId                 = "";
    /**수령자*/
    private String recBy                     = "";
    /**설비 ID*/
    private String equipId                   = "";
    /**공장 ID*/
    private String plantId                   = "";
    /**출고자*/
    private String reqBy                     = "";
    /**출고부서*/
    private String reqDept                   = "";
    /** 출고요청 id */
    private String ptEmgIssReqId = "";
    /** 긴급출고완료상태[미출고,출고완료] */
    private String ptemgissStatus = "";
    /** 출고구분 */
    private String ptissType = "";
    /** 작업연결상태[W:연결대기,C:연결완료] */
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
