package mobile.dream.org.emp.dao;

import java.util.List;

import common.bean.User;
import mobile.dream.org.emp.dto.OrgEmpLovListDTO;

/**
 * 사원검색 팝업
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public interface OrgEmpLovListDAO
{
    /**
     * 검색
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param orgEmpLovListDTO
     * @param loginUser
     * @return
     */
    public List findEmpList(OrgEmpLovListDTO orgEmpLovListDTO, User loginUser);
}