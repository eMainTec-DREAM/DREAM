package mobile.dream.org.emp.service;

import java.util.List;

import common.bean.User;
import mobile.dream.org.emp.dto.OrgEmpLovListDTO;

/**
 * 사원팝업 Service
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 */
public interface OrgEmpLovListService
{

    /**
     * 사원검색
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param loginUser
     * @param OrgEmpLovListDTO
     * @return
     */
    List findEmpList(OrgEmpLovListDTO orgEmpLovListDTO, User loginUser);
}