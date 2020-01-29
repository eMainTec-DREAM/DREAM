package mobile.dream.asset.categ.list.dao;

import java.util.List;

import common.bean.User;
import mobile.dream.asset.categ.list.dto.AssetCategListLovDTO;


/**
 * ���������˻� �˾�
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public interface AssetCategListLovDAO
{
    /**
     * ��������
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param assetCategListLovDTO
     * @param loginUser
     * @return
     */
    public List findEqCtgList(AssetCategListLovDTO assetCategListLovDTO, User loginUser);
}