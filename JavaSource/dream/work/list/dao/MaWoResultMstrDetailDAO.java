package dream.work.list.dao;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.pers.appreq.dto.AppReqDetailDTO;
import dream.work.list.dto.MaWoResultFailDetailDTO;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.dto.MaWoResultMstrDetailDTO;
import dream.work.list.dto.MaWoResultPmCalDTO;

/**
 * 작업결과 - 상세 dao
 * 
 * @author kim21017
 * @version $Id: MaWoResultMstrDetailDAO.java,v 1.0 2015/12/02 08:25:47 kim21017 Exp $
 * @since 1.0
 */
public interface MaWoResultMstrDetailDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaWoResultMstrDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultMstrCommonDTO
     * @return
     */
    public MaWoResultMstrDetailDTO findDetail(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, User loginUser);
    
    /**
     * detail fail find
     * @author kim21017
     * @version $Id: MaWoResultMstrDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultMstrCommonDTO
     * @return
     */
    public MaWoResultFailDetailDTO findFailDetail(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO);
    
    /**
     * detail calib find
     * @author kim21017
     * @version $Id: MaWoResultMstrDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultMstrCommonDTO
     * @return
     */
    public MaWoResultPmCalDTO findCalDetail(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO);
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaWoResultMstrDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultMstrDetailDTO
     * @return
     */
    public int insertDetail(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO, User loginUser);
    public int insertWoequip(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO);
    public int updateWoequip(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO);
    public String selectWoequipCnt(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO);
    
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaWoResultMstrDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultMstrDetailDTO
     * @return
     */
    public int updateDetail(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO, User loginUser);
    
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaWoResultMstrDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultFailDetailDTO
     * @param maWoResultMstrCommonDTO
     * @return
     */
    public int updateFailDetail(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, MaWoResultFailDetailDTO maWoResultFailDetailDTO);
    
    /**
     * detail calib update
     * @author kim21017
     * @version $Id: MaWoResultMstrDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultPmCalDTO
     * @param maWoResultMstrCommonDTO
     * @return
     */
    public int updateCalDetail(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, MaWoResultPmCalDTO maWoResultPmCalDTO);
    
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaWoResultMstrDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultMstrDetailDTO
     * @return
     */
    public int completeDetail(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO, String woStaus);
    
    /**
     * update pm sched
     * @author kim21017
     * @version $Id: MaWoResultMstrDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultMstrDetailDTO
     * @return
     */
    public int updatePmSched(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO);
    public int insertWorkFmea(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO);
    public int deleteWorkFmea(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO);
    public int updateWoReqStatus(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO);
    
    
    /**
     * create point data
     * @author kim21017
     * @version $Id: MaWoResultMstrDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultMstrDetailDTO
     * @return
     */
    public int createPoint(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO);
    
       
    
    /**
     * 정상출고내역 데이터 생성
     * @author kim21017
     * @version $Id: MaWoResultMstrDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultMstrDetailDTO
     * @param ptisslistId 
     * @param wopartId 
     * @return
     */
    public int insertPtIssList(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO, String wopartId, String ptisslistId, User loginUser);
    
    public int deletePtIssList(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO,User loginUser);
     
    public int createPtRepairList(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO);
    
    /**
     * WOPART 최신단가(TAPARTS)로 수정 및 사용수량과 창고확인
     * @author kim21017
     * @version $Id: MaWoResultMstrDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultMstrDetailDTO
     * @return
     */
    public int updatePrice(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO, String wopartId);
    
    /**
     * 부품수리 아이디 업데이트
     * @author kim21017
     * @version $Id: MaWoResultMstrDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultMstrDetailDTO
     * @return
     */
    public int updatePtRepairId(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO);
    
    /**
     * 설비구성자재 수정
     * @author kim21017
     * @version $Id: MaWoResultMstrDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultMstrDetailDTO
     * @return
     */
    public int updateEqPart(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO);
    
    public int updateCancelEqPart(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO);
    public int detetePtRepairList(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO);
    
    /**
     * 이상점검 조치결과 업데이트
     * @author kim21017
     * @version $Id: MaWoResultMstrDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultMstrDetailDTO
     * @return
     */
    public int updateBdPoint(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO);
    public int updateNgBdPoint(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO);
    
        

    /**
     * 리포트 바디
     * @author kim21017
     * @version $Id: MaWoResultMstrDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrDetailDTO
     * @return
     */
    public List findReportWoDetail(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO);
    
    /**
     * 리포트 작업자
     * @author kim21017
     * @version $Id: MaWoResultMstrDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrDetailDTO
     * @return
     */
    public List findReportWoCraftDetail(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO);
    
    /**
     * 리포트 투입부품
     * @author kim21017
     * @version $Id: MaWoResultMstrDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrDetailDTO
     * @return
     */
    public List findReportWoPartDetail(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO);

    /**
     * 리포트 검사항목
     * @author kim21017
     * @version $Id: MaWoResultMstrDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @param maEqMstrDetailDTO
     * @return
     */
    public List findReportWoPointDetail(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO);
    
    /**
     * 리포트 작업설비항목
     * @author kim21017
     * @version $Id: MaWoResultMstrDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @param maEqMstrDetailDTO
     * @return
     */
    public List findReportWoEqDetail(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO);

    /**
     * 리포트 필수검사항목
     * @author kim21017
     * @version $Id: MaWoResultMstrDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @param maEqMstrDetailDTO
     * @return
     */
    public List findReportWoStPointDetail(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO);
    
    /**
     * 교정 리포트 바디
     * @author ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param maEqMstrDetailDTO
     * @return
     */
    public List findPmGnlReportWoDetail(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO);
    
    /**
     * 교정 리포트 바디(압력계)
     * @author ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param maEqMstrDetailDTO
     * @return
     */
    public List findPmPrsReportWoDetail(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO);
    
    /**
     * 교정 리포트 바디(저울)
     * @author ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param maEqMstrDetailDTO
     * @return
     */
    public List findPmSclReportWoDetail(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO);
    
    /**
     * 교정 리포트 표준기
     * @author ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param maEqMstrDetailDTO
     * @return
     */
    public List findReportWoCalibStdEqDetail(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO);
    
    /**
     * 교정 리포트 측정데이터
     * @author ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param maEqMstrDetailDTO
     * @return
     */
    public List findReportWoCalibGnlValueDetail(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO);
    
    /**
     * 교정 리포트(저울) 측정데이터
     * @author ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param maEqMstrDetailDTO
     * @return
     */
    public List findReportWoCalibSclValueDetail(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO);
    
    public String checkPoint(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO,User user);
    
    /**
	 * Find WcodeID
	 * @param maWoResultMstrCommonDTO
	 * @return
	 */
	public String findWcodeId(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO); 
	
	public String findPmId(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO);
	
	public int updateLastSchDate(String compNo, String pmId, String actDate);

	/**
	 * 순환부품 (Serial 자재 고장수리 테이블 데이터 생성
	 * @param maWoResultMstrDetailDTO
	 * @return 
	 */
	public int createSerialPtRepairList(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO);

	public void updateInEquip(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO);

	public void updateOutEquip(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO);

	public List findWopartList(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO);
	
	public String[] findWopartIssList(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO);
	
	public String[] findWoEqList(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO);

	public void insertPtissSerialList(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO, String wopartId,
			String ptisslistId);

	public int findSerialCount(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO);

	public int checkIsSerialPart(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO);

	public void updatePequip(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO);

	public int updateEqhistory(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO);
	public void insertEqhistory(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO);
	public void deleteEqhistory(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO);

    public int setStatus(AppReqDetailDTO appReqDetailDTO, User user);

    public String getStatus(AppReqDetailDTO appReqDetailDTO, User user);

    public String findPage(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, User user);

    public int updateWoResStatus(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO, String woResStatus);

    // 작업계획목록 존재여부 검사
    public String woPlanCheck(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO,User user);

    public int updateWoPlanStatus(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO, String woStatus);

    // 이상점검처리(작업결과) 조치결과 정상 업데이트
    public int updatePmPointStatusGdWoRslt(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO);
    // 이상점검처리(작업결과) 조치결과 (정상->이상) 업데이트
    public int updatePmPointStatusBdWoRslt(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO, String pmPointRepStatus);
    // 이상점검처리(작업계획) 조치결과 정상 업데이트
    public int updatePmPointStatusGdWoPlan(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO);
    // 이상점검처리(작업계획) 조치결과 (정상->이상) 업데이트
    public int updatePmPointStatusBdWoPlan(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO, String pmPointRepStatus);
    // 이상점검처리(작업요청) 조치결과 정상 업데이트
    public int updatePmPointStatusGdWoReq(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO);
    // 이상점검처리(작업요청) 조치결과 (정상->이상) 업데이트
    public int updatePmPointStatusBdWoReq(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO, String pmPointRepStatus);

    public String getLastAppEmpId(AppReqDetailDTO appReqDetailDTO, User user);
    
    public int updateWoStatus(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO, User loginUser);
    
    public String[] getNextCalibDate(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, MaWoResultPmCalDTO maWoResultPmCalDTO);
    public String[] getNextPmWoDate(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, MaWoResultMstrDetailDTO maWoResultMstrDetailDTO);

    public int linkPtIssList(String wopartId, String ptisslistId, User loginUser);
    
    public int updateWoTotAmt(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO, User loginUser) throws Exception;
    
    public int cancelConfirmPmSched(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO, String pmSchedStatus);
    
    public String findPlanInitDate(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO, User loginUser, String pmId);
	public int updatePlanInitDate(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO, User loginUser, String pmId, String planInitDate); 
	
	public List findWoReqId(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO, User user);
}