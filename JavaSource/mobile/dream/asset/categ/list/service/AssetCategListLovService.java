package mobile.dream.asset.categ.list.service;

import java.util.List;

import common.bean.User;
import mobile.dream.asset.categ.list.dto.AssetCategListLovDTO;


/**
 * 설비종류팝업 Service
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 */
public interface AssetCategListLovService
{

    /**
     * 설비종류검색
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param AssetCategListLovDTO
     * @param loginUser
     * @return
     */
    List findEqCtgList(AssetCategListLovDTO assetCategListLovDTO, User loginUser);
}