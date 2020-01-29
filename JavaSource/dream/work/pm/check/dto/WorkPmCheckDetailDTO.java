package dream.work.pm.check.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * WorkPmCheck Page - Detail DTO
 * @author youngjoo38
 * @version $Id: WorkPmCheckDetailDTO.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 *
 */
public class WorkPmCheckDetailDTO extends BaseDTO
{
    /**Key 표준점검항목 ID */                                
    private String checkPointId           = "";  
                                                       
    /** 표준점검항목 NO*/                              
    private String checkPointNo           = "";  
    /** 표준점검분류 ID*/                              
    private String checkPointTypeId       = "";  
    /** 표준점검분류 DESC*/                            
    private String checkPointTypeDesc     = "";  
    /** 표준점검항목 */                                
    private String checkPoint             = "";  
    /** 측정내용*/                                   
    private String checkValue             = "";  
    /** 단위*/                                     
    private String uom                    = "";  
    /** 부품 ID */                                  
    private String partId                 = "";  
    /** 부품 NO */                                  
    private String partNo                 = "";  
    /** 부품 DESC */                                  
    private String partDesc               = "";  
    /** 사용여부 */                                  
    private String isActive               = "";  
    /** 비고 */                                    
    private String remark                 = "";
    /** 배율값 */                                    
    private String bvalue                 = "";
    /** 공장 ID */
    private String plantId				  = "";
    /** 공장명 */
    private String plantDesc			  = "";
    
    public String getPlantId() {
		return plantId;
	}
	public void setPlantId(String plantId) {
		this.plantId = plantId;
	}
	public String getPlantDesc() {
		return plantDesc;
	}
	public void setPlantDesc(String plantDesc) {
		this.plantDesc = plantDesc;
	}
	public String getBvalue()
    {
        return bvalue;
    }
    public void setBvalue(String bvalue)
    {
        this.bvalue = CommonUtil.convertMoney(bvalue);
    }
    public String getPartNo()
    {
        return partNo;
    }
    public void setPartNo(String partNo)
    {
        this.partNo = partNo;
    }
    public String getCheckPointId()
    {
        return checkPointId;
    }
    public String getPartId()
    {
        return partId;
    }
    public void setPartId(String partId)
    {
        this.partId = partId;
    }
    public String getPartDesc()
    {
        return partDesc;
    }
    public void setPartDesc(String partDesc)
    {
        this.partDesc = partDesc;
    }
    public void setCheckPointId(String checkPointId)
    {
        this.checkPointId = checkPointId;
        super.setAuditKey(checkPointId);
    }
    public String getCheckPointNo()
    {
        return checkPointNo;
    }
    public void setCheckPointNo(String checkPointNo)
    {
        this.checkPointNo = checkPointNo;
    }
    public String getCheckPointTypeId()
    {
        return checkPointTypeId;
    }
    public void setCheckPointTypeId(String checkPointTypeId)
    {
        this.checkPointTypeId = checkPointTypeId;
    }
    public String getCheckPointTypeDesc()
    {
        return checkPointTypeDesc;
    }
    public void setCheckPointTypeDesc(String checkPointTypeDesc)
    {
        this.checkPointTypeDesc = checkPointTypeDesc;
    }
    public String getCheckPoint()
    {
        return checkPoint;
    }
    public void setCheckPoint(String checkPoint)
    {
        this.checkPoint = checkPoint;
    }
    public String getCheckValue()
    {
        return checkValue;
    }
    public void setCheckValue(String checkValue)
    {
        this.checkValue = checkValue;
    }
    public String getUom()
    {
        return uom;
    }
    public void setUom(String uom)
    {
        this.uom = uom;
    }
    public String getIsActive()
    {
        return isActive;
    }
    public void setIsActive(String isActive)
    {
        this.isActive = isActive;
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
