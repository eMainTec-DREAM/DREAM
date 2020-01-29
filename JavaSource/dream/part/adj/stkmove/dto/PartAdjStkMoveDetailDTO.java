package dream.part.adj.stkmove.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * ����̵� - �� DTO
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 *
 */
public class PartAdjStkMoveDetailDTO extends BaseDTO
{	
    /** ����̵� id */
    private String ptStkMoveId              = "";
    /** ����̵� no */
    private String ptStkMoveNo              = "";
    /** ����̵�����  */
    private String ptStkMoveStatus          = "";
    /** ����̵����� Desc  */
    private String ptStkMoveStatusDesc      = "";
    /** ����  */
    private String plant                    = "";
    /** ����  Desc */
    private String plantDesc                = "";
    /** From â�� id  */
    private String fromWcodeId              = "";
    /** From â�� Desc  */
    private String fromWname                = "";
    /** To â�� id  */
    private String toWcodeId                = "";
    /** To â�� Desc  */
    private String toWname                  = "";
    /** ó������  */
    private String moveDate                 = "";
    /** ��ǰ id  */
    private String partId                   = "";
    /** ��ǰ no  */
    private String partNo                   = "";
    /** ǰ��/�԰�  */
    private String ptNameSize               = "";
    /** ǰ��  */
    private String ptDesc                   = "";
    /** �԰�  */
    private String ptSize                   = "";
    /** ��  */
    private String model                    = "";
    /** ��ǰ Grade  */
    private String partGrade                = "";
    /** ��ǰ Grade Desc  */
    private String partGradeDesc            = "";
    /** �̵�����  */
    private String moveQty                  = "";
    /** ��Ϻμ� id  */
    private String regDept                  = "";
    /** �������  */
    private String regDate                  = "";
    /** ����� id  */
    private String regId                    = "";
    /** �����/�μ� Desc  */
    private String regEmpDept               = "";
    /** ���  */
    private String remark                   = "";
    
    public String getPtNameSize()
    {
        return ptNameSize;
    }
    public void setPtNameSize(String ptNameSize)
    {
        this.ptNameSize = ptNameSize;
    }
    public String getPtStkMoveId()
    {
        return ptStkMoveId;
    }
    public void setPtStkMoveId(String ptStkMoveId)
    {
        this.ptStkMoveId = ptStkMoveId;
    }
    public String getPtStkMoveNo()
    {
        return ptStkMoveNo;
    }
    public void setPtStkMoveNo(String ptStkMoveNo)
    {
        this.ptStkMoveNo = ptStkMoveNo;
    }
    public String getPtStkMoveStatus()
    {
        return ptStkMoveStatus;
    }
    public void setPtStkMoveStatus(String ptStkMoveStatus)
    {
        this.ptStkMoveStatus = ptStkMoveStatus;
    }
    public String getPtStkMoveStatusDesc()
    {
        return ptStkMoveStatusDesc;
    }
    public void setPtStkMoveStatusDesc(String ptStkMoveStatusDesc)
    {
        this.ptStkMoveStatusDesc = ptStkMoveStatusDesc;
    }
    public String getPlant()
    {
        return plant;
    }
    public void setPlant(String plant)
    {
        this.plant = plant;
    }
    public String getPlantDesc()
    {
        return plantDesc;
    }
    public void setPlantDesc(String plantDesc)
    {
        this.plantDesc = plantDesc;
    }
    public String getFromWcodeId()
    {
        return fromWcodeId;
    }
    public void setFromWcodeId(String fromWcodeId)
    {
        this.fromWcodeId = fromWcodeId;
    }
    public String getFromWname()
    {
        return fromWname;
    }
    public void setFromWname(String fromWname)
    {
        this.fromWname = fromWname;
    }
    public String getToWcodeId()
    {
        return toWcodeId;
    }
    public void setToWcodeId(String toWcodeId)
    {
        this.toWcodeId = toWcodeId;
    }
    public String getToWname()
    {
        return toWname;
    }
    public void setToWname(String toWname)
    {
        this.toWname = toWname;
    }
    public String getMoveDate()
    {
        return moveDate;
    }
    public void setMoveDate(String moveDate)
    {
        this.moveDate = CommonUtil.convertDate(moveDate);
    }
    public String getPartId()
    {
        return partId;
    }
    public void setPartId(String partId)
    {
        this.partId = partId;
    }
    public String getPartNo()
    {
        return partNo;
    }
    public void setPartNo(String partNo)
    {
        this.partNo = partNo;
    }
    public String getPtDesc()
    {
        return ptDesc;
    }
    public void setPtDesc(String ptDesc)
    {
        this.ptDesc = ptDesc;
    }
    public String getPtSize()
    {
        return ptSize;
    }
    public void setPtSize(String ptSize)
    {
        this.ptSize = ptSize;
    }
    public String getModel()
    {
        return model;
    }
    public void setModel(String model)
    {
        this.model = model;
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
    public String getMoveQty()
    {
        return moveQty;
    }
    public void setMoveQty(String moveQty)
    {
        this.moveQty = CommonUtil.convertMoney(moveQty);
    }
    public String getRegDept()
    {
        return regDept;
    }
    public void setRegDept(String regDept)
    {
        this.regDept = regDept;
    }
    public String getRegDate()
    {
        return regDate;
    }
    public void setRegDate(String regDate)
    {
        this.regDate = CommonUtil.convertDate(regDate);
    }
    public String getRegId()
    {
        return regId;
    }
    public void setRegId(String regId)
    {
        this.regId = regId;
    }
    public String getRegEmpDept()
    {
        return regEmpDept;
    }
    public void setRegEmpDept(String regEmpDept)
    {
        this.regEmpDept = regEmpDept;
    }
    public String getRemark()
    {
        return remark;
    }
    public void setRemark(String remark)
    {
        this.remark = remark;
    }
	
}
