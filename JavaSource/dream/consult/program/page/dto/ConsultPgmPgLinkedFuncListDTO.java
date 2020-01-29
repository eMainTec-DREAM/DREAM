package dream.consult.program.page.dto;

import common.bean.BaseDTO;

/**
 * 화면별 연결기능 목록 dto
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 */
public class ConsultPgmPgLinkedFuncListDTO extends BaseDTO
{
	/** 연결기능 아이디 */
	private String pgLinkedFuncId 	= "";

	public String getPgLinkedFuncId() {
		return pgLinkedFuncId;
	}

	public void setPgLinkedFuncId(String pgLinkedFuncId) {
		this.pgLinkedFuncId = pgLinkedFuncId;
	}
}