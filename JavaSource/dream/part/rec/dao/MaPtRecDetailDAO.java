package dream.part.rec.dao;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.part.rec.dto.MaPtRecDetailDTO;

/**
 * 구매입고 - 상세 dao
 * 
 * @author ssong
 * @version $Id: $
 * @since 1.0
 */
public interface MaPtRecDetailDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * detail find
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param compNo
     * @param prRecListId
     * @return
     */
    public MaPtRecDetailDTO findDetail(User user, String prRecListId);
    
    /**
     * 
     * @author  javaworker
     * @version $Id: ConfigDAO.java,v 1.1 2013/08/30 09:14:38 javaworker Exp $
     * @since   1.0
     * 
     * @return
     */
    public List findSerialList(MaPtRecDetailDTO maPtRecDetailDTO);
    
    /**
     * detail insert
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtRecDetailDTO
     * @return
     */
    public int insertDetail(MaPtRecDetailDTO maPtRecDetailDTO, User loginUser);
    public int insertPart(MaPtRecDetailDTO maPtRecDetailDTO, User user);
    
    /**
     * detail insert
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtRecDetailDTO
     * @return
     */
    public int insertEquipment(String serialNo,String compNo, String equipId, String partId, String deptId);
    
    /**
     * detail insert
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtRecDetailDTO
     * @return
     */
    public int insertEqDevice(String compNo, String equipId, String partId, String wcodeId);
    
    /**
     * detail stock
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtRecDetailDTO
     * @return
     */
    public int updateDetail(MaPtRecDetailDTO maPtRecDetailDTO, User user);
    
    /**
     * detail 품번 Update
     * @author syyang
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtRecDetailDTO
     * @return
     */
    public int updatePartNo(MaPtRecDetailDTO maPtRecDetailDTO, User user);
    
    /**
     * detail 상태 Update
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtRecDetailDTO
     * @return
     */
    public int updatePtRecListStatus(MaPtRecDetailDTO maPtRecDetailDTO, User user);
       
    /**
     * valid check
     * @author 
     * @version $Id:  $
     * @since   1.0
     * 
     * @param maPtRecDetailDTO
     * @return
     */
    public String validPrRecListNo(MaPtRecDetailDTO maPtRecDetailDTO);
    
    /**
     * valid check
     * @author 
     * @version $Id:  $
     * @since   1.0
     * 
     * @param maPtRecDetailDTO
     * @return
     */
    public String validSerialCount(MaPtRecDetailDTO maPtRecDetailDTO);
    
    /**
     * 상태 조회
     * @author 
     * @version $Id:  $
     * @since   1.0
     * 
     * @param maPtRecDetailDTO
     * @return
     */
    public String findPrRecListStatus(String compNo, String prRecListId);
      
    /**
     * 입고 History insert
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtRecDetailDTO
     * @return
     */
    public int insertRecHistory(String ptRecHistId, MaPtRecDetailDTO maPtRecDetailDTO, User user);
    
    /**
     * 입고완료 전에 SP_PT_INSTOCK 프로시져 호출 
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param compNo
     * @param ptRecHistId
     * @return
     * @throws Exception
     */
    public int executeSpPtInstock(String compNo, String ptRecHistId) throws Exception;

    public String checkExistValue(String ptRecListId);
    
    public String checkExistNonValue(String fcRecListId);
    
    public String checkExistPartNo(MaPtRecDetailDTO maPtRecDetailDTO, User user);
    
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
    public int deleteSerial(MaPtRecDetailDTO maPtRecDetailDTO);
    
    /**
     * detail insert
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtRecDetailDTO
     * @return
     */
    public int updateSerial(String compNo, String equipId,  String prreclistSerialId);
    
    /**
     * detail insert
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtRecDetailDTO
     * @return
     */
    public int updateEquipment(String compNo, String isEquipId,String status);
    
    /**
     * detail stock
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtRecDetailDTO
     * @return
     */
    public int updateSerialPart(MaPtRecDetailDTO maPtRecDetailDTO);
   
}