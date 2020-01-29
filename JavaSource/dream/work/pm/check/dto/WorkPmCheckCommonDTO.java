package dream.work.pm.check.dto;

import common.bean.BaseDTO;

/**
 * WorkPmCheck Page - ���� DTO
 * @author youngjoo38
 * @version $Id: WorkPmCheckCommonDTO.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 *
 */
public class WorkPmCheckCommonDTO extends BaseDTO
{
    /**Key ǥ�������׸� ID */ 
    private String checkPointId                 = "";
    
    /**Filter ǥ�������׸� NO*/
    private String filterCheckPointNo           = "";    
    /**Filter ǥ�����˺з� ID*/
    private String filterCheckPointTypeId       = "";    
    /**Filter ǥ�����˺з� DESC*/
    private String filterCheckPointTypeDesc     = "";    
    /**Filter ǥ�������׸� */
    private String filterCheckPoint             = "";    
    /**Filter ��������*/
    private String filterCheckValue             = "";    
    /**Filter ����*/
    private String filterUom                    = "";    
    /**Filter ��뿩�� */
    private String filterIsActive               = "";
    /**Filter ���� ID */ 
    private String filterPlant                  = "";
    /**Filter ���� DESC */  
    private String filterPlantDesc              = "";
    /**Filter ��� */ 
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
