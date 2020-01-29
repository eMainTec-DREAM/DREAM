package dream.part.stk.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * �������Ȯ�� - �� DTO
 * @author  hyosung
 * @version $Id:$
 * @since   1.0
 *
 */
public class PartStkSerialListDTO extends BaseDTO
{
    /** ȸ��*/
    private String compNo               ="";
    /** �ø��� NO*/
    private String serialNo             ="";
    /** ����� */
    private String description          ="";
    /** ����  */
    private String equipStatus          ="";
    /** ��ǰ */
    private String partId               = "";
    /** ��ǰ NO*/
    private String partNo               = "";
    /** ��ǰ�� */
    private String partDesc             = "";
	/** ����ID */
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
