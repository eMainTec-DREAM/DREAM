package gaia.gaia.dao;
import java.util.ArrayList;
import java.util.List;

import common.bean.User;
import gaia.gaia.dto.GaiaDTO;

/**
 * Gaia Login DAO
 * @author  jung7126
 * @version $Id: LoginDAO.java,v 1.4 2014/01/14 05:33:59 pochul2423 Exp $
 * @since   1.0
 */
public interface GaiaDAO
{
    /**
     * �ش� User Id �� ������ ��ȸ�Ѵ�.        
     * @author  jung7126
     * @version $Id: LoginDAO.java,v 1.4 2014/01/14 05:33:59 pochul2423 Exp $
     * @since   1.0
     * 
     * @param gaiaDTO
     * @return
     * @throws Exception
     */
    public List findUserInfo(GaiaDTO gaiaDTO);

    public ArrayList findGaiaMenuList(User loginUser);

    /**
     * Find Field Info of each Page 
     * @author  Mark
     * @version $Id: codetemplates.xml,v 1.1 2007/02/15 06:41:03 dawn Exp $
     * @since   1.0
     * 
     * @param loginUser
     * @return
     */
    public List findPageFields(User loginUser);

    /**
     * ������ �α��� �����丮
     * @author  Mark
     * @version $Id: codetemplates.xml,v 1.1 2007/02/15 06:41:03 dawn Exp $
     * @since   1.0
     * 
     * @param loginUser
     * @param loginTerminal
     * @param terminalNo
     */
    public void insertLogHist(User loginUser, String loginTerminal,String terminalNo);
}