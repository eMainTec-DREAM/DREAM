package dream.ass.asset.form;

import common.struts.BaseForm;
import dream.ass.asset.dto.AssAssetCommonDTO;
import dream.ass.asset.dto.AssAssetScoreListDTO;

/**
 * AssAssetScore Page - List Form
 * @author youngjoo38
 * @version $Id: AssAssetScoreListForm.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 * @struts.form name="assAssetScoreListForm"
 * */
public class AssAssetScoreListForm extends BaseForm {
    
    private AssAssetCommonDTO assAssetCommonDTO = new AssAssetCommonDTO();
    private AssAssetScoreListDTO assAssetScoreListDTO = new AssAssetScoreListDTO();

    
    public AssAssetCommonDTO getAssAssetCommonDTO()
    {
        return assAssetCommonDTO;
    }

    public void setAssAssetCommonDTO(AssAssetCommonDTO assAssetCommonDTO)
    {
        this.assAssetCommonDTO = assAssetCommonDTO;
    }

    public AssAssetScoreListDTO getAssAssetScoreListDTO() {
        return assAssetScoreListDTO;
    }

    public void setAssAssetScoreListDTO(AssAssetScoreListDTO assAssetScoreListDTO) {
        this.assAssetScoreListDTO = assAssetScoreListDTO;
    }
}