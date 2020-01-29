package dream.consult.program.lang.service;

import java.util.List;

import common.bean.User;
import dream.consult.program.lang.dto.MaLangMngCommonDTO;
import dream.work.let.categ.dto.WorkLetCategCommonDTO;

/**
 * 다국어 - 목록 service
 * @author  kim21017
 * @version $Id: MaLangMngListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface MaLangMngListService
{     
    /**
     *  grid find
     * @author  kim21017
     * @version $Id: MaLangMngListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @param maLangMngCommonDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findKeyList(MaLangMngCommonDTO maLangMngCommonDTO, User user);    
    
    /**
     * delete
     * @author kim21017
     * @version $Id: MaLangMngListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param deleteRows
     * @param deleteRowsExt
     * @return
     * @throws Exception
     */
    public int deleteKey(String[] deleteRows) throws Exception;
    public String findTotalCount(MaLangMngCommonDTO maLangMngCommonDTO, User user) throws Exception;


}
