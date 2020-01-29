package dream.pers.appln.service;

import dream.pers.appln.dto.MaAppLineCommonDTO;
import dream.pers.appln.dto.MaAppLineUsrDetailDTO;
import dream.pers.appln.dto.MaAppLineUsrListDTO;

/**
 *  - detail
 * @author  kim210117
 * @version $Id: MaAppLineUsrDetailService.java,v 1.0 2015/12/04 09:08:29 kim210117 Exp $
 * @since   1.0
 */
public interface MaAppLineUsrDetailService
{    
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaAppLineUsrDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maAppLineUsrListDTO
     * @param maAppLineCommonDTO
     * @return
     * @throws Exception
     */
    public MaAppLineUsrDetailDTO findDetail(MaAppLineUsrListDTO maAppLineUsrListDTO, MaAppLineCommonDTO maAppLineCommonDTO)throws Exception;
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaAppLineUsrDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maAppLineUsrDetailDTO
     * @param maAppLineCommonDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(MaAppLineUsrDetailDTO maAppLineUsrDetailDTO, MaAppLineCommonDTO maAppLineCommonDTO) throws Exception;
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaAppLineUsrDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maAppLineUsrDetailDTO
     * @param maAppLineCommonDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(MaAppLineUsrDetailDTO maAppLineUsrDetailDTO, MaAppLineCommonDTO maAppLineCommonDTO) throws Exception;
    public String checkAppSeq(MaAppLineUsrDetailDTO maAppLineUsrDetailDTO, MaAppLineCommonDTO maAppLineCommonDTO) throws Exception;
    public String appSeq(MaAppLineUsrDetailDTO maAppLineUsrDetailDTO, MaAppLineCommonDTO maAppLineCommonDTO) throws Exception;
    
}
