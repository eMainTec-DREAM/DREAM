package intf.dream.bee.auth.dto;

import common.bean.BaseDTO;

/**
 * @author  
 * @version $Id: $
 * @since   1.0
 * 
 */
public class BeeAuthCommonDTO extends BaseDTO
{
	
	/** 회사코드 */
	private String compNo                  = "";

	public String getCompNo() {
		return compNo;
	}

	public void setCompNo(String compNo) {
		this.compNo = compNo;
	}
	
	
}
