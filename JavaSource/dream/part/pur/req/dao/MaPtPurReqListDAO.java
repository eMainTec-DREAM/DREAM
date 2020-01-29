package dream.part.pur.req.dao;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.ass.asset.dto.AssAssetCommonDTO;
import dream.part.pur.req.dto.MaPtReqCommonDTO;

/**
 * 부품수리 - 목록 dao
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
     * 작성상태 변경
     * @author  nhkim8548
     * @version $Id:$
     * @since   1.0
     * @param user
     * @param updateRow
     * @return
     */
    public int updateStatus(User user, String updateRow);
    /**
     * 작성상태 확인
     * @author  nhkim8548
     * @version $Id:$
     * @since   1.0
     * @param id
     * @param user
     * @return
     */
    public String chkPurStatus(String id, User user);
    /**
     * 작성자 확인
     * @author  nhkim8548
     * @version $Id:$
     * @since   1.0
     * @param id
     * @param user
     * @return
     */
    public String chkDelUser(String id, User user);
    /**
     * 메일 접수자 확인
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
     * 메일 부서별 접수자 확인
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
     * 메일 제목
     * @author  nhkim8548
     * @version $Id:$
     * @since   1.0
     * @param   id
     * @param   user
     * @return
     */
    public String findTitle(String keyNo, User user);
    /**
     * 메일 내용
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