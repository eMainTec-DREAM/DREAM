package dream.tool.iss.rtn.service;

import java.util.List;

import common.bean.User;
import dream.tool.iss.rtn.dto.MaPttRtnCommonDTO;

/**
 * 공기구반납 - 목록 service
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public interface MaPttRtnListService
{     
    /**
     *  grid find
     * @author  ssong
     * @version $Id:$
     * @param maPttRtnCommonDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findList(MaPttRtnCommonDTO maPttRtnCommonDTO, User user);
    
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

	public String findTotalCount(MaPttRtnCommonDTO maPttRtnCommonDTO, User user);

}
