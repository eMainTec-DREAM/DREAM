package dream.asset.rpt.bdintensity.service.spring;

import java.util.List;

import common.bean.User;
import dream.asset.rpt.bdintensity.dto.AssetRptBdIntensityCommonDTO;
import dream.asset.rpt.bdintensity.service.AssetRptBdIntensityListService;

/**
 * 설비별 고장강도율 목록 - List Service
 * @author youngjoo38
 * @version $Id:$
 * @since 1.0
 */
public abstract class AbstractAssetRptBdIntensityListService implements AssetRptBdIntensityListService
{
	protected AssetRptBdIntensityListService assetRptBdIntensityListService;
	
	public AbstractAssetRptBdIntensityListService(AssetRptBdIntensityListService assetRptBdIntensityListService)
	{
		this.assetRptBdIntensityListService = assetRptBdIntensityListService;
	}
	
    public abstract List findList(AssetRptBdIntensityCommonDTO assetRptBdIntensityCommonDTO, User user) throws Exception;
    public abstract String findTotalCount(AssetRptBdIntensityCommonDTO assetRptBdIntensityCommonDTO, User user) throws Exception;
}
