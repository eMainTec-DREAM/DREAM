package dream.consult.program.guide.service;

import common.bean.User;
import dream.consult.program.guide.dto.ConsultPgmGuideCommonDTO;
import dream.consult.program.guide.dto.ConsultPgmGuideDetailDTO;
/**
 * Guide Page - Detail Service
 * @author kim21017
 * @version $Id: ConsultPgmGuideDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 */
public interface ConsultPgmGuideDetailService
{    
	/**
	 * FIND PROGRAM GUIDE DETAIL
	 * @param consultPgmGuideCommonDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public ConsultPgmGuideDetailDTO findPgmGuideDetail(ConsultPgmGuideCommonDTO consultPgmGuideCommonDTO, User user) throws Exception;
	/**
	 * INSERT PROGRAM GUIDE
	 * @param consultPgmGuideDetailDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public int insertPgmGuideDetail(ConsultPgmGuideDetailDTO consultPgmGuideDetailDTO, User user) throws Exception;
    /**
     * UPDATE PROGRAM GUIDE
     * @param consultPgmGuideDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int updatePgmGuideDetail(ConsultPgmGuideDetailDTO consultPgmGuideDetailDTO, User user) throws Exception;
}
