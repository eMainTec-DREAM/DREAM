package mobile.dream.asset.list.service;

import java.util.List;

import common.bean.User;
import mobile.dream.asset.list.dto.AssetListLovListDTO;

/**
 * ���� Service
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 */
public interface AssetListLovListService
{

    /**
     * ����˻�
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