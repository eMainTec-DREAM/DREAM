package dream.asset.std.product.service;

import common.bean.User;
import dream.asset.std.product.dto.AssetStdProductCommonDTO;
import dream.asset.std.product.dto.AssetStdProductEquipDetailDTO;
import dream.asset.std.product.dto.AssetStdProductEquipListDTO;
/**
 * 생산설비 - Detail Service
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 */
public interface AssetStdProductEquipDetailService
{    
	/**
	 * FIND DETAIL
	 * @param assetStdProductCommonDTO
	 * @param assetStdProductEquipListDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public AssetStdProductEquipDetailDTO findDetail(AssetStdProductCommonDTO assetStdProductCommonDTO,AssetStdProductEquipListDTO assetStdProductEquipListDTO, User user) throws Exception;
	/**
	 * INSERT DETAIL
     * @param assetStdProductCommonDTO
	 * @param assetStdProductEquipDetailDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public int insertDetail(AssetStdProductCommonDTO assetStdProductCommonDTO,AssetStdProductEquipDetailDTO assetStdProductEquipDetailDTO, User user) throws Exception;
    /**
     * UPDATE DETAIL
     * @param assetStdProductCommonDTO
     * @param assetStdProductEquipDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int updateDetail(AssetStdProductCommonDTO assetStdProductCommonDTO,AssetStdProductEquipDetailDTO assetStdProductEquipDetailDTO, User user) throws Exception;
}
