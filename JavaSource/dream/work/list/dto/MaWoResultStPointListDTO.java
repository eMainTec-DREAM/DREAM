package dream.work.list.dto;

import common.bean.BaseDTO;

/**
 * �۾���� �۾��ʼ��˻��׸� ��� DTO
 * @author  kim21017
 * @version $Id: MaWoResultStPointListDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class MaWoResultStPointListDTO extends BaseDTO
{
	/** �۾��ʼ��˻��׸� id */
	private String woStPointId 	= "";

	public String getWoStPointId() {
		return woStPointId;
	}

	public void setWoStPointId(String woStPointId) {
		this.woStPointId = woStPointId;
	}

}