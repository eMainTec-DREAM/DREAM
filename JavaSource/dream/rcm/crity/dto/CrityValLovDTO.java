package dream.rcm.crity.dto;

import common.bean.BaseDTO;
/**
 * Criticality Matrix Val LOV
 * @author hyosung
 * @version $Id: CrityValLovDTO.java,v 1.0 2015/12/02 09:12:40 hyosung Exp $
 * @since 1.0
 *
 */
public class CrityValLovDTO extends BaseDTO
{
    /** crityList Έν */
	private String crityListDesc       =  "";

    public String getCrityListDesc()
    {
        return crityListDesc;
    }

    public void setCrityListDesc(String crityListDesc)
    {
        this.crityListDesc = crityListDesc;
    }
	
	
	
}
