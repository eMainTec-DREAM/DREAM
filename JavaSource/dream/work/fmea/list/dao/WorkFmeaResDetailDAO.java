package dream.work.fmea.list.dao;

import common.bean.User;
import dream.work.fmea.list.dto.WorkFmeaResCommonDTO;
import dream.work.fmea.list.dto.WorkFmeaResDetailDTO;

/**
 * 고장영향성평가 - Detail DAO
 * @author kim21017
 * @version $Id: WorkFmeaResDetailDAO.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 *
 */
public interface WorkFmeaResDetailDAO
{
    /**
     * FIND DETAIL
     * @param workFmeaResCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    public WorkFmeaResDetailDTO findDetail(WorkFmeaResCommonDTO workFmeaResCommonDTO, User user) throws Exception;
    
    /**
     * INSERT DETAIL
     * @param workFmeaResDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int insertDetail(WorkFmeaResDetailDTO workFmeaResDetailDTO, User user) throws Exception;
    
    /**
     * UPDATE DETAIL
     * @param workFmeaResDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int updateDetail(WorkFmeaResDetailDTO workFmeaResDetailDTO, User user) throws Exception;
    
    /**
     * ASSCOMPLETED DETAIL
     * @param workFmeaResDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int completedDetail(WorkFmeaResDetailDTO workFmeaResDetailDTO, User user) throws Exception;
    
}