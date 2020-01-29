package dream.part.rec.dao;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.part.rec.dto.MaPtRecCommonDTO;
import dream.work.list.dto.MaWoResultMstrCommonDTO;

/**
 * 구매입고 - 목록 dao
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public interface MaPtRecListDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * grid find
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtRecCommonDTO
     * @return List
     */
    public List findList(MaPtRecCommonDTO maPtRecCommonDTO, User user);
    
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
    public int deleteList(String compNo, String prRecListId);
    
    public String findTotalCount(MaPtRecCommonDTO maPtRecCommonDTO, User user);
    
    public int insertQrCode(String id, String fileName, User loginUser);
    
    public int deleteQrCode(String fileName, User loginUser) throws Exception;

    /**
     * 엑셀업로드 데이터
     * @author  syyang
     * @version $Id: Exp $
     * @since   1.0
     * 
     * @param maPtRecCommonDTO
     * @param user
     * @return
     */
    public String getData(MaPtRecCommonDTO maPtRecCommonDTO, User user);   
}