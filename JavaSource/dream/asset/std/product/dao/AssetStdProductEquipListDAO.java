package dream.asset.std.product.dao;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.asset.std.product.dto.AssetStdProductCommonDTO;
import dream.asset.std.product.dto.AssetStdProductEquipListDTO;

/**
 * 생산설비 - List DAO
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 *
 */
public interface AssetStdProductEquipListDAO extends BaseJdbcDaoSupportIntf
{
	/**
	 * FIND LIST
	 * @param assetStdProductCommonDTO
	 * @param assetStdProductEquipListDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public List findList(AssetStdProductCommonDTO assetStdProductCommonDTO,AssetStdProductEquipListDTO assetStdProductEquipListDTO, User user) throws Exception;
    
    /**
     * DELETE LIST
     * @param id
     * @param user
     * @return
     * @throws Exception
     */
    public int deleteList(String id, User user) throws Exception;
	/**
	 * FIND LIST COUNT
	 * @param assetStdProductCommonDTO
	 * @param assetStdProductEquipListDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public String findTotalCount(AssetStdProductCommonDTO assetStdProductCommonDTO,AssetStdProductEquipListDTO assetStdProductEquipListDTO, User user) throws Exception;
    
}