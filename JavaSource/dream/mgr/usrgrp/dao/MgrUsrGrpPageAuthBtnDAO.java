package dream.mgr.usrgrp.dao;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.mgr.usrgrp.dto.MgrUsrGrpPageAuthBtnDTO;

/**
 * 화면권한설정상세탭버튼권한
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 *
 */
public interface MgrUsrGrpPageAuthBtnDAO extends BaseJdbcDaoSupportIntf
{
    public List findList(MgrUsrGrpPageAuthBtnDTO mgrUsrGrpPageAuthBtnDTO, User user) throws Exception;
    
    public int insertDetail(MgrUsrGrpPageAuthBtnDTO mgrUsrGrpPageAuthBtnDTO, User user) throws Exception;
    
    public int deleteList(String pgbtnId, String usrgrpId, User user) throws Exception;
    
    public String findTotalCount(MgrUsrGrpPageAuthBtnDTO mgrUsrGrpPageAuthBtnDTO, User user) throws Exception;
    
}