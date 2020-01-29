package dream.ass.asset.form;

import common.struts.BaseForm;
import dream.ass.asset.dto.AssAssetCommonDTO;


/**
 * AssAsset Page - List Form
 * @author youngjoo38
 * @version $Id: AssAssetListForm.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 * @struts.form name="assAssetListForm"
 * */
public class AssAssetListForm extends BaseForm {
    
    private AssAssetCommonDTO assAssetCommonDTO = new AssAssetCommonDTO();

    public AssAssetCommonDTO getAssAssetCommonDTO() {
        return assAssetCommonDTO;
    }

    public void setAssAssetCommonDTO(AssAssetCommonDTO assAssetCommonDTO) {
        this.assAssetCommonDTO = assAssetCommonDTO;
    }
}