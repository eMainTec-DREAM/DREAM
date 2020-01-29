package mobile.dream.asset.loc.list.dao;

import java.util.List;

import common.bean.User;
import mobile.dream.asset.loc.list.dto.AssetLocListLovDTO;


/**
 * 위치분류검색 팝업
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public interface AssetLocListLovDAO
{
    /**
     * 위치분류
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param assetLocListLovDTO
     * @return
     */
    public List findEqLocList(AssetLocListLovDTO assetLocListLovDTO, User loginUser);
}