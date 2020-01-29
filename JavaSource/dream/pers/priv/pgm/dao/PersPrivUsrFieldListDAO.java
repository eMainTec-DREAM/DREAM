package dream.pers.priv.pgm.dao;

import java.util.List;

import com.fins.org.json.JSONObject;
import common.bean.User;

import dream.pers.priv.pgm.dto.PersPrivUsrFieldCommonDTO;

/**
 * 화면별 필드 목록 dao
 * @author  kim21017
 * @version $Id: MaPgUsrFieldListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface PersPrivUsrFieldListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaPgUsrFieldListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPgMngCommonDTO
     * @param maPgUsrFieldListDTO
     * @return List
     */
    public List findFieldList(PersPrivUsrFieldCommonDTO persPrivUsrFieldCommonDTO, User user);
    
    /**
     * delete
     * @author kim21017
     * @version $Id: MaPgUsrFieldListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @return
     */
    public int deleteFieldList(String id);

    public void createField(JSONObject order, String fileName, User user);

    public int hideFieldList(String id, User user, boolean hide);

    public String findTotalCount(PersPrivUsrFieldCommonDTO persPrivUsrFieldCommonDTO, User user);
}