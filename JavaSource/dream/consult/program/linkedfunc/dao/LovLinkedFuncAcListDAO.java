package dream.consult.program.linkedfunc.dao;

import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.consult.program.linkedfunc.dto.LovLinkedFuncAcListDTO;

/**
 * Linked Function ÆË¾÷
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public interface LovLinkedFuncAcListDAO
{
    /**
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param lovLinkedFuncAcListDTO
     * @param loginUser
     * @return
     */
    public List findLinkedFuncAcList(LovLinkedFuncAcListDTO lovLinkedFuncAcListDTO, User loginUser);

    public List findAcList(LovLinkedFuncAcListDTO lovLinkedFuncAcListDTO, User user,
            Map<String, String> columnMap, Map<String, String> conditionMap);
}