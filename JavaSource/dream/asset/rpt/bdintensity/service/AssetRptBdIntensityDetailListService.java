package dream.asset.rpt.bdintensity.service;

import java.util.List;

import common.bean.User;
import dream.asset.rpt.bdintensity.form.AssetRptBdIntensityDetailListForm;

/**
 * 에너지사용량(일별) 상세 목록
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public interface AssetRptBdIntensityDetailListService
{     
    public List findFreqChart(AssetRptBdIntensityDetailListForm assetRptBdIntensityDetailListForm, User loginUser);
    public List findDuraChart(AssetRptBdIntensityDetailListForm assetRptBdIntensityDetailListForm, User loginUser);
}
