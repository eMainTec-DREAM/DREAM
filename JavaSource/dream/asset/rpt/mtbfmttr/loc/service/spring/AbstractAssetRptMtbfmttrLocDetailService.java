package dream.asset.rpt.mtbfmttr.loc.service.spring;

import java.util.List;

import common.bean.User;
import dream.asset.rpt.mtbfmttr.loc.dto.AssetRptMtbfmttrLocDetailDTO;
import dream.asset.rpt.mtbfmttr.loc.service.AssetRptMtbfmttrLocDetailService;

/**
 * MTBF,MTTR(위치) 상세 목록
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 */
public abstract class AbstractAssetRptMtbfmttrLocDetailService implements AssetRptMtbfmttrLocDetailService
{   
	protected AssetRptMtbfmttrLocDetailService assetRptMtbfmttrLocDetailService;
	
	public AbstractAssetRptMtbfmttrLocDetailService(AssetRptMtbfmttrLocDetailService assetRptMtbfmttrLocDetailService)
	{
		this.assetRptMtbfmttrLocDetailService = assetRptMtbfmttrLocDetailService;
	}
	
	public abstract List findDetail(AssetRptMtbfmttrLocDetailDTO assetRptMtbfmttrLocDetailDTO, User loginUser);
}
