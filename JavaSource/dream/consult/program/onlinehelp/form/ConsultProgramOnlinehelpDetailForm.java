package dream.consult.program.onlinehelp.form;

import common.struts.BaseForm;
import dream.consult.program.onlinehelp.dto.ConsultProgramOnlinehelpCommonDTO;
import dream.consult.program.onlinehelp.dto.ConsultProgramOnlinehelpDetailDTO;
import dream.doc.file.dto.MaDocLibCommonDTO;

/**
 * 도움말- 상세 Form
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="consultProgramOnlinehelpDetailForm"
 */
public class ConsultProgramOnlinehelpDetailForm extends BaseForm
{
    //========================================================================
    /** 화면 공통 */ 
    private ConsultProgramOnlinehelpCommonDTO consultProgramOnlinehelpCommonDTO = new ConsultProgramOnlinehelpCommonDTO();
    //========================================================================
    /** 화면 상세 */
    private ConsultProgramOnlinehelpDetailDTO consultProgramOnlinehelpDetailDTO = new ConsultProgramOnlinehelpDetailDTO();
    /** 첨부문서 DTO */
    private MaDocLibCommonDTO maDocLibCommonDTO = new MaDocLibCommonDTO();
    

	public MaDocLibCommonDTO getMaDocLibCommonDTO()
    {
        return maDocLibCommonDTO;
    }

    public void setMaDocLibCommonDTO(MaDocLibCommonDTO maDocLibCommonDTO)
    {
        this.maDocLibCommonDTO = maDocLibCommonDTO;
    }

    public ConsultProgramOnlinehelpCommonDTO getConsultProgramOnlinehelpCommonDTO() {
		return consultProgramOnlinehelpCommonDTO;
	}

    public void setConsultProgramOnlinehelpCommonDTO(ConsultProgramOnlinehelpCommonDTO consultProgramOnlinehelpCommonDTO) {
		this.consultProgramOnlinehelpCommonDTO = consultProgramOnlinehelpCommonDTO;
	}

	public ConsultProgramOnlinehelpDetailDTO getConsultProgramOnlinehelpDetailDTO() {
		return consultProgramOnlinehelpDetailDTO;
	}

	public void setConsultProgramOnlinehelpDetailDTO(ConsultProgramOnlinehelpDetailDTO consultProgramOnlinehelpDetailDTO) {
		this.consultProgramOnlinehelpDetailDTO = consultProgramOnlinehelpDetailDTO;
	}
}
