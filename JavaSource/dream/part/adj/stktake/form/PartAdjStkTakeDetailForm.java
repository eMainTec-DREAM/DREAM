package dream.part.adj.stktake.form;

import common.struts.BaseForm;
import dream.doc.file.dto.MaDocLibCommonDTO;
import dream.part.adj.stktake.dto.PartAdjStkTakeCommonDTO;
import dream.part.adj.stktake.dto.PartAdjStkTakeDetailDTO;
import dream.pers.appreq.dto.AppReqCommonDTO;

/**
 * ��ǰ�ǻ� - �� Form
 * @author  kim21017
 * @version $Id: PartAdjStkTakeDetailForm.java,v 1.0 2015/12/02 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="partAdjStkTakeDetailForm"
 */
public class PartAdjStkTakeDetailForm extends BaseForm
{
    //========================================================================
    /** ���Ž�û ���� */ 
    private PartAdjStkTakeCommonDTO partAdjStkTakeCommonDTO = new PartAdjStkTakeCommonDTO();
    //========================================================================
    /** ���Ž�û �� */
    private PartAdjStkTakeDetailDTO partAdjStkTakeDetailDTO = new PartAdjStkTakeDetailDTO();
    
    /** ÷�ι��� DTO */
    private MaDocLibCommonDTO maDocLibCommonDTO = new MaDocLibCommonDTO();
    /** ���� ���� �̷� DTO  */
    private AppReqCommonDTO appReqCommonDTO = new AppReqCommonDTO();

    
	public AppReqCommonDTO getAppReqCommonDTO()
    {
        return appReqCommonDTO;
    }

    public void setAppReqCommonDTO(AppReqCommonDTO appReqCommonDTO)
    {
        this.appReqCommonDTO = appReqCommonDTO;
    }

    public MaDocLibCommonDTO getMaDocLibCommonDTO() {
		return maDocLibCommonDTO;
	}

	public void setMaDocLibCommonDTO(MaDocLibCommonDTO maDocLibCommonDTO) {
		this.maDocLibCommonDTO = maDocLibCommonDTO;
	}

	public PartAdjStkTakeCommonDTO getPartAdjStkTakeCommonDTO() {
		return partAdjStkTakeCommonDTO;
	}

	public void setPartAdjStkTakeCommonDTO(PartAdjStkTakeCommonDTO partAdjStkTakeCommonDTO) {
		this.partAdjStkTakeCommonDTO = partAdjStkTakeCommonDTO;
	}

	public PartAdjStkTakeDetailDTO getPartAdjStkTakeDetailDTO() {
		return partAdjStkTakeDetailDTO;
	}

	public void setPartAdjStkTakeDetailDTO(PartAdjStkTakeDetailDTO partAdjStkTakeDetailDTO) {
		this.partAdjStkTakeDetailDTO = partAdjStkTakeDetailDTO;
	}
}