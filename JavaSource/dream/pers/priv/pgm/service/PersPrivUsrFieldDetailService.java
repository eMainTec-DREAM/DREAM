package dream.pers.priv.pgm.service;

import common.bean.User;

import dream.pers.priv.pgm.dto.PersPrivUsrFieldCommonDTO;
import dream.pers.priv.pgm.dto.PersPrivUsrFieldDetailDTO;

/**
 * 화면별 필드 상세
 * @author  kim210117
 * @version $Id: MaPgUsrFieldDetailService.java,v 1.0 2015/12/04 09:08:29 kim210117 Exp $
 * @since   1.0
 */
public interface PersPrivUsrFieldDetailService
{    
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaPgUsrFieldDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @param user 
     * @since   1.0
     * 
     * @param pageId
     * @param pgFieldId
     * @return
     * @throws Exception
     */
	public PersPrivUsrFieldDetailDTO findDetail(PersPrivUsrFieldDetailDTO persPrivUsrFieldDetailDTO, User user);
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaPgUsrFieldDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPgUsrFieldDetailDTO
     * @param maPgMngCommonDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(PersPrivUsrFieldDetailDTO persPrivUsrFieldDetailDTO, PersPrivUsrFieldCommonDTO persPrivUsrFieldCommonDTO) throws Exception;
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaPgUsrFieldDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPgUsrFieldDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public String insertDetail(PersPrivUsrFieldDetailDTO persPrivUsrFieldDetailDTO, User user) throws Exception;
}
