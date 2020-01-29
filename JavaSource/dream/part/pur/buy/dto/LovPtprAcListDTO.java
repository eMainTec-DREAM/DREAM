package dream.part.pur.buy.dto;

import common.bean.BaseDTO;

/**
 * 备概脚没 何前   DTO
 * @author  kim21017
 * @version $Id: MaPtBuyReqListDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class LovPtprAcListDTO extends BaseDTO
{
	private String description = "";
	
	private String deptId      = "";
	
	private String deptDesc    = "";

	
    public String getDeptId()
    {
        return deptId;
    }

    public void setDeptId(String deptId)
    {
        this.deptId = deptId;
    }

    public String getDeptDesc()
    {
        return deptDesc;
    }

    public void setDeptDesc(String deptDesc)
    {
        this.deptDesc = deptDesc;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

}