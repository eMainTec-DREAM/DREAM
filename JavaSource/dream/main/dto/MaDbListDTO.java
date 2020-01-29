package dream.main.dto;

import common.bean.BaseDTO;

/**
 * Dashboard List DTO
 * @author  
 * @version $Id: $
 * @since   1.0
 * 
 */
public class MaDbListDTO extends BaseDTO
{
	
	/** 회사코드 */
	private String compNo                  = "";

    public String getCompNo()
    {
        return compNo;
    }

    public void setCompNo(String compNo)
    {
        this.compNo = compNo;
    }

}
