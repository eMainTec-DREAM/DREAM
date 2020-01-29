package dream.mgr.usrgrp.dao;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.mgr.usrgrp.dto.MgrUsrGrpPageAuthTabDTO;

/**
 * 화면권한설정상세탭탭권한
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 *
 */
public interface MgrUsrGrpPageAuthTabDAO extends BaseJdbcDaoSupportIntf
{
    public List findList(MgrUsrGrpPageAuthTabDTO mgrUsrGrpPageAuthTabDTO, User user) throws Exception;
    
    public int insertDetail(MgrUsrGrpPageAuthTabDTO mgrUsrGrpPageAuthTabDTO, User user) throws Exception;
    
    public int deleteList(String pgpageId, String usrgrpId, User user) throws Exception;
    
    public String findTotalCount(MgrUsrGrpPageAuthTabDTO mgrUsrGrpPageAuthTabDTO, User user) throws Exception;
    
}