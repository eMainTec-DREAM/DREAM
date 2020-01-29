package dream.comm.revision.service;

import common.bean.User;
import dream.comm.revision.dto.CommRevHistCommonDTO;
import dream.comm.revision.dto.CommRevHistDetailDTO;

/**
 * »ó¼¼ service
 * 
 * @author jung7126
 * @version $Id: CommRevHistDetailService.java,v 1.0 2015/12/02 09:12:40 jung7126 Exp $
 * @since 1.0
 */
public interface CommRevHistDetailService
{   
	/**
     * find detail
     * @author jung7126
     * @version $Id: CommRevHistDetailService.java,v 1.0 2015/12/02 09:12:40 jung7126 Exp $
     * @since   1.0
     * 
     * @param commRevHistDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public CommRevHistDetailDTO findDetail(CommRevHistCommonDTO commRevHistCommonDTO, User user) throws Exception;
    /**
     * revision update
     * @author jung7126
     * @version $Id: WorkPmListRevCreateService.java,v 1.0 2015/12/02 09:12:40 jung7126 Exp $
     * @since   1.0
     * 
     * @param workPmListRevCreateDTO
     * @return
     * @throws Exception
     */
    public int updateRevHist(CommRevHistDetailDTO commRevHistDetailDTO) throws Exception;
}
