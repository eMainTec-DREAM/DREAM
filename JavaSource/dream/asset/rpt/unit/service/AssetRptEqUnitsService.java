package dream.asset.rpt.unit.service;

import java.util.List;

import common.bean.User;
import dream.asset.rpt.unit.dto.AssetRptEqUnitsDTO;

/**
 * ¸ñ·Ï
 * @author  euna0207
 * @version $Id$
 * @since   1.0
 */
public interface AssetRptEqUnitsService
{     
    /**
     *  grid find
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param pageId
     * @param user 
     * @throws Exception
     */
    public List<AssetRptEqUnitsDTO> findList(AssetRptEqUnitsDTO assetRptEqUnitsDTO, User user) throws Exception;
    
    public String findTotalCount(AssetRptEqUnitsDTO assetRptEqUnitsDTO, User user) throws Exception;
}
