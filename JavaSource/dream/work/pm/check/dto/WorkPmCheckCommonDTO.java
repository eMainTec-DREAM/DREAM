package dream.work.pm.check.dto;

import common.bean.BaseDTO;

/**
 * WorkPmCheck Page - 공통 DTO
 * @author youngjoo38
 * @version $Id: WorkPmCheckCommonDTO.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 *
 */
public class WorkPmCheckCommonDTO extends BaseDTO
{
    /**Key 표준점검항목 ID */ 
    private String checkPointId                 = "";
    
    /**Filter 표준점검항목 NO*/
    private String filterCheckPointNo           = "";    
    /**Filter 표준점검분류 ID*/
    private String filterCheckPointTypeId       = "";    
    /**Filter 표준점검분류 DESC*/
    private String filterCheckPointTypeDesc     = "";    
    /**Filter 표준점검항목 */
    private String filterCheckPoint             = "";    
    /**Filter 측정내용*/
    private String filterCheckValue             = "";    
    /**Filter 단위*/
    private String filterUom                    = "";    
    /**Filter 사용여부 */
    private String filterIsActive               = "";
    /**Filter 공장 ID */ 
    private String filterPlant                  = "";
    /**Filter 공장 DESC */  
    private String filterPlantDesc              = "";
    /**Filter 비고 */ 
    private String filterRemark                 = "";
    
    public String getFilterPlant()
    {
        return filterPlant;
    }
    public void setFilterPlant(String filterPlant)
    {
        this.filterPlant = filterPlant;
    }
    public String getFilterPlantDesc()
    {
        return filterPlantDesc;
    }
    public void setFilterPlantDesc(String filterPlantDesc)
    {
        this.filterPlantDesc = filterPlantDesc;
    }
    public String getCheckPointId()
    {
        return checkPointId;
    }
    public void setCheckPointId(String checkPointId)
    {
        this.checkPointId = checkPointId;
        super.setAuditKey(checkPointId);
    }
    public String getFilterCheckPointNo()
    {
        return filterCheckPointNo;
    }
    public void setFilterCheckPointNo(String filterCheckPointNo)
    {
        this.filterCheckPointNo = filterCheckPointNo;
    }
    public String getFilterCheckPointTypeId()
    {
        return filterCheckPointTypeId;
    }
    public void setFilterCheckPointTypeId(String filterCheckPointTypeId)
    {
        this.filterCheckPointTypeId = filterCheckPointTypeId;
    }
    public String getFilterCheckPointTypeDesc()
    {
        return filterCheckPointTypeDesc;
    }
    public void setFilterCheckPointTypeDesc(String filterCheckPointTypeDesc)
    {
        this.filterCheckPointTypeDesc = filterCheckPointTypeDesc;
    }
    public String getFilterCheckPoint()
    {
        return filterCheckPoint;
    }
    public void setFilterCheckPoint(String filterCheckPoint)
    {
        this.filterCheckPoint = filterCheckPoint;
    }
    public String getFilterCheckValue()
    {
        return filterCheckValue;
    }
    public void setFilterCheckValue(String filterCheckValue)
    {
        this.filterCheckValue = filterCheckValue;
    }
    public String getFilterUom()
    {
        return filterUom;
    }
    public void setFilterUom(String filterUom)
    {
        this.filterUom = filterUom;
    }
    public String getFilterIsActive()
    {
        return filterIsActive;
    }
    public void setFilterIsActive(String filterIsActive)
    {
        this.filterIsActive = filterIsActive;
    }
    public String getFilterRemark()
    {
        return filterRemark;
    }
    public void setFilterRemark(String filterRemark)
    {
        this.filterRemark = filterRemark;
    }
    
}
