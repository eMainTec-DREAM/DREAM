package mobile.dream.work.pm.list.dao;

import common.bean.User;
import mobile.dream.work.pm.list.dto.WorkPmWorkCommonDTO;
import mobile.dream.work.pm.list.dto.WorkPmWorkDetailDTO;

/**
 * »ó¼¼ dao
 * @author jung7126
 * @version $Id: WorkPmWorkDetailDAO.java,v 1.0 2015/12/02 08:25:47 jung7126 Exp $
 * @since 1.0
 */
public interface WorkPmWorkDetailDAO
{
    /**
     * detail find
     * @author jung7126
     * @version $Id: WorkPmWorkDetailDAO.java,v 1.0 20155/12/02 08:25:47 jung7126 Exp $
     * @since   1.0
     * 
     * @param eqCtgId
     * @param compNo
     * @return
     */
    public WorkPmWorkDetailDTO findDetail(WorkPmWorkCommonDTO dto, User user);
    
    /**
     * detail insert
     * @author jung7126
     * @version $Id: WorkPmWorkDetailDAO.java,v 1.0 20155/12/02 08:25:47 jung7126 Exp $
     * @since   1.0
     * 
     * @param workPmWorkDetailDTO
     * @return
     */
    public int insertDetail(WorkPmWorkDetailDTO workPmWorkDetailDTO);
    /**
     * detail update
     * @author jung7126
     * @version $Id: WorkPmWorkDetailDAO.java,v 1.0 20155/12/02 08:25:47 jung7126 Exp $
     * @since   1.0
     * 
     * @param workPmWorkDetailDTO
     * @return
     */
    public int updateDetail(WorkPmWorkDetailDTO workPmWorkDetailDTO) throws Exception;
}