package mobile.dream.asset.loc.list.dao;

import java.util.List;

import common.bean.User;
import mobile.dream.asset.loc.list.dto.AssetLocListLovDTO;


/**
 * ��ġ�з��˻� �˾�
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public interface AssetLocListLovDAO
{
    /**
     * ��ġ�з�
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param assetLocListLovDTO
     * @return
     */
    public List findEqLocList(AssetLocListLovDTO assetLocListLovDTO, User loginUser);
}