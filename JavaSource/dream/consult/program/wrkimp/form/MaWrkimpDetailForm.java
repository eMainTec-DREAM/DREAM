package dream.consult.program.wrkimp.form;

import common.struts.BaseForm;
import dream.consult.program.wrkimp.dto.MaWrkimpCommonDTO;
import dream.consult.program.wrkimp.dto.MaWrkimpDetailDTO;
import dream.doc.file.dto.MaDocLibCommonDTO;


/**
 * 상세 Form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="maWrkimpDetailForm"
 */
public class MaWrkimpDetailForm extends BaseForm
{
    //========================================================================
    /** 공통 */ 
    private MaWrkimpCommonDTO maWrkimpCommonDTO = new MaWrkimpCommonDTO();
    //========================================================================
    /** 상세 */
    private MaWrkimpDetailDTO maWrkimpDetailDTO = new MaWrkimpDetailDTO();

    /** 첨부문서 DTO */
    private MaDocLibCommonDTO maDocLibCommonDTO = new MaDocLibCommonDTO();

    
	public MaDocLibCommonDTO getMaDocLibCommonDTO() {
		return maDocLibCommonDTO;
	}

	public void setMaDocLibCommonDTO(MaDocLibCommonDTO maDocLibCommonDTO) {
		this.maDocLibCommonDTO = maDocLibCommonDTO;
	}

	public MaWrkimpCommonDTO getMaWrkimpCommonDTO() 
	{
		return maWrkimpCommonDTO;
	}

	public void setMaWrkimpCommonDTO(MaWrkimpCommonDTO maWrkimpCommonDTO) 
	{
		this.maWrkimpCommonDTO = maWrkimpCommonDTO;
	}

	public MaWrkimpDetailDTO getMaWrkimpDetailDTO() 
	{
		return maWrkimpDetailDTO;
	}

	public void setMaWrkimpDetailDTO(MaWrkimpDetailDTO maWrkimpDetailDTO) 
	{
		this.maWrkimpDetailDTO = maWrkimpDetailDTO;
	}

}
