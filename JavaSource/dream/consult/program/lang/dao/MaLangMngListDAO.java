package dream.consult.program.lang.dao;

import java.util.List;

import common.bean.User;
import dream.consult.program.lang.dto.MaLangMngCommonDTO;
import dream.work.let.categ.dto.WorkLetCategCommonDTO;

/**
 * 다국어 - 목록 dao
 * @author  kim21017
 * @version $Id: MaLangMngListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface MaLangMngListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaLangMngListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maLangMngCommonDTO
     * @return List
     */
    public List findKeyList(MaLangMngCommonDTO maLangMngCommonDTO, User user);
    
    /**
     * delete
     * @author kim21017
     * @version $Id: MaLangMngListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @return
     */
    public int deleteKey(String id);
    
    public String findTotalCount(MaLangMngCommonDTO maLangMngCommonDTO, User user) throws Exception;
}