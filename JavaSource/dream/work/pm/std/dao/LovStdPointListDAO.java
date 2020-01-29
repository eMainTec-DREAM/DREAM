package dream.work.pm.std.dao;

import java.util.List;

import common.bean.User;
import dream.work.pm.std.dto.LovStdPointListDTO;

/**
 * ǥ���׸��� �˾�
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public interface LovStdPointListDAO
{
    /**
     * �׸� �˻�
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param lovStdPointListDTO
     * @param loginUser
     * @return
     */
    public List findStdPointList(LovStdPointListDTO lovStdPointListDTO, User loginUser);

	public void inputStdPoint(LovStdPointListDTO lovStdPointListDTO, User user);
}