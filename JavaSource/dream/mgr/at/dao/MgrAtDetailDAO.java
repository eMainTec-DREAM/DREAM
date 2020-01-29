package dream.mgr.at.dao;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.mgr.at.dto.MgrAtCommonDTO;
import dream.mgr.at.dto.MgrAtDetailDTO;

/**
 * »ó¼¼ dao
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 */
public interface MgrAtDetailDAO extends BaseJdbcDaoSupportIntf
{
	/**
     * Detail Á¶Èñ
     * @author youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param mgrAtDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public MgrAtDetailDTO findDetail(MgrAtCommonDTO mgrAtCommonDTO, User user) throws Exception;
}