package dream.ass.asset.form;

import dream.ass.asset.dto.AssAssetScoreCopyLovDTO;
import dream.comm.form.MaFinderAcForm;

/**
 * 평가결과복사 LOV Form
 * @author  js.lee
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="assAssetScoreCopyLovForm"
 */
public class AssAssetScoreCopyLovForm extends MaFinderAcForm
{
    /** 평가결과복사 LOV DTO */
    private AssAssetScoreCopyLovDTO assAssetScoreCopyLovDTO = new AssAssetScoreCopyLovDTO();

	public AssAssetScoreCopyLovDTO getAssAssetScoreCopyLovDTO() {
		return assAssetScoreCopyLovDTO;
	}

	public void setAssAssetScoreCopyLovDTO(AssAssetScoreCopyLovDTO assAssetScoreCopyLovDTO) {
		this.assAssetScoreCopyLovDTO = assAssetScoreCopyLovDTO;
	}
}
