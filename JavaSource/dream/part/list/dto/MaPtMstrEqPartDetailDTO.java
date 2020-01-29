package dream.part.list.dto;

import common.bean.BaseDTO;

/**
 * 사용설비 상세 dto
 * @author  ssong
 * @version 
 * @since   1.0
 */
public class MaPtMstrEqPartDetailDTO extends BaseDTO
{
    /** 회사코드 - PK */
    private String compNo           = "";
    /** 설비구성자재Id - PK */
    private String eqPartId         = "";  
    /** 자재Id */
    private String partId           = "";  
    /** 자재명 */
    private String partDesc         = "";  
    /** 설비Id */
    private String equipId          = "";
    /** 설비번호 */
    private String itemNo           = "";
    /** 설비명 */
    private String equipDesc        = "";
    /** 설비부위Id */
    private String eqAsmbId          = "";
    /** 설비부위명 */
    private String eqAsmbDesc        = "";
    /** 설비위치Id */
    private String eqLocId          = "";
    /** 설비위치명 */
    private String eqLocDesc        = "";
    /** 설비종류Id */
    private String eqCtgId          = "";
    /** 설비종류명 */
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