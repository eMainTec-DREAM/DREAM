package dream.work.service.form;

import common.struts.BaseForm;
import dream.doc.file.dto.MaDocLibCommonDTO;
import dream.work.service.dto.WorkServiceCommonDTO;
import dream.work.service.dto.WorkServiceDetailDTO;

/**
 * 서비스 마스터 상세 Form
 * @author cjscjs9
 * @version $Id: WorkServiceDetailForm.java,v 1.0 2018/07/27 09:12:40 cjscjs9 Exp $
 * @since 1.0
 * @struts.form name="workServiceDetailForm"
 */
public class WorkServiceDetailForm extends BaseForm
{
	/**서비스마스터 공통 DTO*/
	private WorkServiceCommonDTO workServiceCommonDTO = new WorkServiceCommonDTO();
	/**서비스마스터 공통 DTO*/
	private WorkServiceDetailDTO workServiceDetailDTO = new WorkServiceDetailDTO();
    /** 첨부문서 DTO */
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
