package dream.work.list.service;

import java.util.List;

import common.bean.ResponseDTO;
import common.bean.User;
import dream.pers.appreq.dto.AppReqDetailDTO;
import dream.work.list.dto.MaWoResultFailDetailDTO;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.dto.MaWoResultMstrDetailDTO;
import dream.work.list.dto.MaWoResultPmCalDTO;

/**
 * 작업결과 - 상세 service
 * 
 * @author kim21017
 * @version $Id: MaWoResultMstrDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 */
public interface MaWoResultMstrDetailService 
{    
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaWoResultMstrDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultMstrCommonDTO
     * @return
     * @throws Exception
     */
    public MaWoResultMstrDetailDTO findDetail(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO)throws Exception;
    /**
     * detail fail find
     * @author kim21017
     * @version $Id: MaWoResultMstrDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultMstrCommonDTO
     * @return
     * @throws Exception
     */
    public MaWoResultFailDetailDTO findFailDetail(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO)throws Exception;
    /**
     * detail calib find
     * @author kim21017
     * @version $Id: MaWoResultMstrDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultMstrCommonDTO
     * @return
     * @throws Exception
     */
    public MaWoResultPmCalDTO findCalDetail(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO)throws Exception;
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaWoResultMstrDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultMstrDetailDTO
     * @param maWoResultMstrCommonDTO 
     * @return
     * @throws Exception
     */
    public ResponseDTO insertDetail(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, User loginUser) throws Exception;
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaWoResultMstrDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultMstrDetailDTO
     * @return
     * @throws Exception
     */
    public ResponseDTO updateDetail(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO, User loginUser) throws Exception;

    /**
     * detail update
     * @author euna0207
     * @version $Id: MaWoResultMstrDetailService.java,v 1.0 2015/12/02 09:12:40 euna0207 Exp $
     * @since   1.0
     * 
     * @param maWoResultMstrDetailDTO
     * @return
     * @throws Exception
     */
    public int updateWoStatus(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO, User loginUser) throws Exception;

    /**
     * detail fail update
     * @author kim21017
     * @version $Id: MaWoResultMstrDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultMstrCommonDTO
     * @param maWoResultFailDetailDTO
     * @param user 
     * @return
     * @throws Exception
     */
    public ResponseDTO updateFailDetail(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO,MaWoResultFailDetailDTO maWoResultFailDetailDTO, User user) throws Exception;
    /**
     * detail calib update
     * @author kim21017
     * @version $Id: MaWoResultMstrDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultMstrCommonDTO
     * @param maWoResultPmCalDTO
     * @param user 
     * @return
     * @throws Exception
     */
    public ResponseDTO updateCalDetail(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO,MaWoResultPmCalDTO maWoResultPmCalDTO, User user) throws Exception;
    
    /**
     * detail complete
     * @author kim21017
     * @version $Id: MaWoResultMstrDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultMstrDetailDTO
     * @return
     * @throws Exception
     */
    public int completeDetail(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO, MaWoResultPmCalDTO maWoResultPmCalDTO) throws Exception;
    
    /**
     * detail complete
     * @author kim21017
     * @version $Id: MaWoResultMstrDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultMstrDetailDTO
     * @return
     * @throws Exception
     */
    public int completeCancelDetail(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO, MaWoResultPmCalDTO maWoResultPmCalDTO) throws Exception;
    

    
    /**
     * create point data
     * @author kim21017
     * @version $Id: MaWoResultMstrDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultMstrDetailDTO
     * @return
     * @throws Exception
     */
    public int createPoint(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO) throws Exception;
    /**
     * report
     * @author kim21017
     * @version $Id: MaWoResultMstrDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultMstrDetailDTO
     * @return
     */
    public List getReportView(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO);
    /**
     * report
     * @author kim21017
     * @version $Id: MaWoResultMstrDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultMstrDetailDTO
     * @return
     */
    public List getPmGnlReportView(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO);
    /**
     * report
     * @author kim21017
     * @version $Id: MaWoResultMstrDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultMstrDetailDTO
     * @return
     */
    public List getPmPrsReportView(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO);
    /**
     * report
     * @author kim21017
     * @version $Id: MaWoResultMstrDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultMstrDetailDTO
     * @return
     */
    public List getPmSclReportView(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO);
    /**
     * 점검항목 검사
     * @param maWoResultMstrDetailDTO
     * @param user
     * @return
     */
    public String checkPoint(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO,User user) throws Exception;
	/**
	 * Serial Contn CHeck 
	 * @param maWoResultMstrCommonDTO
	 * @return
	 */
	public int findSerialCount(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO);
    public String getStatus(AppReqDetailDTO appReqDetailDTO, User user);
    public void appProcess(AppReqDetailDTO appReqDetailDTO, User user) throws Exception;
    public String findPage(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO,User user);

    // 작업계획목록 존재여부 검사
    public String woPlanCheck(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO,User user) throws Exception;
    /**
     * 작업 비용집계
     * @param maWoResultMstrDetailDTO
     * @param user
     * @return
     */
    public int updateWoTotAmt(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO, User loginUser) throws Exception;
}
