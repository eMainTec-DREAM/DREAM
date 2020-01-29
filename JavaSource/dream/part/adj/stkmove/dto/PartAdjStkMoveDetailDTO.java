package dream.part.adj.stkmove.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 재고이동 - 상세 DTO
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 *
 */
public class PartAdjStkMoveDetailDTO extends BaseDTO
{	
    /** 재고이동 id */
    private String ptStkMoveId              = "";
    /** 재고이동 no */
    private String ptStkMoveNo              = "";
    /** 재고이동상태  */
    private String ptStkMoveStatus          = "";
    /** 재고이동상태 Desc  */
    private String ptStkMoveStatusDesc      = "";
    /** 공장  */
    private String plant                    = "";
    /** 공장  Desc */
    private String plantDesc                = "";
    /** From 창고 id  */
    private String fromWcodeId              = "";
    /** From 창고 Desc  */
    private String fromWname                = "";
    /** To 창고 id  */
    private String toWcodeId                = "";
    /** To 창고 Desc  */
    private String toWname                  = "";
    /** 처리일자  */
    private String moveDate                 = "";
    /** 부품 id  */
    private String partId                   = "";
    /** 부품 no  */
    private String partNo                   = "";
    /** 품명/규격  */
    private String ptNameSize               = "";
    /** 품명  */
    private String ptDesc                   = "";
    /** 규격  */
    private String ptSize                   = "";
    /** 모델  */
    private String model                    = "";
    /** 부품 Grade  */
    private String partGrade                = "";
    /** 부품 Grade Desc  */
    private String partGradeDesc            = "";
    /** 이동수량  */
    private String moveQty                  = "";
    /** 등록부서 id  */
    private String regDept                  = "";
    /** 등록일자  */
    private String regDate                  = "";
    /** 등록자 id  */
    private String regId                    = "";
    /** 등록자/부서 Desc  */
    private String regEmpDept               = "";
    /** 비고  */
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
