package mobile.dream.org.dept.service;

import java.util.List;

import common.bean.User;
import mobile.dream.org.dept.dto.OrgDeptLovListDTO;

/**
 * 부서팝업 Service
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 */
public interface OrgDeptLovListService
{

    /**
     * 부서검색
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param OrgDeptLovListDTO
     * @param loginUser
     * @return
     */
    List findDeptList(OrgDeptLovListDTO orgDeptLovListDTO, User loginUser);
}