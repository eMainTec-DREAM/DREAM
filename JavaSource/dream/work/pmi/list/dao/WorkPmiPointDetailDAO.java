package dream.work.pmi.list.dao;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.work.pmi.list.dto.WorkPmiCommonDTO;
import dream.work.pmi.list.dto.WorkPmiPointDetailDTO;

/**
 * 점검작업 점검 상세 dao
 * @author  kim21017
 * @version $Id: WorkPmiPointDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 */
public interface WorkPmiPointDetailDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: WorkPmiPointDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param wkOrId
     * @param woPointId
     * @param compNo
     * @return
     */
    public WorkPmiPointDetailDTO findDetail(String pminslistId, String pmInsPointId, String pmPointId, User user);

    /**
     * detail update
     * @author kim21017
     * @version $Id: WorkPmiPointDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param workPmiPointDetailDTO
     * @param workPmiCommonDTO
     * @return
     */
    public int updateDetail(WorkPmiPointDetailDTO workPmiPointDetailDTO, WorkPmiCommonDTO workPmiCommonDTO, boolean inputFlag, User user);
}