/**
 * 
 */
package dream.asset.rpt.service;

import java.util.List;

import common.bean.User;
import dream.asset.rpt.dto.AssetRptWorkHistCommonDTO;
/**
 * 설비이력(과거) - List Service
 * @author js.lee
 * @version $Id: $
 * @since 1.0
 */
public interface AssetRptWorkHistListService {
	/**
	 * FIND PROGRAM GUIDE LIST
	 * @param assetRptWorkHistCommonDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List findRptWorkHistList(AssetRptWorkHistCommonDTO assetRptWorkHistCommonDTO, User user) throws Exception;
	/**
	 * DELETE PROGRAM GUIDE LIST
	 * @param deleteRows
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public int deleteRptWorkHistList(String[] deleteRows, User user) throws Exception;
	/**
	 * FIND PROGRAM GUIDE LIST COUNT
	 * @param assetRptWorkHistCommonDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public String findTotalCount(AssetRptWorkHistCommonDTO assetRptWorkHistCommonDTO, User user) throws Exception;
}
