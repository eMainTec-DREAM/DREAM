package dream.work.list.dao;

import common.bean.User;
import dream.work.list.dto.WorkListCavalDetailDTO;
import dream.work.list.dto.MaWoResultMstrCommonDTO;

/**
 * 작업상세  - 검교정 - 측정값 상세 dao
 * @author  kim21017
 * @version $Id: WorkListCavalDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 */
public interface WorkListCavalDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: WorkListCavalDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param wkOrId
     * @param woCalibValueId
     * @param compNo
     * @return
     */
    public WorkListCavalDetailDTO findDetail(String wkOrId, String woCalibValueId, String compNo);

    /**
     * detail update
     * @author kim21017
     * @version $Id: WorkListCavalDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param workListCavalDetailDTO
     * @param maWoResultMstrCommonDTO
     * @return
     */
    public int updateDetail(WorkListCavalDetailDTO workListCavalDetailDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO);
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: WorkListCavalDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param workListCavalDetailDTO
     * @param maWoResultMstrCommonDTO
     * @return
     */
    public int insertDetail(WorkListCavalDetailDTO workListCavalDetailDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO);
}