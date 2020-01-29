package dream.ass.asset.form;

import common.struts.BaseForm;
import dream.ass.asset.dto.AssAssetCommonDTO;
import dream.ass.asset.dto.AssAssetDetailDTO;
import dream.asset.list.dto.MaEqMstrCommonDTO;

/**
 * AssAsset Page - Detail Form
 * @author youngjoo38
 * @version $Id: AssAssetDetailForm.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 * @struts.form name="assAssetDetailForm"
 */
public class AssAssetDetailForm extends BaseForm
{
    private AssAssetCommonDTO assAssetCommonDTO = new AssAssetCommonDTO();
    private AssAssetDetailDTO assAssetDetailDTO = new AssAssetDetailDTO();
    private MaEqMstrCommonDTO maEqMstrCommonDTO = new MaEqMstrCommonDTO();

    
    public MaEqMstrCommonDTO getMaEqMstrCommonDTO()
    {
        return maEqMstrCommonDTO;
    }
    public void setMaEqMstrCommonDTO(MaEqMstrCommonDTO maEqMstrCommonDTO)
    {
        this.maEqMstrCommonDTO = maEqMstrCommonDTO;
    }
    public AssAssetCommonDTO getAssAssetCommonDTO() {
        return assAssetCommonDTO;
    }
    public void setAssAssetCommonDTO(AssAssetCommonDTO assAssetCommonDTO) {
        this.assAssetCommonDTO = assAssetCommonDTO;
    }
    public AssAssetDetailDTO getAssAssetDetailDTO() {
        return assAssetDetailDTO;
    }
    public void setAssAssetDetailDTO(AssAssetDetailDTO assAssetDetailDTO) {
        this.assAssetDetailDTO = assAssetDetailDTO;
    }
}
