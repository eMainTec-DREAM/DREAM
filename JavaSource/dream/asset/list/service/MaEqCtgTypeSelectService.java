package dream.asset.list.service;

import java.util.List;

import common.bean.User;
import dream.asset.list.dto.MaEqCtgTypeSelectDTO;

/**
 * �������� �˾� Service
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 *
 */
public interface MaEqCtgTypeSelectService
{

    /**
     * @author  kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param loginUser
     * @param maEqCtgTypeSelectDTO
     * @return
     */
    List findList(User loginUser, MaEqCtgTypeSelectDTO maEqCtgTypeSelectDTO);
}