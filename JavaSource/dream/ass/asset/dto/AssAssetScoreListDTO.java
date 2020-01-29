package dream.ass.asset.dto;

import common.bean.BaseDTO;

/**
 * AssAssetScore Page - DTO
 * @author youngjoo38
 * @version $Id: AssAssetScoreListDTO.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 *
 */
public class AssAssetScoreListDTO extends BaseDTO
{
    /**Key 설비등급평가점수 ID */ 
    private String eqAssPValId                  = "";
    
    /**Filter 항목구분 ID*/
    private String filterAssPointTypeId         = "";    
    /**Filter 항목구분 DESC*/
    private String filterAssPointTypeDesc       = "";    
    /**Filter 항목 ID*/
    private String filterAssBasePointId         = "";    
    /**Filter 항목 DESC*/
    private String filterAssBasePointDesc       = "";    
    /**Filter 선택 ID (평가기준)*/
    private String filterAssEvalId              = "";    
    /**Filter 선택 DESC (평가기준)*/
    private String filterAssEvalDesc            = "";    
    /**Filter 설비등급평가점수 DESC*/
    private String filterEqAssPValDesc          = "";    
    /**Filter 비고*/
    private String filterRemark                 = "";
    /**Filter평가기준LIST ID*/
    private String filterAssBaseListId          = "";   
    /**평가기준LIST ID*/
    private String assBaseListId		        = "";   
    /**항목 ID*/
    private String assBasePointId		        = "";
    
    /** 설비등급평가 ID */
    private String eqasslistId					= "";
    /** 설비등급평가 ID (LOV) */
    private String eqasslistIdLov				= "";
    
    public String getEqasslistId() {
		return eqasslistId;
	}
	public void setEqasslistId(String eqasslistId) {
		this.eqasslistId = eqasslistId;
	}
	public String getEqasslistIdLov() {
		return eqasslistIdLov;
	}
	public void setEqasslistIdLov(String eqasslistIdLov) {
		this.eqasslistIdLov = eqasslistIdLov;
	}
	public String getAssBasePointId() {
		return assBasePointId;
	}
	public void setAssBasePointId(String assBasePointId) {
		this.assBasePointId = assBasePointId;
	}
	public String getAssBaseListId() {
		return assBaseListId;
	}
	public void setAssBaseListId(String assBaseListId) {
		this.assBaseListId = assBaseListId;
	}
	public String getEqAssPValId()
    {
        return eqAssPValId;
    }
    public void setEqAssPValId(String eqAssPValId)
    {
        this.eqAssPValId = eqAssPValId;
        super.setAuditKey(eqAssPValId);
    }
    public String getFilterAssPointTypeId()
    {
        return filterAssPointTypeId;
    }
    public void setFilterAssPointTypeId(String filterAssPointTypeId)
    {
        this.filterAssPointTypeId = filterAssPointTypeId;
    }
    public String getFilterAssPointTypeDesc()
    {
        return filterAssPointTypeDesc;
    }
    public void setFilterAssPointTypeDesc(String filterAssPointTypeDesc)
    {
        this.filterAssPointTypeDesc = filterAssPointTypeDesc;
    }
    public String getFilterAssBasePointId()
    {
        return filterAssBasePointId;
    }
    public void setFilterAssBasePointId(String filterAssBasePointId)
    {
        this.filterAssBasePointId = filterAssBasePointId;
    }
    public String getFilterAssBasePointDesc()
    {
        return filterAssBasePointDesc;
    }
    public void setFilterAssBasePointDesc(String filterAssBasePointDesc)
    {
        this.filterAssBasePointDesc = filterAssBasePointDesc;
    }
    public String getFilterAssEvalId()
    {
        return filterAssEvalId;
    }
    public void setFilterAssEvalId(String filterAssEvalId)
    {
        this.filterAssEvalId = filterAssEvalId;
    }
    public String getFilterAssEvalDesc()
    {
        return filterAssEvalDesc;
    }
    public void setFilterAssEvalDesc(String filterAssEvalDesc)
    {
        this.filterAssEvalDesc = filterAssEvalDesc;
    }
    public String getFilterEqAssPValDesc()
    {
        return filterEqAssPValDesc;
    }
    public void setFilterEqAssPValDesc(String filterEqAssPValDesc)
    {
        this.filterEqAssPValDesc = filterEqAssPValDesc;
    }
    public String getFilterRemark()
    {
        return filterRemark;
    }
    public void setFilterRemark(String filterRemark)
    {
        this.filterRemark = filterRemark;
    }
    public String getFilterAssBaseListId()
    {
        return filterAssBaseListId;
    }
    public void setFilterAssBaseListId(String filterAssBaseListId)
    {
        this.filterAssBaseListId = filterAssBaseListId;
    }
    
}
