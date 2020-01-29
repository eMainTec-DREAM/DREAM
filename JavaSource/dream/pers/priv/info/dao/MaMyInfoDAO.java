package dream.pers.priv.info.dao;

import dream.pers.priv.info.dto.MaMyInfoCommonDTO;

/**
 * ³»Á¤º¸ DAO
 * @author  kim21017
 * @version $Id: MaMyInfoDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface MaMyInfoDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaMyInfoDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maMyInfoCommonDTO
     * @return MaMyInfoCommonDTO
     */
	public MaMyInfoCommonDTO findDetail(MaMyInfoCommonDTO maMyInfoCommonDTO);
	
	/**
     * detail update
     * @author kim21017
     * @version $Id: MaMyInfoDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maBtnMngDetailDTO
     * @return
     */
    public int updateDetail(MaMyInfoCommonDTO maMyInfoCommonDTO);
}