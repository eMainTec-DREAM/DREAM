package dream.tool.list.dto;

import common.bean.BaseDTO;

/**
 * ��ǰ������ List DTO
 * @author  
 * @version $Id: $
 * @since   1.0
 * 
 */
public class MaPttMstrListDTO extends BaseDTO
{
    /** ����Id */
    private String partId = "";

    public String getPartId()
    {
        return partId;
    }

    public void setPartId(String partId)
    {
        this.partId = partId;
    }
}
