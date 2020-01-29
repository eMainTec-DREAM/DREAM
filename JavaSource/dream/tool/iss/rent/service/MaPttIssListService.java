package dream.tool.iss.rent.service;

import java.util.List;

import common.bean.User;
import dream.tool.iss.rent.dto.MaPttIssCommonDTO;

/**
 * �����԰� - ��� service
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public interface MaPttIssListService
{     
    /**
     *  grid find
     * @author  ssong
     * @version $Id:$
     * @param maPttIssCommonDTO
     * @since   1.0
     * 
     * @return ��ȸ ��� 
     * @throws Exception
     */
    public List findList(MaPttIssCommonDTO maPttIssCommonDTO, User user);
    
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

	public String findTotalCount(MaPttIssCommonDTO maPttIssCommonDTO, User user);

}
