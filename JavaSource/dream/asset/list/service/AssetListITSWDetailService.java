package dream.asset.list.service;

import common.bean.User;
import dream.asset.list.dto.AssetListITSWDetailDTO;
import dream.asset.list.dto.AssetListITSWListDTO;
/**
 * Detail Service
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 */
public interface AssetListITSWDetailService
{    
	/**
	 * FIND DETAIL
	 * @param assetListITSWListDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public AssetListITSWDetailDTO findDetail(AssetListITSWListDTO assetListITSWListDTO, User user) throws Exception;
	/**
	 * INSERT DETAIL
	 * @param assetListITSWDetailDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public int insertDetail(AssetListITSWDetailDTO assetListITSWDetailDTO, User user) throws Exception;
    /**
     * UPDATE DETAIL
     * @param assetListITSWDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int updateDetail(AssetListITSWDetailDTO assetListITSWDetailDTO, User user) throws Exception;
    
}
