package dream.mgr.usrdata.service;

import java.util.List;

import common.bean.User;
import dream.mgr.usrdata.dto.McDataSelectCommonDTO;


/**
 * ����ڵ����� - ��� service
 * @author  kim21017
 * @version $Id: McDataSelectListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface McDataSelectListService
{     
    /**
     *  grid find
     * @author  kim21017
     * @version $Id: McDataSelectListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @param mcDataSelectCommonDTO
     * @since   1.0
     * 
     * @return ��ȸ ��� 
     * @throws Exception
     */
    public List findMenuList(McDataSelectCommonDTO mcDataSelectCommonDTO, User loginUser);    
    /**
     * delete
     * @author kim21017
     * @version $Id: McDataSelectListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param deleteRows
     * @return
     * @throws Exception
     */
    public int deleteMenu(String[] deleteRows) throws Exception;
    
    public String findTotalCount(McDataSelectCommonDTO mcDataSelectCommonDTO, User user) throws Exception;


}