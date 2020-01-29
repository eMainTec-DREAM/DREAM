package dream.asset.rpt.unit.service.spring;

import java.util.List;

import common.bean.User;
import dream.asset.rpt.unit.dao.AssetRptEqUnitsDAO;
import dream.asset.rpt.unit.dto.AssetRptEqUnitsDTO;
import dream.asset.rpt.unit.service.AssetRptEqUnitsService;

/**
 * ¸ñ·Ï
 * @author euna0207
 * @version $Id$
 * @since 1.0
 * 
 * @spring.bean id="assetRptEqUnitsServiceTarget"
 * @spring.txbn id="assetRptEqUnitsService"
 * @spring.property name="assetRptEqUnitsDAO" ref="assetRptEqUnitsDAO"
 */
public class AssetRptEqUnitsServiceImpl implements AssetRptEqUnitsService
{
    private AssetRptEqUnitsDAO assetRptEqUnitsDAO = null;

    public AssetRptEqUnitsDAO getAssetRptEqUnitsDAO() {
		return assetRptEqUnitsDAO;
	}

	public void setAssetRptEqUnitsDAO(AssetRptEqUnitsDAO assetRptEqUnitsDAO) {
		this.assetRptEqUnitsDAO = assetRptEqUnitsDAO;
	}

    public List findList(AssetRptEqUnitsDTO assetRptEqUnitsDTO, User user) throws Exception
    {      
        return assetRptEqUnitsDAO.findList(assetRptEqUnitsDTO, user);
    }
    
    public String findTotalCount(AssetRptEqUnitsDTO assetRptEqUnitsDTO, User user) throws Exception
    {      
    	return assetRptEqUnitsDAO.findTotalCount(assetRptEqUnitsDTO, user);
    }
    
}

