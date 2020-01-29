package dream.asset.rpt.eqParts.service.spring;

import java.util.List;

import common.bean.User;
import dream.asset.rpt.eqParts.dao.AssetRptEqPartsDAO;
import dream.asset.rpt.eqParts.dto.AssetRptEqPartsDTO;
import dream.asset.rpt.eqParts.service.AssetRptEqPartsService;

/**
 * 汲厚备己何前 - Service implements
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * @spring.bean id="assetRptEqPartsServiceTarget"
 * @spring.txbn id="assetRptEqPartsService"
 * @spring.property name="assetRptEqPartsDAO" ref="assetRptEqPartsDAO"
 */
public class AssetRptEqPartsServiceImpl implements AssetRptEqPartsService
{
    private AssetRptEqPartsDAO assetRptEqPartsDAO = null;

    public List findList(AssetRptEqPartsDTO assetRptEqPartsDTO, User user) throws Exception
    {      
        return assetRptEqPartsDAO.findList(assetRptEqPartsDTO,user);
    }

    public AssetRptEqPartsDAO getAssetRptEqPartsDAO() {
        return assetRptEqPartsDAO;
    }

    public void setAssetRptEqPartsDAO(AssetRptEqPartsDAO assetRptEqPartsDAO) {
        this.assetRptEqPartsDAO = assetRptEqPartsDAO;
    }    
    
    public String findTotalCount(AssetRptEqPartsDTO assetRptEqPartsDTO,User user)  throws Exception
    {
        return assetRptEqPartsDAO.findTotalCount(assetRptEqPartsDTO, user);
    }
}
