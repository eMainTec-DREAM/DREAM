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
    /** ���� ID */
    private String poItemListId      = "";
    /** ���ֹ�ȣ */
    private String orderNo           = "";
    /** ��ǰ��ȣ */
    private String partNo            = "";
    /** ��ǰ�� */
    private String partNameSize      = "";
    /** ���� ID */
    private String plantId           = "";
    /** ���� DESC */
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
