package dream.consult.program.page.dto;

import common.bean.BaseDTO;

/**
 * 화면별 탭리스트 목록 dto
 * @author  kim21017
 * @version $Id: MaPgMngPageListDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class MaPgMngPageListDTO extends BaseDTO
{
	/** PGPAGE_ID */
	private String pgPageId 	= "";

	public String getPgPageId() {
		return pgPageId;
	}

	public void setPgPageId(String pgPageId) {
		this.pgPageId = pgPageId;
	}
}