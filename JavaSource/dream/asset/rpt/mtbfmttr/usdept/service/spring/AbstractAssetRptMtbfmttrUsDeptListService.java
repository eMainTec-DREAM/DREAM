package dream.asset.rpt.mtbfmttr.usdept.service.spring;

import java.util.List;

import common.bean.User;
import dream.asset.rpt.mtbfmttr.usdept.dto.AssetRptMtbfmttrUsDeptCommonDTO;
import dream.asset.rpt.mtbfmttr.usdept.service.AssetRptMtbfmttrUsDeptListService;

/**
 * MTBF,MTTR(사용부서) 목록
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public abstract class AbstractAssetRptMtbfmttrUsDeptListService implements AssetRptMtbfmttrUsDeptListService
{     
	protected AssetRptMtbfmttrUsDeptListService assetRptMtbfmttrUsDeptListService;
	
	public AbstractAssetRptMtbfmttrUsDeptListService(AssetRptMtbfmttrUsDeptListService assetRptMtbfmttrUsDeptListService)
	{
		this.assetRptMtbfmttrUsDeptListService = assetRptMtbfmttrUsDeptListService;
	}
	
    public abstract List findList(AssetRptMtbfmttrUsDeptCommonDTO assetRptMtbfmttrUsDeptCommonDTO, User loginUser);
    public abstract String findTotalCount(AssetRptMtbfmttrUsDeptCommonDTO assetRptMtbfmttrUsDeptCommonDTO, User user);
    
}
