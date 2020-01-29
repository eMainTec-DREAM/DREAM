package dream.pers.appln.dao;


import dream.pers.appln.dto.MaAppLineCommonDTO;
import dream.pers.appln.dto.MaAppLineUsrDetailDTO;
import dream.pers.appln.dto.MaAppLineUsrListDTO;

/**
 *  »ó¼¼ dao
 * @author  kim21017
 * @version $Id: MaAppLineUsrDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 */
public interface MaAppLineUsrDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaAppLineUsrDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maAppLineUsrListDTO
     * @param maAppLineCommonDTO
     * @return
     */
    public MaAppLineUsrDetailDTO findDetail(MaAppLineUsrListDTO maAppLineUsrListDTO, MaAppLineCommonDTO maAppLineCommonDTO);
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaAppLineUsrDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maAppLineUsrDetailDTO
     * @param maAppLineCommonDTO
     * @return
     */
    public int updateDetail(MaAppLineUsrDetailDTO maAppLineUsrDetailDTO, MaAppLineCommonDTO maAppLineCommonDTO);
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaAppLineUsrDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maAppLineUsrDetailDTO
     * @param maAppLineCommonDTO
     * @return
     */
    public int insertDetail(MaAppLineUsrDetailDTO maAppLineUsrDetailDTO, MaAppLineCommonDTO maAppLineCommonDTO);
    
    public String checkAppSeq(MaAppLineUsrDetailDTO maAppLineUsrDetailDTO, MaAppLineCommonDTO maAppLineCommonDTO);
    public String appSeq(MaAppLineUsrDetailDTO maAppLineUsrDetailDTO, MaAppLineCommonDTO maAppLineCommonDTO);
}