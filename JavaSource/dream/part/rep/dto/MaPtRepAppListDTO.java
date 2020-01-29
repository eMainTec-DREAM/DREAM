package dream.part.rep.dto;

import common.bean.BaseDTO;

/**
 * 수리기안 목록 dto
 * @author  ssong
 * @version $Id: $
 * @since   1.0
 */
public class MaPtRepAppListDTO extends BaseDTO
{
	/** 기안품의 id */
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