package dream.asset.rpt.service;

import common.bean.User;
import dream.asset.rpt.dto.AssetRptWorkHistCommonDTO;
import dream.asset.rpt.dto.AssetRptWorkHistDetailDTO;
/**
 * 설비이력(과거) - Detail Service
 * @author js.lee
 * @version $Id:$
 * @since 1.0
 */
public interface AssetRptWorkHistDetailService
{    
	/**
	 * FIND PROGRAM GUIDE DETAIL
	 * @param assetRptWorkHistCommonDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public AssetRptWorkHistDetailDTO findRptWorkHistDetail(AssetRptWorkHistCommonDTO assetRptWorkHistCommonDTO, User user) throws Exception;
	/**
	 * INSERT PROGRAM GUIDE
	 * @param assetRptWorkHistDetailDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public int insertRptWorkHistDetail(AssetRptWorkHistDetailDTO assetRptWorkHistDetailDTO, User user) throws Exception;
    /**
     * UPDATE PROGRAM GUIDE
     * @param assetRptWorkHistDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int updateRptWorkHistDetail(AssetRptWorkHistDetailDTO assetRptWorkHistDetailDTO, User user) throws Exception;
}
