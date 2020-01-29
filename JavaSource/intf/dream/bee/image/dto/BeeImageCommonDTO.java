package intf.dream.bee.image.dto;

import common.bean.BaseDTO;

/**
 * 공통 DTO
 * @author  
 * @version $Id: $
 * @since   1.0
 * 
 */
public class BeeImageCommonDTO extends BaseDTO
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
