package dream.consult.program.page.dto;

import common.bean.BaseDTO;

/**
 * ȭ�麰 ������ ��� dto
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 */
public class ConsultPgmPgLinkedFuncListDTO extends BaseDTO
{
	/** ������ ���̵� */
	private String pgLinkedFuncId 	= "";

	public String getPgLinkedFuncId() {
		return pgLinkedFuncId;
	}

	public void setPgLinkedFuncId(String pgLinkedFuncId) {
		this.pgLinkedFuncId = pgLinkedFuncId;
	}
}