package dream.part.rec.dto;

import common.bean.BaseDTO;

/**
 * �����԰� ��ǰ   DTO
 * @author  kim21017
 * @version $Id: MaPtRecSerialListDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class MaPtRecSerialListDTO extends BaseDTO
{
	/** �����ûItem ID */
	private String prreclistSerialId 	= "";

	public String getPrreclistSerialId() {
		return prreclistSerialId;
	}

	public void setPrreclistSerialId(String prreclistSerialId) {
		this.prreclistSerialId = prreclistSerialId;
	}


	
}