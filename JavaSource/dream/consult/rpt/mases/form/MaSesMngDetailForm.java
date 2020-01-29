package dream.consult.rpt.mases.form;

import common.struts.BaseForm;
import dream.consult.rpt.mases.dto.MaSesMngCommonDTO;

/**
 * 실시간 접속자 상세
 * @author  kim21017
 * @version $Id: MaSesMngDetailForm.java,v 1.0 2015/12/10 09:10:18 kim21017 Exp $
 * @since   1.0
 * @struts.form name="maSesMngDetailForm"
 */
public class MaSesMngDetailForm extends BaseForm
{
    /** 공통 DTO */
    private MaSesMngCommonDTO maSesMngCommonDTO = new MaSesMngCommonDTO();
    
    public MaSesMngCommonDTO getMaSesMngCommonDTO() {
		return maSesMngCommonDTO;
	}

	public void setMaSesMngCommonDTO(MaSesMngCommonDTO maSesMngCommonDTO) {
		this.maSesMngCommonDTO = maSesMngCommonDTO;
	}
}
