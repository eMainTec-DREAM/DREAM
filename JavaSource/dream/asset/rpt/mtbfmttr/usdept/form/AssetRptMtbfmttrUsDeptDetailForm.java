package dream.asset.rpt.mtbfmttr.usdept.form;

import common.struts.BaseForm;
import dream.asset.rpt.mtbfmttr.usdept.dto.AssetRptMtbfmttrUsDeptCommonDTO;
import dream.asset.rpt.mtbfmttr.usdept.dto.AssetRptMtbfmttrUsDeptDetailDTO;

/**
 * MTBF,MTTR(���μ�) ��
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="assetRptMtbfmttrUsDeptDetailForm"
 */
public class AssetRptMtbfmttrUsDeptDetailForm extends BaseForm
{    
    //===============================================================
    /** ���� */
    private AssetRptMtbfmttrUsDeptCommonDTO assetRptMtbfmttrUsDeptCommonDTO = new AssetRptMtbfmttrUsDeptCommonDTO();
    
    private AssetRptMtbfmttrUsDeptDetailDTO assetRptMtbfmttrUsDeptDetailDTO = new AssetRptMtbfmttrUsDeptDetailDTO();
    
    public AssetRptMtbfmttrUsDeptCommonDTO getAssetRptMtbfmttrUsDeptCommonDTO()
    {
        return assetRptMtbfmttrUsDeptCommonDTO;
    }

    public void setAssetRptMtbfmttrUsDeptCommonDTO(
            AssetRptMtbfmttrUsDeptCommonDTO assetRptMtbfmttrUsDeptCommonDTO)
    {
        this.assetRptMtbfmttrUsDeptCommonDTO = assetRptMtbfmttrUsDeptCommonDTO;
    }
    
    public AssetRptMtbfmttrUsDeptDetailDTO getAssetRptMtbfmttrUsDeptDetailDTO()
    {
        return assetRptMtbfmttrUsDeptDetailDTO;
    }

    public void setAssetRptMtbfmttrUsDeptDetailDTO(
            AssetRptMtbfmttrUsDeptDetailDTO assetRptMtbfmttrUsDeptDetailDTO)
    {
        this.assetRptMtbfmttrUsDeptDetailDTO = assetRptMtbfmttrUsDeptDetailDTO;
    }
	
}
