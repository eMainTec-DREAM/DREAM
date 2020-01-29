package dream.work.pm.std.dao;

import common.bean.User;
import dream.work.pm.std.dto.WorkPmStdCalibCommonDTO;
import dream.work.pm.std.dto.WorkPmStdCalibValDetailDTO;
import dream.work.pm.std.dto.WorkPmStdCalibValListDTO;

/**
 * 표준교정값 - Detail DAO
 * @author kim21017
 * @version $Id: WorkPmStdCalibValDetailDAO.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 *
 */
public interface WorkPmStdCalibValDetailDAO
{
    /**
     * FIND DETAIL
     * @param workPmStdCalibCommonDTO
     * @param workPmStdCalibValListDTO
     * @param user
     * @return
     * @throws Exception
     */
    public WorkPmStdCalibValDetailDTO findDetail(WorkPmStdCalibCommonDTO workPmStdCalibCommonDTO,WorkPmStdCalibValListDTO workPmStdCalibValListDTO, User user) throws Exception;
    
    /**
     * INSERT DETAIL
     * @param workPmStdCalibCommonDTO
     * @param workPmStdCalibValDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int insertDetail(WorkPmStdCalibCommonDTO workPmStdCalibCommonDTO,WorkPmStdCalibValDetailDTO workPmStdCalibValDetailDTO, User user) throws Exception;
    
    /**
     * UPDATE DETAIL
     * @param workPmStdCalibCommonDTO
     * @param workPmStdCalibValDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int updateDetail(WorkPmStdCalibCommonDTO workPmStdCalibCommonDTO,WorkPmStdCalibValDetailDTO workPmStdCalibValDetailDTO, User user) throws Exception;
    
}