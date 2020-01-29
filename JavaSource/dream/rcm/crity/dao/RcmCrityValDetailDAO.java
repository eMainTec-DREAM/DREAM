package dream.rcm.crity.dao;

import common.bean.User;
import dream.rcm.crity.dto.RcmCrityCommonDTO;
import dream.rcm.crity.dto.RcmCrityValDetailDTO;
import dream.rcm.crity.dto.RcmCrityValListDTO;

/**
 * Criticality Matrix Val Page - Detail DAO
 * @author kim21017
 * @version $Id: RcmCrityValDetailDAO.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 *
 */
public interface RcmCrityValDetailDAO
{
    /**
     * FIND DETAIL
     * @param rcmCrityCommonDTO
     * @param rcmCrityValListDTO
     * @param user
     * @return
     * @throws Exception
     */
    public RcmCrityValDetailDTO findDetail(RcmCrityCommonDTO rcmCrityCommonDTO,RcmCrityValListDTO rcmCrityValListDTO, User user) throws Exception;
    
    /**
     * UPDATE DETAIL
     * @param rcmCrityCommonDTO
     * @param rcmCrityDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int updateDetail(RcmCrityCommonDTO rcmCrityCommonDTO,RcmCrityValDetailDTO rcmCrityValDetailDTO, User user) throws Exception;
    
}