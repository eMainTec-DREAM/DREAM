package dream.work.list.dto;

import common.bean.BaseDTO;

/**
 * �۾���� ���� Serial ��� DTO
 * @author  jung7126
 * @version $Id: WorkListBmRplPartSerialListDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class WorkListBmRplPartSerialListDTO extends BaseDTO
{
	/** �۾���ǰSerial id */
	private String wopartSerialId 	= "";

	public String getWopartSerialId() {
		return wopartSerialId;
	}

	public void setWopartSerialId(String wopartSerialId) {
		this.wopartSerialId = wopartSerialId;
	}
}