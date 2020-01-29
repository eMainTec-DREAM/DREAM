package dream.asset.list.form;

import common.struts.BaseForm;
import dream.asset.list.dto.AssetListITDetailDTO;
import dream.asset.list.dto.MaEqBuildMstrDetailDTO;
import dream.asset.list.dto.MaEqDeviceMstrDetailDTO;
import dream.asset.list.dto.MaEqMoldMstrDetailDTO;
import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.dto.MaEqMstrDetailDTO;
import dream.asset.list.dto.MaEqToolMstrDetailDTO;
import dream.doc.file.dto.MaDocLibCommonDTO;

/**
 * ���񸶽���- �� Form
 * @author  kim21017
 * @version $Id: MaEqMstrDetailForm.java,v 1.0 2015/12/02 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maEqMstrDetailForm"
 */
public class MaEqMstrDetailForm extends BaseForm
{
    //========================================================================
    /** ���񸶽��� ���� */ 
    private MaEqMstrCommonDTO maEqMstrCommonDTO = new MaEqMstrCommonDTO();
    //========================================================================
    /** ���񸶽��� �� */
    private MaEqMstrDetailDTO maEqMstrDetailDTO = new MaEqMstrDetailDTO();
    /** ÷�ι��� DTO */
    private MaDocLibCommonDTO maDocLibCommonDTO = new MaDocLibCommonDTO();
    /** ���� �� DTO */
    private MaEqMoldMstrDetailDTO maEqMoldMstrDetailDTO = new MaEqMoldMstrDetailDTO();
    /** ������ �� DTO */
    private MaEqToolMstrDetailDTO maEqToolMstrDetailDTO = new MaEqToolMstrDetailDTO();
    /** �ǹ� �� DTO */
    private MaEqBuildMstrDetailDTO maEqBuildMstrDetailDTO = new MaEqBuildMstrDetailDTO();
    /** ���� �� DTO */
    private MaEqDeviceMstrDetailDTO maEqDeviceMstrDetailDTO = new MaEqDeviceMstrDetailDTO();
    /** IT��� �� DTO */
    private AssetListITDetailDTO assetListITDetailDTO = new AssetListITDetailDTO();
    
	public MaEqDeviceMstrDetailDTO getMaEqDeviceMstrDetailDTO() {
		return maEqDeviceMstrDetailDTO;
	}

	public AssetListITDetailDTO getAssetListITDetailDTO() {
		return assetListITDetailDTO;
	}

	public void setAssetListITDetailDTO(AssetListITDetailDTO assetListITDetailDTO) {
		this.assetListITDetailDTO = assetListITDetailDTO;
	}

	public void setMaEqDeviceMstrDetailDTO(MaEqDeviceMstrDetailDTO maEqDeviceMstrDetailDTO) {
		this.maEqDeviceMstrDetailDTO = maEqDeviceMstrDetailDTO;
	}

	public MaEqBuildMstrDetailDTO getMaEqBuildMstrDetailDTO() {
		return maEqBuildMstrDetailDTO;
	}

	public void setMaEqBuildMstrDetailDTO(MaEqBuildMstrDetailDTO maEqBuildMstrDetailDTO) {
		this.maEqBuildMstrDetailDTO = maEqBuildMstrDetailDTO;
	}

	public MaEqToolMstrDetailDTO getMaEqToolMstrDetailDTO() {
		return maEqToolMstrDetailDTO;
	}

	public void setMaEqToolMstrDetailDTO(MaEqToolMstrDetailDTO maEqToolMstrDetailDTO) {
		this.maEqToolMstrDetailDTO = maEqToolMstrDetailDTO;
	}

	public MaEqMoldMstrDetailDTO getMaEqMoldMstrDetailDTO() {
		return maEqMoldMstrDetailDTO;
	}

	public void setMaEqMoldMstrDetailDTO(MaEqMoldMstrDetailDTO maEqMoldMstrDetailDTO) {
		this.maEqMoldMstrDetailDTO = maEqMoldMstrDetailDTO;
	}

	public MaDocLibCommonDTO getMaDocLibCommonDTO()
    {
        return maDocLibCommonDTO;
    }

    public void setMaDocLibCommonDTO(MaDocLibCommonDTO maDocLibCommonDTO)
    {
        this.maDocLibCommonDTO = maDocLibCommonDTO;
    }

    public MaEqMstrCommonDTO getMaEqMstrCommonDTO() {
		return maEqMstrCommonDTO;
	}

    public void setMaEqMstrCommonDTO(MaEqMstrCommonDTO maEqMstrCommonDTO) {
		this.maEqMstrCommonDTO = maEqMstrCommonDTO;
	}

	public MaEqMstrDetailDTO getMaEqMstrDetailDTO() {
		return maEqMstrDetailDTO;
	}

	public void setMaEqMstrDetailDTO(MaEqMstrDetailDTO maEqMstrDetailDTO) {
		this.maEqMstrDetailDTO = maEqMstrDetailDTO;
	}
}
