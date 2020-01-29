package dream.rcm.crity.dao;

import common.bean.User;
import dream.rcm.crity.dto.RcmCrityColDetailDTO;
import dream.rcm.crity.dto.RcmCrityColListDTO;
import dream.rcm.crity.dto.RcmCrityCommonDTO;

/**
 * Criticality Matrix Col Page - Detail DAO
 * @author kim21017
 * @version $Id: RcmCrityColDetailDAO.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 *
 */
public interface RcmCrityColDetailDAO
{
    /**
     * FIND DETAIL
     * @param rcmCrityCommonDTO
     * @param rcmCrityColListDTO
     * @param user
     * @return
     * @throws Exception
     */
    public RcmCrityColDetailDTO findDetail(RcmCrityCommonDTO rcmCrityCommonDTO,RcmCrityColListDTO rcmCrityColListDTO, User user) throws Exception;
    
    /**
     * INSERT DETAIL
     * @param rcmCrityCommonDTO
     * @param rcmCrityDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int insertDetail(RcmCrityCommonDTO rcmCrityCommonDTO,RcmCrityColDetailDTO rcmCrityColDetailDTO, User user) throws Exception;
    
    /**
     * UPDATE DETAIL
     * @param rcmCrityCommonDTO
     * @param rcmCrityDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int updateDetail(RcmCrityCommonDTO rcmCrityCommonDTO,RcmCrityColDetailDTO rcmCrityColDetailDTO, User user) throws Exception;
    /**
     * CREATE or UPDATE VALUE
     * @param rcmCrityCommonDTO
     * @param rcmCrityDetailDTO
     * @param user
     * @param rowId
     * @return
     * @throws Exception
     */
    public int updateValue(RcmCrityCommonDTO rcmCrityCommonDTO,RcmCrityColDetailDTO rcmCrityColDetailDTO, User user, String rowId) throws Exception;
    /**
     * SELECT ROW LIST
     * @param rcmCrityCommonDTO
     * @param rcmCrityDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public String[] findRowList(RcmCrityCommonDTO rcmCrityCommonDTO, User user) throws Exception;
    
}