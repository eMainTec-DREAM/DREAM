package dream.mgr.mail.dao;

import dream.mgr.mail.dto.MaMailCommonDTO;
import dream.mgr.mail.dto.MaMailUsrDetailDTO;
import dream.mgr.mail.dto.MaMailUsrListDTO;

/**
 * 메일수신자설정 - 수신자 상세 dao
 * @author  kim21017
 * @version $Id: MaMailUsrDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 */
public interface MaMailUsrDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaMailUsrDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maMailUsrListDTO
     * @param maMailCommonDTO
     * @return
     */
    public MaMailUsrDetailDTO findDetail(MaMailUsrListDTO maMailUsrListDTO, MaMailCommonDTO maMailCommonDTO);

    /**
     * detail update
     * @author kim21017
     * @version $Id: MaMailUsrDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maMailUsrDetailDTO
     * @param maMailCommonDTO
     * @return
     */
    public int updateDetail(MaMailUsrDetailDTO maMailUsrDetailDTO, MaMailCommonDTO maMailCommonDTO);
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaMailUsrDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maMailUsrDetailDTO
     * @param maMailCommonDTO
     * @return
     */
    public int insertDetail(MaMailUsrDetailDTO maMailUsrDetailDTO, MaMailCommonDTO maMailCommonDTO);
}