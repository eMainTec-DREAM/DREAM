package dream.part.list.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 자재재고 공통 DTO
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 * 
 */
public class MaPtMstrStockListDTO extends BaseDTO
{
	/** ID */
	private String wcodeId             = "";
	private String partId              = "";
	
    public String getWcodeId()
    {
        return wcodeId;
    }
    public void setWcodeId(String wcodeId)
    {
        this.wcodeId = wcodeId;
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
