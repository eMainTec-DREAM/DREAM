package dream.consult.program.lang.service;

import common.bean.User;
import dream.consult.program.lang.dto.MaLangMngDetailDTO;

/**
 * 다국어 - 상세 service
 * 
 * @author kim21017
 * @version $Id: MaLangMngDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 */
public interface MaLangMngDetailService
{    
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaLangMngDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param langIdKo
     * @param langIdEn
     * @return
     * @throws Exception
     */
    public MaLangMngDetailDTO findDetail(String langId, User user)throws Exception;
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaLangMngDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maLangMngDetailDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(MaLangMngDetailDTO maLangMngDetailDTO, User user) throws Exception;
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaLangMngDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maLangMngDetailDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(MaLangMngDetailDTO maLangMngDetailDTO, User user) throws Exception;

    /**
     * valid keyId
     * @author kim21017
     * @version $Id: MaLangMngDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maLangMngDetailDTO
     * @return
     * @throws Exception
     */
    public String validKeyId(MaLangMngDetailDTO maLangMngDetailDTO, User user) throws Exception;
}
