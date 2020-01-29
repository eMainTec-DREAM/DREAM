package dream.cert.list.service;

import java.util.List;

import common.bean.User;
import dream.cert.list.dto.CertCommonDTO;

/**
 * �ڰ����з� - ��� service
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public interface CertListService
{     
    /**
     *  grid find
     * @author  ssong
     * @version $Id:$
     * @param certCommonDTO
     * @since   1.0
     * 
     * @return ��ȸ ��� 
     * @throws Exception
     */
    public List findList(CertCommonDTO certCommonDTO, User user);
    
    /**
     * delete
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param compNo
     * @param deleteRows
     * @return
     * @throws Exception
     */
    public int deleteList(String compNo, String[] deleteRows) throws Exception;

    public String findTotalCount(CertCommonDTO certCommonDTO, User user) throws Exception;

}
