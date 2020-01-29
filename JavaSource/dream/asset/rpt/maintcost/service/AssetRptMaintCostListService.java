package dream.asset.rpt.maintcost.service;

import java.util.List;

import common.bean.User;
import dream.asset.rpt.maintcost.dto.AssetRptMaintCostCommonDTO;

/**
 * ���������� ������Ȳ ��� - List Service
 * @author youngjoo38
 * @version $Id:$
 * @since 1.0
 */
public interface AssetRptMaintCostListService
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
     * find Total Count
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param assetRptMaintCostCommonDTO
     * @return
     */
    public String findTotalCount(AssetRptMaintCostCommonDTO assetRptMaintCostCommonDTO, User user) throws Exception;
}
