package dream.part.pur.req.dao;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.ass.asset.dto.AssAssetCommonDTO;
import dream.part.pur.req.dto.MaPtReqCommonDTO;

/**
 * ��ǰ���� - ��� dao
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public interface MaPtPurReqListDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * grid find
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtReqCommonDTO
     * @return List
     */
    public List findList(MaPtReqCommonDTO maPtReqCommonDTO, User user);
    
    /**
     * delete
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param user
     * @param ptPnListId
     * @return
     */
    public int deleteList(User user, String ptPnListId);
    /**
     * �ۼ����� ����
     * @author  nhkim8548
     * @version $Id:$
     * @since   1.0
     * @param user
     * @param updateRow
     * @return
     */
    public int updateStatus(User user, String updateRow);
    /**
     * �ۼ����� Ȯ��
     * @author  nhkim8548
     * @version $Id:$
     * @since   1.0
     * @param id
     * @param user
     * @return
     */
    public String chkPurStatus(String id, User user);
    /**
     * �ۼ��� Ȯ��
     * @author  nhkim8548
     * @version $Id:$
     * @since   1.0
     * @param id
     * @param user
     * @return
     */
    public String chkDelUser(String id, User user);
    /**
     * ���� ������ Ȯ��
     * @author  nhkim8548
     * @version $Id:$
     * @since   1.0
     * @param   id
     * @param   user
     * @return
     */
    public String[] findMaPtPurReqUserMailList(String id, User user);
    public String[] findMaPtPurReqUserEmpNoList(String id, User user);
    /**
     * ���� �μ��� ������ Ȯ��
     * @author  nhkim8548
     * @version $Id:$
     * @since   1.0
     * @param   id
     * @param   user
     * @return
     */
    public String[] findMaPtPurReqDeptMailList(String id, User user);
    public String[] findMaPtPurReqDeptEmpNoList(String id, User user);
    /**
     * ���� ����
     * @author  nhkim8548
     * @version $Id:$
     * @since   1.0
     * @param   id
     * @param   user
     * @return
     */
    public String findTitle(String keyNo, User user);
    /**
     * ���� ����
     * @author  nhkim8548
     * @version $Id:$
     * @since   1.0
     * @param   id
     * @param   user
     * @return
     */
    public List selectMaPtPurReqDetail(String id, User user);
    
    public String findTotalCount(MaPtReqCommonDTO maPtReqCommonDTO, User user) throws Exception;
    
}