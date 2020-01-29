package dream.part.rec.service;

import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.part.rec.dto.MaPtRecCommonDTO;
import dream.part.rec.dto.MaPtRecDetailDTO;

/**
 * 구매입고 - 목록 service
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public interface MaPtRecListService
{     
    /**
     *  grid find
     * @author  ssong
     * @version $Id:$
     * @param maPtRecCommonDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findList(MaPtRecCommonDTO maPtRecCommonDTO,User user);
    
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
    
    public String findTotalCount(MaPtRecCommonDTO maPtRecCommonDTO, User user);

    public int insertList(MaPtRecDetailDTO maPtRecDetailDTO, User loginUser) throws Exception;
    
    public int confirmPtRec(String[] selectedPtRecList, User user) throws Exception;

    public int insertQrCode(String[] selectRows, String fileName, User user) throws Exception;

    // List 저장
    public void saveList(List<Map> gridList, User user) throws Exception;

    public String getData(MaPtRecCommonDTO maPtRecCommonDTO, User user);
	
}
