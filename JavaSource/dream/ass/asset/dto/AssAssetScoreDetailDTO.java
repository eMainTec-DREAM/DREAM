package dream.ass.asset.dto;

import common.bean.BaseDTO;

/**
 * 평가점수 - Detail DTO
 * @author youngjoo38
 * @version $Id: AssAssetScoreDetailDTO.java,v 1.0 2017/08/28 12:37:40 youngjoo38 Exp $
 * @since 1.0
 *
 */
public class AssAssetScoreDetailDTO extends BaseDTO
{ 
    
    /**Key 설비등급평가점수 ID */ 
    private String eqAssPValId            = "";
    /**구분 ID*/
    private String assPointTypeId         = "";    
    /**구분 DESC*/
    private String assPointTypeDesc       = "";    
    /**항목 ID*/
    private String assBasePointId         = "";    
    /**항목 DESC*/
    private String assBasePointDesc       = "";    
    /**선택 ID (평가기준)*/
    private String assEvalId              = "";    
    /**선택 DESC (평가기준)*/
    private String assEvalDesc            = "";    
    /**평가기준LIST ID*/
    private String assBaseListId          = "";    
    /**설비등급평가점수 DESC*/
    private String eqAssPValDesc          = "";    
    
    /**평가항목별 평가기준ID*/
    private String assBasePValId          = "";    
    
    
    /**비고*/
    private String remark                 = "";
    
    
    
    
    public String getAssBasePValId()
    {
        return assBasePValId;
    }
    public void setAssBasePValId(String assBasePValId)
    {
        this.assBasePValId = assBasePValId;
    }
    public String getEqAssPValId()
    {
        return eqAssPValId;
    }
    public void setEqAssPValId(String eqAssPValId)
    {
        this.eqAssPValId = eqAssPValId;
    }
    public String getAssPointTypeId()
    {
        return assPointTypeId;
    }
    public void setAssPointTypeId(String assPointTypeId)
    {
        this.assPointTypeId = assPointTypeId;
    }
    public String getAssPointTypeDesc()
    {
        return assPointTypeDesc;
    }
    public void setAssPointTypeDesc(String assPointTypeDesc)
    {
        this.assPointTypeDesc = assPointTypeDesc;
    }
    public String getAssBasePointId()
    {
        return assBasePointId;
    }
    public void setAssBasePointId(String assBasePointId)
    {
        this.assBasePointId = assBasePointId;
    }
    public String getAssBasePointDesc()
    {
        return assBasePointDesc;
    }
    public void setAssBasePointDesc(String assBasePointDesc)
    {
        this.assBasePointDesc = assBasePointDesc;
    }
    public String getAssEvalId()
    {
        return assEvalId;
    }
    public void setAssEvalId(String assEvalId)
    {
        this.assEvalId = assEvalId;
    }
    public String getAssEvalDesc()
    {
        return assEvalDesc;
    }
    public void setAssEvalDesc(String assEvalDesc)
    {
        this.assEvalDesc = assEvalDesc;
    }
    public String getEqAssPValDesc()
    {
        return eqAssPValDesc;
    }
    public void setEqAssPValDesc(String eqAssPValDesc)
    {
        this.eqAssPValDesc = eqAssPValDesc;
    }
    public String getRemark()
    {
        return remark;
    }
    public void setRemark(String remark)
    {
        this.remark = remark;
    }
    public String getAssBaseListId()
    {
        return assBaseListId;
    }
    public void setAssBaseListId(String assBaseListId)
    {
        this.assBaseListId = assBaseListId;
    }
    
    
}
