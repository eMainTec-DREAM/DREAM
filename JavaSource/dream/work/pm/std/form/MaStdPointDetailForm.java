package dream.work.pm.std.form;

import common.struts.BaseForm;
import dream.doc.file.dto.MaDocLibCommonDTO;
import dream.work.pm.std.dto.MaStdPointCommonDTO;
import dream.work.pm.std.dto.MaStdPointDetailDTO;
import dream.work.pm.std.dto.MaStdPointListDTO;

/**
 * ǥ���׸� ����Ʈ- �� Form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="maStdPointDetailForm"
 */
public class MaStdPointDetailForm extends BaseForm
{
    //========================================================================
    /** ǥ���׸� ���� */ 
    private MaStdPointCommonDTO maStdPointCommonDTO = new MaStdPointCommonDTO();
    //========================================================================
    /** ǥ���׸񸮚� ��� */
    private MaStdPointListDTO maStdPointListDTO = new MaStdPointListDTO();
    /** ǥ���׸񸮚� �� */
    private MaStdPointDetailDTO maStdPointDetailDTO = new MaStdPointDetailDTO();
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
