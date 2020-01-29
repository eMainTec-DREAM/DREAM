package dream.part.rec.dto;

import common.bean.BaseDTO;

/**
 * LovPoItemAcList DTO
 * @author  nhkim8548
 * @version $Id: LovPoItemAcList.java,v 1.0 2018/08/06 09:21:20 nhkim8548 Exp $
 * @since   1.0
 */
public class LovPoItemAcListDTO extends BaseDTO
{
    /** 발주 ID */
    private String poItemListId      = "";
    /** 발주번호 */
    private String orderNo           = "";
    /** 부품번호 */
    private String partNo            = "";
    /** 부품명 */
    private String partNameSize      = "";
    /** 공장 ID */
    private String plantId           = "";
    /** 공장 DESC */
    private String plantDesc         = "";
    
    public String getPoItemListId()
    {
        return poItemListId;
    }
    public void setPoItemListId(String poItemListId)
    {
        this.poItemListId = poItemListId;
    }
    public String getOrderNo()
    {
        return orderNo;
    }
    public void setOrderNo(String orderNo)
    {
        this.orderNo = orderNo;
    }
    public String getPartNo()
    {
        return partNo;
    }
    public void setPartNo(String partNo)
    {
        this.partNo = partNo;
    }
    public String getPartNameSize()
    {
        return partNameSize;
    }
    public void setPartNameSize(String partNameSize)
    {
        this.partNameSize = partNameSize;
    }
    public String getPlantId()
    {
        return plantId;
    }
    public void setPlantId(String plantId)
    {
        this.plantId = plantId;
    }
    public String getPlantDesc()
    {
        return plantDesc;
    }
    public void setPlantDesc(String plantDesc)
    {
        this.plantDesc = plantDesc;
    }
}
