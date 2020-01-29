package dream.consult.program.page.dao;

import java.util.List;

import dream.consult.program.page.dto.MaPgMngBtnListDTO;
import dream.consult.program.page.dto.MaPgMngCommonDTO;

/**
 * 화면별 버튼 목록 dao
 * @author  kim21017
 * @version $Id: MaPgMngBtnListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface MaPgMngBtnListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaPgMngBtnListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPgMngCommonDTO
     * @param maPgMngBtnListDTO
     * @return List
     */
    public List findBtnList(MaPgMngCommonDTO maPgMngCommonDTO, MaPgMngBtnListDTO maPgMngBtnListDTO);
    
    /**
     * delete
     * @author kim21017
     * @version $Id: MaPgMngBtnListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @return
     */
    public int deleteBtnList(String id);
}