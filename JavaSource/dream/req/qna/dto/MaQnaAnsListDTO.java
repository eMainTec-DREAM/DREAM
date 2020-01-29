package dream.req.qna.dto;

import common.bean.BaseDTO;

/**
 * 질의   DTO
 * @author  kim21017
 * @version $Id: MaQnaAnsListDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class MaQnaAnsListDTO extends BaseDTO
{
	/** 답변ID */
	private String answerId 	= "";

	public String getAnswerId() {
		return answerId;
	}

	public void setAnswerId(String answerId) {
		this.answerId = answerId;
		super.setAuditKey(answerId);
	}
	
}