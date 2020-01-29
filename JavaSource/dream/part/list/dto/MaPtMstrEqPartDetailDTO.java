package dream.part.list.dto;

import common.bean.BaseDTO;

/**
 * ��뼳�� �� dto
 * @author  ssong
 * @version 
 * @since   1.0
 */
public class MaPtMstrEqPartDetailDTO extends BaseDTO
{
    /** ȸ���ڵ� - PK */
    private String compNo           = "";
    /** ���񱸼�����Id - PK */
    private String eqPartId         = "";  
    /** ����Id */
    private String partId           = "";  
    /** ����� */
    private String partDesc         = "";  
    /** ����Id */
    private String equipId          = "";
    /** �����ȣ */
    private String itemNo           = "";
    /** ����� */
    private String equipDesc        = "";
    /** �������Id */
    private String eqAsmbId          = "";
    /** ��������� */
    private String eqAsmbDesc        = "";
    /** ������ġId */
    private String eqLocId          = "";
    /** ������ġ�� */
    private String eqLocDesc        = "";
    /** ��������Id */
    private String eqCtgId          = "";
    /** ���������� */
    private String eqCtgDesc        = "";
    
    public String getEqAsmbId()
    {
        return eqAsmbId;
    }
    public void setEqAsmbId(String eqAsmbId)
    {
        this.eqAsmbId = eqAsmbId;
    }
    public String getEqAsmbDesc()
    {
        return eqAsmbDesc;
    }
    public void setEqAsmbDesc(String eqAsmbDesc)
    {
        this.eqAsmbDesc = eqAsmbDesc;
    }
    public String getEqLocId()
    {
        return eqLocId;
    }
    public void setEqLocId(String eqLocId)
    {
        this.eqLocId = eqLocId;
    }
    public String getEqCtgId()
    {
        return eqCtgId;
    }
    public void setEqCtgId(String eqCtgId)
    {
        this.eqCtgId = eqCtgId;
    }
    public String getItemNo()
    {
        return itemNo;
    }
    public void setItemNo(String itemNo)
    {
        this.itemNo = itemNo;
    }
    public String getPartDesc()
    {
        return partDesc;
    }
    public void setPartDesc(String partDesc)
    {
        this.partDesc = partDesc;
    }
    public String getCompNo()
    {
        return compNo;
    }
    public void setCompNo(String compNo)
    {
        this.compNo = compNo;
    }
    public String getEqPartId()
    {
        return eqPartId;
    }
    public void setEqPartId(String eqPartId)
    {
        this.eqPartId = eqPartId;
        super.setAuditKey(eqPartId);
    }
    public String getPartId()
    {
        return partId;
    }
    public void setPartId(String partId)
    {
        this.partId = partId;
    }
    public String getEquipId()
    {
        return equipId;
    }
    public void setEquipId(String equipId)
    {
        this.equipId = equipId;
    }
    public String getEquipDesc()
    {
        return equipDesc;
    }
    public void setEquipDesc(String equipDesc)
    {
        this.equipDesc = equipDesc;
    }
    public String getEqLocDesc()
    {
        return eqLocDesc;
    }
    public void setEqLocDesc(String eqLocDesc)
    {
        this.eqLocDesc = eqLocDesc;
    }
    public String getEqCtgDesc()
    {
        return eqCtgDesc;
    }
    public void setEqCtgDesc(String eqCtgDesc)
    {
        this.eqCtgDesc = eqCtgDesc;
    }
   }