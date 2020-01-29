package dream.work.pm.std.form;

import common.struts.BaseForm;
import dream.doc.file.dto.MaDocLibCommonDTO;
import dream.work.pm.std.dto.MaStdPointCommonDTO;
import dream.work.pm.std.dto.MaStdPointDetailDTO;
import dream.work.pm.std.dto.MaStdPointListDTO;

/**
 * 표준항목 리스트- 상세 Form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="maStdPointDetailForm"
 */
public class MaStdPointDetailForm extends BaseForm
{
    //========================================================================
    /** 표준항목 공통 */ 
    private MaStdPointCommonDTO maStdPointCommonDTO = new MaStdPointCommonDTO();
    //========================================================================
    /** 표준항목리슽 목록 */
    private MaStdPointListDTO maStdPointListDTO = new MaStdPointListDTO();
    /** 표준항목리슽 상세 */
    private MaStdPointDetailDTO maStdPointDetailDTO = new MaStdPointDetailDTO();
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
	public MaStdPointCommonDTO getMaStdPointCommonDTO() 
	{
		return maStdPointCommonDTO;
	}

	public void setMaStdPointCommonDTO(MaStdPointCommonDTO maStdPointCommonDTO) 
	{
		this.maStdPointCommonDTO = maStdPointCommonDTO;
	}

	public MaStdPointDetailDTO getMaStdPointDetailDTO() 
	{
		return maStdPointDetailDTO;
	}

	public void setMaStdPointDetailDTO(MaStdPointDetailDTO maStdPointDetailDTO) 
	{
		this.maStdPointDetailDTO = maStdPointDetailDTO;
	}

	public MaStdPointListDTO getMaStdPointListDTO() {
		return maStdPointListDTO;
	}

	public void setMaStdPointListDTO(MaStdPointListDTO maStdPointListDTO) {
		this.maStdPointListDTO = maStdPointListDTO;
	}

}
