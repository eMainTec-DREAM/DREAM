package dream.asset.list.dao;

import java.util.List;

import common.bean.User;
import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.dto.MaEqMstrDetailDTO;
import dream.asset.list.dto.MaEqMstrMoldDetailDTO;



/**
 * 설비마스터 - 상세 dao
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
     * 설비변경이력 insert
     * @author kim21017
     * @version $Id: MaEqMstrDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrDetailDTO
     * @return
     */
    public int insertEqHist(MaEqMstrDetailDTO maEqMstrDetailDTO);
    
    /**
     * 저장시 설비부위 추가
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
     * 예방점검 시행여부 N으로 변경
     * @author kim21017
     * @version $Id: MaEqMstrDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrDetailDTO
     * @return
     */
    public int updatePmActive(MaEqMstrDetailDTO maEqMstrDetailDTO);
    
    /**
     * 오늘날짜 이후 wo미발행 스케쥴 삭제
     * @author kim21017
     * @version $Id: MaEqMstrDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrDetailDTO
     * @return
     */
    public int deletePmSched(MaEqMstrDetailDTO maEqMstrDetailDTO);
    
    
    /**
     * 리포트 바디
     * @author kim21017
     * @version $Id: MaEqMstrDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrDetailDTO
     * @return
     */
    public List findReportEquipDetail(MaEqMstrDetailDTO maEqMstrDetailDTO) ;
    
    /**
     * 리포트 설비부품
     * @author kim21017
     * @version $Id: MaEqMstrDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrDetailDTO
     * @return
     */
    public List findReportWoPartDetail(MaEqMstrDetailDTO maEqMstrDetailDTO) ;
    
    /**
     * 리포트 수리 및 교체내역
     * @author kim21017
     * @version $Id: MaEqMstrDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrDetailDTO
     * @return
     */
    public List findReportWoRepDetail(MaEqMstrDetailDTO maEqMstrDetailDTO) ;
    
    /**
     * 리포트 주유현황
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