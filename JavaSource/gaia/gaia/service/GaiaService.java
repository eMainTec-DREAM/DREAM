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
     * �ش� ���ǿ��� �̹� �α����� �ߴ��� ���ߴ����� ýũ
     * @author  javaworker
     * @version $Id: LoginService.java,v 1.1 2013/08/30 09:14:51 javaworker Exp $
     * @since   1.0
     * 
     * @param sessionID
     * @return
     */
    public boolean isLogin(String sessionID);
    
    /**
     * �ش� ���̵��� ���� ����� �������� �̹� ������� ���̵������� �˾ƺ���
     * @author  javaworker
     * @version $Id: LoginService.java,v 1.1 2013/08/30 09:14:51 javaworker Exp $
     * @since   1.0
     * 
     * @param userID
     * @return
     */
    public boolean isUsing(String userID);

    /**
     * Session �� invalidate �Ѵ�.
     * @author  javaworker
     * @version $Id: LoginService.java,v 1.1 2013/08/30 09:14:51 javaworker Exp $
     * @since   1.0
     * 
     * @param session
     */
    public void unBound(HttpSession session);
    
    /**
     * �α����� �Ǿ��ִ� ��� �ٽ� �α����� �� ��� 
     * ������ �α����� �Ǿ� �ִ� �����͸� �����ϰ� 
     * �ٽ� �α����� �� ��� ����ȴ�. 
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
     * index ���� id, password �� 
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
