package dream.part.list.dto;

import common.bean.BaseDTO;

/**
 * 사용설비 목록 dto
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 */
public class MaPtMstrEqPartListDTO extends BaseDTO
{
    /** 회사코드 - PK */
    private String compNo           = "";
    /** 설비구성자재Id - PK */
    private String eqPartId         = "";  
    /** 자재Id */
    private String partId           = "";
    
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
    }
    public String getPartId()
    {
        return partId;
    }
    public void setPartId(String partId)
    {
        this.partId = partId;
    } 

}