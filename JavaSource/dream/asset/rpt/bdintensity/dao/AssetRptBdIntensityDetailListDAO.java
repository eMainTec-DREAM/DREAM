package dream.asset.rpt.bdintensity.dao;

import java.util.List;

import common.bean.User;
import dream.asset.rpt.bdintensity.form.AssetRptBdIntensityDetailListForm;

/**
 * ��������뷮(�Ϻ�) �� ��� dao
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public interface AssetRptBdIntensityDetailListDAO
{
    public List findFreqChart(AssetRptBdIntensityDetailListForm assetRptBdIntensityDetailListForm, User loginUser);
    public List findDuraChart(AssetRptBdIntensityDetailListForm assetRptBdIntensityDetailListForm, User loginUser);
}