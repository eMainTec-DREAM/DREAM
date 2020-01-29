package dream.consult.program.page.dao;

import java.util.List;

import common.bean.User;
import dream.consult.program.page.dto.MaPgMngCommonDTO;
import dream.consult.program.page.dto.MaPgMngFieldListDTO;

/**
 * 화면별 필드 목록 dao
 * @author  kim21017
 * @version $Id: MaPgMngFieldListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface MaPgMngFieldListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaPgMngFieldListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPgMngCommonDTO
     * @param maPgMngFieldListDTO
     * @return List
     */
    public List findFieldList(MaPgMngCommonDTO maPgMngCommonDTO, MaPgMngFieldListDTO maPgMngFieldListDTO);
    
    /**
     * delete
     * @author kim21017
     * @version $Id: MaPgMngFieldListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @return
     */
    public int deleteFieldList(String id);
    
    public int sysYColList(String id);

    public int sysNColList(String id);

    public String findTotalCount(MaPgMngCommonDTO maPgMngCommonDTO, MaPgMngFieldListDTO maPgMngFieldListDTO, User user) throws Exception;

}