package dream.part.list.service;

import java.util.List;

import common.bean.User;
import dream.part.list.dto.LovPartsListDTO;
import dream.part.list.form.LovPartsListForm;

/**
 * �����˾� Service
 * @author  kim21017
 * @version $Id: LovPartsListService.java,v 1.2 2014/01/28 07:49:27 kim21017 Exp $
 * @since   1.0
 *
 */
public interface LovPartsListService
{

    /**
     * ����˻�
     * @author  kim21017
     * @version $Id: LovPartsListService.java,v 1.2 2014/01/28 07:49:27 kim21017 Exp $
     * @since   1.0
     * 
     * @param LovPartsListDTO
     * @param loginUser
     * @return
     */
    List findPartsList(LovPartsListDTO lovPartsListDTO, User loginUser);

    /**
     * ����˻� AC LOV
     * @author  kim21017
     * @version $Id: LovPartsListService.java,v 1.2 2014/01/28 07:49:27 kim21017 Exp $
     * @since   1.0
     * 
     * @param lovPartsListForm
     * @param loginUser
     * @return
     */
    List findPartsAcList(LovPartsListForm lovPartsListForm, User loginUser);
    
    /**
     * find Total Count
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param lovPartsListForm
     * @return
     */
    public String findTotalCount(LovPartsListForm lovPartsListForm, User user) throws Exception;

}