package mobile.dream.asset.categ.list.service;

import java.util.List;

import common.bean.User;
import mobile.dream.asset.categ.list.dto.AssetCategListLovDTO;


/**
 * ���������˾� Service
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 */
public interface AssetCategListLovService
{

    /**
     * ���������˻�
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