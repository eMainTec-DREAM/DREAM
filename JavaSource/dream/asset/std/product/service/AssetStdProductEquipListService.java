package dream.asset.std.product.service;

import java.util.List;

import common.bean.User;
import dream.asset.std.product.dto.AssetStdProductCommonDTO;
import dream.asset.std.product.dto.AssetStdProductEquipDetailDTO;
import dream.asset.std.product.dto.AssetStdProductEquipListDTO;
/**
 * 생산설비 - List Service
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 */
public interface AssetStdProductEquipListService {
	/**
	 * FIND LIST
	 * @param assetStdProductCommonDTO
	 * @param assetStdProductEquipListDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List findList(AssetStdProductCommonDTO assetStdProductCommonDTO, AssetStdProductEquipListDTO assetStdProductEquipListDTO, User user) throws Exception;
	/**
	 * DELETE LIST
	 * @param deleteRows
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public int deleteList(String[] deleteRows, User user) throws Exception;
	/**
	 * FIND LIST COUNT
	 * @param assetStdProductCommonDTO
	 * @param assetStdProductEquipListDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public String findTotalCount(AssetStdProductCommonDTO assetStdProductCommonDTO,AssetStdProductEquipListDTO assetStdProductEquipListDTO, User user) throws Exception;
    
    public int insertList(AssetStdProductCommonDTO assetStdProductCommonDTO, AssetStdProductEquipDetailDTO assetStdProductEquipDetailDTO, AssetStdProductEquipListDTO assetStdProductEquipListDTO, User user) throws Exception;
}
