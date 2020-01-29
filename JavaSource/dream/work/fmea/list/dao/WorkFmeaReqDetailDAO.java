package dream.work.fmea.list.dao;

import common.bean.User;
import dream.work.fmea.list.dto.WorkFmeaReqCommonDTO;
import dream.work.fmea.list.dto.WorkFmeaReqDetailDTO;

/**
 * 고장영향성평가 - Detail DAO
 * @author kim21017
 * @version $Id: WorkFmeaReqDetailDAO.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 *
 */
public interface WorkFmeaReqDetailDAO
{
    /**
     * FIND DETAIL
     * @param workFmeaReqCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    public WorkFmeaReqDetailDTO findDetail(WorkFmeaReqCommonDTO workFmeaReqCommonDTO, User user) throws Exception;
    
    /**
     * INSERT DETAIL
     * @param workFmeaReqDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int insertDetail(WorkFmeaReqDetailDTO workFmeaReqDetailDTO, User user) throws Exception;
    
    /**
     * UPDATE DETAIL
     * @param workFmeaReqDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int updateDetail(WorkFmeaReqDetailDTO workFmeaReqDetailDTO, User user) throws Exception;
    
    /**
     * UPDATE STATUS
     * @param workFmeaReqDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int updateStatus(WorkFmeaReqDetailDTO workFmeaReqDetailDTO, User user) throws Exception;
    
}