package dream.asset.rpt.ass.asset.dto;

import common.bean.BaseDTO;
/**
 * 설비등급평가 항목별 점수 DTO
 * @author nhkim8548
 * @version $Id: AssRptAssAssetScoreCommonDTO.java,v 1.0 2018/8/23 09:11:20 nhkim8548 Exp $
 * @since 1.0
 *
 */
public class AssetRptAssAssetScoreCommonDTO extends BaseDTO
{
    /** 설비 ID */
    private String equipId               = "";
    /** 필터 - 설비번호 */
    private String filterEquipNo             = "";
    /** 필터 - 설비명 */
    private String filterEquipDesc           = "";
    /** 필터 - 평가항목구분  ID */
    private String filterAssCategoryTypeId   = "";
    /** 필터 - 평가항목구분 */
    private String filterAssCategoryTypeDesc = "";
    /** 필터-공장id */
    private String filterPlantId             = "";
    /** 필터-공장명 */
    private String filterPlantDesc           = "";
    /** 필터 - 평가항목 */
    private String filterAssCategoryDesc     = "";
    /** 필터-시작일자 */
    private String filterAssStartDate        = "";
    /** 필터-종료일자 */
    private String filterAssEndDate          = "";
    
    public String getEquipId()
    {
        return equipId;
    }
    public void setEquipId(String equipId)
    {
        this.equipId = equipId;
    }
    public String getFilterEquipNo()
    {
        return filterEquipNo;
    }
    public void setFilterEquipNo(String filterEquipNo)
    {
        this.filterEquipNo = filterEquipNo;
    }
    public String getFilterEquipDesc()
    {
        return filterEquipDesc;
    }
    public void setFilterEquipDesc(String filterEquipDesc)
    {
        this.filterEquipDesc = filterEquipDesc;
    }
    public String getFilterAssCategoryTypeId()
    {
        return filterAssCategoryTypeId;
    }
    public void setFilterAssCategoryTypeId(String filterAssCategoryTypeId)
    {
        this.filterAssCategoryTypeId = filterAssCategoryTypeId;
    }
    public String getFilterAssCategoryTypeDesc()
    {
        return filterAssCategoryTypeDesc;
    }
    public void setFilterAssCategoryTypeDesc(String filterAssCategoryTypeDesc)
    {
        this.filterAssCategoryTypeDesc = filterAssCategoryTypeDesc;
    }
    public String getFilterPlantId()
    {
        return filterPlantId;
    }
    public void setFilterPlantId(String filterPlantId)
    {
        this.filterPlantId = filterPlantId;
    }
    public String getFilterPlantDesc()
    {
        return filterPlantDesc;
    }
    public void setFilterPlantDesc(String filterPlantDesc)
    {
        this.filterPlantDesc = filterPlantDesc;
    }
    public String getFilterAssCategoryDesc()
    {
        return filterAssCategoryDesc;
    }
    public void setFilterAssCategoryDesc(String filterAssCategoryDesc)
    {
        this.filterAssCategoryDesc = filterAssCategoryDesc;
    }
    public String getFilterAssStartDate()
    {
        return filterAssStartDate;
    }
    public void setFilterAssStartDate(String filterAssStartDate)
    {
        this.filterAssStartDate = filterAssStartDate;
    }
    public String getFilterAssEndDate()
    {
        return filterAssEndDate;
    }
    public void setFilterAssEndDate(String filterAssEndDate)
    {
        this.filterAssEndDate = filterAssEndDate;
    }
}
