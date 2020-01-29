package dream.fail.code.service;

import java.util.List;

import common.bean.User;
import dream.fail.code.dto.MaFailureCommonDTO;
import dream.fail.code.form.FailCodeLovForm;

/**
 * �����ڵ� LOV - ��� service
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface FailCodeLovService
{     
    /**
     *  grid find
     * @author  ssong
     * @version $Id: $
     * @param maFailureCommonDTO
     * @param failCodeLovForm 
     * @since   1.0
     * 
     * @return ��ȸ ��� 
     * @throws Exception
     */
    public List findList(MaFailureCommonDTO maFailureCommonDTO, User user, FailCodeLovForm failCodeLovForm);    
}
