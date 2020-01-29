package dream.consult.program.page.service;

import java.util.List;

import dream.consult.program.page.dto.MaPgMngBtnListDTO;
import dream.consult.program.page.dto.MaPgMngCommonDTO;

/**
 * 화면별 버튼  목록
 * @author  kim21017
 * @version $Id: MaPgMngBtnListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface MaPgMngBtnListService
{     
    /**
     *  grid find
     * @author  kim21017
     * @version $Id: MaPgMngBtnListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPgMngCommonDTO
     * @param maPgMngBtnListDTO
     * @throws Exception
     */
    public List findBtnList(MaPgMngCommonDTO maPgMngCommonDTO, MaPgMngBtnListDTO maPgMngBtnListDTO);
    /**
     *  delete
     * @author  kim21017
     * @version $Id: MaPgMngBtnListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param deleteRows
     * @throws Exception
     */
    public int deleteBtnList(String[] deleteRows) throws Exception;

}
