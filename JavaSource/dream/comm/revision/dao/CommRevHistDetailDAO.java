package dream.comm.revision.dao;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.comm.revision.dto.CommRevHistDetailDTO;

/**
 * »ó¼¼ dao
 * @author jung7126
 * @version $Id: CommRevHistDetailDAO.java,v 1.0 2015/12/02 08:25:47 jung7126 Exp $
 * @since 1.0
 */
public interface CommRevHistDetailDAO extends BaseJdbcDaoSupportIntf
{
	/**
     * Detail Á¶Èñ
     * @author jung7126
     * @version $Id: CommRevHistDetailDAO.java,v 1.0 20155/12/02 08:25:47 jung7126 Exp $
     * @since   1.0
     * 
     * @param commRevHistDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public CommRevHistDetailDTO findPmDetail(String revisionhistId, User user) throws Exception;
    public CommRevHistDetailDTO findAssetDetail(String revisionhistId, User user) throws Exception;
    
    /**
     * revision update
     * @author jung7126
     * @version $Id: CommRevHistDetailDAO.java,v 1.0 20155/12/02 08:25:47 jung7126 Exp $
     * @since   1.0
     * 
     * @param commRevHistDetailDTO
     * @return
     */
    public int updateRevHist(CommRevHistDetailDTO commRevHistDetailDTO);
}