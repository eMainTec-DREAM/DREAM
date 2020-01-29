package dream.asset.rpt.dao;

import java.util.List;

import common.bean.User;
import dream.asset.rpt.dto.AssetRptWorkHistCommonDTO;

/**
 * 설비이력(과거) - List DAO
 * @author js.lee
 * @version $Id: $
 * @since 1.0
 *
 */
public interface AssetRptWorkHistListDAO
{
	/**
	 * FIND LIST
	 * @param assetRptWorkHistCommonDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public List findRptWorkHistList(AssetRptWorkHistCommonDTO assetRptWorkHistCommonDTO, User user) throws Exception;
    
    /**
     * DELETE LIST
     * @param id
     * @param user
     * @return
     * @throws Exception
     */
    public int deleteRptWorkHistList(String id, User user) throws Exception;
    /**
	 * FIND LIST COUNT
	 * @param assetRptWorkHistCommonDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public String findTotalCount(AssetRptWorkHistCommonDTO assetRptWorkHistCommonDTO, User user) throws Exception;
    
}