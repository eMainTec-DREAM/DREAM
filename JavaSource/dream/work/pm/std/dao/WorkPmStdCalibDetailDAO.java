package dream.work.pm.std.dao;

import common.bean.User;
import dream.work.pm.std.dto.WorkPmStdCalibCommonDTO;
import dream.work.pm.std.dto.WorkPmStdCalibDetailDTO;

/**
 * 고정표준값 - Detail DAO
 * @author kim21017
 * @version $Id: WorkPmStdCalibDetailDAO.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 *
 */
public interface WorkPmStdCalibDetailDAO
{
    /**
     * FIND DETAIL
     * @param workPmStdCalibCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    public WorkPmStdCalibDetailDTO findDetail(WorkPmStdCalibCommonDTO workPmStdCalibCommonDTO, User user) throws Exception;
    
    /**
     * INSERT DETAIL
     * @param workPmStdCalibDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int insertDetail(WorkPmStdCalibDetailDTO workPmStdCalibDetailDTO, User user) throws Exception;
    
    /**
     * UPDATE DETAIL
     * @param workPmStdCalibDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int updateDetail(WorkPmStdCalibDetailDTO workPmStdCalibDetailDTO, User user) throws Exception;
    
}