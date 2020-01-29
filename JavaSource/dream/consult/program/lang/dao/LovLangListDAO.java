package dream.consult.program.lang.dao;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.mafinder.mamstr.dto.LovPageListDTO;
import dream.consult.program.lang.dto.LovLangListDTO;

/**
 * 다국어검색 팝업
 * @author  kim21017
 * @version $Id: LovLangListDAO.java,v 1.0 2016/01/18 00:16:44 kim21017 Exp $
 * @since   1.0
 */
public interface LovLangListDAO
{
    /**
     * 다국어 검색
     * @author  kim21017
     * @version $Id: LovLangListDAO.java,v 1.0 2016/01/18 00:16:44 kim21017 Exp $
     * @since   1.0
     * 
     * @param lovLangListDTO
     * @param loginUser
     * @return
     */
    public List findLangList(LovLangListDTO lovLangListDTO, User loginUser);
    /**
     * 다국어 검색 AC
     * @author  kim21017
     * @version $Id: LovLangListDAO.java,v 1.0 2016/01/18 00:16:44 kim21017 Exp $
     * @since   1.0
     * 
     * @param lovLangListDTO
     * @param loginUser
     * @return
     */
    public List findLangAcList(LovLangListDTO lovLangListDTO, User loginUser, Map<String, String> columnMap, Map<String, String> conditionMap);
    public String findTotalCount(LovLangListDTO lovLangListDTO, User user, Map<String, String> columnMap, Map<String, String> conditionMap);
}