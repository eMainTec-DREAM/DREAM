package dream.consult.program.guide.dao;

import common.bean.User;
import dream.consult.program.guide.dto.ConsultPgmGuideCommonDTO;
import dream.consult.program.guide.dto.ConsultPgmGuideDetailDTO;

/**
 * Guide Page - Detail DAO
 * @author kim21017
 * @version $Id: ConsultPgmGuideDetailDAO.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 *
 */
public interface ConsultPgmGuideDetailDAO
{
    /**
     * FIND DETAIL
     * @param consultPgmGuideCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    public ConsultPgmGuideDetailDTO findPgmGuideDetail(ConsultPgmGuideCommonDTO consultPgmGuideCommonDTO, User user) throws Exception;
    
    /**
     * INSERT DETAIL
     * @param consultPgmGuideDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int insertPgmGuideDetail(ConsultPgmGuideDetailDTO consultPgmGuideDetailDTO, User user) throws Exception;
    
    /**
     * UPDATE DETAIL
     * @param consultPgmGuideDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int updatePgmGuideDetail(ConsultPgmGuideDetailDTO consultPgmGuideDetailDTO, User user) throws Exception;
    
}