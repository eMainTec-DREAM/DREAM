package dream.work.list.dao;

import common.bean.User;
import dream.work.list.dto.WorkListGnlCavalDetailDTO;
import dream.work.list.dto.MaWoResultMstrCommonDTO;

/**
 * 작업상세  - 검교정 - 측정값 상세 dao
 * @author  kim21017
 * @version $Id: WorkListGnlCavalDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 */
public interface WorkListGnlCavalDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: WorkListGnlCavalDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param wkOrId
     * @param woCalibValueId
     * @param compNo
     * @return
     */
    public WorkListGnlCavalDetailDTO findDetail(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, User user);

    /**
     * detail update
     * @author kim21017
     * @version $Id: WorkListGnlCavalDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param workListGnlCavalDetailDTO
     * @param maWoResultMstrCommonDTO
     * @return
     */
    public int updateDetail(WorkListGnlCavalDetailDTO workListGnlCavalDetailDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO);
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: WorkListGnlCavalDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param workListGnlCavalDetailDTO
     * @param maWoResultMstrCommonDTO
     * @return
     */
    public int insertDetail(WorkListGnlCavalDetailDTO workListGnlCavalDetailDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO);
}