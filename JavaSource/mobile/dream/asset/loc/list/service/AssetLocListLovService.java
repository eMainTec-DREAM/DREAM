package mobile.dream.asset.loc.list.service;

import java.util.List;

import common.bean.User;
import mobile.dream.asset.loc.list.dto.AssetLocListLovDTO;


/**
 * ��ġ�з��˾� Service
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 */
public interface AssetLocListLovService
{

    /**
     * ��ġ�з��˻�
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param AssetLocListLovDTO
     * @param loginUser
     * @return
     */
    List findEqLocList(AssetLocListLovDTO assetLocListLovDTO, User loginUser);
}