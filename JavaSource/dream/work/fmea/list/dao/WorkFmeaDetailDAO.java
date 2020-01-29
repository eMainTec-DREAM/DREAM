package dream.work.fmea.list.dao;

import common.bean.User;
import dream.pers.appreq.dto.AppReqDetailDTO;
import dream.work.fmea.list.dto.WorkFmeaCommonDTO;
import dream.work.fmea.list.dto.WorkFmeaDetailDTO;

/**
 * 고장영향성평가 - Detail DAO
 * @author kim21017
 * @version $Id: WorkFmeaDetailDAO.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 *
 */
public interface WorkFmeaDetailDAO
{
    /**
     * FIND DETAIL
     * @param workFmeaCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    public WorkFmeaDetailDTO findDetail(WorkFmeaCommonDTO workFmeaCommonDTO, User user) throws Exception;
    
    /**
     * INSERT DETAIL
     * @param workFmeaDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int insertDetail(WorkFmeaDetailDTO workFmeaDetailDTO, User user) throws Exception;
    
    /**
     * UPDATE DETAIL
     * @param workFmeaDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int updateDetail(WorkFmeaDetailDTO workFmeaDetailDTO, User user) throws Exception;
    
    /**
     * CONFIRM DETAIL
     * @param workFmeaDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int confirmDetail(WorkFmeaDetailDTO workFmeaDetailDTO, User user) throws Exception;

    public int setStatus(AppReqDetailDTO appReqDetailDTO, User user);

    public String getStatus(AppReqDetailDTO appReqDetailDTO, User user);
    
}