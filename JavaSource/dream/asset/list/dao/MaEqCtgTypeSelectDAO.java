package dream.asset.list.dao;

import java.util.List;

import common.bean.User;
import dream.asset.list.dto.MaEqCtgTypeSelectDTO;

/**
 * �������� �����˾�
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 *
 */
public interface MaEqCtgTypeSelectDAO
{
    /**
     * @author  kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param loginUser
     * @return
     */
    public List findList(User loginUser, MaEqCtgTypeSelectDTO maEqCtgTypeSelectDTO);
}