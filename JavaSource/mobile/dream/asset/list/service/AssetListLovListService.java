package mobile.dream.asset.list.service;

import java.util.List;

import common.bean.User;
import mobile.dream.asset.list.dto.AssetListLovListDTO;

/**
 * 설비 Service
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 */
public interface AssetListLovListService
{

    /**
     * 설비검색
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param AssetListLovListDTO
     * @param loginUser
     * @return
     */
    List findEquipList(AssetListLovListDTO assetListLovListDTO, User loginUser);
}