package dream.part.list.dto;

import common.bean.BaseDTO;

/**
 * 부품거래처 목록 dto
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 */
public class MaPtMstrVendorListDTO extends BaseDTO
{
    /** 회사코드 - PK */
    private String compNo           = "";
    /** 자재거래처Id - PK */
    private String ptVendorId       = "";  
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
    public String getPtVendorId()
    {
        return ptVendorId;
    }
    public void setPtVendorId(String ptVendorId)
    {
        this.ptVendorId = ptVendorId;
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