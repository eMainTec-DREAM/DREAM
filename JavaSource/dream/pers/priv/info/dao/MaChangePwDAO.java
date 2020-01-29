package dream.pers.priv.info.dao;

import dream.pers.priv.info.dto.MaChangePwDTO;

/**
 * 시스템코드-분류 dao
 * @author  kim21017
 * @version $Id: MaChangePwDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 */
public interface MaChangePwDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaChangePwDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maChangePwDTO
     * @return
     */
    public MaChangePwDTO findDetail(MaChangePwDTO maChangePwDTO);

    /**
     * detail update
     * @author kim21017
     * @version $Id: MaChangePwDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maChangePwDTO
     * @return
     */
    public int updateDetail(MaChangePwDTO maChangePwDTO);
    public int insertPwChanHist(MaChangePwDTO maChangePwDTO);
    public String checkPwHist(MaChangePwDTO maChangePwDTO, String pwChangeHistCnt);
}