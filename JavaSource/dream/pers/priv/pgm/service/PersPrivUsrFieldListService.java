package dream.pers.priv.pgm.service;

import java.util.List;

import common.bean.User;

import dream.pers.priv.pgm.dto.PersPrivUsrFieldCommonDTO;

/**
 * 화면별 유저 필드  목록
 * @author  kim21017
 * @version $Id: MaPgUsrFieldListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface PersPrivUsrFieldListService
{
    /**
     *  grid find
     * @author  kim21017
     * @version $Id: MaPgUsrFieldListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPgMngCommonDTO
     * @param user 
     * @param maPgUsrFieldListDTO
     * @throws ClassNotFoundException 
     * @throws IllegalAccessException 
     * @throws InstantiationException 
     * @throws Exception
     */
    public List findFieldList(PersPrivUsrFieldCommonDTO persPrivUsrFieldCommonDTO, User user) throws ClassNotFoundException, InstantiationException, IllegalAccessException;
    /**
     *  delete
     * @author  kim21017
     * @version $Id: MaPgUsrFieldListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param deleteRows
     * @throws Exception
     */
    public int deleteFieldList(String[] deleteRows) throws Exception;
    /**
     * Make Field Data
     * @author  jung7126
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPgMngCommonDTO
     * @param user
     */
    public void createFieldList(PersPrivUsrFieldCommonDTO persPrivUsrFieldCommonDTO, User user);
    /**
     * Hide Fields
     * @author  Mark
     * @version $Id: codetemplates.xml,v 1.1 2007/02/15 06:41:03 dawn Exp $
     * @since   1.0
     * 
     * @param deleteRows
     * @param user 
     * @return 
     * @throws Exception 
     */
    public int hideFieldList(String[] deleteRows, User user, boolean hide) throws Exception;
    public String findTotalCount(PersPrivUsrFieldCommonDTO persPrivUsrFieldCommonDTO, User user);

}
