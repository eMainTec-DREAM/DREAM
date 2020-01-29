package dream.ass.asset.dto;

import common.bean.BaseDTO;

/**
 * ������ - Detail DTO
 * @author youngjoo38
 * @version $Id: AssAssetScoreDetailDTO.java,v 1.0 2017/08/28 12:37:40 youngjoo38 Exp $
 * @since 1.0
 *
 */
public class AssAssetScoreDetailDTO extends BaseDTO
{ 
    
    /**Key ������������ ID */ 
    private String eqAssPValId            = "";
    /**���� ID*/
    private String assPointTypeId         = "";    
    /**���� DESC*/
    private String assPointTypeDesc       = "";    
    /**�׸� ID*/
    private String assBasePointId         = "";    
    /**�׸� DESC*/
    private String assBasePointDesc       = "";    
    /**���� ID (�򰡱���)*/
    private String assEvalId              = "";    
    /**���� DESC (�򰡱���)*/
    private String assEvalDesc            = "";    
    /**�򰡱���LIST ID*/
    private String assBaseListId          = "";    
    /**������������ DESC*/
    private String eqAssPValDesc          = "";    
    
    /**���׸� �򰡱���ID*/
    private String assBasePValId          = "";    
    
    
    /**���*/
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
