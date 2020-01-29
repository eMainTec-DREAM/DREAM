package dream.consult.program.lang.dao;

import common.bean.User;
import dream.consult.program.lang.dto.MaLangMngDetailDTO;

/**
 * 다국어 - 상세 dao
 * 
 * @author kim21017
 * @version $Id: MaLangMngDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
 * @since 1.0
 */
public interface MaLangMngDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaLangMngDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param langIdKo
     * @param langIdEn
     * @return
     */
    public MaLangMngDetailDTO findDetail(String langId, User user);

    /**
     * detail update
     * @author kim21017
     * @version $Id: MaLangMngDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maLangMngDetailDTO
     * @return
     */
    public int updateDetail(MaLangMngDetailDTO maLangMngDetailDTO, User user);
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaLangMngDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maLangMngDetailDTO
     * @return
     */
    public int insertDetail(MaLangMngDetailDTO maLangMngDetailDTO, User user);
    
    /**
     * valid keyId
     * @author kim21017
     * @version $Id: MaLangMngDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maLangMngDetailDTO
     * @return
     */
    public String validKeyId(MaLangMngDetailDTO maLangMngDetailDTO, User user);
}