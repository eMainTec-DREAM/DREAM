package dream.cert.list.dao;

import java.util.List;

import common.bean.User;
import dream.cert.list.dto.CertCommonDTO;

/**
 * 자격증분류 - 목록 dao
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public interface CertListDAO
{
    /**
     * grid find
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param certCommonDTO
     * @return List
     */
    public List findList(CertCommonDTO certCommonDTO,User user);
    
    /**
     * delete
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param id
     * @param compNo
     * @return
     */
    public int deleteList(String compNo, String certListId);

    public String findTotalCount(CertCommonDTO certCommonDTO, User user) throws Exception;
}