package dream.consult.program.page.dto;

import common.bean.BaseDTO;

/**
 * �ʵ� �⺻ �� ��� dto
 * @author  kim21017
 * @version $Id: MaPgMngFieldValueListDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class MaPgMngFieldValueListDTO extends BaseDTO
{
	/** �ʵ���̵� */
	private String pgFieldId 		= "";
	/** �ʵ� �� ���̵� */
	private String pgFieldValueId 	= "";

	public String getPgFieldId() {
		return pgFieldId;
	}
	public void setPgFieldId(String pgFieldId) {
		this.pgFieldId = pgFieldId;
	}
	public String getPgFieldValueId() {
		return pgFieldValueId;
	}
	public void setPgFieldValueId(String pgFieldValueId) {
		this.pgFieldValueId = pgFieldValueId;
	}
}