package dream.asset.list.dao;

import java.util.List;

import common.bean.User;
import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.dto.MaEqMstrDetailDTO;
import dream.asset.list.dto.MaEqMstrMoldDetailDTO;



/**
 * ���񸶽��� - �� dao
 * 
 * @author kim21017
 * @version $Id: MaEqMstrDetailDAO.java,v 1.0 2015/12/02 08:25:47 kim21017 Exp $
 * @since 1.0
 */
public interface MaEqMstrMoldDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaEqMstrDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param equipId
     * @param compNo
     * @return
     */
    public MaEqMstrDetailDTO findDetail(String equipId, String compNo, User user);
    
    
    public MaEqMstrMoldDetailDTO findMoldDetail(String equipId, User user);    
    
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaEqMstrDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrDetailDTO
     * @return
     */
    public int insertDetail(MaEqMstrDetailDTO maEqMstrDetailDTO);
    
    
    public int insertMoldDetail(MaEqMstrDetailDTO maEqMstrDetailDTO, MaEqMstrMoldDetailDTO maEqMstrMoldDetailDTO, User loginUser);
    
    
    /**
     * ���񺯰��̷� insert
     * @author kim21017
     * @version $Id: MaEqMstrDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrDetailDTO
     * @return
     */
    public int insertEqHist(MaEqMstrDetailDTO maEqMstrDetailDTO);
    
    /**
     * ����� ������� �߰�
     * @author kim21017
     * @version $Id: MaEqMstrDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrDetailDTO
     * @return
     */
    public int insertEqAsmb(MaEqMstrDetailDTO maEqMstrDetailDTO);
    
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaEqMstrDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrDetailDTO
     * @return
     */
    public int updateDetail(MaEqMstrDetailDTO maEqMstrDetailDTO);
    
    public int updateMoldDetail(MaEqMstrDetailDTO maEqMstrDetailDTO, MaEqMstrMoldDetailDTO maEqMstrMoldDetailDTO, User loginUser);
    
    /**
     * �������� ���࿩�� N���� ����
     * @author kim21017
     * @version $Id: MaEqMstrDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrDetailDTO
     * @return
     */
    public int updatePmActive(MaEqMstrDetailDTO maEqMstrDetailDTO);
    
    /**
     * ���ó�¥ ���� wo�̹��� ������ ����
     * @author kim21017
     * @version $Id: MaEqMstrDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrDetailDTO
     * @return
     */
    public int deletePmSched(MaEqMstrDetailDTO maEqMstrDetailDTO);
    
    
    /**
     * ����Ʈ �ٵ�
     * @author kim21017
     * @version $Id: MaEqMstrDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrDetailDTO
     * @return
     */
    public List findReportEquipDetail(MaEqMstrDetailDTO maEqMstrDetailDTO) ;
    
    /**
     * ����Ʈ �����ǰ
     * @author kim21017
     * @version $Id: MaEqMstrDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrDetailDTO
     * @return
     */
    public List findReportWoPartDetail(MaEqMstrDetailDTO maEqMstrDetailDTO) ;
    
    /**
     * ����Ʈ ���� �� ��ü����
     * @author kim21017
     * @version $Id: MaEqMstrDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrDetailDTO
     * @return
     */
    public List findReportWoRepDetail(MaEqMstrDetailDTO maEqMstrDetailDTO) ;
    
    /**
     * ����Ʈ ������Ȳ
     * @author kim21017
     * @version $Id: MaEqMstrDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrDetailDTO
     * @return
     */
    public List findReportWoOilDetail(MaEqMstrDetailDTO maEqMstrDetailDTO);
    
    public List findReportWoImgDetail(MaEqMstrDetailDTO maEqMstrDetailDTO) ;
    public int updateIsLastVersion(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrDetailDTO maEqMstrDetailDTO, User user);
	public int updateRevisionHist(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrDetailDTO maEqMstrDetailDTO, User user);
	public int updateBeforeIsLastVersion(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrDetailDTO maEqMstrDetailDTO, User user);
	
    public void SP_IF_UPD_TXEQUIPMENT(String equipId, User loginUser) throws Exception;

    /**
     * detail copy
     * @author syyang
     * @version $Id: MaEqMstrDetailDAO.java,v 1.0 20155/12/02 08:25:47 syyang Exp $
     * @since   1.0
     * 
     * @param maEqMstrDetailDTO
     * @return
     */
    public int copyDetail(MaEqMstrDetailDTO maEqMstrDetailDTO, String revisionHistId,String revisionStatus, User loginUser);
    public int copyMoldDetail(MaEqMstrDetailDTO maEqMstrDetailDTO, MaEqMstrMoldDetailDTO maEqMstrMoldDetailDTO, User loginUser);
    public int insertCopyEqHist(MaEqMstrDetailDTO maEqMstrDetailDTO, User loginUser);

    public String getNextSequence(String seqName);
    
}