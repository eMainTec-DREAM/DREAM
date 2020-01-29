package dream.asset.rpt.nyearpo.service.spring;

import java.util.List;

import common.bean.User;
import dream.asset.rpt.nyearpo.dao.AssetRptNYearPODetailDAO;
import dream.asset.rpt.nyearpo.dto.AssetRptNYearPOCommonDTO;
import dream.asset.rpt.nyearpo.dto.AssetRptNYearPODetailDTO;
import dream.asset.rpt.nyearpo.service.AssetRptNYearPODetailService;

/**
 * N Year Spare Part 상세 목록
 * @author youngjoo38
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="assetRptNYearPODetailServiceTarget"
 * @spring.txbn id="assetRptNYearPODetailService"
 * @spring.property name="assetRptNYearPODetailDAO" ref="assetRptNYearPODetailDAO"
 */
public class AssetRptNYearPODetailServiceImpl implements AssetRptNYearPODetailService
{
    private AssetRptNYearPODetailDAO assetRptNYearPODetailDAO = null;
    
    public AssetRptNYearPODetailDAO getAssetRptNYearPODetailDAO()
    {
        return assetRptNYearPODetailDAO;
    }
    
    public void setAssetRptNYearPODetailDAO(
            AssetRptNYearPODetailDAO assetRptNYearPODetailDAO)
    {
        this.assetRptNYearPODetailDAO = assetRptNYearPODetailDAO;
    }
    
    public List findDetail(AssetRptNYearPOCommonDTO assetRptNYearPOCommonDTO, AssetRptNYearPODetailDTO assetRptNYearPODetailDTO, User loginUser)
    {
        return assetRptNYearPODetailDAO.findDetail(assetRptNYearPOCommonDTO, assetRptNYearPODetailDTO, loginUser);
        
    }
	
}

