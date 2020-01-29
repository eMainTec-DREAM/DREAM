package dream.cert.list.form;

import common.struts.BaseForm;

import dream.cert.list.dto.CertCommonDTO;
import dream.cert.list.dto.CertEmpDetailDTO;
import dream.cert.list.dto.CertEmpListDTO;
import dream.doc.file.dto.MaDocLibCommonDTO;

/**
 * 작업결과 작업자
 * @author  kim2107
 * @version $Id: CertEmpDetailForm.java,v 1.0 2015/12/04 09:09:54 kim2107 Exp $
 * @since   1.0
 *
 * @struts.form name="certEmpDetailForm"
 */
public class CertEmpDetailForm extends BaseForm
{
    //===============================================================
    /** 공통 DTO */
    private CertCommonDTO certCommonDTO = new CertCommonDTO();
	/** 작업결과 작업자 목록 DTO  */
    private CertEmpListDTO certEmpListDTO = new CertEmpListDTO();
	/** 작업결과 작업자 상세 DTO  */
    private CertEmpDetailDTO certEmpDetailDTO = new CertEmpDetailDTO();
    /** 첨부문서 DTO */
    private MaDocLibCommonDTO maDocLibCommonDTO = new MaDocLibCommonDTO();
    
	public MaDocLibCommonDTO getMaDocLibCommonDTO() {
		return maDocLibCommonDTO;
	}
	public void setMaDocLibCommonDTO(MaDocLibCommonDTO maDocLibCommonDTO) {
		this.maDocLibCommonDTO = maDocLibCommonDTO;
	}
	public CertCommonDTO getCertCommonDTO() {
		return certCommonDTO;
	}
	public void setCertCommonDTO(CertCommonDTO certCommonDTO) {
		this.certCommonDTO = certCommonDTO;
	}
	public CertEmpListDTO getCertEmpListDTO() {
		return certEmpListDTO;
	}
	public void setCertEmpListDTO(CertEmpListDTO certEmpListDTO) {
		this.certEmpListDTO = certEmpListDTO;
	}
	public CertEmpDetailDTO getCertEmpDetailDTO() {
		return certEmpDetailDTO;
	}
	public void setCertEmpDetailDTO(CertEmpDetailDTO certEmpDetailDTO) {
		this.certEmpDetailDTO = certEmpDetailDTO;
	}
    
    

}
