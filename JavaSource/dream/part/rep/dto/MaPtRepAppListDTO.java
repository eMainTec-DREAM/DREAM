package dream.part.rep.dto;

import common.bean.BaseDTO;

/**
 * ������� ��� dto
 * @author  ssong
 * @version $Id: $
 * @since   1.0
 */
public class MaPtRepAppListDTO extends BaseDTO
{
	/** ���ǰ�� id */
	private String ptRprAppListId 	= "";

    public String getPtRprAppListId()
    {
        return ptRprAppListId;
    }

    public void setPtRprAppListId(String ptRprAppListId)
    {
        this.ptRprAppListId = ptRprAppListId;
    }
	
}