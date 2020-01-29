package dream.work.service.form;

import common.struts.BaseForm;
import dream.doc.file.dto.MaDocLibCommonDTO;
import dream.work.service.dto.WorkServiceCommonDTO;
import dream.work.service.dto.WorkServiceDetailDTO;

/**
 * ���� ������ �� Form
 * @author cjscjs9
 * @version $Id: WorkServiceDetailForm.java,v 1.0 2018/07/27 09:12:40 cjscjs9 Exp $
 * @since 1.0
 * @struts.form name="workServiceDetailForm"
 */
public class WorkServiceDetailForm extends BaseForm
{
	/**���񽺸����� ���� DTO*/
	private WorkServiceCommonDTO workServiceCommonDTO = new WorkServiceCommonDTO();
	/**���񽺸����� ���� DTO*/
	private WorkServiceDetailDTO workServiceDetailDTO = new WorkServiceDetailDTO();
    /** ÷�ι��� DTO */
    private MaDocLibCommonDTO maDocLibCommonDTO = new MaDocLibCommonDTO();
    
	public MaDocLibCommonDTO getMaDocLibCommonDTO() {
		return maDocLibCommonDTO;
	}
	public void setMaDocLibCommonDTO(MaDocLibCommonDTO maDocLibCommonDTO) {
		this.maDocLibCommonDTO = maDocLibCommonDTO;
	}
	public WorkServiceCommonDTO getWorkServiceCommonDTO() {
		return workServiceCommonDTO;
	}
	public void setWorkServiceCommonDTO(WorkServiceCommonDTO workServiceCommonDTO) {
		this.workServiceCommonDTO = workServiceCommonDTO;
	}
	public WorkServiceDetailDTO getWorkServiceDetailDTO() {
		return workServiceDetailDTO;
	}
	public void setWorkServiceDetailDTO(WorkServiceDetailDTO workServiceDetailDTO) {
		this.workServiceDetailDTO = workServiceDetailDTO;
	}
}
