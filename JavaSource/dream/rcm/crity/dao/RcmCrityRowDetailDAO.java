package dream.rcm.crity.dao;

import common.bean.User;
import dream.rcm.crity.dto.RcmCrityCommonDTO;
import dream.rcm.crity.dto.RcmCrityRowDetailDTO;
import dream.rcm.crity.dto.RcmCrityRowListDTO;

/**
 * Criticality Matrix Row Page - Detail DAO
 * @author kim21017
 * @version $Id: RcmCrityRowDetailDAO.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 *
 */
public interface RcmCrityRowDetailDAO
{
    /**
     * FIND DETAIL
     * @param rcmCrityCommonDTO
     * @param rcmCrityRowListDTO
     * @param user
     * @return
     * @throws Exception
     */
    public RcmCrityRowDetailDTO findDetail(RcmCrityCommonDTO rcmCrityCommonDTO,RcmCrityRowListDTO rcmCrityRowListDTO, User user) throws Exception;
    
    /**
     * INSERT DETAIL
     * @param rcmCrityCommonDTO
     * @param rcmCrityDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int insertDetail(RcmCrityCommonDTO rcmCrityCommonDTO,RcmCrityRowDetailDTO rcmCrityRowDetailDTO, User user) throws Exception;
    
    /**
     * UPDATE DETAIL
     * @param rcmCrityCommonDTO
     * @param rcmCrityDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int updateDetail(RcmCrityCommonDTO rcmCrityCommonDTO,RcmCrityRowDetailDTO rcmCrityRowDetailDTO, User user) throws Exception;
    /**
     * CREATE or UPDATE VALUE
     * @param rcmCrityCommonDTO
     * @param rcmCrityDetailDTO
     * @param user
     * @param colId
     * @return
     * @throws Exception
     */
    public int updateValue(RcmCrityCommonDTO rcmCrityCommonDTO,RcmCrityRowDetailDTO rcmCrityRowDetailDTO, User user, String colId) throws Exception;
    /**
     * SELECT COL LIST
     * @param rcmCrityCommonDTO
     * @param rcmCrityDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public String[] findColList(RcmCrityCommonDTO rcmCrityCommonDTO, User user) throws Exception;
}