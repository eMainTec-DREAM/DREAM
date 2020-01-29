package dream.pers.priv.pgm.dao;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;

import dream.pers.priv.pgm.dto.PersPrivUsrFieldCommonDTO;
import dream.pers.priv.pgm.dto.PersPrivUsrFieldDetailDTO;

/**
 * 화면별 필드 상세 dao
 * @author  kim21017
 * @version $Id: MaPgUsrFieldDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 */
public interface PersPrivUsrFieldDetailDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaPgUsrFieldDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param pageId
     * @param pgFieldId
     * @return
     */
    public PersPrivUsrFieldDetailDTO findDetail(PersPrivUsrFieldDetailDTO maPgUsrFieldDetailDTO, User user);

    /**
     * detail update
     * @author kim21017
     * @version $Id: MaPgUsrFieldDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPgUsrFieldDetailDTO
     * @param maPgMngCommonDTO
     * @return
     */
    public int updateDetail(PersPrivUsrFieldDetailDTO maPgUsrFieldDetailDTO, PersPrivUsrFieldCommonDTO persPrivUsrFieldCommonDTO);
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaPgUsrFieldDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPgUsrFieldDetailDTO
     * @param maPgMngCommonDTO
     * @return
     */
    public int insertDetail(PersPrivUsrFieldDetailDTO maPgUsrFieldDetailDTO, User user);
}