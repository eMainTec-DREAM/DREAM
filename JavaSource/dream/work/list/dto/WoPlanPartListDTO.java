package dream.work.list.dto;

import common.bean.BaseDTO;

/**
 * �۾���ȹ��� - ���Ժ�ǰ ��� DTO
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 */
public class WoPlanPartListDTO extends BaseDTO
{
	/** �۾���ȹ��� ���Ժ�ǰ id */
	private String woPartId 	= "";

	public String getWoPartId() {
		return woPartId;
	}

	public void setWoPartId(String woPartId) {
		this.woPartId = woPartId;
	}
	
}