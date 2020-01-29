/**
 * 
 */
package gaia.gaia.service;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import common.bean.User;
import gaia.gaia.dto.GaiaDTO;

/**
 * Gaia Login Service
 * @author  jung7126
 * @version $Id: LoginService.java,v 1.1 2013/08/30 09:14:51 javaworker Exp $
 * @since   1.0
 *
 */
public interface GaiaService
{
    /**
     * 해당 세션에서 이미 로그인을 했는지 안했는지를 첵크
     * @author  javaworker
     * @version $Id: LoginService.java,v 1.1 2013/08/30 09:14:51 javaworker Exp $
     * @since   1.0
     * 
     * @param sessionID
     * @return
     */
    public boolean isLogin(String sessionID);
    
    /**
     * 해당 아이디의 동시 사용을 막기위해 이미 사용중인 아이디인지를 알아본다
     * @author  javaworker
     * @version $Id: LoginService.java,v 1.1 2013/08/30 09:14:51 javaworker Exp $
     * @since   1.0
     * 
     * @param userID
     * @return
     */
    public boolean isUsing(String userID);

    /**
     * Session 을 invalidate 한다.
     * @author  javaworker
     * @version $Id: LoginService.java,v 1.1 2013/08/30 09:14:51 javaworker Exp $
     * @since   1.0
     * 
     * @param session
     */
    public void unBound(HttpSession session);
    
    /**
     * 로그인이 되어있는 경우 다시 로그인을 할 경우 
     * 기존에 로그인이 되어 있던 데이터를 제거하고 
     * 다시 로그인을 할 경우 실행된다. 
     * @author  mentor
     * @version $Id: LoginService.java,v 1.1 2013/08/30 09:14:51 javaworker Exp $
     * @since   1.0
     * 
     * @param userId
     * @param sessionId 
     * @return
     */
    public boolean isForceLogOut(String userId, String sessionId);
    
    /**
     * index 에서 id, password 를 
     * @author  javaworker
     * @version $Id: LoginService.java,v 1.1 2013/08/30 09:14:51 javaworker Exp $
     * @param localeId 
     * @param compNo 
     * @since   1.0
     * 
     * @param loginDTO
     * @return
     * @throws Exception
     */
    public List findUserInfo(GaiaDTO gaiaDTO);

    /**
     * Find Gaia Menu List
     * @author  Mark
     * @version $Id: codetemplates.xml,v 1.1 2007/02/15 06:41:03 dawn Exp $
     * @since   1.0
     * 
     * @param loginUser
     * @return
     */
    public ArrayList findGaiaMenuList(User loginUser);

    /**
     * Find System & User Field Info
     * @author  Mark
     * @version $Id: codetemplates.xml,v 1.1 2007/02/15 06:41:03 dawn Exp $
     * @since   1.0
     * 
     * @param loginUser
     * @return
     */
    public Hashtable findPageFields(User loginUser);

    public void insertLogHist(User loginUser, HttpServletRequest request);

}
