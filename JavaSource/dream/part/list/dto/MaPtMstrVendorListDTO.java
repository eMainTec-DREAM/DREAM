package dream.part.list.dto;

import common.bean.BaseDTO;

/**
 * ��ǰ�ŷ�ó ��� dto
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 */
public class MaPtMstrVendorListDTO extends BaseDTO
{
    /** ȸ���ڵ� - PK */
    private String compNo           = "";
    /** ����ŷ�óId - PK */
    private String ptVendorId       = "";  
    /** ����Id */
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