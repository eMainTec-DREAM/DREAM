package dream.asset.list.dto;

import common.bean.BaseDTO;

/**
 * AssetListAss Page - List DTO
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 *
 */
public class AssetListAssListDTO extends BaseDTO
{
    /**Key 설비등급평가ID */ 
    private String eqasslistId                  = "";
    /**설비ID */ 
    private String equipId                      = "";
    
    public String getEqasslistId()
    {
        return eqasslistId;
    }
    public void setEqasslistId(String eqasslistId)
    {
        this.eqasslistId = eqasslistId;
        super.setAuditKey(eqasslistId);
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
