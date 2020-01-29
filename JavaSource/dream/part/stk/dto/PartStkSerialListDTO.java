package dream.part.stk.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 자재출고확정 - 상세 DTO
 * @author  hyosung
 * @version $Id:$
 * @since   1.0
 *
 */
public class PartStkSerialListDTO extends BaseDTO
{
    /** 회사*/
    private String compNo               ="";
    /** 시리얼 NO*/
    private String serialNo             ="";
    /** 설비명 */
    private String description          ="";
    /** 상태  */
    private String equipStatus          ="";
    /** 부품 */
    private String partId               = "";
    /** 부품 NO*/
    private String partNo               = "";
    /** 부품명 */
    private String partDesc             = "";
	/** 설비ID */
    private String equipId              ="";
    public String getCompNo()
    {
        return compNo;
    }
    public void setCompNo(String compNo)
    {
        this.compNo = compNo;
    }
    public String getSerialNo()
    {
        return serialNo;
    }
    public void setSerialNo(String serialNo)
    {
        this.serialNo = serialNo;
    }
    public String getDescription()
    {
        return description;
    }
    public void setDescription(String description)
    {
        this.description = description;
    }
    public String getEquipStatus()
    {
        return equipStatus;
    }
    public void setEquipStatus(String equipStatus)
    {
        this.equipStatus = equipStatus;
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
    public String getPartDesc()
    {
        return partDesc;
    }
    public void setPartDesc(String partDesc)
    {
        this.partDesc = partDesc;
    }
    public String getEquipId()
    {
        return equipId;
    }
    public void setEquipId(String equipId)
    {
        this.equipId = equipId;
    }
    
    
    
    
    
}
