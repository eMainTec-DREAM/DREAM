package dream.ass.asset.form;

import common.struts.BaseForm;
import dream.ass.asset.dto.AssAssetCommonDTO;
import dream.ass.asset.dto.AssAssetScoreDetailDTO;
import dream.ass.asset.dto.AssAssetScoreListDTO;

/**
 * 평가점수 - Detail Form
 * @author youngjoo38
 * @version $Id: AssAssetScoreDetailForm.java,v 1.0 2017/08/28 12:37:40 youngjoo38 Exp $
 * @since 1.0
 * @struts.form name="assAssetScoreDetailForm"
 */
public class AssAssetScoreDetailForm extends BaseForm
{
	private AssAssetCommonDTO assAssetCommonDTO = new AssAssetCommonDTO();
	private AssAssetScoreListDTO assAssetScoreListDTO = new AssAssetScoreListDTO();
	private AssAssetScoreDetailDTO assAssetScoreDetailDTO = new AssAssetScoreDetailDTO();
	
	public AssAssetCommonDTO getAssAssetCommonDTO() {
		return assAssetCommonDTO;
	}
	public void setAssAssetCommonDTO(AssAssetCommonDTO assAssetCommonDTO) {
		this.assAssetCommonDTO = assAssetCommonDTO;
	}
	public AssAssetScoreListDTO getAssAssetScoreListDTO() {
		return assAssetScoreListDTO;
	}
	public void setAssAssetScoreListDTO(AssAssetScoreListDTO assAssetScoreListDTO) {
		this.assAssetScoreListDTO = assAssetScoreListDTO;
	}
	public AssAssetScoreDetailDTO getAssAssetScoreDetailDTO() {
		return assAssetScoreDetailDTO;
	}
	public void setAssAssetScoreDetailDTO(AssAssetScoreDetailDTO assAssetScoreDetailDTO) {
		this.assAssetScoreDetailDTO = assAssetScoreDetailDTO;
	}
}
