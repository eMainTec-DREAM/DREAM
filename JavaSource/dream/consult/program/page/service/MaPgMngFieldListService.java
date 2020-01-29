package dream.consult.program.page.service;

import java.util.List;

import common.bean.User;
import dream.consult.program.page.dto.MaGrdMngCommonDTO;
import dream.consult.program.page.dto.MaPgMngCommonDTO;
import dream.consult.program.page.dto.MaPgMngFieldListDTO;

/**
 * 화면별 필드  목록
 * @author  kim21017
 * @version $Id: MaPgMngFieldListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface MaPgMngFieldListService
{
    /**
     *  grid find
     * @author  kim21017
     * @version $Id: MaPgMngFieldListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPgMngCommonDTO
     * @param maPgMngFieldListDTO
     * @throws ClassNotFoundException 
     * @throws IllegalAccessException 
     * @throws InstantiationException 
     * @throws Exception
     */
    public List findFieldList(MaPgMngCommonDTO maPgMngCommonDTO, MaPgMngFieldListDTO maPgMngFieldListDTO) throws ClassNotFoundException, InstantiationException, IllegalAccessException;
    /**
     *  delete
     * @author  kim21017
     * @version $Id: MaPgMngFieldListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param deleteRows
     * @throws Exception
     */
    public int deleteFieldList(String[] deleteRows) throws Exception;
    public int sysYColList(String[] deleteRows) throws Exception;
    public int sysNColList(String[] deleteRows) throws Exception;
    public String findTotalCount(MaPgMngCommonDTO maPgMngCommonDTO, MaPgMngFieldListDTO maPgMngFieldListDTO, User user) throws Exception;
}
