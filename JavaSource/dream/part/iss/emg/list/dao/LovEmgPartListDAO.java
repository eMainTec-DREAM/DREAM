package dream.part.iss.emg.list.dao;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.part.iss.emg.list.dto.LovEmgPartListDTO;

/**
 * ������ �˾�
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public interface LovEmgPartListDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * �׸� �˻�
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param lovEmgPartListDTO
     * @param loginUser
     * @return
     */
    public List findEmgPartList(LovEmgPartListDTO lovEmgPartListDTO, User loginUser);
	
    public String findTotalCount(LovEmgPartListDTO lovEmgPartListDTO, User user) throws Exception;

}