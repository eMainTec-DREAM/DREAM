package dream.asset.rpt.maintcost.dao;

import java.util.List;

import common.bean.User;
import dream.asset.rpt.maintcost.dto.AssetRptMaintCostCommonDTO;

/**
 * ���������� ������Ȳ ��� - List DAO
 * @author youngjoo38
 * @version $Id:$
 * @since 1.0
 *
 */
public interface AssetRptMaintCostListDAO
{
    /**
     * FIND LIST
     * @param assetRptMaintCostCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    
    public List findList(AssetRptMaintCostCommonDTO assetRptMaintCostCommonDTO, User user) throws Exception;

    /**
     * FIND TOTAL LIST
     * @param assetRptMaintCostCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    public String findTotalCount(AssetRptMaintCostCommonDTO assetRptMaintCostCommonDTO, User user) throws Exception;
    
}
