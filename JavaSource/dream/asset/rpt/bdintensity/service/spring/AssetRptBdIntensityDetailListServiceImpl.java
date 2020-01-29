package dream.asset.rpt.bdintensity.service.spring;

import java.util.List;

import common.bean.User;
import dream.asset.rpt.bdintensity.dao.AssetRptBdIntensityDetailListDAO;
import dream.asset.rpt.bdintensity.form.AssetRptBdIntensityDetailListForm;
import dream.asset.rpt.bdintensity.service.AssetRptBdIntensityDetailListService;

/**
 * 에너지사용량(일별) 상세 목록
 * @author youngjoo38
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="assetRptBdIntensityDetailListServiceTarget"
 * @spring.txbn id="assetRptBdIntensityDetailListService"
 * @spring.property name="assetRptBdIntensityDetailListDAO" ref="assetRptBdIntensityDetailListDAO"
 */
public class AssetRptBdIntensityDetailListServiceImpl implements AssetRptBdIntensityDetailListService
{
    private AssetRptBdIntensityDetailListDAO assetRptBdIntensityDetailListDAO = null;
    
    public AssetRptBdIntensityDetailListDAO getAssetRptBdIntensityDetailListDAO()
    {
        return assetRptBdIntensityDetailListDAO;
    }
    
    public void setAssetRptBdIntensityDetailListDAO(
            AssetRptBdIntensityDetailListDAO assetRptBdIntensityDetailListDAO)
    {
        this.assetRptBdIntensityDetailListDAO = assetRptBdIntensityDetailListDAO;
    }
    
    public List findFreqChart(AssetRptBdIntensityDetailListForm assetRptBdIntensityDetailListForm, User loginUser)
    {
        return assetRptBdIntensityDetailListDAO.findFreqChart(assetRptBdIntensityDetailListForm, loginUser);
        
    }
    public List findDuraChart(AssetRptBdIntensityDetailListForm assetRptBdIntensityDetailListForm, User loginUser)
    {
    	return assetRptBdIntensityDetailListDAO.findDuraChart(assetRptBdIntensityDetailListForm, loginUser);
    	
    }
	
}

