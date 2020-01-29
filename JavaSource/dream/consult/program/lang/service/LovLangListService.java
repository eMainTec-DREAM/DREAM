package dream.consult.program.lang.service;

import java.util.List;

import common.bean.User;
import dream.consult.program.lang.dto.LovLangListDTO;
import dream.consult.program.lang.form.LovLangListForm;

/**
 * 다국어팝업 Service
 * @author  kim21017
 * @version $Id: LovLangListService.java,v 1.2 2014/01/28 07:49:27 kim21017 Exp $
 * @since   1.0
 *
 */
public interface LovLangListService
{

    /**
     * 다국어검색
     * @author  kim21017
     * @version $Id: LovLangListService.java,v 1.2 2014/01/28 07:49:27 kim21017 Exp $
     * @since   1.0
     * 
     * @param LovLangListDTO
     * @param loginUser
     * @return
     */
    List findLangList(LovLangListDTO lovLangListDTO, User loginUser);
    /**
     * 다국어검색 AC
     * @author  kim21017
     * @version $Id: LovLangListService.java,v 1.2 2014/01/28 07:49:27 kim21017 Exp $
     * @since   1.0
     * 
     * @param lovLangListForm
     * @param loginUser
     * @return
     */
    List findLangAcList(LovLangListForm lovLangListForm, User loginUser);
    public String findTotalCount(LovLangListForm lovLangListForm, User loginUser) throws Exception;
}