package dream.mgr.audtrail.dao;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.mgr.audtrail.dto.MgrAudTrailCommonDTO;
import dream.mgr.audtrail.dto.MgrAudTrailDetailDTO;

/**
 * �� dao
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 */
public interface MgrAudTrailDetailDAO extends BaseJdbcDaoSupportIntf
{
	/**
     * Detail ����
     * @author youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param mgrAudTrailDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public MgrAudTrailDetailDTO findDetail(MgrAudTrailCommonDTO mgrAudTrailCommonDTO, User user) throws Exception;
}