package mobile.dream.work.pm.list.service;

import common.bean.User;
import mobile.dream.work.pm.list.dto.WorkPmWorkCommonDTO;
import mobile.dream.work.pm.list.dto.WorkPmWorkDetailDTO;

/**
 * »ó¼¼ service
 * 
 * @author jung7126
 * @version $Id: WorkPmWorkDetailService.java,v 1.0 2015/12/02 09:12:40 jung7126 Exp $
 * @since 1.0
 */
public interface WorkPmWorkDetailService
{    
    /**
     * detail find
     * @author jung7126
     * @version $Id: WorkPmWorkDetailService.java,v 1.0 2015/12/02 09:12:40 jung7126 Exp $
     * @since   1.0
     * 
     * @param dto
     * @param compNo
     * @return
     * @throws Exception
     */
    public WorkPmWorkDetailDTO findDetail(WorkPmWorkCommonDTO dto,User user)throws Exception;
    /**
     * detail insert
     * @author jung7126
     * @version $Id: WorkPmWorkDetailService.java,v 1.0 2015/12/02 09:12:40 jung7126 Exp $
     * @since   1.0
     * 
     * @param workPmWorkDetailDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(WorkPmWorkDetailDTO workPmWorkDetailDTO) throws Exception;
    /**
     * detail update
     * @author jung7126
     * @version $Id: WorkPmWorkDetailService.java,v 1.0 2015/12/02 09:12:40 jung7126 Exp $
     * @since   1.0
     * 
     * @param workPmWorkDetailDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(WorkPmWorkDetailDTO workPmWorkDetailDTO) throws Exception;
}
