package mobile.dream.asset.list.dao;

import java.util.List;

import common.bean.User;
import mobile.dream.asset.list.dto.AssetListLovListDTO;


/**
 * ���� �˾�
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public interface AssetListLovListDAO
{
    /**
     * ���� �˻�
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param assetListLovListDTO
     * @param loginUser
     * @return
     */
    public List findEquipList(AssetListLovListDTO assetListLovListDTO, User loginUser);
}