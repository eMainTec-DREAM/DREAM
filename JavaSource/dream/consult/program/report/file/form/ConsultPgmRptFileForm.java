package dream.consult.program.report.file.form;

import common.struts.BaseForm;
import dream.consult.program.report.dto.ConsultPgmRptDTO;
import dream.consult.program.report.file.dto.ConsultPgmRptFileDTO;
import dream.req.work.dto.MaWoReqCommonDTO;
import dream.req.work.dto.MaWoReqDetailDTO;

/**
 * ��¹� ���� ���� - ��� form 
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="consultPgmRptFileForm"
 */
public class ConsultPgmRptFileForm extends BaseForm
{    
    //=======================================================================
    /** Report List ���� */
    private ConsultPgmRptDTO consultPgmRptDTO = new ConsultPgmRptDTO();
    //=======================================================================
    /** Alarm �۾���û */
    private ConsultPgmRptFileDTO consultPgmRptFileDTO = new ConsultPgmRptFileDTO();
    
    /** �۾���û ���� */
    private MaWoReqCommonDTO maWoReqCommonDTO = new MaWoReqCommonDTO();
    
    /** �۾����� �� */
    private MaWoReqDetailDTO maWoReqDetailDTO = new MaWoReqDetailDTO();

	public ConsultPgmRptDTO getConsultPgmRptDTO() {
		return consultPgmRptDTO;
	}

	public void setConsultPgmRptDTO(ConsultPgmRptDTO consultPgmRptDTO) {
		this.consultPgmRptDTO = consultPgmRptDTO;
	}

	public ConsultPgmRptFileDTO getConsultPgmRptFileDTO() {
		return consultPgmRptFileDTO;
	}

	public void setConsultPgmRptFileDTO(ConsultPgmRptFileDTO consultPgmRptFileDTO) {
		this.consultPgmRptFileDTO = consultPgmRptFileDTO;
	}

	public MaWoReqCommonDTO getMaWoReqCommonDTO() {
		return maWoReqCommonDTO;
	}

	public void setMaWoReqCommonDTO(MaWoReqCommonDTO maWoReqCommonDTO) {
		this.maWoReqCommonDTO = maWoReqCommonDTO;
	}

	public MaWoReqDetailDTO getMaWoReqDetailDTO() {
		return maWoReqDetailDTO;
	}

	public void setMaWoReqDetailDTO(MaWoReqDetailDTO maWoReqDetailDTO) {
		this.maWoReqDetailDTO = maWoReqDetailDTO;
	}
}
