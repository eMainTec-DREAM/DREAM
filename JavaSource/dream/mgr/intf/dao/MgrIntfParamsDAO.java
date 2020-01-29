package dream.mgr.intf.dao;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.mgr.intf.dto.IntfUserExecVO;
import dream.mgr.intf.dto.IntfUserExecValueVO;
import dream.mgr.intf.dto.MgrIntfCommonDTO;

/**
 * 인터페이스 파라미터 dao
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface MgrIntfParamsDAO extends BaseJdbcDaoSupportIntf
{
    public List findList(MgrIntfCommonDTO mgrIntfCommonDTO, User user) throws Exception;

    public IntfUserExecVO selectTAINTFUSEREXEC(MgrIntfCommonDTO mgrIntfCommonDTO, User user) throws Exception;

    public int insertTAINTFUSEREXEC(IntfUserExecVO intfUserExecVO) throws Exception;

    public IntfUserExecValueVO selectTAINTFUSEREXECVALUE(IntfUserExecValueVO intfUserExecValueVO) throws Exception;

    public int insertTAINTFUSEREXECVALUE(IntfUserExecValueVO intfUserExecValueVO) throws Exception;

    public int updateTAINTFUSEREXECVALUE(IntfUserExecValueVO intfUserExecValueVO) throws Exception;
    
}