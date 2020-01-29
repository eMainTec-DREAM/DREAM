package dream.consult.program.onlinehelp.form;

import common.struts.BaseForm;
import dream.consult.program.onlinehelp.dto.ConsultProgramOnlinehelpCommonDTO;
import dream.consult.program.onlinehelp.dto.ConsultProgramOnlinehelpDetailDTO;
import dream.doc.file.dto.MaDocLibCommonDTO;

/**
 * ����- �� Form
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="consultProgramOnlinehelpDetailForm"
 */
public class ConsultProgramOnlinehelpDetailForm extends BaseForm
{
    //========================================================================
    /** ȭ�� ���� */ 
    private ConsultProgramOnlinehelpCommonDTO consultProgramOnlinehelpCommonDTO = new ConsultProgramOnlinehelpCommonDTO();
    //========================================================================
    /** ȭ�� �� */
    private ConsultProgramOnlinehelpDetailDTO consultProgramOnlinehelpDetailDTO = new ConsultProgramOnlinehelpDetailDTO();
    /** ÷�ι��� DTO */
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
