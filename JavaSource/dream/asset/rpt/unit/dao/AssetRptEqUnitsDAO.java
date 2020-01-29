package dream.asset.rpt.unit.dao;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.asset.rpt.unit.dto.AssetRptEqUnitsDTO;

/**
 *  ¸ñ·Ï dao
 * @author  euna0207
 * @version $Id$
 * @since   1.0
 */
public interface AssetRptEqUnitsDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * grid find
     * @author  euna0207
     * @version $Id$
     * @since   1.0
     * 
     * @param maMyInfoCommonDTO
     * @param user 
     * @return List
     */
    public List findList(AssetRptEqUnitsDTO assetRptEqUnitsDTO, User loginUser);
    
    public String findTotalCount(AssetRptEqUnitsDTO assetRptEqUnitsDTO, User user) throws Exception;

    public String getColums(AssetRptEqUnitsDTO assetRptEqUnitsDTO, User user);
    
    public String getTables(AssetRptEqUnitsDTO assetRptEqUnitsDTO, User user);
    
    public String getOrderBy(AssetRptEqUnitsDTO assetRptEqUnitsDTO, User user);
    
    public String getWhere(AssetRptEqUnitsDTO assetRptEqUnitsDTO, User user);
}