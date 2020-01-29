package dream.org.dept.service;

import java.util.List;

import common.bean.User;
import dream.org.dept.dto.MaDeptCommonDTO;

/**
 * 보전부서 - 목록 service
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface MaDeptListService
{     
    /**
     *  grid find
     * @author  ssong
     * @version $Id: $
     * @param maDeptCommonDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findDeptList(MaDeptCommonDTO maDeptCommonDTO, User user, boolean excelExport);    
   
    /**
     * delete List
     * @author  ssong
     * @version $Id: $
     * @since   1.0
     * 
     * @param compNo
     * @param deleteRows
     * @return
     * @throws Exception
     */
    public int deleteList(String compNo, String[] deleteRows) throws Exception;

	public String getData(User user, MaDeptCommonDTO maDeptCommonDTO);
}
