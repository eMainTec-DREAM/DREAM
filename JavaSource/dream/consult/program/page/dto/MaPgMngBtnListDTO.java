package dream.consult.program.page.dto;

import common.bean.BaseDTO;

/**
 * 화면별 버튼 목록 dto
 * @author  kim21017
 * @version $Id: MaPgMngBtnListDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class MaPgMngBtnListDTO extends BaseDTO
{
	/** PGBTN_ID */
	private String pgBtnId 	= "";

	public String getPgBtnId() {
		return pgBtnId;
	}

	public void setPgBtnId(String pgBtnId) {
		this.pgBtnId = pgBtnId;
	}
}