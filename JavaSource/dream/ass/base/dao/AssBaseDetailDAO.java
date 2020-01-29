package dream.ass.base.dao;

import common.bean.User;
import dream.ass.base.dto.AssBaseCommonDTO;
import dream.ass.base.dto.AssBaseDetailDTO;

/**
 * 설비등급 평가기준 - Detail DAO
 * @author kim21017
 * @version $Id: AssBaseDetailDAO.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 *
 */
public interface AssBaseDetailDAO
{
    /**
     * FIND DETAIL
     * @param assBaseCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    public AssBaseDetailDTO findDetail(AssBaseCommonDTO assBaseCommonDTO, User user) throws Exception;
    
    /**
     * INSERT DETAIL
     * @param assBaseDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int insertDetail(AssBaseDetailDTO assBaseDetailDTO, User user) throws Exception;
    
    /**
     * UPDATE DETAIL
     * @param assBaseDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int updateDetail(AssBaseDetailDTO assBaseDetailDTO, User user) throws Exception;
    
}