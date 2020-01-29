package dream.ass.asset.form;

import dream.ass.asset.dto.AssAssetScoreCopyLovDTO;
import dream.comm.form.MaFinderAcForm;

/**
 * �򰡰������ LOV Form
 * @author  js.lee
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="assAssetScoreCopyLovForm"
 */
public class AssAssetScoreCopyLovForm extends MaFinderAcForm
{
    /** �򰡰������ LOV DTO */
    private AssAssetScoreCopyLovDTO assAssetScoreCopyLovDTO = new AssAssetScoreCopyLovDTO();

	public AssAssetScoreCopyLovDTO getAssAssetScoreCopyLovDTO() {
		return assAssetScoreCopyLovDTO;
	}

	public void setAssAssetScoreCopyLovDTO(AssAssetScoreCopyLovDTO assAssetScoreCopyLovDTO) {
		this.assAssetScoreCopyLovDTO = assAssetScoreCopyLovDTO;
	}
}
