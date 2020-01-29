package dream.asset.rpt.eqpartprecon.service.spring;

import java.util.List;

import common.bean.User;
import dream.asset.rpt.eqpartprecon.dao.AssetRptEqPartPreConListDAO;
import dream.asset.rpt.eqpartprecon.dto.AssetRptEqPartPreConCommonDTO;
import dream.asset.rpt.eqpartprecon.service.AssetRptEqPartPreConListService;

/**
 * AssetRptEqPartPreCon Page - List Service implements
 * @author youngjoo38
 * @version $Id:$
 * @since 1.0
 * @spring.bean id="assetRptEqPartPreConListServiceTarget"
 * @spring.txbn id="assetRptEqPartPreConListService"
 * @spring.property name="assetRptEqPartPreConListDAO" ref="assetRptEqPartPreConListDAO"
 */
public class AssetRptEqPartPreConListServiceImpl implements AssetRptEqPartPreConListService
{
    private AssetRptEqPartPreConListDAO assetRptEqPartPreConListDAO = null;

    public List findList(AssetRptEqPartPreConCommonDTO assetRptEqPartPreConCommonDTO, User user) throws Exception
    {      
        return assetRptEqPartPreConListDAO.findList(assetRptEqPartPreConCommonDTO,user);
    }

    public AssetRptEqPartPreConListDAO getAssetRptEqPartPreConListDAO() {
        return assetRptEqPartPreConListDAO;
    }

    public void setAssetRptEqPartPreConListDAO(AssetRptEqPartPreConListDAO assetRptEqPartPreConListDAO) {
        this.assetRptEqPartPreConListDAO = assetRptEqPartPreConListDAO;
    }    
    
    public String findTotalCount(AssetRptEqPartPreConCommonDTO assetRptEqPartPreConCommonDTO,User user)  throws Exception
    {
        return assetRptEqPartPreConListDAO.findTotalCount(assetRptEqPartPreConCommonDTO, user);
    }
}
