package dream.asset.rpt.mtbfmttr.loc.service.spring;

import java.util.List;

import common.bean.User;
import dream.asset.rpt.mtbfmttr.loc.dto.AssetRptMtbfmttrLocCommonDTO;
import dream.asset.rpt.mtbfmttr.loc.service.AssetRptMtbfmttrLocListService;

/**
 * MTBF,MTTR(위치) 목록
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 */
public abstract class AbstractAssetRptMtbfmttrLocListService implements AssetRptMtbfmttrLocListService
{     
	protected AssetRptMtbfmttrLocListService assetRptMtbfmttrLocListService; 
	
	public AbstractAssetRptMtbfmttrLocListService(AssetRptMtbfmttrLocListService assetRptMtbfmttrLocListService)
	{
		this.assetRptMtbfmttrLocListService = assetRptMtbfmttrLocListService;
	}
	
    public abstract List findList(AssetRptMtbfmttrLocCommonDTO assetRptMtbfmttrLocCommonDTO, User loginUser);
    public abstract String findTotalCount(AssetRptMtbfmttrLocCommonDTO assetRptMtbfmttrLocCommonDTO, User user);
}
