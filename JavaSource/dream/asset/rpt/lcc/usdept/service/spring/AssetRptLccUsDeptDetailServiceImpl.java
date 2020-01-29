package dream.asset.rpt.lcc.usdept.service.spring;

import java.util.List;

import common.bean.User;
import dream.asset.rpt.lcc.usdept.dao.AssetRptLccUsDeptDetailDAO;
import dream.asset.rpt.lcc.usdept.dto.AssetRptLccUsDeptCommonDTO;
import dream.asset.rpt.lcc.usdept.dto.AssetRptLccUsDeptDetailDTO;
import dream.asset.rpt.lcc.usdept.service.AssetRptLccUsDeptDetailService;

/**
 * 고장TOP(사용부서) 상세 목록
 * @author youngjoo38
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="assetRptLccUsDeptDetailServiceTarget"
 * @spring.txbn id="assetRptLccUsDeptDetailService"
 * @spring.property name="assetRptLccUsDeptDetailDAO" ref="assetRptLccUsDeptDetailDAO"
 */
public class AssetRptLccUsDeptDetailServiceImpl implements AssetRptLccUsDeptDetailService
{
    private AssetRptLccUsDeptDetailDAO assetRptLccUsDeptDetailDAO = null;
    
    public AssetRptLccUsDeptDetailDAO getAssetRptLccUsDeptDetailDAO()
    {
        return assetRptLccUsDeptDetailDAO;
    }
    
    public void setAssetRptLccUsDeptDetailDAO(
            AssetRptLccUsDeptDetailDAO assetRptLccUsDeptDetailDAO)
    {
        this.assetRptLccUsDeptDetailDAO = assetRptLccUsDeptDetailDAO;
    }
    
    public List findDetail(AssetRptLccUsDeptCommonDTO assetRptLccUsDeptCommonDTO, AssetRptLccUsDeptDetailDTO assetRptLccUsDeptDetailDTO, User loginUser)
    {
        return assetRptLccUsDeptDetailDAO.findDetail(assetRptLccUsDeptCommonDTO, assetRptLccUsDeptDetailDTO, loginUser);
        
    }
	
}

