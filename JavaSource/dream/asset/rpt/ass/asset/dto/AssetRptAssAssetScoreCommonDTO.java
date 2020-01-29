package dream.asset.rpt.ass.asset.dto;

import common.bean.BaseDTO;
/**
 * �������� �׸� ���� DTO
 * @author nhkim8548
 * @version $Id: AssRptAssAssetScoreCommonDTO.java,v 1.0 2018/8/23 09:11:20 nhkim8548 Exp $
 * @since 1.0
 *
 */
public class AssetRptAssAssetScoreCommonDTO extends BaseDTO
{
    /** ���� ID */
    private String equipId               = "";
    /** ���� - �����ȣ */
    private String filterEquipNo             = "";
    /** ���� - ����� */
    private String filterEquipDesc           = "";
    /** ���� - ���׸񱸺�  ID */
    private String filterAssCategoryTypeId   = "";
    /** ���� - ���׸񱸺� */
    private String filterAssCategoryTypeDesc = "";
    /** ����-����id */
    private String filterPlantId             = "";
    /** ����-����� */
    private String filterPlantDesc           = "";
    /** ���� - ���׸� */
    private String filterAssCategoryDesc     = "";
    /** ����-�������� */
    private String filterAssStartDate        = "";
    /** ����-�������� */
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
