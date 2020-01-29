package dream.asset.rpt.mtbfmttr.usdept.service.spring;

import java.util.List;

import common.bean.User;
import dream.asset.rpt.mtbfmttr.usdept.dto.AssetRptMtbfmttrUsDeptCommonDTO;
import dream.asset.rpt.mtbfmttr.usdept.dto.AssetRptMtbfmttrUsDeptDetailDTO;
import dream.asset.rpt.mtbfmttr.usdept.service.AssetRptMtbfmttrUsDeptDetailService;

/**
 * MTBF,MTTR(���μ�) �� ���
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public abstract class AbstractAssetRptMtbfmttrUsDeptDetailService implements AssetRptMtbfmttrUsDeptDetailService
{     
	protected AssetRptMtbfmttrUsDeptDetailService assetRptMtbfmttrUsDeptDetailService;
	
	public AbstractAssetRptMtbfmttrUsDeptDetailService(AssetRptMtbfmttrUsDeptDetailService assetRptMtbfmttrUsDeptDetailService)
	{
		this.assetRptMtbfmttrUsDeptDetailService = assetRptMtbfmttrUsDeptDetailService;
	}
	
	public abstract List findDetail(AssetRptMtbfmttrUsDeptCommonDTO assetRptMtbfmttrUsDeptCommonDTO, AssetRptMtbfmttrUsDeptDetailDTO assetRptMtbfmttrUsDeptDetailDTO, User loginUser);
    
}
