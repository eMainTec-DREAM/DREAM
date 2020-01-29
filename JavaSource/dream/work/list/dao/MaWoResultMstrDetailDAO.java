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
 * �۾���� - �� dao
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
     * ��������� ������ ����
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
     * WOPART �ֽŴܰ�(TAPARTS)�� ���� �� �������� â��Ȯ��
     * @author kim21017
     * @version $Id: MaWoResultMstrDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultMstrDetailDTO
     * @return
     */
    public int updatePrice(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO, String wopartId);
    
    /**
     * ��ǰ���� ���̵� ������Ʈ
     * @author kim21017
     * @version $Id: MaWoResultMstrDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultMstrDetailDTO
     * @return
     */
    public int updatePtRepairId(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO);
    
    /**
     * ���񱸼����� ����
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
     * �̻����� ��ġ��� ������Ʈ
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
     * ����Ʈ �ٵ�
     * @author kim21017
     * @version $Id: MaWoResultMstrDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrDetailDTO
     * @return
     */
    public List findReportWoDetail(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO);
    
    /**
     * ����Ʈ �۾���
     * @author kim21017
     * @version $Id: MaWoResultMstrDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrDetailDTO
     * @return
     */
    public List findReportWoCraftDetail(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO);
    
    /**
     * ����Ʈ ���Ժ�ǰ
     * @author kim21017
     * @version $Id: MaWoResultMstrDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrDetailDTO
     * @return
     */
    public List findReportWoPartDetail(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO);

    /**
     * ����Ʈ �˻��׸�
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
     * ����Ʈ �۾������׸�
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
     * ����Ʈ �ʼ��˻��׸�
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
     * ���� ����Ʈ �ٵ�
     * @author ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param maEqMstrDetailDTO
     * @return
     */
    public List findPmGnlReportWoDetail(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO);
    
    /**
     * ���� ����Ʈ �ٵ�(�з°�)
     * @author ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param maEqMstrDetailDTO
     * @return
     */
    public List findPmPrsReportWoDetail(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO);
    
    /**
     * ���� ����Ʈ �ٵ�(����)
     * @author ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param maEqMstrDetailDTO
     * @return
     */
    public List findPmSclReportWoDetail(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO);
    
    /**
     * ���� ����Ʈ ǥ�ر�
     * @author ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param maEqMstrDetailDTO
     * @return
     */
    public List findReportWoCalibStdEqDetail(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO);
    
    /**
     * ���� ����Ʈ ����������
     * @author ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param maEqMstrDetailDTO
     * @return
     */
    public List findReportWoCalibGnlValueDetail(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO);
    
    /**
     * ���� ����Ʈ(����) ����������
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
	 * ��ȯ��ǰ (Serial ���� ������� ���̺� ������ ����
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

    // �۾���ȹ��� ���翩�� �˻�
    public String woPlanCheck(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO,User user);

    public int updateWoPlanStatus(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO, String woStatus);

    // �̻�����ó��(�۾����) ��ġ��� ���� ������Ʈ
    public int updatePmPointStatusGdWoRslt(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO);
    // �̻�����ó��(�۾����) ��ġ��� (����->�̻�) ������Ʈ
    public int updatePmPointStatusBdWoRslt(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO, String pmPointRepStatus);
    // �̻�����ó��(�۾���ȹ) ��ġ��� ���� ������Ʈ
    public int updatePmPointStatusGdWoPlan(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO);
    // �̻�����ó��(�۾���ȹ) ��ġ��� (����->�̻�) ������Ʈ
    public int updatePmPointStatusBdWoPlan(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO, String pmPointRepStatus);
    // �̻�����ó��(�۾���û) ��ġ��� ���� ������Ʈ
    public int updatePmPointStatusGdWoReq(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO);
    // �̻�����ó��(�۾���û) ��ġ��� (����->�̻�) ������Ʈ
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