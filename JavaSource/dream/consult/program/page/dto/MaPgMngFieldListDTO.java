package dream.consult.program.page.dto;

import common.bean.BaseDTO;

/**
 * ȭ�麰 �ʵ� ��� dto
 * @author  kim21017
 * @version $Id: MaPgMngFieldListDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class MaPgMngFieldListDTO extends BaseDTO
{
	/** �ʵ���̵� */
	private String pgFieldId 	= "";

	public String getPgFieldId() {
		return pgFieldId;
	}

	public void setPgFieldId(String pgFieldId) {
		this.pgFieldId = pgFieldId;
	}
}