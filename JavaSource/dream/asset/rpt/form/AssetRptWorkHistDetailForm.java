package dream.asset.rpt.form;

import common.struts.BaseForm;
import dream.asset.rpt.dto.AssetRptWorkHistCommonDTO;
import dream.asset.rpt.dto.AssetRptWorkHistDetailDTO;
import dream.doc.file.dto.MaDocLibCommonDTO;

/**
 * 설비이력(과거) - Detail Form
 * @author js.lee
 * @version $Id: $
 * @since 1.0
 * @struts.form name="assetRptWorkHistDetailForm"
 */
public class AssetRptWorkHistDetailForm extends BaseForm
{
	private AssetRptWorkHistCommonDTO assetRptWorkHistCommonDTO = new AssetRptWorkHistCommonDTO();
	private AssetRptWorkHistDetailDTO assetRptWorkHistDetailDTO = new AssetRptWorkHistDetailDTO();
    
    /** 첨부문서 DTO */
    private MaDocLibCommonDTO maDocLibCommonDTO = new MaDocLibCommonDTO();
    
	public MaDocLibCommonDTO getMaDocLibCommonDTO() {
		return maDocLibCommonDTO;
	}
	public void setMaDocLibCommonDTO(MaDocLibCommonDTO maDocLibCommonDTO) {
		this.maDocLibCommonDTO = maDocLibCommonDTO;
	}
	public AssetRptWorkHistCommonDTO getAssetRptWorkHistCommonDTO() {
		return assetRptWorkHistCommonDTO;
	}
	public void setAssetRptWorkHistCommonDTO(AssetRptWorkHistCommonDTO assetRptWorkHistCommonDTO) {
		this.assetRptWorkHistCommonDTO = assetRptWorkHistCommonDTO;
	}
	public AssetRptWorkHistDetailDTO getAssetRptWorkHistDetailDTO() {
		return assetRptWorkHistDetailDTO;
	}
	public void setAssetRptWorkHistDetailDTO(AssetRptWorkHistDetailDTO assetRptWorkHistDetailDTO) {
		this.assetRptWorkHistDetailDTO = assetRptWorkHistDetailDTO;
	}
}
