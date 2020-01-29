package dream.invt.list.service;

import java.util.List;

import common.bean.User;
import dream.invt.list.dto.InvtCommonDTO;

/**
 * 설비 목록
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 */
public interface InvtEquipListService
{     
    /**
     *  grid find
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param invtCommonDTO
     * @param invtEquipListDTO
     * @throws Exception
     */
    public List findList(InvtCommonDTO invtCommonDTO, User user);
    /**
     *  delete
     * @author  youngjoo38
     * @version $Id$
     * @param user 
     * @since   1.0
     * 
     * @param strings
     * @param d_id 
     * @throws Exception
     */
    public int deleteList(String[] m_id, User user) throws Exception;

    public String findTotalCount(InvtCommonDTO invtCommonDTO, User user) throws Exception;
    
    public int insertNewEqList(InvtCommonDTO invtCommonDTO, User user) throws Exception;
    public int insertEqList(InvtCommonDTO invtCommonDTO, User user) throws Exception;

    // 설비중복검사
    public String validEquip(InvtCommonDTO invtCommonDTO, User user) throws Exception;

}
