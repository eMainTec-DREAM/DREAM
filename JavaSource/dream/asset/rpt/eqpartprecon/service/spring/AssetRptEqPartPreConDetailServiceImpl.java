package dream.asset.rpt.eqpartprecon.service.spring;

import java.util.List;

import common.bean.User;
import dream.asset.rpt.eqpartprecon.dao.AssetRptEqPartPreConDetailDAO;
import dream.asset.rpt.eqpartprecon.dto.AssetRptEqPartPreConDetailDTO;
import dream.asset.rpt.eqpartprecon.service.AssetRptEqPartPreConDetailService;

/**
 * AssetRptEqPartPreCon Page - Detail Service implements
 * @author youngjoo38
 * @version $Id:$
 * @since 1.0
 * @spring.bean id="assetRptEqPartPreConDetailServiceTarget"
 * @spring.txbn id="assetRptEqPartPreConDetailService"
 * @spring.property name="assetRptEqPartPreConDetailDAO" ref="assetRptEqPartPreConDetailDAO"
 */
public class AssetRptEqPartPreConDetailServiceImpl implements AssetRptEqPartPreConDetailService
{
    private AssetRptEqPartPreConDetailDAO assetRptEqPartPreConDetailDAO = null;

    public List findPartDetail(AssetRptEqPartPreConDetailDTO assetRptEqPartPreConDetailDTO, User user) throws Exception
    {      
        return assetRptEqPartPreConDetailDAO.findPartDetail(assetRptEqPartPreConDetailDTO,user);
    }
    public List findStockDetail(AssetRptEqPartPreConDetailDTO assetRptEqPartPreConDetailDTO, User user) throws Exception
    {      
        return assetRptEqPartPreConDetailDAO.findStockDetail(assetRptEqPartPreConDetailDTO,user);
    }

    public AssetRptEqPartPreConDetailDAO getAssetRptEqPartPreConDetailDAO() {
        return assetRptEqPartPreConDetailDAO;
    }

    public void setAssetRptEqPartPreConDetailDAO(AssetRptEqPartPreConDetailDAO assetRptEqPartPreConDetailDAO) {
        this.assetRptEqPartPreConDetailDAO = assetRptEqPartPreConDetailDAO;
    }    
}
