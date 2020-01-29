package mobile.dream.asset.list.dao;

import java.util.List;

import common.bean.User;
import mobile.dream.asset.list.dto.AssetListLovListDTO;


/**
 * 설비 팝업
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public interface AssetListLovListDAO
{
    /**
     * 설비 검색
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