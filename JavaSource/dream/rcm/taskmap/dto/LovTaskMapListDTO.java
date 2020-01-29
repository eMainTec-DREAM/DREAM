package dream.rcm.taskmap.dto;

import common.bean.BaseDTO;

/**
 * ÁúÀÇ ÆË¾÷ DTO
 * @author  hyosung
 * @version $Id: LovTaskMapListDTO.java,v 1.1 2016/01/18 09:12:12 hyosung Exp $
 * @since   1.0
 */
public class LovTaskMapListDTO extends BaseDTO
{
    /** Search Description */
    private String taskMapDesc 	= "";
    
	
	public String getTaskMapDesc() {
		return taskMapDesc;
	}
	public void setTaskMapDesc(String taskMapDesc) {
		this.taskMapDesc = taskMapDesc;
	}
	
    
}
