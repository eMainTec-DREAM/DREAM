package dream.pers.priv.pgm.dao;

import common.bean.User;
import dream.pers.priv.pgm.dto.MaGrdUsrCommonDTO;
import dream.pers.priv.pgm.dto.MaGrdUsrDetailDTO;

/**
 * 칼럼 상세 dao
 * @author  jung7126
 * @version $Id: MaGrdUsrDetailDAO.java,v 1.0 2015/12/04 08:10:27 jung7126 Exp $
 * @since   1.0
 */
public interface MaGrdUsrDetailDAO
{
    /**
     * detail find
     * @author jung7126
     * @version $Id: MaGrdUsrDetailDAO.java,v 1.0 20155/12/02 08:25:47 jung7126 Exp $
     * @since   1.0
     * 
     * @param maGrdUsrCommonDTO
     * @param grdColId
     * @param user 
     * @return
     */
    public MaGrdUsrDetailDTO findDetail(MaGrdUsrCommonDTO maGrdUsrCommonDTO, User user);

    /**
     * detail update
     * @author jung7126
     * @version $Id: MaGrdUsrDetailDAO.java,v 1.0 20155/12/02 08:25:47 jung7126 Exp $
     * @since   1.0
     * 
     * @param maGrdUsrDetailDTO
     * @param maGrdUsrCommonDTO
     * @param user 
     * @return
     */
    public int updateDetail(MaGrdUsrDetailDTO maGrdUsrDetailDTO, MaGrdUsrCommonDTO maGrdUsrCommonDTO, User user);
    
    /**
     * detail insert
     * @author jung7126
     * @version $Id: MaGrdUsrDetailDAO.java,v 1.0 20155/12/02 08:25:47 jung7126 Exp $
     * @since   1.0
     * 
     * @param maGrdUsrDetailDTO
     * @param maGrdUsrCommonDTO
     * @param user 
     * @return
     */
    public int insertDetail(MaGrdUsrDetailDTO maGrdUsrDetailDTO, MaGrdUsrCommonDTO maGrdUsrCommonDTO, User user);
    
    /**
     * 유저 컬럼 정의 상세 화면
     * @author  Mark
     * @version $Id: codetemplates.xml,v 1.1 2007/02/15 06:41:03 dawn Exp $
     * @since   1.0
     * 
     * @param maGrdUsrCommonDTO
     * @param user
     * @return
     */
    public MaGrdUsrDetailDTO findUsrDetail(MaGrdUsrCommonDTO maGrdUsrCommonDTO, User user);
}