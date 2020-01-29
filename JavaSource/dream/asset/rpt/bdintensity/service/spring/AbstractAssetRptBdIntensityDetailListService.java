package dream.asset.rpt.bdintensity.service.spring;

import java.util.List;

import common.bean.User;
import dream.asset.rpt.bdintensity.form.AssetRptBdIntensityDetailListForm;
import dream.asset.rpt.bdintensity.service.AssetRptBdIntensityDetailListService;

/**
 * 에너지사용량(일별) 상세 목록
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public abstract class AbstractAssetRptBdIntensityDetailListService implements AssetRptBdIntensityDetailListService
{     
	protected AssetRptBdIntensityDetailListService assetRptBdIntensityDetailListService;
	
	public AbstractAssetRptBdIntensityDetailListService(AssetRptBdIntensityDetailListService assetRptBdIntensityDetailListService)
	{
		this.assetRptBdIntensityDetailListService = assetRptBdIntensityDetailListService;
	} 
	
    public abstract List findFreqChart(AssetRptBdIntensityDetailListForm assetRptBdIntensityDetailListForm, User loginUser);
    public abstract List findDuraChart(AssetRptBdIntensityDetailListForm assetRptBdIntensityDetailListForm, User loginUser);
}
