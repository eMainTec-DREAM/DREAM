package dream.part.iss.wo.dto;

import common.bean.BaseDTO;

/**
 * ½Ã¸®¾ó ÆË¾÷ DTO
 * @author  hyosung
 * @version $Id: LovSerialListDTO.java,v 1.1 2016/01/18 09:12:12 hyosung Exp $
 * @since   1.0
 */
public class LovSerialListDTO extends BaseDTO
{
    private String serialNo     ="";
   
    private String equipId      ="";
    private String partDesc     ="";
    
    

    public String getSerialNo()
    {
        return serialNo;
    }

    public void setSerialNo(String serialNo)
    {
        this.serialNo = serialNo;
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
